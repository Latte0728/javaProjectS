package com.spring.javaProjectS.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.javaProjectS.dao.MemberDAO;
import com.spring.javaProjectS.vo.MemberVO;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	MemberDAO memberDAO;

	@Override
	public MemberVO getMemberIdCheck(String mid) {
		return memberDAO.getMemberIdCheck(mid);
	}

	@Override
	public MemberVO getMemberNickCheck(String nickName) {
		return memberDAO.getMemberNickCheck(nickName);
	}

	@Override
	public int setMemberJoinOk(MemberVO vo) {
		// 사진처리...
		vo.setPhoto("noimage.jpg");
		
		
		return memberDAO.setMemberJoinOk(vo);
	}

	@Override
	public int setMemberDelete(String sMid) {
		
		return memberDAO.setMemberDelete(sMid);
	}

	@Override
	public MemberVO pwdChangeGet(String mid) {
		
		return memberDAO.pwdChangeGet(mid);
	}

	@Override
	public int setPasswordUpdate(String pwd, String mid) {
		
		return memberDAO.setPasswordUpdate(pwd, mid);
	}

	
}