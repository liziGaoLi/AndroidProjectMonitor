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

public class PersonnelVerification extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.webView)  // TODO 人员核查 WebView
    WebView webView;
    @BindView(R.id.backBtn)  // 返回按钮
        ImageView backBtn;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personnel_verification);
        ButterKnife.bind(this);
        backBtn.setOnClickListener(this);
        context = webView.getContext();
        initWebView();  //初始化html页面
    }

    private void initWebView(){
        webView.getSettings().setJavaScriptEnabled(true);  //设置html,页面可以交互
        webView.setBackgroundColor(Color.TRANSPARENT);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                String id = url.substring(url.lastIndexOf("=")+1, url.length());
                Intent intent = new Intent(context, VerificationDetail.class);
                intent.putExtra("id", id);
                startActivity(intent);
                return true;
            }

        });
        webView.loadUrl("file:///android_asset/personnel_verification.html");
    }

    @Override
    public void onClick(View v) {
        if (v == backBtn) {
            finish();
        }
    }
}
