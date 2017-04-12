<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<style>
.view{
	padding: 30px 40px 60px 40px;
}
.blockNavigation{
	background: #fff;
	padding:15px;
}
.blockNavigationRow{
	padding:5px;
	margin-left:15px;
}
.buttonsRow{
	padding-top:35px;
	margin-left:45px;
}
.block{
	border-radius: 2px;
	position: relative;
    background: #fff;
	width: 230px;
}
.photo{
	padding: 15px;
}
.blockInfo{
	width: 600px;
	height:230px;
	border-radius: 2px;
	position: relative;
    background: #fff;
	margin-left: 15px;
}
.info{
	padding: 15px;
}
.infoRow{
	color: #828282;
}
.post{
	background: #fff;
	width: 845px;
	margin-top:15px;
}
.postText{
	padding:5px;
	word-wrap:break-word;
}
.postButton{
	margin-top:5px;
}
.addPost{
	margin-top:15px;
}
</style>	
	<div class="col-md-12 view">
	<div class="col-md-2">
		<div class="col-md-12 blockNavigation">
			<div class="row blockNavigationRow">
				<a href="/member/<sec:authentication property="principal.id" />">My page</a>
			</div>
			<div class="row blockNavigationRow">
				<a href="/member/<sec:authentication property="principal.id" />/friends">My friends</a>
			</div>
			<div class="row blockNavigationRow">
				<a href="">My massages</a>
			</div>
			<div class="row blockNavigationRow">
				<a href="">My groups</a>
			</div>
		</div>
		<div class="row buttonsRow">
			<sec:authorize access="isAuthenticated()">
			<form:form action="/logout" method="POST">
				<button type="submit" class="btn btn-danger">Logout</button>
			</form:form>
			</sec:authorize>
		</div>
	</div>
	<div class="col-md-10">
		<c:forEach items="${friends.users}" var="user">
			<div class="row"><a href="/member/${user.id}">${user.name} ${user.surname}</a></div>
		</c:forEach>
	</div>
</div>
	