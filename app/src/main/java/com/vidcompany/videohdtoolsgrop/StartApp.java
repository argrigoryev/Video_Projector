package com.vidcompany.videohdtoolsgrop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.ads.nativetemplates.TemplateView;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.gms.ads.nativead.NativeAd;

import java.io.File;

public class StartApp extends AppCompatActivity {

    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkStorageAccessPermission();
        setContentView(R.layout.activity_start_app);
        this.setTitleGradient();
        this.setNativeAds();
    }

    private void setTitleGradient() {
        TextView title = (TextView)findViewById(R.id.title);
        Shader textShader = new LinearGradient(0, 0, 800, 0, new int[]{Color.parseColor("#B6E5FF"),Color.parseColor("#F6D0C5")}, new float[]{0, 1}, Shader.TileMode.CLAMP);
        title.getPaint().setShader(textShader);
    }

    private void setNativeAds() {
        AdLoader adLoader = new AdLoader.Builder(this, "ca-app-pub-9562261954889993/4517410105")
                .forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
                    @Override
                    public void onNativeAdLoaded(NativeAd NativeAd) {
                        TemplateView t = findViewById(R.id.native_ad_view_news_feed);
                        t.setNativeAd(NativeAd);
                    }
                }).build();
        adLoader.loadAd(new AdRequest.Builder().build());
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                String[] storagePaths = StorageUtil.getStorageDirectories(this);
                for (String path : storagePaths) {
                    File storage = new File(path);
                    Method.load_Directory_Files(storage);
                }
            }
        }
    }

    public void onGetStartedAppButtonClick(View view) {
        this.showAds();
    }

    private void checkStorageAccessPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                new androidx.appcompat.app.AlertDialog.Builder(this)
                        .setTitle("Permission Needed")
                        .setMessage("This permission is needed to access media file in your phone")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ActivityCompat.requestPermissions(StartApp.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                            }
                        })
                        .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).show();
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
            }
        } else {
            String[] storagePaths = StorageUtil.getStorageDirectories(this);
            for (String path : storagePaths) {
                File storage = new File(path);
                Method.load_Directory_Files(storage);
            }
        }
    }

    private void showAds() {
        AdRequest adRequest = new AdRequest.Builder().build();
        InterstitialAd.load(this,"ca-app-pub-9562261954889993/2171174717", adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        mInterstitialAd = interstitialAd;
                        mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback(){
                            @Override
                            public void onAdDismissedFullScreenContent() {
                                Intent splashScreen = new Intent(StartApp.this, Main.class);
                                startActivity(splashScreen);
                            }

                            @Override
                            public void onAdFailedToShowFullScreenContent(AdError adError) {
                                Intent splashScreen = new Intent(StartApp.this, Main.class);
                                startActivity(splashScreen);
                            }

                            @Override
                            public void onAdShowedFullScreenContent() {
                                mInterstitialAd = null;
                            }

                            @Override
                            public void onAdClicked() {
                                Intent splashScreen = new Intent(StartApp.this, Main.class);
                                startActivity(splashScreen);
                            }
                        });
                        mInterstitialAd.show(StartApp.this);
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        mInterstitialAd = null;
                        Intent splashScreen = new Intent(StartApp.this, Main.class);
                        startActivity(splashScreen);
                    }
                });
    }
}