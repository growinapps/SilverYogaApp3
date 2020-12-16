package com.growin.silveryogaapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.firebase.auth.FirebaseAuth;
import com.growin.silveryogaapp.adapter.ComsetAdapter;
import com.growin.silveryogaapp.databinding.ActivityComsetSystemBinding;

import java.util.Arrays;
import java.util.List;

public class ComsetSystem extends BaseActivity {

    ActivityComsetSystemBinding act;

    private ComsetAdapter adapter;

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
        act.comsetList.setLayoutManager(linearLayoutManager);

        adapter = new ComsetAdapter();
        act.comsetList.setAdapter(adapter);

        act.logOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void setValues() {
        getData();
    }

    private void getData() {
        List<String> titleList = Arrays.asList("최근에 이용한 수업", "건강정보", "공지사항",
                                                "FAQ", "고객센터");

        for (int i=0; i<5; i++) {
            adapter.addComset(titleList.get(i));

            int dataCnt = adapter.getItemCount();
            String dataCntStr = Integer.toString(dataCnt);
            Log.d("데이터 갯수 : ", dataCntStr);
        }

        adapter.notifyDataSetChanged();
    }

    @Override
    public void bindViews() {
        act = DataBindingUtil.setContentView(this, R.layout.activity_comset_system);
    }

}