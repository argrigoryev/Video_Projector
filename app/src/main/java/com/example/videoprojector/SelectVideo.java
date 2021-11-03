package com.example.videoprojector;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Bundle;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.view.View;

import java.io.File;


public class SelectVideo extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private Uri fileUri;

    private GridLayoutManager layoutManager;
    private boolean permission;
    private File storage;
    private String[] storagePaths;
    private Object GridLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GridLayoutManager = new GridLayoutManager(this, 2);
        setContentView(R.layout.activity_select_video);

        TextView title = (TextView)findViewById(R.id.title);
        Shader textShader=new LinearGradient(0, 0, 800, 0, new int[]{Color.parseColor("#B6E5FF"),Color.parseColor("#F6D0C5")},
                new float[]{0, 1}, Shader.TileMode.CLAMP);
        title.getPaint().setShader(textShader);



        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //if you face lack in scrolling then add following lines
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemViewCacheSize(20);
        recyclerView.setDrawingCacheEnabled(true);
        recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        recyclerView.setNestedScrollingEnabled(false);

        recyclerViewAdapter = new RecyclerViewAdapter(this);

        recyclerView.setAdapter(recyclerViewAdapter);
    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
//                                           @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (requestCode == 1){
//            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
//                //load data here
//                //for first time data will be loaded here
//                //then it will be loaded in splash screen
//                //because if we could not have permission then we could not load data in splash screen window
//                storagePaths = StorageUtill.getStorageDirectories(this);
//
//                for (String path : storagePaths) {
//                    storage = new File(path);
//                    Metod.load_Directory_Files(storage);
//                }
//
//                recyclerViewAdapter.notifyDataSetChanged();
//            }
//        }
//    }



    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case 1:
                if (resultCode == Activity.RESULT_OK){
                    fileUri  = data.getData();
                    getContentResolver().takePersistableUriPermission(fileUri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION
                            | Intent.FLAG_GRANT_READ_URI_PERMISSION);
                }
        }
    }

}
