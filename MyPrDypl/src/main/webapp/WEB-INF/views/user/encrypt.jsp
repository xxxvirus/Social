<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
	<style>
	.leftText{
	}
	</style>
	<div class="row">
	<div class="col-md-3 col-md-offset-5">
	<ul class="nav nav-pills">
            <li class="active"><a href="/encrypt">Encrypt</a></li>
            <li><a href="/decrypt">Decrypt</a></li>
    </ul>
    </div>
    </div>
	<div class="col-md-2"></div>
	<div class="col-md-8">
	<div class="row"><h3 class="text-center">Enter Key</h3></div>
	<form class="form-horizontal" action="/encrypt/enc" method="GET">
		<div class="form-group">
    		<div class="col-sm-12">
				<input type="text" name="key" class="form-control"/>
			</div>
		</div>
		<div class="form-group">
    		<div class="col-sm-5">
    			<div class="row">
    			<h4 class="text-center">Plain Text</h4>
    			</div>
    			<div class="row">
    			<textarea class="form-control" id="value" name="value" rows="5"></textarea>
    			</div>
    		</div>
    		<div class="col-sm-2">
				<button type="submit" class="btn btn-success btn-xs btn-block">Encrypt</button>
			</div>
			<div class="col-sm-5 leftText">
				<div class="row">
    			<h4 class="text-center">Result Text</h4>
    			</div>
    			<div class="row">
    			<textarea class="form-control" id="value" name="value" rows="5">${text}</textarea>
    			</div>
    		</div>
		</div>
	</form>
			
    </div>
