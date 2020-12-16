package com.growin.silveryogaapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.databinding.DataBindingUtil;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.growin.silveryogaapp.databinding.ActivityMain2Binding;

import java.util.ArrayList;

public class MainActivity2 extends BaseActivity {

    ActivityMain2Binding act;

    private FirebaseDatabase pDatabase;
    private DatabaseReference pDatabaseRef;

    private ArrayList<String> menuList = new ArrayList<String>();

    private String logName;
    private String logEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bindViews();
        setupEvents();
        setValues();
    }

    @Override
    public void setupEvents() {
        act.titleLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(intent);
            }
        });

        act.partitionLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, PartitionYoga.class);
                intent.putExtra("Email", logEmail);
                startActivity(intent);
            }
        });

        act.rankingLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, RankingYoga.class);
                intent.putExtra("Email", logEmail);
                startActivity(intent);
            }
        });

        act.favoriteLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, FavoriteYoga.class);
                intent.putExtra("Email", logEmail);
                startActivity(intent);
            }
        });

        act.comsetLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, ComsetSystem.class);
                intent.putExtra("Email", logEmail);
                startActivity(intent);
            }
        });
    }

    @Override
    public void setValues() {

        GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(this);

        if(signInAccount != null) {
            logName = signInAccount.getDisplayName();
            logEmail = signInAccount.getEmail();

            Log.d("이름 : ", logName);
            Log.d("이메일 : ", logEmail);
        }

    }

    @Override
    public void bindViews() {
        act = DataBindingUtil.setContentView(this, R.layout.activity_main2);
    }
}