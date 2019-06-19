package com.english.listening.init;


import com.english.listening.constants.FileConstants;
import com.english.listening.utils.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 初始化文件目录
 * @author CCJ
 * @date 2018/11/17 23:04
 **/
@Slf4j
@Component
public class FileDirInit {
    public void FileDirCreateInit(){
        //创建根目录
        createDir(FileConstants.DEFAULT_ROOT_PATH);
        //创建作业上传目录
        createDir(FileConstants.DEFAULT_ROOT_PATH +FileConstants.DS +FileConstants.DEFAULT_HOMEWORK_PATH);
        //创建视频目录
        createDir(FileConstants.DEFAULT_ROOT_PATH +FileConstants.DS +FileConstants.DEFAULT_VIDEO_PATH);
        //创建课件目录
        createDir(FileConstants.DEFAULT_ROOT_PATH +FileConstants.DS +FileConstants.DEFAULT_KJ_PAHT);
    }

    private void createDir(String path){
        if (FileUtil.getFiles(path) == null){
            Boolean b = FileUtil.createDir(path);
            log.info("创建目录:------"+path+"------成功");
            if (!b){
                log.error("初始化创建文件目录异常");
                throw new RuntimeException("系统创建文件异常");
            }
        }
        log.info("目录:------"+path+"------已存在");
    }
}
