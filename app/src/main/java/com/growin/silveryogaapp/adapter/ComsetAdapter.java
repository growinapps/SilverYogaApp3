package com.growin.silveryogaapp.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.growin.silveryogaapp.DetailYoga;
import com.growin.silveryogaapp.R;

import java.util.ArrayList;

public class ComsetAdapter extends RecyclerView.Adapter<ComsetAdapter.ItemViewHolder> {

    ArrayList<String> comsetList = new ArrayList<String>();

    @NonNull
    @Override
    public ComsetAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.yoga_partition, parent, false);

        return new ComsetAdapter.ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ComsetAdapter.ItemViewHolder holder, int position) {
        holder.onBind(comsetList.get(position));
    }

    @Override
    public int getItemCount() {
        return comsetList.size();
    }

    public void addComset(String title) {
        comsetList.add(title);
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;

        ItemViewHolder(View comsetView) {
            super(comsetView);

            textView = comsetView.findViewById(R.id.partitionTitle);

            comsetView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();

                    if (pos != RecyclerView.NO_POSITION) {

                        if (pos == 0) {
                            Intent intent = new Intent(v.getContext(), DetailYoga.class);
                            v.getContext().startActivity(intent);
                        }
                    }

                }
            });
        }

        void onBind(String title) {
            textView.setText(title);
        }
    }
}
