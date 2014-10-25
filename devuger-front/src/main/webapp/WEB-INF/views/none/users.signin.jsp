<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<div class="row">

	<div class="col-lg-6 col-lg-offset-3">
		<div class="panel panel-primary">
			<div class="panel-body">

				<a href="${contextPath}/fb/signin?redirect=${user.redirect}" target="_top" class="btn btn-facebook btn-block">
					<span class="fa fa-facebook-square fa-lg"></span>&nbsp;&nbsp;페이스북 로그인
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
					<form:form id="signin-form" modelAttribute="user" action="${contextPath}/users/signin" method="post" class="form-horizontal">
						<form:hidden path="redirect" />
						<fieldset>
							<div class="form-group">
								<form:label path="email" cssClass="col-lg-3 control-label">이메일</form:label>
								<div class="col-lg-9">
									<form:input path="email" class="form-control" />
									<small><form:errors path="email" cssClass="error" /></small>
								</div>
							</div>
							<div class="form-group">
								<form:label path="hashedPassword" cssClass="col-lg-3 control-label">비밀번호</form:label>
								<div class="col-lg-9">
									<form:password path="hashedPassword" class="form-control"/>
									<small><form:errors path="hashedPassword" cssClass="error" /></small>
								</div>
							</div>
							<hr/>
							<div class="form-group">
								<div class="col-lg-9 col-lg-offset-3">
									<form:button class="btn btn-primary btn-block">로그인</form:button>
								</div>
							</div>

						</fieldset>
					</form:form>
				</div>
				<a href="${contextPath}/users/find/password" class="btn btn-link">비밀번호를 까먹었어요.</a><br/>
				<a href="${contextPath}/users/signup?redirect=${user.redirect}" class="btn btn-link">회원가입</a><br/>
			
			</div>
		</div>
	</div>

</div>