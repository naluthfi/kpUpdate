package com.example.windows10.nyobadua;

import android.support.v7.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class kamera extends AppCompatActivity {
    ImageButton imagecam1;
    ImageView gambar;
    Bitmap bitmap;
    ImageButton imagecam2;
    Uri imageuri;

    private static final int PICK_IMAGE=100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);

        imagecam1 = (ImageButton) findViewById(R.id.imagecam1);
        gambar = (ImageView) findViewById(R.id.gambar);
        imagecam2 = (ImageButton) findViewById(R.id.imagecam2);

        imagecam2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opengallery();
            }
        });

        imagecam1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cam = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cam,0);
            }
        });
    }

    private void opengallery(){
        Intent gallery = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && requestCode == PICK_IMAGE){
            imageuri = data.getData();
            gambar.setImageURI(imageuri);
        }

        else {
            bitmap = (Bitmap) data.getExtras().get("data");
            gambar.setImageBitmap(bitmap);
        }
    }
}
