<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<div class="row">

	<div class="col-xs-6 col-xs-offset-3">
		<div class="panel panel-primary">
			<div class="panel-body">
		
				<a href="${contextPath}/fb/signin?redirect=${redirect}" target="_top" class="btn btn-facebook btn-block">
					<span class="fa fa-facebook-square fa-lg"></span>&nbsp;&nbsp;페이스북 회원가입
				</a>
				<br/>
				<hr/>
				<br/>
				<c:if test="${message ne null}">
					<div class="alert alert-dismissable alert-danger">
						<button type="button" class="close" data-dismiss="alert">×</button>
						${message}
					</div>
				</c:if>
				<div class="well bs-component">
					<form:form id="signup-form" modelAttribute="user" action="${contextPath}/users/signup" method="post" class="form-horizontal">
						<input type="hidden" name="redirect" value="${redirect}" />
						<fieldset>
							<div class="form-group">
								<form:label path="email" cssClass="col-xs-3 control-label">이메일</form:label>
								<div class="col-xs-9">
									<form:input path="email" class="form-control" data-validation="email" />
									<small><form:errors path="email" cssClass="error"></form:errors></small>
								</div>
							</div>
							<div class="form-group">
								<form:label path="username" cssClass="col-xs-3 control-label">사용자명</form:label>
								<div class="col-xs-9">
									<form:input path="username" class="form-control" />
									<small><form:errors path="username" cssClass="error"></form:errors></small>
								</div>
							</div>
							<div class="form-group">
								<form:label path="hashedPassword" cssClass="col-xs-3 control-label">비밀번호</form:label>
								<div class="col-xs-9">
									<form:password path="hashedPassword" class="form-control" />
									<small><form:errors path="hashedPassword" cssClass="error"></form:errors></small>
								</div>
							</div>
							<div class="form-group">
								<form:label path="hashedPasswordConfirm" cssClass="col-xs-3 control-label">비밀번호 확인</form:label>
								<div class="col-xs-9">
									<form:password path="hashedPasswordConfirm" class="form-control"/>
									<small><form:errors path="hashedPasswordConfirm" cssClass="error"></form:errors></small>
								</div>
							</div>
							<br/>
							<div class="form-group">
								<div class="col-xs-9 col-xs-offset-3">
									<form:checkbox path="serviceTerms" id="serviceTerms"></form:checkbox>
									<form:label path="serviceTerms" cssClass="control-label"><a href="${contextPath}/terms/service" target="_blank">서비스이용약관</a>에 대해 동의합니다.</form:label>
									<div>
										<small><form:errors path="serviceTerms" cssClass="error"></form:errors></small>
									</div>
								</div>
							</div>
							<div class="form-group">
								<div class="col-xs-9 col-xs-offset-3">
									<form:checkbox path="userInfoTerms" id="userInfoTerms"></form:checkbox>
									<form:label path="userInfoTerms" cssClass="control-label"><a href="${contextPath}/terms/service" target="_blank">개인정보취급방침</a>에 대해 동의합니다.</form:label>
									<div>
										<small><form:errors path="userInfoTerms" cssClass="error"></form:errors></small>
									</div>
								</div>
							</div>
							<hr/>
							<form:button class="btn btn-primary btn-block">이메일로 회원가입</form:button>
						</fieldset>
					</form:form>

				</div>
			</div>
		</div>

	</div>
</div>