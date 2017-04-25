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
	width: 180px;
	height:252px;
}
.blockButton{
	padding: 15px;
	margin-top:-15px;
}
.blockInfo{
	width: 825px;
	height:180px;
	border-radius: 2px;
	position: relative;
    background: #fff;
}
.info{
	padding: 15px;
}
.infoRow{
	color: #828282;
}
.blockMembers{
	background: #fff;
}
.blockMemebrs-userName{
	margin-left: 5px;
}
.post{
	background: #fff;
	width: 825px;
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
				<a href="">My messages</a>
			</div>
			<div class="row blockNavigationRow">
				<a href="/member/<sec:authentication property="principal.id" />/mygroups">My groups</a>
			</div>
			<div class="row blockNavigationRow">
				<a href="/decrypt">AES Encryptor</a>
			</div>
		</div>
	</div>
	<div class="col-md-8">
		<div class="row blockInfo">
		<div class="col-md-12">
			<div class="row info"><h2>${groupName.nameOfG}</h2></div>
			<hr>
			<div class="row"><p>${groupName.aboutG}</p></div>
		</div>
		</div>
		<div class="row">
			<div class="col-md-12">
				<div class="row addPost">
					<form:form class="form-horizontal" action="/group/${groupName.id}" method="POST" modelAttribute="post">
						<div class="form-group">
    					<div class="col-sm-12">
      						<form:textarea class="form-control" path="text" rows="3"/>
    					</div>
    					</div>
    					<div class="form-group">
    					<div class="col-sm-2">
      						<button type="submit" class="btn btn-success btn-block">Add Post</button>
    					</div>
    					</div>
					</form:form>
				</div>
				<c:forEach items="${posts}" var="post">
					<div class="row post">
						<div class="col-md-12"><p>Author: <a href="/member/${post.user.id}">${post.user.name} ${post.user.surname}</a></p></div>
						<div class="col-md-10 postText"><p>${post.text}</p></div>
						<div class="col-md-1 postButton">
							<a class="btn btn-danger btn-xs" href="/group/${groupName.id}/dec/${post.id}">dec</a>
						</div>
						<sec:authentication property="principal.id" var="userId"/>
						<c:if test="${userId == post.user.id}">
						<div class="col-md-1 postButton">
							<a class="btn btn-danger btn-xs" href="/group/${groupName.id}/delete/${post.id}">delete</a>
						</div>
						</c:if>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
	<div class="col-md-2 blockMembers">
		<div class="row">
			<a class="btn btn-success btn btn-block" href="/group/${groupName.id}/follow">Follow</a>
		</div>
		<div class="row">
			<a class="btn btn-danger btn btn-block" href="/group/${groupName.id}/exit">Exit</a>
		</div>
		<div class="row"><h2>Members</h2></div>
		<c:forEach items="${group.users}" var="user">
			<div class="row blockMemebrs-userName"><a href="/member/${user.id}">${user.name} ${user.surname}</a></div>
		</c:forEach>
	</div>

</div>
	
	
	