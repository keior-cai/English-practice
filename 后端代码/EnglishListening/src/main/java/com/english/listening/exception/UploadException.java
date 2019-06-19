package com.english.listening.exception;



/**
 * 自定义异常，主要用于校验错误异常
 * @author Gentle
 *
 */
public class UploadException extends RuntimeException {

   private static final long serialVersionUID = 1L;

   public UploadException() {
   }

   public UploadException(String message) {
      super(message);
   }

   public UploadException(Throwable cause) {
      super(cause);
   }

   public UploadException(String message, Throwable cause) {
      super(message, cause);
   }



}