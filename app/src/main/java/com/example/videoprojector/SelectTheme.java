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
        Model.instance().selectedTheme = null;
    }

    private void saveTheme(int id) {
        String selectedTheme = null;
        switch (id) {
            case R.id.theme_1:
                selectedTheme = "theme_1";
                break;
            case R.id.theme_2:
                selectedTheme = "theme_2";
                break;
            case R.id.theme_3:
                selectedTheme = "theme_3";
                break;
            case R.id.theme_4:
                selectedTheme = "theme_4";
                break;
            case R.id.theme_5:
                selectedTheme = "theme_5";
                break;
            case R.id.theme_6:
                selectedTheme = "theme_6";
                break;
            case R.id.theme_7:
                selectedTheme = "theme_7";
                break;
            case R.id.theme_8:
                selectedTheme = "theme_8";
                break;
            case R.id.theme_9:
                selectedTheme = "theme_9";
                break;
        }
        Model.instance().selectedTheme = selectedTheme;
    }


    public void onThemeSelect(View imageView) {
        this.saveTheme(imageView.getId());
        if (Model.instance().selectedVideo == null) {
            Intent videoProjectorIntent = new Intent(SelectTheme.this, SelectProjectorVideo.class);
            startActivity(videoProjectorIntent);
        } else {
            Intent videoProjectorIntent = new Intent(SelectTheme.this, VideoProjector.class);
            startActivity(videoProjectorIntent);
        }
    }
}