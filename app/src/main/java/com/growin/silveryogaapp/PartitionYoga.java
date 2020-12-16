package com.growin.silveryogaapp;

import android.os.Bundle;
import android.util.Log;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.growin.silveryogaapp.adapter.PartitionAdapter;
import com.growin.silveryogaapp.databinding.ActivityPartitionYogaBinding;

import java.util.Arrays;
import java.util.List;

public class PartitionYoga extends BaseActivity {

    private PartitionAdapter adapter;

    ActivityPartitionYogaBinding act;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bindViews();
        setupEvents();
        setValues();
    }

    @Override
    public void setupEvents() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        act.partitionList.setLayoutManager(linearLayoutManager);

        adapter = new PartitionAdapter();
        act.partitionList.setAdapter(adapter);
    }

    @Override
    public void setValues() {
        getData();
    }

    private void getData() {
        List<String> titleList = Arrays.asList("어깨 통증 완화 요가", "허리 근력 강화 요가",
                                            "무릎 자세 교정 요가", "관절에 좋은 요가");

        for (int i=0; i<4; i++) {
            adapter.addPartition(titleList.get(i));

            int dataCnt = adapter.getItemCount();
            String dataCntStr = Integer.toString(dataCnt);
            Log.d("데이터 갯수 : ", dataCntStr);
        }

        adapter.notifyDataSetChanged();
    }

    @Override
    public void bindViews() {

        act = DataBindingUtil.setContentView(this, R.layout.activity_partition_yoga);

    }
}