package com.example.apptest.androidprojectmonitor.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.example.apptest.androidprojectmonitor.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        entry1.setOnClickListener(this);
        entry2.setOnClickListener(this);
        entry3.setOnClickListener(this);
        entry4.setOnClickListener(this);
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
