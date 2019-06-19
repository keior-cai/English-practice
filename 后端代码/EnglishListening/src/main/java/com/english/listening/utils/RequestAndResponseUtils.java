package com.english.listening.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.english.listening.pojo.StudentInfo;
import com.english.listening.pojo.TeacherInfo;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.net.InetAddress;

/**
 * 请求的工具类工具类
 * @author wen
 *
 */

public class RequestAndResponseUtils {
	public static String D = ",";
	public static int IP_LENGTH = 15;
	public static  String UNKNOWN = "unknown";
	public static String LOCALHOST = "127.0.0.1";

	/**
	 * 获取request对象
	 * @return
	 */

	public static HttpServletRequest getRequest() {
		// HttpServlerRequest
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

	}
	/**
	 * 获取responnse对象
	 * @return
	 */
	
	public  static HttpServletResponse getResponse() {
		// HttpServlerRequest
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();

	}

	public static HttpSession getSession(){
		return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest().getSession();
	}

	public static Object getSessionObject(){
		HttpSession session = getSession();
		HttpServletRequest request = getRequest();
		String uuId = request.getHeader("token");
		return session.getAttribute(uuId);
	}

	public static String getSessionStudentNumber(){
		StudentInfo studentInfo = (StudentInfo) getSessionObject();
		return studentInfo.getStudentNumber();
	}

	public static String getSessionTeacherNumber(){
		TeacherInfo teacherInfo = (TeacherInfo) getSessionObject();
		return teacherInfo.getTeacherNumber();
	}

	public static Integer getSessionTeacheId(){
		TeacherInfo teacherInfo = (TeacherInfo) getSessionObject();
		return teacherInfo.getTeacherId();
	}
	/**
	 * 获取客户端IP
	 * @author CCJ
	 * @date 2018/11/10 17:05
	 * @return java.lang.String
	 **/
	public static String getRequestIpAddr(){
		HttpServletRequest httpServletRequest = getRequest();
		String ip = httpServletRequest.getHeader("x-forwarded-for");
		if(ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
			ip = httpServletRequest.getHeader("Proxy-Client-IP");
		}
		if(ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
			ip = httpServletRequest.getHeader("WL-Proxy-Client-IP");
		}
		if(ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
			ip = httpServletRequest.getRemoteAddr();
			if(ip.equals(LOCALHOST)){
				//根据网卡取本机配置的IP
				InetAddress inet=null;
				try {
					inet = InetAddress.getLocalHost();
				} catch (Exception e) {
					e.printStackTrace();
				}
				ip = inet.getHostAddress();
			}
		}
		// 多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
		if(ip != null && ip.length() > IP_LENGTH){
			if(ip.indexOf(D)>0){
				ip = ip.substring(0,ip.indexOf(D));
			}
		}
		return ip;
	}
}