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
			<a class="btn btn-danger btn-xs" href="/member/${users.id}/addToFriend">add</a>
		</div>
	</div>
	<div class="col-md-10">
		<div class="row">
			<div class="col-md-4 block">
				<div class="row photo"><img src="/resources/img/poster.jpg"></div>
			</div>
			<div class="col-md-8 blockInfo">
				<div class="row info"><h2>${users.name} ${users.surname}</h2></div>
				<div class="row">
					<div class="col-md-3 infoRow">Country:</div> <div class="col-md-9">${users.country}</div>
					<div class="col-md-3 infoRow">City:</div> <div class="col-md-9">${users.city}</div>
					<div class="col-md-3 infoRow">Email:</div> <div class="col-md-9">${users.email}</div>
					<div class="col-md-3 infoRow">Phone:</div> <div class="col-md-9">${users.phoneNumber}</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
				<sec:authentication property="principal.id" var="userId"/>
				<c:if test="${userId == users.id}">
				<div class="row addPost">
					<form:form class="form-horizontal" action="/member/${users.id}" method="POST" modelAttribute="post">
						<div class="form-group">
    					<div class="col-sm-10">
      						<form:textarea path="text" rows="2" cols="117" />
    					</div>
    					</div>
    					<div class="form-group">
    					<div class="col-sm-10">
      						<input name="key" class="form-control"/>
    					</div>
    					</div>
    					<div class="form-group">
    					<div class="col-sm-2">
      						<button type="submit" class="btn btn-success btn-block">Add Post</button>
    					</div>
    					</div>
					</form:form>
				</div>
				</c:if>
				<c:forEach items="${posts}" var="post">
					<div class="row post">
						<div class="col-md-10 postText"><p>${post.text}</p></div>
						<sec:authentication property="principal.id" var="userId"/>
						<c:if test="${userId == users.id}">
						<div class="col-md-2 postButton">
							<a class="btn btn-danger btn-xs" href="/member/${users.id}/delete/${post.id}">delete</a>
						</div>
						</c:if>
					</div>
				</c:forEach>
			</div>
			
		</div>
	</div>
</div>
