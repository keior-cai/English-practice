package com.english.listening.exception;



/**
 * 自定义异常，主要用于校验错误异常
 * @author Gentle
 *
 */
public class DownLoadException extends RuntimeException {

   private static final long serialVersionUID = 1L;

   public DownLoadException() {
   }

   public DownLoadException(String message) {
      super(message);
   }

   public DownLoadException(Throwable cause) {
      super(cause);
   }

   public DownLoadException(String message, Throwable cause) {
      super(message, cause);
   }



}