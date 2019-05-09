package com.example.apptest.androidprojectmonitor.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apptest.androidprojectmonitor.App;
import com.example.apptest.androidprojectmonitor.R;
import com.example.apptest.androidprojectmonitor.entity.LoginBean;
import com.example.apptest.androidprojectmonitor.feature.Method;
import com.example.apptest.androidprojectmonitor.feature.MimeType;
import com.example.apptest.androidprojectmonitor.feature.OkHttpDSL;
import com.example.apptest.androidprojectmonitor.feature.RequestDescription;

import butterknife.BindView;
import butterknife.ButterKnife;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

public class MainActivity extends SplashActivityWrapper implements View.OnClickListener {
    @BindView(R.id.entry1) // TODO 人员核查
            LinearLayout entry1;
    @BindView(R.id.entry2) // TODO 车辆核查
            LinearLayout entry2;
    @BindView(R.id.entry3) // TODO 临时布控
            LinearLayout entry3;
    @BindView(R.id.entry4) // TODO 人员查询
            LinearLayout entry4;
    @BindView(R.id.entry5) // TODO 模糊检索
            LinearLayout entry5;
    @BindView(R.id.entry6) // TODO 车辆查询
            LinearLayout entry6;
    @BindView(R.id.entry7) // TODO 全省卡口
            LinearLayout entry7;
    @BindView(R.id.entry8) // TODO 人脸识别
            LinearLayout entry8;
    @BindView(R.id.entry9) // TODO 通讯录
            LinearLayout entry9;

    @BindView(R.id.userInfor)
    TextView userInfor;


    @Override
    public void login(String s) {
        OkHttpDSL okHttpDSL = new OkHttpDSL();
        RequestDescription requestDescription = okHttpDSL.getRequestDescription();
        requestDescription.method = Method.POST;
        requestDescription.uri = "http://192.168.20.230:8081/uas/sso/singlesignoncontrol/checkbill.do";
        requestDescription.setMimeType(MimeType.APPLICATION_X_FORM_URLENCODED);
        requestDescription.setBody("strBill=" + s);
        Function1<Throwable, Unit> exception = ex -> {
            Toast.makeText(this, "出现了一点问题:" + ex.getMessage(), Toast.LENGTH_SHORT).show();
            return null;
        };
        Function2<LoginBean, Continuation, Unit> success = (loginBean, c) -> {
            App.app().setLoginBean(loginBean);
            if (loginBean != null && loginBean.getUserInfo() != null) {
                userInfor.setText(loginBean.getUserInfo().getName() + "/" + loginBean.getUserInfo().getCode());
            }
            return null;
        };

        okHttpDSL.callType(LoginBean.class, success, exception);
    }

    @Override
    public void uaacApiError(String s) {
        Toast.makeText(this, "出现了一点问题:" + s, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        entry1.setOnClickListener(this);
        entry2.setOnClickListener(this);
        entry3.setOnClickListener(this);
        entry4.setOnClickListener(this);
        entry5.setOnClickListener(this);
        entry6.setOnClickListener(this);
        entry9.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == entry1) {
            startActivity(PersonnelVerification.class);
        } else if (v == entry2) {
            startActivity(CarCheck.class);
        } else if (v == entry3) {
            startActivity(TemporaryControl.class);
        } else if (v == entry5) {
            startActivity(ObscureSearch.class);
        } else if (v == entry4) {
            startActivity(PersonCheck.class);
        } else if (v == entry6) {
            startActivity(CarSearch.class);
        } else if (v == entry9) {
            startActivity(ContactsActivity.class);
        }
    }

    private void startActivity(Class target) {
        Intent intent = new Intent(this, target);
        startActivity(intent);
    }
}
