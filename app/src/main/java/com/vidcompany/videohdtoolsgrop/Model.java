package com.vidcompany.videohdtoolsgrop;

import java.io.File;

public class Model {
    public static Model instance = null;
    private Model() {}
    public static Model instance() {
        if (instance == null) {
            instance = new Model();
        }
        return instance;
    }

    File selectedVideo = null;
    String selectedTheme = null;
}
