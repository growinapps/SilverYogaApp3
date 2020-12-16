package com.growin.silveryogaapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.growin.silveryogaapp.R;
import com.growin.silveryogaapp.YogaVideo;
import com.growin.silveryogaapp.data.Content;

import java.util.ArrayList;

public class ContentAdapter extends RecyclerView.Adapter<ContentAdapter.ItemViewHolder> {

    private ArrayList<Content> contentsList;
    private Context context;

    private FirebaseDatabase pDatabase;
    private DatabaseReference pDatabaseRef;
    private int position;
    private int updateCnt;

    public ContentAdapter(ArrayList<Content> data, Context context) {
        this.contentsList = data;
        this.context = context;
    }

    @NonNull
    @Override
    public ContentAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.yoga_content, parent, false);

        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {

        Glide.with(holder.itemView).load(contentsList.get(position).getImgUri()).into(holder.imageView);
        holder.textView.setText(contentsList.get(position).getTitle());
        holder.cntTextView.setText(String.valueOf(contentsList.get(position).getCnt()));

    }

    @Override
    public int getItemCount() {
        return (contentsList != null ? contentsList.size() : 0);
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView;
        TextView cntTextView;

        public ItemViewHolder(@NonNull View contentView) {
            super(contentView);

            this.imageView = contentView.findViewById(R.id.yogaContentImg);
            this.textView = contentView.findViewById(R.id.yogaContentTxt);
            this.cntTextView = contentView.findViewById(R.id.yogaCountTxt);

            contentView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    position = getAdapterPosition();
                    Content pItem = contentsList.get(position);
                    updateCnt = UpdateCount(pItem);
                    contentsList.get(position).setCnt(updateCnt);

                    Intent intent = new Intent(v.getContext(), YogaVideo.class);
                    intent.putExtra("imgPath", pItem.getImgPath());
                    intent.putExtra("poseName", pItem.getTitle());
                    intent.putExtra("videoId", pItem.getVideo());
                    v.getContext().startActivity(intent);
                }
            });

        }

    }

    private int UpdateCount(Content content){

        int cnt =0;
        cnt = content.getCnt()+1;
        pDatabase = FirebaseDatabase.getInstance();
        pDatabaseRef = pDatabase.getReference("SilverYoga");
        pDatabaseRef.child("Contents").child(String.valueOf(content.getIdx())).child("count").setValue(Integer.valueOf(cnt));

        return cnt;
    }
}
