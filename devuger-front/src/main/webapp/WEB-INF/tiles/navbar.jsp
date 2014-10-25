<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<div class="navbar navbar-default navbar-fixed-top">
  <div class="container">
    <div class="navbar-header">
      <a href="${contextPath}/" class="navbar-brand">Devuger</a>
    </div>

		<ul class="nav navbar-nav navbar-right">
			<c:if test="${user eq null}">
				<li><a href="#" data-toggle="modal" data-target="#signin-modal">로그인</a></li>		
			</c:if>
			<c:if test="${user ne null}">
				<li><a href="${contextPath}/users/signout">로그아웃</a></li>		
			</c:if>
				<li><a href="https://github.com/chanoo/devuger" target="_blank">GitHub</a></li>
		</ul>
	</div>
</div>
<!-- Modal -->
<div class="modal fade" id="signin-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-body">
      
				<a href="${contextPath}/fb/signin?redirect=" target="_top" class="btn btn-facebook btn-block">
					<span class="fa fa-facebook-square fa-lg"></span>&nbsp;&nbsp;페이스북 로그인
				</a>

				<br/>
				<hr/>
				<br/>
				<div id="messages">
					<div class="alert alert-dismissable alert-danger" id="signin-message" style="display:none;">
						<button type="button" class="close" data-dismiss="alert">×</button>
						<span></span>
					</div>				
				</div>
				
				<div class="well bs-component">
					<form:form id="signin-form" action="${contextPath}/users/signin.json" method="post" class="form-horizontal">
						<fieldset>

							<div class="form-group">
								<label for="email" class="col-lg-3 control-label">이메일</label>
								<div class="col-lg-9">
									<input type="text" name="email" id="email" class="form-control" />
								</div>
							</div>
							<div class="form-group">
								<label for="hashedPassword" class="col-lg-3 control-label">비밀번호</label>
								<div class="col-lg-9">
									<input type="password" name="hashedPassword" id="hashedPassword" class="form-control" />
								</div>
							</div>
							<hr/>
							<div class="form-group">
								<div class="col-lg-9 col-lg-offset-3">
									<button class="btn btn-primary btn-block">이메일로 로그인</button>
								</div>
							</div>

						</fieldset>
					</form:form>
				</div>
				<a href="${contextPath}/users/find/password" class="btn btn-link">비밀번호를 까먹었어요.</a><br/>
				<a href="${contextPath}/users/signup?redirect=" class="btn btn-link">회원가입</a><br/>
				
      </div>
    </div>
  </div>
</div>
<script>
$("#signin-form").submit(function() {
	
	var frm = $(this).serialize();

	$.ajax({
		url : "${contextPath}/users/signin.json",
		type : "post",
		data : frm,
		success : function(json, textStatus) {
			if(json.result != "success") {

			  var $signinmessage = $("#signin-message").clone();
			  $("span", $signinmessage).text(json.message);
			  $($signinmessage).css("display", "block");
			  $($signinmessage).appendTo("#messages");

			} else {

			  $('#signin-modal').modal('hide')
			  $('#signin-modal').on("hidden.bs.modal", function () {
			    // do something…
			    document.location.reload();
			  });

			}
		}
	});

	return false;
});
</script>