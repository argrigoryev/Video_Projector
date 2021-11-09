package com.example.videoprojector;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTitleGradient();
    }

    private void setTitleGradient() {
        TextView title = (TextView)findViewById(R.id.title);
        Shader textShader=new LinearGradient(0, 0, 800, 0, new int[]{Color.parseColor("#B6E5FF"),Color.parseColor("#F6D0C5")}, new float[]{0, 1}, Shader.TileMode.CLAMP);
        title.getPaint().setShader(textShader);
    }

    public void onShareButtonClick(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.alexandrgrigoryev.checkpassword"));
        startActivity(browserIntent);
    }

    public void onPrivacyPoliceButtonClick(View view) {
        Intent privacyPolicyIntent = new Intent(Main.this, PrivacyPolicy.class);
        startActivity(privacyPolicyIntent);
    }

    public void onStartButtonClick(View view) {
        Intent selectIntent = new Intent(Main.this, Select.class);
        startActivity(selectIntent);
    }

    public void onRateButtonClick(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.alexandrgrigoryev.checkpassword"));
        startActivity(browserIntent);
    }
}