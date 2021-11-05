package com.example.videoprojector;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SelectTheme extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_theme);
        TextView title = (TextView)findViewById(R.id.title);
        Shader textShader=new LinearGradient(0, 0, 800, 0, new int[]{Color.parseColor("#B6E5FF"),Color.parseColor("#F6D0C5")},
                new float[]{0, 1}, Shader.TileMode.CLAMP);
        title.getPaint().setShader(textShader);
    }


    public void onThemeSelect(View imageView) {
        Model.instance().selectedTheme = "" + imageView.getId();
        if (Model.instance().selectedVideo == null) {
            Intent videoProjectorIntent = new Intent(SelectTheme.this, SelectProjectorVideo.class);
            startActivity(videoProjectorIntent);
        } else {
            Intent videoProjectorIntent = new Intent(SelectTheme.this, VideoProjector.class);
            startActivity(videoProjectorIntent);
        }
    }
}