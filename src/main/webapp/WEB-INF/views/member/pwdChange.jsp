<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>pwdChange.jsp</title>
	<jsp:include page="/WEB-INF/views/include/bs4.jsp" />
	<script>
	    'use strict';
	    
	    function pCheck() {
	    	let pwd = $("#pwd").val();
	    	
	    	$.ajax({
	    		 url : "${ctp}/member/pwdChange",
	    		 // 쉼표 안 적을 시 생기는 오류 :
	    		 // Uncaught SyntaxError : Unexpected identifier 'type' (at pwdChange:31:9)
	    		 type : "post", 
	    		 data :	{pwd : pwd},
	    		 success:function(res) {
	    			 if(res != 1) {
	    				 alert("비밀번호가 다릅니다.");
	    			 	 return false;
	    			 }
	    			 else {
	    				 pCheckChange();
	    			 }
	    		 },
	    		 error : function() {
	     			alert("전송오류!!")
	     		} 
	    	});
	    }
	    
	    function pCheckChange() {
	    	let newPwd = $("#newPwd").val();
	    	let newCheck = $("#newCheck").val();
	    	if(newPwd != newCheck) {
	    		alert("다시 한번 확인해주세요");
	    		return false;
	    	}
	    	$.ajax({
	    		 url : "${ctp}/member/pwdChangeOk",
	    		 // 쉼표 안 적을 시 생기는 오류 :
	    		 // Uncaught SyntaxError : Unexpected identifier 'type' (at pwdChange:31:9)
	    		 type : "post", 
	    		 data :	{pwd : newPwd},
	    		 success:function(res) {
	    			 if(res != 1) {
	    				 alert("변경 실패.");
	    			 }
	    			 else {
	    				 alert("변경이 완료되었습니다.다시 로그인해주세요");
	    				 location.href='${ctp}/member/memberLogin';
	    			 }
	    		 },
	    		 error : function() {
	     			alert("전송오류!!")
	     		} 
	    	});
	    }
	</script>
</head>
<body>
<jsp:include page="/WEB-INF/views/include/nav.jsp"/>
<jsp:include page="/WEB-INF/views/include/slide2.jsp"/>
<p><br/></p>
<div class="container">
		<h2 align="center">비밀번호 변경</h2>
		<table border="1" style="text-align:center" class="table table-hover">
			<tr >
				<th bgcolor="Gray">기존 비밀 번호</th>
				<td><input type="password" id="pwd" name="pwd" placeholder="기존 비밀번호를 입력해주세요" class="form-control" autofocus required></td>
			</tr>
			<tr>
				<th bgcolor="Gray">새 비밀번호</th>
				<td><input type="password" id="newPwd" name="newPwd" placeholder="새 비밀번호를 입력 해주세요" class="form-control" ></td>
			</tr>
			<tr>
				<th bgcolor="Gray">새 비밀 번호 확인</th>
				<td><input type="password" id="newCheck" name="newCheck" placeholder="새 비밀번호를 입력 해주세요" class="form-control" ></td>
			</tr>
		</table>
		<hr/>
		<div class="text-center">
			<span><input type="button" value="변경하기" onclick="pCheck()" class="btn  btn-primary" /></span>
			<span><input type="button" value="취소" onclick="location.herf='pwdCheck.jsp';" class="btn  btn-danger"/></span>
		</div>
</div>
<p><br/></p>
</body>
</html>