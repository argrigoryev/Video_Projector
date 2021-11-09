package com.example.videoprojector;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

public class VideoProjector extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_projector);
        this.setTitleGradient();


        ImageView imageView = findViewById(R.id.backgroundTheme);
        String imageUri = "@drawable/" + Model.instance().selectedTheme;
        int imageResource = getResources().getIdentifier(imageUri, null, getPackageName());
        Drawable res = getResources().getDrawable(imageResource);
        imageView.setImageDrawable(res);

        Uri uri = Uri.parse(Model.instance().selectedVideo.getAbsolutePath());
        VideoView videoView = (VideoView)findViewById(R.id.videoPlayer);
        videoView.setVideoURI(uri);
        videoView.start();

        VideoView videoView2 = (VideoView)findViewById(R.id.videoPhone);
        videoView2.setVideoURI(uri);
        videoView2.start();
    }

    private void setTitleGradient() {
        TextView title = (TextView)findViewById(R.id.title);
        Shader textShader=new LinearGradient(0, 0, 800, 0, new int[]{Color.parseColor("#B6E5FF"),Color.parseColor("#F6D0C5")}, new float[]{0, 1}, Shader.TileMode.CLAMP);
        title.getPaint().setShader(textShader);
    }
}