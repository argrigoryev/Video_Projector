package com.vidcompany.videohdtoolsgrop;

import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.ads.nativetemplates.TemplateView;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.nativead.NativeAd;

public class VideoPlayer extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);
        this.setNativeAds();
        VideoView videoView = (VideoView)findViewById(R.id.videoPlayer);
        MediaController mediaController= new MediaController(this);
        mediaController.setAnchorView(videoView);
        mediaController.setPadding(0,0,0,250);
        Uri uri = Uri.parse(Model.instance().selectedVideo.getAbsolutePath());
        videoView.setMediaController(mediaController);
        videoView.setVideoURI(uri);
        videoView.requestFocus();
        videoView.start();
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
}
