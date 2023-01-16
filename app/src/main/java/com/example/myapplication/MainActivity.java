package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    TextView tv;
    ImageView img;
    Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.textview);
        img = findViewById(R.id.imageView3);
        bt = findViewById(R.id.button);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        final Bitmap bitmap =
                                getPicture("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS4HtmaPWDFwVXLtxyl5FNQ9jN9z-ahaSAO3moQnfzlgsIpNp0UM3229XnQQnjfpFQ1hlg&usqp=CAU");
                        img.post(new Runnable() {
                            @Override
                            public void run() {
                                img.setImageBitmap(bitmap);
                            }
                        });
                    }
                });
                thread.start();
                tv.setText("Download thanh cong");
            }
        });
    }
    private Bitmap getPicture (String link){

        URL url;
        Bitmap bitmap = null;
        try {
            url = new URL(link);
            bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }
}