<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param value="Dev 목록" name="title"/>
</jsp:include>
<table class="table w-75 mx-auto">
    <tr>
      <th scope="col">번호</th>
      <th scope="col">이름</th>
      <th scope="col">경력</th>
      <th scope="col">이메일</th>
      <th scope="col">성별</th>
      <th scope="col">개발가능언어</th>
      <th scope="col">수정 | 삭제</th>
    </tr>
	<c:if test="${empty list}">
	<tr>
	    <td colspan='6'>자료없음</td>
	</tr>
	</c:if>
	<c:if test="${not empty list}">
	<c:forEach items="${list}" var="dev">
	<tr>
		<td scope="row">${dev.no}</td>
		<td>${dev.name}</td>
		<td>${dev.career}년</td>
		<td>${dev.email}</td>
		<td>${dev.gender}</td>
		<td>
			<c:forEach items="${dev.lang}" var="lang" varStatus="vs">
			${lang}${vs.last ? "" : ","}
			</c:forEach>
		</td>
		<td>
			<button class="btn btn-outline-secondary" onclick="updateDev();" data-no="${dev.no};">수정</button>
			<button class="btn btn-outline-danger" onclick="deleteDev();" data-no="${dev.no};">삭제</button>
		</td>
	</tr>
	</c:forEach>
	</c:if>
</table>
<script>
function updateDev(id){

	//GET /demo/updateDev?no=123 ---> devUpdateForm.jsp
	//POST /demo/updateDev.do
	var $devFrm = $("devFrm")
	$devFrm
		.attr("action", `${pageContext.request.contextPath}/demo/updateDev?\${no}.do`)
		.attr("method", "POST")
		.submit();
}
function deleteDev(){
	//POST /demo/deleteDev.do
}
</script>

<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
