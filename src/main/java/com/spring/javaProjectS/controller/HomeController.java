package com.spring.javaProjectS.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = {"/","/h"}, method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		return "home";
		//return "redirect:/user/userList";
		//return "redirect:/user2/user2List";
	}
	@RequestMapping(value="/imageUpload")
	public void imageUploadGet(MultipartFile upload, 
			HttpServletRequest request, HttpServletResponse response) throws IOException {
			// 넘기는 값이 없을 때는 경로만 지정하고 void타입으로 지정해준다.	
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		// 중복 파일명 처리 
		String realPath = request.getSession().getServletContext().getRealPath("/resources/data/ckeditor/board/");
		String oFileName = upload.getOriginalFilename();
	
		Date data = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
		oFileName = sdf.format(data) + "" + oFileName;
	
		byte[] bytes = upload.getBytes();
		// 업로드 한번에 못하니 바이트로 나눠서 보냄
		FileOutputStream fos = new FileOutputStream(new File(realPath+oFileName));
		// realPath+oFileName : 경로명 + 파일명
		fos.write(bytes);
		// /resources/data/ckeditor/board/ <-- 이 경로에 저장됨
		
		//byte[] bytes = upload.getBytes();
		//FileOutputStream fos = new FileOutputStream(new File(realPath+oFileName));
		//fos.write(bytes);
		// 파일 저장 완료 
		
		PrintWriter out = response.getWriter();
		String fileUrl = request.getContextPath() + "/data/ckeditor/board/" + oFileName;
		out.println("{\"originalFilename\":\""+oFileName+"\",\"uploaded\":1,\"url\":\""+fileUrl+"\"}");
		
		out.flush();
		// 파일 찌꺼기 보내기(제거)
		fos.close();
	}	
	
}
	




