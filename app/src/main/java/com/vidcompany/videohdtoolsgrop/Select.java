package com.vidcompany.videohdtoolsgrop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
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

public class Select extends AppCompatActivity {

    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        this.setTitleGradient();
        this.setNativeAds();
    }

    private void setTitleGradient() {
        TextView title = (TextView)findViewById(R.id.title);
        Shader textShader=new LinearGradient(0, 0, 800, 0, new int[]{Color.parseColor("#B6E5FF"),Color.parseColor("#F6D0C5")}, new float[]{0, 1}, Shader.TileMode.CLAMP);
        title.getPaint().setShader(textShader);
    }

    private void setNativeAds() {
        AdLoader adLoader = new AdLoader.Builder(this, "ca-app-pub-9562261954889993/5608513386")
                .forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
                    @Override
                    public void onNativeAdLoaded(NativeAd NativeAd) {
                        TemplateView t = findViewById(R.id.native_ad_view_news_feed);
                        t.setNativeAd(NativeAd);
                    }
                }).build();
        adLoader.loadAd(new AdRequest.Builder().build());
    }

    public void onVideoStreamingButtonClick(View view) {
        this.showAdsForSelectPlayerVideo();

    }

    private void showAdsForSelectPlayerVideo() {
        AdRequest adRequest = new AdRequest.Builder().build();
        InterstitialAd.load(this,"ca-app-pub-9562261954889993/1152880161", adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        mInterstitialAd = interstitialAd;
                        mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback(){
                            @Override
                            public void onAdDismissedFullScreenContent() {
                                Intent splashScreen = new Intent(Select.this, SelectPlayerVideo.class);
                                startActivity(splashScreen);
                            }

                            @Override
                            public void onAdFailedToShowFullScreenContent(AdError adError) {
                                Intent splashScreen = new Intent(Select.this, SelectPlayerVideo.class);
                                startActivity(splashScreen);
                            }

                            @Override
                            public void onAdShowedFullScreenContent() {
                                mInterstitialAd = null;
                            }

                            @Override
                            public void onAdClicked() {
                                Intent splashScreen = new Intent(Select.this, SelectPlayerVideo.class);
                                startActivity(splashScreen);
                            }
                        });
                        mInterstitialAd.show(Select.this);
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        mInterstitialAd = null;
                        Intent splashScreen = new Intent(Select.this, SelectPlayerVideo.class);
                        startActivity(splashScreen);
                    }
                });
    }
}