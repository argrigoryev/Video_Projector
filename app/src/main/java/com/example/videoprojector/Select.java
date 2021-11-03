package com.example.videoprojector;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Select extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        TextView title = (TextView)findViewById(R.id.title);
        Shader textShader=new LinearGradient(0, 0, 800, 0, new int[]{Color.parseColor("#B6E5FF"),Color.parseColor("#F6D0C5")},
                new float[]{0, 1}, Shader.TileMode.CLAMP);
        title.getPaint().setShader(textShader);
    }

    public void onVideoProjectorSelectButtonClick(View view) {
        Intent videoprojectorselect = new Intent(Select.this, Menu.class);
        startActivity(videoprojectorselect);
    }

    public void onVideoStreamingButtonClick(View view) {
        Intent videostreaming = new Intent(Select.this, SelectVideo2.class);
        startActivity(videostreaming);

    }
}