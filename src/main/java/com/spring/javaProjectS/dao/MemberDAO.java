package com.spring.javaProjectS.dao;

import org.apache.ibatis.annotations.Param;

import com.spring.javaProjectS.vo.MemberVO;

public interface MemberDAO {

	public MemberVO getMemberIdCheck(@Param("mid") String mid);

	public MemberVO getMemberNickCheck(@Param("nickName") String nickName);

	public int setMemberJoinOk(@Param("vo") MemberVO vo);

	public int setMemberDelete(@Param("sMid") String sMid);

	public MemberVO pwdChangeGet(@Param("mid") String mid);

	public int setPasswordUpdate(@Param("pwd") String pwd, @Param("mid") String mid);

}
