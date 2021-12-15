package com.vidcompany.videohdtoolsgrop;

import android.app.Application;
import com.yandex.metrica.YandexMetrica;
import com.yandex.metrica.YandexMetricaConfig;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        this.initializeAppMetrica();
    }

    private void initializeAppMetrica() {
        YandexMetricaConfig config = YandexMetricaConfig.newConfigBuilder("03514748-89b3-4218-9ea3-36f817d87a80").build();
        YandexMetrica.activate(getApplicationContext(), config);
        YandexMetrica.enableActivityAutoTracking(this);
    }
}
