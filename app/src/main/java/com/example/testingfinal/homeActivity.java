package com.example.testingfinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.bumptech.glide.Glide;
import com.example.testingfinal.VideoEditorFolder.PortraitCameraActivity;

public class homeActivity extends AppCompatActivity {
    private ImageView pepegga;
    private static final int  CAMERA_PERMISSION_REQUEST_CODE = 88888;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        pepegga = (ImageView)findViewById(R.id.imageView2);
        Glide.with(this).load(R.drawable.pepega).into(pepegga);

    }

    static final int REQUEST_VIDEO_CAPTURE = 1;

    private void checkPermission(){
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M){
            return;
        }
        //verificar permisos de la camara
        if(checkSelfPermission(Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED ||
            checkSelfPermission(Manifest.permission.RECORD_AUDIO)!= PackageManager.PERMISSION_GRANTED ||
                checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissions(new String[]{Manifest.permission.CAMERA,Manifest.permission.RECORD_AUDIO,Manifest.permission.WRITE_EXTERNAL_STORAGE},CAMERA_PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,@NonNull int[] grantResults){
        switch (requestCode){
            case CAMERA_PERMISSION_REQUEST_CODE:
                if(grantResults.length > 0 && grantResults[0]== PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(homeActivity.this,"permisos concedidos",Toast.LENGTH_SHORT).show();


                } else{
                    Toast.makeText(homeActivity.this,"permisos no concedidos",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }


    public void agregar(View view) {
        checkPermission();

        Intent camara = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        if(camara.resolveActivity(getPackageManager())!=null){
            startActivityForResult(camara,REQUEST_VIDEO_CAPTURE);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        checkPermission();
    }
}