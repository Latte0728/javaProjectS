<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>ajaxForm.jsp</title>
  <jsp:include page="/WEB-INF/views/include/bs4.jsp" />
  <script>
    'use strict';
    
    function ajaxTest1(idx) {
    	//alert("idx : " + idx);
    	$.ajax({
    		//ajax	: 비동기식 Ajax를 이용하여 HTTP 요청을 전송함
    		url   : "${ctp}/study/ajax/ajaxTest1",
    		// ${ctp}/study/ajax/ajaxTest1 부분에서 ajaxTest1은 jsp가 아닌 사용자가 원하는 변수명을 작성
    		// 변수 명 작성 시 컨트롤러와 변수 명을 일치시키자!!!
    		type  : "post",
    		data  : {idx : idx},
    		// 2. ajax를 이용하여 요청할 데이터를 컨트롤러로 보낸다.!!!
    		
    		success:function(res) {
    			$("#demo1").html(res);
    			// demo1이라는 id를 가진 해당 html태그에 res값을 html형식으로 넣어준다.
    		},
    		error : function() {
    			alert("전송오류!");
    		}
    	});
    }
    
    function ajaxTest2(str) {
    	$.ajax({
    		url   : "${ctp}/study/ajax/ajaxTest2",
    		type  : "post",
    		// contentType: "application/x-www-form-urlencoded; charset=UTF-8",
    		// contentType : 해더의 Content-Type을 설정한다.
    		// headers : {      
    		//	"Content-Type" : "application/json"},
    		data  : {str : str},
    		success:function(res) {
    			$("#demo2").html(res);
    		},
    		error : function() {
    			alert("전송오류!");
    		}
    	});
    }
  </script>
</head>
<body>
<jsp:include page="/WEB-INF/views/include/nav.jsp" />
<jsp:include page="/WEB-INF/views/include/slide2.jsp" />
<p><br/></p>
<div class="container">
  <h2>AJax 연습</h2>
  <hr/>
  <div>기본1(String) :
    <a href="javascript:ajaxTest1(10)" class="btn btn-success mr-2 mb-2">값전달1</a>
    <!-- btn btn-success mr-2 mb-2 는 단지 버튼처럼 보이게 하는 부트스트랩임 -->
    <!-- 1. 하이퍼 링크 클릭 시, 
    a href="javascript:ajaxTest1(10) 로 인하여, 
    자바스크립트의 function ajaxTest1(idx) 로 넘어감. 
    참고  :  javascript:ajaxTest1(10) 중 (10) 부분은 값이 제대로 넘어왔는지 확인하기 위해 사용되었음-->
   
    : <span id="demo1"></span>
  </div>
  <div>기본2(String) :
    <a href="javascript:ajaxTest2('안녕')" class="btn btn-success mr-2">값전달2</a>
    : <span id="demo2"></span>
  </div>
  <hr/>
  <div>응용(배열/ArrayList/Map)
    <a href="${ctp}/study/ajax/ajaxTest3_1" class="btn btn-primary mr-2">시(도)/구(시,군,동)(String배열)</a>
    <a href="${ctp}/study/ajax/ajaxTest3_2" class="btn btn-secondary mr-2">시(도)/구(시,군,동)(String배열)</a>
    <a href="${ctp}/study/ajax/ajaxTest3_3" class="btn btn-success mr-2">시(도)/구(시,군,동)(String배열)</a>
  </div>
  <hr/>
  <div>응용(Database)
    <a href="${ctp}/study/ajax/ajaxTest4_1" class="btn btn-primary mr-2">회원정보1인(vo)/회원정보여러명(vos)</a>
    
  </div>
</div>
<p><br/></p>
<jsp:include page="/WEB-INF/views/include/footer.jsp" />
</body>
</html>