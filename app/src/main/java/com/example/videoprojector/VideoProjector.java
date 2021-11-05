package com.example.videoprojector;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoProjector extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_projector);

        ImageView imageView = findViewById(R.id.theme);
        String imageUri = "@drawable/" + Model.instance().selectedTheme;
        int imageResource = getResources().getIdentifier(imageUri, null, getPackageName());
        Drawable res = getResources().getDrawable(imageResource);
        imageView.setImageDrawable(res);

        VideoView videoView =(VideoView)findViewById(R.id.vdVw);
        MediaController mediaController= new MediaController(this);
        mediaController.setAnchorView(videoView);
        Uri uri = Uri.parse(Model.instance().selectedVideo.getAbsolutePath());
        videoView.setVideoURI(uri);
        videoView.requestFocus();
        videoView.start();

        VideoView videoView2 =(VideoView)findViewById(R.id.videoView3);
        MediaController mediaController2= new MediaController(this);
        mediaController2.setAnchorView(videoView2);
        videoView2.setVideoURI(uri);
        videoView2.requestFocus();
        videoView2.start();

        // Model.instance().selectedTheme
    }
}