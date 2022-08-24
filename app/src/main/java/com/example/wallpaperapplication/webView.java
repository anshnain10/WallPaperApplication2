package com.example.wallpaperapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.Toolbar;

import java.util.ArrayList;

import io.paperdb.Paper;

public class webView extends AppCompatActivity {
    WebView webView;
    ImageButton btn;
    String imageid=Paper.book().read(CustomAdapter.imageId);
    String lkes=Paper.book().read(CustomAdapter.lkes);
    ArrayList<Model2> model2ArrayList;
    Model2 model2=new Model2(imageid,lkes);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
//            if (imageid!=null && lkes!=null){
//                if (!imageid.isEmpty()&& !lkes.isEmpty()){
//                    if (lkes.equals(model2.likes)){
//                       Intent intent=new Intent(webView.this,MainActivity.class);
//                        startActivity(intent);
//
//
//
//                    }
//                }
//            }

        Paper.init(this);
        webView=findViewById(R.id.webView);
        btn=findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("what it is reading"+lkes);
                Intent intent=new Intent(webView.this,MainActivity.class);
                startActivity(intent);
            }
        });
        Intent intent=getIntent();
        String url=intent.getStringExtra("url");
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);

    }




}