package com.example.cho.findrecipe.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.example.cho.findrecipe.R;
import com.example.cho.findrecipe.service.KakaoLinkBuildService;

/**
 * Created by cho on 2018-02-03.
 */

public class ResultPopupActivity extends AppCompatActivity {

    private KakaoLinkBuildService kakaoLinkBuildService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_popup);

        kakaoLinkBuildService = new KakaoLinkBuildService();


        String href = getIntent().getStringExtra("href");

        Button kakaoButton = findViewById(R.id.result_link_kakao_button);
        kakaoButton.setOnClickListener(view -> {
            kakaoLinkBuildService.sendMessage(href, this);
        });


        Button naverButton = findViewById(R.id.result_search_naver_button);
        naverButton.setOnClickListener(view -> {

            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(href));
            startActivity(intent);
        });

    }


}
