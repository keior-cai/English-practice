package com.english.listening.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import com.english.listening.exception.CheckException;
import org.apache.commons.io.FileUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * 上传文件
 * @version
 */
public class FileUpload {

	private static String DS = ".";
	/**
	 * @param file 			//文件对象
	 * @param filePath		//上传路径
	 * @param fileName		//文件存储在服务器的随机名，可以传UUID
	 * @return  文件名
	 */
	public static String  fileUp(MultipartFile file, String filePath, String fileName){
		String extName = ""; // 扩展名格式：
		try {
			if (file.getOriginalFilename().lastIndexOf(DS) >= 0){
				extName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(DS));
			}
			copyFile(file.getInputStream(), filePath, (fileName+extName));

			return filePath+"/"+(fileName+extName);
		} catch (IOException e) {
			System.out.println(e);
		}
		return null;
	}

    /**
     * 写文件到当前目录的upload目录中
     * @param in 输入流
     * @param dir 路径
     * @param realName 文件名
     * @return
     * @throws IOException
     */
	public static String copyFile(InputStream in, String dir, String realName)
			throws IOException {
		File file = new File(dir, realName);
		if (!file.exists()) {
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			file.createNewFile();
		}
		FileUtils.copyInputStreamToFile(in, file);
		return realName;
	}


	private static List<String> init(){
		List<String> list = new ArrayList<>();
		list.add("mp4");
		list.add("doc");
		list.add("docx");
		list.add("avi");
		list.add("pdf");
		return  list;
	}

	/**
	 *  校验文件是否符合后缀
	 * @param extName
	 * @return
	 */
	public static boolean checkExtName(String extName){
		List<String> list = init();

		for (String s : list){
			if (s.equalsIgnoreCase(extName)){
				return true;
			}
		}
		return false;
	}

	public static boolean checkFileInfo(@RequestParam("file") MultipartFile multipartFile) {
		if (multipartFile == null) {
			throw new CheckException("上传文件不能为空");
		}
		String originalFilename = multipartFile.getOriginalFilename();
		if (StringUtils.isEmpty(originalFilename)) {
			throw new CheckException("上传的文件名为空");
		}

		String extName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
		if (!FileUpload.checkExtName(extName)) {
			throw  new CheckException("文件扩展名不符合规则");
		}
		if (multipartFile.getSize()>10240000){
			throw  new CheckException("上传文件不课超过10M");
		}

		return true;
	}










}
