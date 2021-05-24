<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param value="메모" name="title"/>
</jsp:include>

<style>
div#memo-container{width:60%; margin:0 auto;text-align:center;}
</style>
<div id="memo-container">
    <form action="${pageContext.request.contextPath}/memo/insertMemo.do" class="form-inline" method="post">
        <input type="text" class="form-control col-sm-10" name="memo" placeholder="메모" required/>&nbsp;
        <button class="btn btn-outline-success" type="submit" >저장</button>
    </form>
    <br />
    <!-- 메모목록 -->
	<table class="table">
	    <tr>
	      <th>번호</th>
	      <th>메모</th>
	      <th>날짜</th>
	      <th>삭제</th>
	    </tr>
	    <c:if test="${empty list}">
	    	<tr>
	    		<td colspan=4>입력한 메모가 없습니다.</td>
	    	</tr>
	    </c:if>
	    
	    <c:if test="${not empty list}">
		    <c:forEach items="${list}" var="mem">
		    <tr>
		      <td scope="row">${mem.no}</td>
		      <td>${mem.memo}</td>
		      <td><fmt:formatDate value="${mem.regDate}" pattern="yy/MM/dd HH:mm"/></td>
		      <td><button type="button" class="btn btn-outline-danger">삭제</button></td>
			</tr>
			</c:forEach>
	    </c:if>
	</table>
</div>
<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
