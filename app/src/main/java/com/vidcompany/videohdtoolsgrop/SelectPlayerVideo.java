package com.vidcompany.videohdtoolsgrop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
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

public class SelectPlayerVideo extends AppCompatActivity {

    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_player_video);
        this.setTitleGradient();
        this.setNativeAds();
        this.initializeVideoList();
    }

    private void setTitleGradient() {
        TextView title = (TextView)findViewById(R.id.title);
        Shader textShader=new LinearGradient(0, 0, 800, 0, new int[]{Color.parseColor("#B6E5FF"),Color.parseColor("#F6D0C5")}, new float[]{0, 1}, Shader.TileMode.CLAMP);
        title.getPaint().setShader(textShader);
    }

    private void setNativeAds() {
        AdLoader adLoader = new AdLoader.Builder(this, "ca-app-pub-9562261954889993/9769736786")
                .forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
                    @Override
                    public void onNativeAdLoaded(NativeAd NativeAd) {
                        TemplateView t = findViewById(R.id.native_ad_view_news_feed);
                        t.setNativeAd(NativeAd);
                    }
                }).build();
        adLoader.loadAd(new AdRequest.Builder().build());
    }

    private void initializeVideoList() {
        new LinearLayoutManager(this);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemViewCacheSize(20);
        recyclerView.setDrawingCacheEnabled(true);
        recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        recyclerView.setNestedScrollingEnabled(false);
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(this);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        Model.instance().selectedVideo = Constant.allMediaList.get(position);
                        AdRequest adRequest = new AdRequest.Builder().build();
                        InterstitialAd.load(SelectPlayerVideo.this,"ca-app-pub-9562261954889993/6213635157", adRequest,
                                new InterstitialAdLoadCallback() {
                                    @Override
                                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                                        mInterstitialAd = interstitialAd;
                                        mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback(){
                                            @Override
                                            public void onAdDismissedFullScreenContent() {
                                                Intent videoPlayerIntent = new Intent(SelectPlayerVideo.this, VideoPlayer.class);
                                                startActivity(videoPlayerIntent);
                                            }

                                            @Override
                                            public void onAdFailedToShowFullScreenContent(AdError adError) {
                                                Intent videoPlayerIntent = new Intent(SelectPlayerVideo.this, VideoPlayer.class);
                                                startActivity(videoPlayerIntent);
                                            }

                                            @Override
                                            public void onAdShowedFullScreenContent() {
                                                mInterstitialAd = null;
                                            }

                                            @Override
                                            public void onAdClicked() {
                                                Intent videoPlayerIntent = new Intent(SelectPlayerVideo.this, VideoPlayer.class);
                                                startActivity(videoPlayerIntent);
                                            }
                                        });
                                        mInterstitialAd.show(SelectPlayerVideo.this);
                                    }

                                    @Override
                                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                                        mInterstitialAd = null;
                                        Intent videoPlayerIntent = new Intent(SelectPlayerVideo.this, VideoPlayer.class);
                                        startActivity(videoPlayerIntent);
                                    }
                                });
                    }
                    @Override public void onLongItemClick(View view, int position) {}
                })
        );
    }
}