package com.example.videoprojector;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        TextView title = (TextView)findViewById(R.id.title);
        Shader textShader=new LinearGradient(0, 0, 800, 0, new int[]{Color.parseColor("#B6E5FF"),Color.parseColor("#F6D0C5")},
                new float[]{0, 1}, Shader.TileMode.CLAMP);
        title.getPaint().setShader(textShader);
        Model.instance().selectedVideo = null;
        Model.instance().selectedTheme = null;
    }

    public void onVideoProjectorMenuButtonClick(View view) {
        Intent selectProjectorVideoIntent = new Intent(Menu.this, SelectProjectorVideo.class);
        startActivity(selectProjectorVideoIntent);
    }

    public void onVideoThemeButtonClick(View view) {
        Intent videotheme = new Intent(Menu.this, SelectTheme.class);
        startActivity(videotheme);
    }

    public void onProjectorGuideButtonClick(View view) {
        Intent projectorguide = new Intent(Menu.this, ProjectorGuide.class);
        startActivity(projectorguide );
    }

    public void onSettingButtonClick(View view) {
        Intent setting = new Intent(Menu.this, Setting.class);
        startActivity(setting);
    }
}