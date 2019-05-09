package com.example.apptest.androidprojectmonitor.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.example.apptest.androidprojectmonitor.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VerificationDetail extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.webView)  // TODO 人员核查详情 WebView
            WebView webView;
    @BindView(R.id.backBtn)  //返回按钮
            ImageView backBtn;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification_detail);
        ButterKnife.bind(this);
        backBtn.setOnClickListener(this);
        context = webView.getContext();
        initWebView();
    }
    private void initWebView(){
        webView.getSettings().setJavaScriptEnabled(true);  //设置html,页面可以交互
        webView.setBackgroundColor(Color.TRANSPARENT);
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        webView.loadUrl("file:///android_asset/verification_detail.html?id="+id);
    }
    @Override
    public void onClick(View v) {
        if (v == backBtn) {
            finish();
        }
    }
}
