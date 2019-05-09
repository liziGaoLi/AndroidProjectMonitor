package com.example.apptest.androidprojectmonitor;

import android.app.Application;

import com.example.apptest.androidprojectmonitor.entity.LoginBean;

public class App extends Application {

    private LoginBean loginBean;

    public LoginBean getLoginBean() {
        return loginBean;
    }

    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
    }

    private static App app;

    public static App app() {
        return app;
    }
}
