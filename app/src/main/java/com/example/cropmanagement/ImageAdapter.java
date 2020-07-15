package com.example.cropmanagement;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cropmanagement.diseasesfragment.Upload;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {


    private Context mContext;
    private List<Upload> mUpload;

    public ImageAdapter(Context mContext, List<Upload> mUpload) {
        this.mContext = mContext;
        this.mUpload = mUpload;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.images_card,parent,false);


        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        Upload uploadcurrent=mUpload.get(position);
        holder.c_diseasesname.setText(uploadcurrent.getmName());
        /*Picasso.with(mContext)
                .load(uploadcurrent.getmImageUrl())
                .fit()
                .centerCrop()
                .into(holder.c_diseasesimage);


         */
        holder.c_diseasesimage.setImageURI(Uri.parse(Uri.decode(uploadcurrent.getmImageUrl())));
    }

    @Override
    public int getItemCount() {
        return mUpload.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder{

        private TextView c_diseasesname;
        private ImageView c_diseasesimage;
        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            c_diseasesname=itemView.findViewById(R.id.c_diseasesname);
            c_diseasesimage=itemView.findViewById(R.id.c_diseasesimage);
        }
    }
}
