package com.growin.silveryogaapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.growin.silveryogaapp.data.Content;
import com.growin.silveryogaapp.data.Favorit;

public class YogaVideo extends YouTubeBaseActivity {

    YouTubePlayerView playerView;
    Button playBtn;
    Button likeBtn;
    YouTubePlayer.OnInitializedListener listener;
    Content pItem;
    String pMail;

    private final FirebaseDatabase pDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference pDatabaseRef;
    private Query pQuery;

    private String pVideo;
    private String pImg;
    private String pName;
    private int stateFavorit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yoga_video);

        GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(this);

        pMail = signInAccount.getEmail();
        pVideo = getIntent().getStringExtra("videoId");
        pImg = getIntent().getStringExtra("imgPath");
        pName = getIntent().getStringExtra("poseName");

        playBtn = findViewById(R.id.youtubeBtn);
        likeBtn = findViewById(R.id.likeBtn);
        playerView = findViewById(R.id.youtubeView);

        CheckFavoritVideo(pMail, pVideo, new IYogaVideo() {
            @Override
            public void onCallBack(Boolean bFavorit) {
                if (bFavorit) {
                    likeBtn.setSelected(true);
                    stateFavorit = 1;
                } else {
                    likeBtn.setSelected(false);
                    stateFavorit = 0;
                }
            }
        });

        listener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.cueVideo(pVideo);
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };

//        playBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                playerView.initialize("아무키", listener);
//            }
//        });

        playerView.initialize("아무키", listener);

        likeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (stateFavorit == 0){
                    Favorit pFavor = new Favorit();
                    pFavor.setImg(pImg);
                    pFavor.setMail(pMail);
                    pFavor.setMail_video(pMail+"_"+pVideo);
                    pFavor.setName(pName);
                    pFavor.setVideo(pVideo);
                    pDatabaseRef = pDatabase.getReference("SilverYoga").child("Favorit");
                    pDatabaseRef.push().setValue(pFavor);

                    stateFavorit = 1;
                    likeBtn.setSelected(true);
                } else {
                    Favorit pFavor = new Favorit();
                    pFavor.setImg(pImg);
                    pFavor.setMail(pMail);
                    pFavor.setMail_video(pMail+"_"+pVideo);
                    pFavor.setName(pName);
                    pFavor.setVideo(pVideo);
                    pQuery = pDatabase.getReference("SilverYoga").child("Favorit").orderByChild("mail_video").equalTo(pMail+"_"+pVideo);
                    pQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for (DataSnapshot ss: snapshot.getChildren()) {
                                ss.getRef().removeValue();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                    stateFavorit = 0;
                    likeBtn.setSelected(false);
                }
            }
        });
    }

    public void CheckFavoritVideo(String strMail, String strVideo, IYogaVideo iYogaVideo){

        pDatabaseRef = pDatabase.getReference("SilverYoga");
        pQuery = pDatabaseRef.child("Favorit").orderByChild("mail_video").equalTo(strMail+"_"+strVideo);

        pQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.getChildrenCount()==1){
                    iYogaVideo.onCallBack(true);
                }
                else{
                    iYogaVideo.onCallBack(false);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //
            }
        });

    }
}