package com.spring.javaProjectS.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.spring.javaProjectS.common.SecurityUtil;
import com.spring.javaProjectS.pagination.PageProcess;
import com.spring.javaProjectS.pagination.PageVO;
import com.spring.javaProjectS.service.PdsService;
import com.spring.javaProjectS.vo.PdsVO;

@Controller
@RequestMapping("/pds")
public class PdsController {

	@Autowired
	PdsService pdsService;
	
	@Autowired
	PageProcess pageProcess;
	
	@RequestMapping(value = "/pdsList", method = RequestMethod.GET)
	public String pdsListGet(Model model,
			@RequestParam(name="pag", defaultValue = "1", required = false) int pag,
			@RequestParam(name="pageSize", defaultValue = "1", required = false) int pageSize,
			@RequestParam(name="part", defaultValue = "전체", required = false) String part
			) {
		PageVO pageVO = pageProcess.totRecCnt(pag, pageSize, "pds", part, "");
		
		List<PdsVO> vos = pdsService.getPdsList(pageVO.getStartIndexNo(),pageSize,part);
		
		model.addAttribute("vos", vos);
		model.addAttribute("pageVO", pageVO);
		
		return "pds/pdsList";
	}
	
	@RequestMapping(value="/pdsInput", method = RequestMethod.GET)
	public String pdsListGet(Model model, String part) {
		model.addAttribute("part", part);
		return "pds/pdsInput";
	}
	@RequestMapping(value="/pdsInput", method = RequestMethod.POST)
	public String pdsInputPOST(PdsVO vo, MultipartHttpServletRequest file) {
		// MultipartHttpServletRequest : 파일을 여러개 넘길때 사용
		SecurityUtil security = new SecurityUtil();
		// encryptSHA256 사용을 위해 생성 
		
		UUID uid = UUID.randomUUID();
		String salt = uid.toString().substring(0,4);
		// salt 키 4자리까지 생성 
		
		String pwd = salt + "" + security.encryptSHA256(vo.getPwd());
		// 비밀 번호 암호화
		
		vo.setPwd(pwd);
		// 비밀 번호 DB 저장
		
		int res = pdsService.setPdsInput(vo,file);
		// 아직 파일 업로드 안되있어서 vo랑 file 객체 넘겨야됨
		
		if(res == 1) return "redirect:/message/pdsInputOk";
		else return "redirect:/message/pdsInputNo";
	}
	@ResponseBody
	@RequestMapping(value = "/pdsDownNumCheck", method = RequestMethod.POST)
	public String pdsDownNumCheckPost(int idx) {
		int res = pdsService.setPdsDownNumCheck(idx);
		return res + "";
	}
	@ResponseBody
	@RequestMapping(value = "/pdsDeleteOk", method = RequestMethod.POST)
	public String pdsDeleteOkPost(int idx, String pwd) {
		PdsVO vo = pdsService.getIdxSearch(idx);
		//String tempPwd = vo.getPwd().substring(0, 4);
		
		int res = 0;
		SecurityUtil security = new SecurityUtil();
		if(security.encryptSHA256(pwd).equals(vo.getPwd().substring(4))) {
			res = pdsService.setPdsDelete(vo);
		}
		
		return res + "";
	}
}
