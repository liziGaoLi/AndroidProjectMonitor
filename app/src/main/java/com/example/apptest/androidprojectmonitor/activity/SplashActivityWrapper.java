//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.example.apptest.androidprojectmonitor.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.xdja.sslvpn.api.VpnApi50;
import com.xdja.sslvpn.api.VpnApi50Impl;
import com.xdja.uaac.api.TokenCallback;
import com.xdja.uaac.api.UaacApi;

public abstract class SplashActivityWrapper extends AppCompatActivity {
    public SplashActivityWrapper() {
    }

    public abstract void login(String var1);

    public abstract void uaacApiError(String var1);

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UaacApi.getToken(this, new TokenCallback() {
            public void onSuccess(final String s, boolean b) {
                if (b) {
                    SplashActivityWrapper.this.login(s);
                } else {
                    SplashActivityWrapper.this.getWindow().getDecorView().postDelayed(new Runnable() {
                        public void run() {
                            SplashActivityWrapper.this.login(s);
                        }
                    }, 5000L);
                }

            }

            public void onError(String s) {
                SplashActivityWrapper.this.uaacApiError(s);
            }
        });
    }


    public  String getHardWareCode() {
        String result = "";

        try {
            VpnApi50 vpi = new VpnApi50Impl(this);
            result = vpi.getCardID();
        } catch (Exception e) {
            e.printStackTrace();
            setResult(RESULT_CANCELED, new Intent().putExtra("err", "无安全客户端组件"));
            finish();
            return "";
        }
        Log.e("getHardWareCode: ", result);
        return result;
    }
}
