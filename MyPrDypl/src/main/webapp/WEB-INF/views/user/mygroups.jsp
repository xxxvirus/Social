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
	width: 845px;
	padding:15px;
	margin-top:10px;
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
				<a href="">My messages</a>
			</div>
			<div class="row blockNavigationRow">
				<a href="">My groups</a>
			</div>
			<div class="row blockNavigationRow">
				<a href="/decrypt">AES Encryptor</a>
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
		<div class="row"><a href="/createGroup"><button type="button" class="btn btn-danger btn-xs">Create Group</button></a></div>
		<c:forEach items="${userGroups.groups}" var="group">
			<div class="row block">
				<div class="col-md-11">
					<a href="/group/${group.id}">${group.nameOfG}</a>
				</div>
				<sec:authentication property="principal.id" var="userId"/>
				<c:if test="${userId == userPage.id}">
				<div class="col-md-1">
					<a href="/member/${userId}/mygroups/exit/${group.id}"><button type="button" class="btn btn-danger btn-xs">Exit</button></a>
				</div>
				</c:if>
			</div>
		</c:forEach>
	</div>
</div>