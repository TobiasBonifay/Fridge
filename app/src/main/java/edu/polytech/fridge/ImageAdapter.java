package edu.polytech.fridge;

import android.content.Context;
import android.graphics.ColorSpace;
import android.media.Image;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {

    private Context mContext;
    private List<Upload> mUploads;
    public ImageAdapter(Context context, List<Upload> uploads){
        mContext=context;
        mUploads = uploads;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(mContext).inflate( R.layout.image_item, parent, false);
        return new ImageViewHolder(view);

    }
    @Override
    public void onBindViewHolder(ImageViewHolder holder ,int position){
        Upload uploadCurrent = mUploads.get(position);

        holder.textViewName.setText(uploadCurrent.getName());
        Picasso.with(mContext).
                load(uploadCurrent.getImageUrl())
                .placeholder(R.mipmap.ic_launcher_round)
                .fit()
                .centerCrop()
                .into(holder.imageView);
    }

    @Override
    public int getItemCount(){
        return mUploads.size();

    }

    public class ImageViewHolder extends RecyclerView.ViewHolder{
        public TextView  textViewName;
        public ImageView imageView;



        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.text_view_name);
            imageView = itemView.findViewById(R.id.image_view_upload);

        }
    }



    //    private ArrayList<Upload> mList;
//    private Context context;
//    public ImageAdapter(Context context, ArrayList<Upload> mList){
//        this.context = context;
//        this.mList=mList;
//    }
//
//    @NonNull
//    @Override
//    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View v = LayoutInflater.from(context).inflate(R.layout.image_item, parent, false);
//        return new MyviewHolder(v);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull MyviewHolder holder, int position) {
//
//        Glide.with(context).load(mList.get(position).getImageUrl()).into(holder.imageView);
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return mList.size();
//    }
//
//    public static class MyviewHolder extends RecyclerView.ViewHolder{
//        ImageView imageView;
//
//        public MyviewHolder(@NonNull View itemView) {
//            super(itemView);
//            imageView = itemView.findViewById(R.id.image_view_upload);
//        }
//    }

}
