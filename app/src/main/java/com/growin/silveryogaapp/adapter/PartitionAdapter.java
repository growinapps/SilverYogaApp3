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

public class PartitionAdapter extends RecyclerView.Adapter<PartitionAdapter.ItemViewHolder> {

    ArrayList<String> partitionList = new ArrayList<String>();

    @NonNull
    @Override
    public PartitionAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.yoga_partition, parent, false);

        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PartitionAdapter.ItemViewHolder holder, int position) {
        holder.onBind(partitionList.get(position));
    }

    @Override
    public int getItemCount() {
        return partitionList.size();
    }

    public void addPartition(String title) {
        partitionList.add(title);
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;

        ItemViewHolder(View partitionView) {
            super(partitionView);

            textView = partitionView.findViewById(R.id.partitionTitle);

            partitionView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), DetailYoga.class);
                    v.getContext().startActivity(intent);
                }
            });
        }

        void onBind(String title) {
            textView.setText(title);
        }
    }
}
