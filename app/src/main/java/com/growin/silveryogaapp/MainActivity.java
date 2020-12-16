package com.growin.silveryogaapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.growin.silveryogaapp.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {

    ActivityMainBinding act;

    public static final String EXTRA_MESSAGE = "com.growin.silveryogaapp.MESSAGE";
    private FirebaseDatabase pDatabase;
    private DatabaseReference pDatabaseRef;

    private ArrayList<String> menuList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bindViews();
        setupEvents();
        setValues();

    }

    @Override
    public void setupEvents() {

        act.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });

        act.frameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PartitionYoga.class);
                startActivity(intent);
            }
        });

        act.frameLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RankingYoga.class);
                startActivity(intent);
            }
        });

        act.frameLayout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FavoriteYoga.class);
                startActivity(intent);
            }
        });

        act.frameLayout4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public void setValues() {
        InitDatabase();

        Log.d("DB연결", "성공!!!!");

        /*for (int i=0; i<menuList.size(); i++) {
            Log.d("MainMenu", menuList.get(i));
        }*/
    }

    private void InitDatabase(){

        pDatabase = FirebaseDatabase.getInstance();
        pDatabaseRef = pDatabase.getReference("SilverYoga").child("MainMenu");

        pDatabaseRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot ss : snapshot.getChildren()) {
                    String strMenu = ss.getKey().toString() +" "+ ss.getValue().toString();
                    menuList.add(strMenu);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @Override
    public void bindViews() {

        act = DataBindingUtil.setContentView(this, R.layout.activity_main);
    }

}