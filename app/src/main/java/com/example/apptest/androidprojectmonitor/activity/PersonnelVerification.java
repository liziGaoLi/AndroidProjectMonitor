package com.example.apptest.androidprojectmonitor.activity;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import com.example.apptest.androidprojectmonitor.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PersonnelVerification extends AppCompatActivity {
    @BindView(R.id.webView)  // TODO WebView
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personnel_verification);
        ButterKnife.bind(this);
        initWebView();  //初始化html页面
    }

    private void initWebView(){
        webView.getSettings().setJavaScriptEnabled(true);  //设置html,页面可以交互
        webView.setBackgroundColor(Color.TRANSPARENT);
        webView.loadUrl("file:///android_asset/personnel_verification.html");
    }
}
