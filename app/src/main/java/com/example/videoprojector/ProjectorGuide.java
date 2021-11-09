package com.example.videoprojector;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ProjectorGuide extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projector_guide);
        this.setTitleGradient();
    }

    private void setTitleGradient() {
        TextView title = (TextView)findViewById(R.id.title);
        Shader textShader=new LinearGradient(0, 0, 800, 0, new int[]{Color.parseColor("#B6E5FF"),Color.parseColor("#F6D0C5")}, new float[]{0, 1}, Shader.TileMode.CLAMP);
        title.getPaint().setShader(textShader);
    }

    public void onWhoCameButtonClick(View view) {
            Intent whoCameUpWithIntent = new Intent(ProjectorGuide.this, WhoCameUpWith.class);
            startActivity(whoCameUpWithIntent );
        }

    public void onHowToUseButtonClick(View view) {
        Intent howToUseIntent = new Intent(ProjectorGuide.this, HowToUse.class);
        startActivity(howToUseIntent );
    }

    public void onHowToChooseButtonClick(View view) {
        Intent howToChooseIntent = new Intent(ProjectorGuide.this, HowToChoose.class);
        startActivity(howToChooseIntent );
    }
}
