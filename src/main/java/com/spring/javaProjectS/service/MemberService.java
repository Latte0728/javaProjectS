package com.spring.javaProjectS.service;

import com.spring.javaProjectS.vo.MemberVO;

public interface MemberService {

	public MemberVO getMemberIdCheck(String mid);

	public MemberVO getMemberNickCheck(String nickName);

	public int setMemberJoinOk(MemberVO vo);

	public int setMemberDelete(String sMid);

	public MemberVO pwdChangeGet(String mid);

	public int setPasswordUpdate(String pwd, String mid);




}
