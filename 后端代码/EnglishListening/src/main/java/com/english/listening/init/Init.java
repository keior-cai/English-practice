package com.english.listening.init;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Init implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private FileDirInit fileDirInit;
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
//        log.info("初始化创建文件目录");
//        fileDirInit.FileDirCreateInit();
    }
}
