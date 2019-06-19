package com.english.listening.utils;

import com.baidu.aip.speech.AipSpeech;

public class AudioSingleton {
    /**
     * 百度开放平台获取
     */
    private static final String APP_ID = "11679901";

    private static final String API_KEY = "FMkPBfeCmc7kGQmhHr3prGzN";

    private static final String SECRET_KEY = "WpWbnNu9SDUscwWTs2sQRtw1WXvGssCg";

    /**
     * 单例模式，jvm保证只生成一个单例
     */
    public static class AudioSingletonInstance {
        public static  AipSpeech aipSpeech = new AipSpeech(APP_ID,API_KEY,SECRET_KEY);
    }

    /**
     * 获取AipSpeech对象
     * @return
     */
    public static AipSpeech getInstance(){
        return AudioSingletonInstance.aipSpeech;
    }
}
