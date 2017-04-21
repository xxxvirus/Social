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
.regBlock {
    margin: 30px 0 20px;
    padding: 25px;
	background-color:#FF7F50;
}
.textRegBlock{
	margin: -4px 0 0;
    font-size: 20px;
    text-align: center;
	padding: 5px 0 21px;
}
.textPageBlock{
	margin: -4px 0 0;
    text-align: center;
	padding: 5px 0 21px;
}
.page_block{
	background: #edeef0;
}
</style>
<div class="row view">
	<div class="col-md-12 page_block">
	<sec:authorize access="isAuthenticated()">
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
				<form:form action="/logout" method="POST">
					<button type="submit" class="btn btn-danger">Logout</button>
				</form:form>
			</div>
			
		</div>
		</sec:authorize>
		<div class="col-md-10" align="center">	
			<div class="row">
				<div class="col-md-6">
				<sec:authorize access="!isAuthenticated()">
					<form:form action="/login" method="GET">
						<button type="submit" class="btn btn-danger">Login</button>
					</form:form>
				</sec:authorize>
				</div>
				<div class="col-md-6">
				<sec:authorize access="!isAuthenticated()">
					<form:form action="/registration" method="GET">
						<button type="submit" class="btn btn-danger">Sign up</button>
					</form:form>
				</sec:authorize>
				</div>
			</div>
			<h1 class="textPageBlock">Welcome to ChiperBook</h1>
			<img src="/resources/img/log.jpg">
		</div>
	</div>
</div>