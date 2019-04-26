package com.example.apptest.androidprojectmonitor.activity;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;

import com.example.apptest.androidprojectmonitor.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CarSearch extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.webView)  // TODO WebView
        WebView webView;
    @BindView(R.id.backBtn)  // TODO 返回按钮
        ImageView backBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_search);
        ButterKnife.bind(this);
        backBtn.setOnClickListener(this);
        initWebView();  //初始化html页面
    }

    private void initWebView(){
        webView.getSettings().setJavaScriptEnabled(true);  //设置html,页面可以交互
        webView.setBackgroundColor(Color.TRANSPARENT);
        webView.loadUrl("file:///android_asset/cars_search.html");
    }

    @Override
    public void onClick(View v) {
        if (v == backBtn) {
            finish();
        }
    }
}
