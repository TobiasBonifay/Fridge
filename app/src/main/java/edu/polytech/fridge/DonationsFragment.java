package edu.polytech.fridge;

import static android.app.Activity.RESULT_OK;
import static android.content.ContentValues.TAG;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Handler;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.List;
import java.util.Objects;

import edu.polytech.fridge.factory.Ingredient;
import edu.polytech.fridge.fridge.data.Fridge;

import edu.polytech.fridge.map.MapActivity;


public class DonationsFragment extends Fragment {
    private ImageView openCamera;
    private Button donation;
    private EditText name;
    private EditText quantity;
    private static final int PICK =1;
    Button uploadBtn;
    private Uri imageUri;
    private StorageReference storageRef;
//    private ProgressBar progressBar;
    private DatabaseReference databaseRef;
    private File file;
    private StorageTask storageTask;

    private Button btn;
    private TextView showUploads;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_donations, container, false);
//        imageView = view.findViewById(R.id.picture);
        openCamera = view.findViewById(R.id.openCamera2);
        donation = view.findViewById(R.id.donate);
        showUploads = view.findViewById(R.id.text_view_show_uploads);
        showUploads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHistoryFragement();
            }
        });
        name = view.findViewById(R.id.textView);
        quantity = view.findViewById(R.id.textView2);
        btn=view.findViewById(R.id.button2);
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        donation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Ingredient> list = Fridge.getInstance().getFoodList();
                for (Ingredient e : list) {
                    if (e.getFoodName().equals(name.getText().toString())) {
                        int q = e.getCurrentQuantity() - Integer.parseInt(quantity.getText().toString());
                        e.setCurrentQuantity(q);
                    }
                }
                startActivity(new Intent(getActivity(), MapActivity.class));
            }
        });
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.CAMERA}, 101);
        }

        //this if is for take a picture directly using the camera
//        openCamera.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                file = new File(getActivity().getExternalCacheDir(),
//                        String.valueOf(System.currentTimeMillis()) + ".jpg");
//                imageUri = Uri.fromFile(file);
//                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
//                    getActivity().startActivityForResult(intent, 101);
//            }
//        });


        //this is for to chose a file from the phone storage

        openCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseFile();
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DonationsFragment.this.getActivity(),AddEvent.class);
                startActivity(i);
                
            }

        });
        storageRef = FirebaseStorage.getInstance().getReference("uploads");
        databaseRef = FirebaseDatabase.getInstance().getReference("uploads");

        uploadBtn = view.findViewById(R.id.upload);
        uploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(storageTask!=null && storageTask.isInProgress()){
                    Toast.makeText(getActivity(),"Upload in progress",Toast.LENGTH_SHORT).show();

            }else {
                    uploadFile();
                }
            }
        });
//        ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
//                new ActivityResultContracts.StartActivityForResult(),
//                new ActivityResultCallback<ActivityResult>() {
//                    @Override
//                    public void onActivityResult(ActivityResult result) {
//                            // There are no request codes
//                            Intent data = result.getData();
//                            Bitmap bitmap = (Bitmap) (data != null ? data.getExtras().get("data") : null);
//                            openCamera.setImageBitmap(bitmap);
//
//                    }
//                });



        


        return view;
    }

    private void openHistoryFragement() {
//        FragmentTransaction fr = getFragmentManager().beginTransaction();
//        fr.replace(R.id.to_replace,new DonationHistory());
//        fr.commit();
      Intent intent = new Intent(getActivity(),DonationsHistory.class);
      startActivity(intent);
    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == 101 ) {
//            Bitmap bitmap = (Bitmap) (data != null ? data.getExtras().get("data") : null);
//            openCamera.setImageBitmap(bitmap);
////            assert bitmap != null;
////            imageUri=getImageUri(requireActivity().getApplicationContext(),bitmap);
//        }
//    }

//    public Uri getImageUri(Context inContext, Bitmap inImage) {
//        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
//        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
//        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
//        assert path != null;
//        return Uri.parse(path);


//    }
    private String getExtension(Uri uri){
        ContentResolver cr = requireActivity().getContentResolver();
        MimeTypeMap mime =  MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(uri));
    }

    private void chooseFile(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data!=null && data.getData()!=null){
            imageUri = data.getData();
            openCamera.setImageURI(imageUri);
        }
    }

    private void uploadFile(){
        if (imageUri !=null){
            StorageReference fileRef = storageRef.child(System.currentTimeMillis() + "." +getExtension(imageUri));
            storageTask= fileRef.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Handler handler =  new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getActivity(),"stuff happening",Toast.LENGTH_SHORT).show();                        }
                    },500);
                    Toast.makeText(getActivity(),"Upload succsess",Toast.LENGTH_SHORT).show();
                    Task<Uri> urlTask = taskSnapshot.getStorage().getDownloadUrl();
                    while (!urlTask.isSuccessful());
                    Uri downloadUrl = urlTask.getResult();

                    Log.d(TAG, "onSuccess: firebase download url: " + downloadUrl.toString()); //use if testing...don't need this line.
                    Upload upload = new Upload("textforNow",downloadUrl.toString());

                    String uploadId = databaseRef.push().getKey();
                    databaseRef.child(uploadId).setValue(upload);
//                    Upload upload =new Upload("text",taskSnapshot.getStorage().getDownloadUrl().toString());
//                    String uploadId = databaseRef.push().getKey();
//                    databaseRef.child(uploadId).setValue(upload);

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getActivity(),e.getMessage(),Toast.LENGTH_SHORT).show();;

                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
//                    double progress = (100 * snapshot.getBytesTransferred()/snapshot.getTotalByteCount());
//                    progressBar.setProgress((int) progress);
                }
            });
        }

         else {
            Toast.makeText(getActivity(),"No file selected",Toast.LENGTH_SHORT).show();
        }

    }
}