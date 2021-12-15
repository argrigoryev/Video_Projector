package com.vidcompany.videohdtoolsgrop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.ads.nativetemplates.TemplateView;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.gms.ads.nativead.NativeAd;
import com.google.android.gms.ads.nativead.NativeAdOptions;

public class Main extends AppCompatActivity {

    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTitleGradient();
        this.setNativeAds();
    }

    private void setTitleGradient() {
        TextView title = (TextView)findViewById(R.id.title);
        Shader textShader=new LinearGradient(0, 0, 800, 0, new int[]{Color.parseColor("#B6E5FF"),Color.parseColor("#F6D0C5")}, new float[]{0, 1}, Shader.TileMode.CLAMP);
        title.getPaint().setShader(textShader);
    }

    private void setNativeAds() {
        AdLoader adLoader = new AdLoader.Builder(this, "ca-app-pub-9562261954889993/9961308470")
                .forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
                    @Override
                    public void onNativeAdLoaded(NativeAd NativeAd) {
                        TemplateView t = findViewById(R.id.native_ad_view_news_feed);
                        t.setNativeAd(NativeAd);
                    }
                }).build();
        adLoader.loadAd(new AdRequest.Builder().build());
    }

    public void onShareButtonClick(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.vidcompany.videohdtoolsgrop"));
        startActivity(browserIntent);
    }

    public void onPrivacyPoliceButtonClick(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://sites.google.com/view/privacy-policy-videopoj/%D0%B3%D0%BB%D0%B0%D0%B2%D0%BD%D0%B0%D1%8F-%D1%81%D1%82%D1%80%D0%B0%D0%BD%D0%B8%D1%86%D0%B0"));
        startActivity(browserIntent);
    }

    public void onStartButtonClick(View view) {
        this.showAds();

    }

    public void onRateButtonClick(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.vidcompany.videohdtoolsgrop"));
        startActivity(browserIntent);
    }

    private void showAds() {
        AdRequest adRequest = new AdRequest.Builder().build();
        InterstitialAd.load(this,"ca-app-pub-9562261954889993/5092125170", adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        mInterstitialAd = interstitialAd;
                        mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback(){
                            @Override
                            public void onAdDismissedFullScreenContent() {
                                Intent splashScreen = new Intent(Main.this, Select.class);
                                startActivity(splashScreen);
                            }

                            @Override
                            public void onAdFailedToShowFullScreenContent(AdError adError) {
                                Intent splashScreen = new Intent(Main.this, Select.class);
                                startActivity(splashScreen);
                            }

                            @Override
                            public void onAdShowedFullScreenContent() {
                                mInterstitialAd = null;
                            }

                            @Override
                            public void onAdClicked() {
                                Intent splashScreen = new Intent(Main.this, Select.class);
                                startActivity(splashScreen);
                            }
                        });
                        mInterstitialAd.show(Main.this);
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        mInterstitialAd = null;
                        Intent splashScreen = new Intent(Main.this, Select.class);
                        startActivity(splashScreen);
                    }
                });
    }

}