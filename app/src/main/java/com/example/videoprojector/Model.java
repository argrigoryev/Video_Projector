package com.example.videoprojector;

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
}
