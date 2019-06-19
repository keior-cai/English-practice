package com.english.listening.utils;

import com.baidu.aip.speech.AipSpeech;
import com.english.listening.exception.CheckException;
import com.english.listening.result.AudioBean;
import javazoom.spi.mpeg.sampled.file.MpegAudioFileReader;
import org.json.JSONObject;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class AudioUtils {
    /**
     * @param mp3filepath mp3文件路径
     * @param pcmfilepath 保存pcm文件路径
     */
    public static boolean convertMP32Pcm(String mp3filepath, String pcmfilepath){
        try {
            //获取文件的音频流，pcm的格式
            AudioInputStream audioInputStream = getPcmAudioInputStream(mp3filepath);
            //将音频转化为  pcm的格式保存下来
            AudioSystem.write(audioInputStream, AudioFileFormat.Type.WAVE, new File(pcmfilepath));
            return true;
        } catch (IOException e) {

            e.printStackTrace();
            return false;
        }
    }

    private static AudioInputStream getPcmAudioInputStream(String mp3filepath) {
        File mp3 = new File(mp3filepath);
        AudioInputStream audioInputStream = null;
        AudioFormat targetFormat = null;
        try {
            AudioInputStream in = null;
            MpegAudioFileReader mp = new MpegAudioFileReader();
            in = mp.getAudioInputStream(mp3);
            AudioFormat baseFormat = in.getFormat();
            targetFormat = new AudioFormat(AudioFormat.Encoding.PCM_UNSIGNED, 16000, 16,
                    baseFormat.getChannels(), baseFormat.getChannels()*2, baseFormat.getSampleRate(), false);
            audioInputStream = AudioSystem.getAudioInputStream(targetFormat, in);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return audioInputStream;
    }

    /**
     * 关闭流
     */
    private static void free(AudioInputStream audioInputStream, AudioInputStream in) {

        if (in != null) {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (audioInputStream != null) {
            try {
                audioInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 检查返回消息
     *
     * @param pcm
     */
    public static void CheckAudio(AudioBean pcm) {
        if (pcm.getErr_no() == 3301) {
            throw new CheckException("语音质量太差,请重新录音");
        }
        if (pcm.getErr_no() == 3309) {
            throw new CheckException("音频数据问题,请重新录音");
        }
        if (pcm.getErr_no() == 3310) {
            throw new CheckException("上传文件过大,请重新录音");
        }


    }

    /**
     * 语音识别返回文本
     *
     * @param pcmPath
     * @param voidetype
     * @return
     */

    public static AudioBean getBaiduMessage(String pcmPath, String voidetype) {
        AipSpeech instance = AudioSingleton.getInstance();
        instance.setConnectionTimeoutInMillis(20000);
        instance.setSocketTimeoutInMillis(60000);
        HashMap<String, Object> options = new HashMap<String, Object>();
        options.put("dev_pid",1737);
        JSONObject asr = instance.asr(pcmPath, voidetype, 16000, options);
        System.out.println(asr);
        AudioBean audioBean = JsonUtil.jsonToObject(asr.toString(), AudioBean.class);
        return audioBean;
    }


    public static void main(String[] args) {
        try {


            //随便找的MP3，用于格式转换
//            AudioUtils.mp3ToPcm(pcmPath, "E:\\a2.pcm");

            AudioUtils.convertMP32Pcm("E:\\2.mp3","E:\\ccc.pcm");
            //讲返回格式转成bean
            AudioBean pcm = getBaiduMessage("e:/ccc.pcm", "pcm");
            System.out.println(pcm.getResult());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
