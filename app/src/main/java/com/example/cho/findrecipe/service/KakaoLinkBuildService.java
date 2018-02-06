package com.example.cho.findrecipe.service;

import android.content.Context;

import com.kakao.kakaolink.KakaoLink;
import com.kakao.kakaolink.KakaoTalkLinkMessageBuilder;
import com.kakao.util.KakaoParameterException;

/**
 * Created by cho on 2018-02-06.
 */

public class KakaoLinkBuildService {

    private final String prefix = "Find Recipe 에서 다음 레시피를 추천 했습니다! ";

    public void sendMessage(String href, Context context) {

        try {
            KakaoLink kakaoLink = KakaoLink.getKakaoLink(context);
            KakaoTalkLinkMessageBuilder messageBuilder = kakaoLink.createKakaoTalkLinkMessageBuilder();

            messageBuilder.addText(prefix + href);

//            messageBuilder.addWebLink("블로그 이동", href);

            kakaoLink.sendMessage(messageBuilder, context);

        } catch (KakaoParameterException e) {
            System.out.println("error occurred from kakao link");
            e.printStackTrace();
        }
    }
}
