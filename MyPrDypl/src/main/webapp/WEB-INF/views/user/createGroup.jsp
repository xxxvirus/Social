<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div class="container-fluid">
<div class="col-md-12"></div>
<div class="row">
	<div class="col-md-3"></div>
	<div class="col-md-7 col-sm-12 col-xs-12">
		<form:form class="form-horizontal" action="/createGroup"
			method="POST" modelAttribute="group">
			<div class="form-group">
				<label for="nameOfG" class="col-sm-2 control-label">Name</label>
				<div class="col-sm-7">
					<form:input class="form-control" path="nameOfG" id="nameOfG" />
				</div>
			</div>
			<div class="form-group">
				<label for="aboutG" class="col-sm-2 control-label">About</label>
				<div class="col-sm-7">
					<form:textarea class="form-control" path="aboutG" id="aboutG" rows="5"/>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-7">
					<button type="submit" class="btn btn-success btn-block">Create</button>
				</div>
			</div>
		</form:form>
	</div>
</div>
</div>