<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<style>
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
<div class="row">
	<div class="col-md-12 page_block">	
		<div class="col-md-8" align="center">	
			<h1 class="textPageBlock">Welcome to ChiperBook</h1>
			<img src="log.jpg">
			<div class="col-md-1 userButton">
				<sec:authorize access="!isAuthenticated()">
					<form:form action="/login" method="GET">
						<button type="submit" class="btn btn-danger">Login</button>
					</form:form>
				</sec:authorize>
			</div>
			<div class="col-md-1 userButton">
				<sec:authorize access="!isAuthenticated()">
					<form:form action="/registration" method="GET">
						<button type="submit" class="btn btn-danger">Sign up</button>
					</form:form>
				</sec:authorize>
				<sec:authorize access="isAuthenticated()">
					<form:form action="/logout" method="POST">
						<button type="submit" class="btn btn-danger">Logout</button>
					</form:form>
				</sec:authorize>
			</div>
		</div>
		<div class="col-md-4">
			<div class="col-md-8 regBlock">
				<form:form class="form-horizontal" action="/"	method="POST">
					<div class="form-group">
						<label for="email" class="sr-only">Email</label>
						<input class="form-control" name="login" id="login" placeholder="email">
					</div>
					<div class="form-group">
						<label for="password" class="sr-only">Password</label>
						<input class="form-control" type="password" name="password" id="password" placeholder="Password">
					</div>
					<div class="form-group">
  							<div class="checkbox">
  								<label>
  								<input name="remember-me" type="checkbox"> Remember me
  								</label>
  							</div>
  					</div>
  					<div class="form-group">
						<button class="btn btn-primary btn-block" type="submit">Log in</button>
					</div>
				</form:form>
			</div>
							<div class="col-md-8 regBlock">
								<h2 class="textRegBlock">For the first time on CB?</h2>
								<form>
									<div class="form-group">
										<label for="login" class="sr-only">Name</label>
										<input class="form-control" id="login" placeholder="Your first name">
									</div>
									<div class="form-group">
										<label for="login" class="sr-only">Last Name</label>
										<input class="form-control" id="login" placeholder="Your last first name">
									</div>
									<div class="form-group">
										<label for="login" class="sr-only">Email</label>
										<input class="form-control" id="login" placeholder="Your email">
									</div>
									<div class="form-group">
										<label for="pass" class="sr-only">Password</label>
										<input class="form-control" id="pass" type="password" placeholder="Password">
									</div>
									<button class="btn btn-success btn-block" type="submit">Sing up</button>
								</form>
							</div>
						</div>
					</div>
					</div>