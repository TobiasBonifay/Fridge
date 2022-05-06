package edu.polytech.fridge;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class DonationsHistory extends AppCompatActivity {
    private RecyclerView mRecyclView;
    private ImageAdapter mAdapter;
    private DatabaseReference mDatabaseref;
    private ArrayList<Upload> mUploads;
//    private Context context;
//    private ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donations_history);
        mRecyclView = findViewById(R.id.recycler_view);
        mRecyclView.setHasFixedSize(true);
        mRecyclView.setLayoutManager(new LinearLayoutManager(this));


//        imageView = findViewById(R.id.view);

        mUploads = new ArrayList<>();

        mDatabaseref = FirebaseDatabase.getInstance().getReference("uploads");

        mDatabaseref.addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(DataSnapshot dataSnapshot){
//                String link = dataSnapshot.getValue(String.class);
//                Picasso.with(context).load(link).into(imageView);
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    Upload upload =postSnapshot.getValue(Upload.class);
                    mUploads.add(upload);
                }
                mAdapter = new ImageAdapter(DonationsHistory.this,mUploads);
                mRecyclView.setAdapter(mAdapter);
//                mAdapter.notifyDataSetChanged();
//                if(mRecyclView != null){

//               }


            }
            @Override
            public void onCancelled(DatabaseError databaseError){
                Toast.makeText(DonationsHistory.this,databaseError.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });

    }
}