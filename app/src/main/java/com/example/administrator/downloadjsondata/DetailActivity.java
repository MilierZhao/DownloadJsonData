package com.example.administrator.downloadjsondata;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity{

    private static final String EXTRA_DETAIL_IMAGE="com.example.admnistraor.downjsondata.image";
    private static final String EXTRA_DETAIL_NAME="com.example.admnistraor.downjsondata.name";
    private static final String EXTRA_DETAIL_SCM="com.example.admnistraor.downjsondata.scm";
    private static final String EXTRA_DETAIL_CAT="com.example.admnistraor.downjsondata.cat";
    private static final String EXTRA_DETAIL_TIME="com.example.admnistraor.downjsondata.time";

    private String image;
    private String name;
    private String scm;
    private String cat;
    private String time;

    private ImageView imageView;
    private TextView nameText;
    private TextView scmText;
    private TextView catText;
    private TextView timeText;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

//         image=getIntent().getStringExtra(EXTRA_DETAIL_IMAGE);
       image="";
        name=getIntent().getStringExtra(EXTRA_DETAIL_NAME);
        scm=getIntent().getStringExtra(EXTRA_DETAIL_SCM);
        cat=getIntent().getStringExtra(EXTRA_DETAIL_CAT);
        time=getIntent().getStringExtra(EXTRA_DETAIL_TIME);

        imageView=(ImageView) findViewById(R.id.imageView);
        nameText=(TextView) findViewById(R.id.detail_name);
        scmText=(TextView) findViewById(R.id.detail_scm);
        catText=(TextView) findViewById(R.id.detail_cat);
        timeText=(TextView) findViewById(R.id.detail_time);

        Glide.with(this).load(image).into(imageView);
        nameText.setText(name);
        scmText.setText(scm);
        catText.setText(cat);
        timeText.setText(time);


    }

    public static Intent newIntent(Context content,String image,String name,String scm,String cat,String time){
        Intent i=new Intent(content,DetailActivity.class);
        i.putExtra(EXTRA_DETAIL_IMAGE,image);
        i.putExtra(EXTRA_DETAIL_NAME,name);
        i.putExtra(EXTRA_DETAIL_SCM,scm);
        i.putExtra(EXTRA_DETAIL_CAT,cat);
        i.putExtra(EXTRA_DETAIL_TIME,time);
        return i;
    }


}
