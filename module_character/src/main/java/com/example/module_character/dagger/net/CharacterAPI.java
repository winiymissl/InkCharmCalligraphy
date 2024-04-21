package com.example.module_character.dagger.net;

import com.example.module_character.data.result.CheckInResult;
import com.example.module_character.data.result.SentenceResult;

import io.reactivex.rxjava3.core.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * @Author winiymissl
 * @Date 2024-04-17 21:12
 * @Version 1.0
 */
public interface CharacterAPI {
    @POST("/grow/check/in")
    @Multipart
    Observable<CheckInResult> checkIn(@Header("Authorization") String token, @Part MultipartBody.Part request, @Part MultipartBody.Part content, @Part MultipartBody.Part score);

    /**
     * a	动画
     * b	漫画
     * c	游戏
     * d	文学
     * e	原创
     * f	来自网络
     * g	其他
     * h	影视
     * i	诗词
     * j	网易云
     * k	哲学
     * l	抖机灵
     */
    @GET("https://v1.hitokoto.cn/")
    Observable<SentenceResult> getSentence(@Query("c") String c);
}