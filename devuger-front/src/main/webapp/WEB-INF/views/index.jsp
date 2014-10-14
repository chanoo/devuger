<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<jsp:scriptlet>
	pageContext.setAttribute("crlf", "\n");
</jsp:scriptlet>
<div class="container">
	<div class="row">
			<div class="col-xs-5">
				<div class="row">
					<div class="col-xs-12">

				<div class="panel panel-default">
				  <div class="panel-body">
				  	<form:form cssClass="form-horizontal">
					    <div class="form-group">
					      <div class="col-xs-12">
						  		<textarea rows="3" cols="200" class="form-control"></textarea>
						  	</div>
				  		</div>
							<div class="form-group">
								<div class="col-lg-10 col-lg-offset-2 text-right">
									<button class="btn btn-link btn-xs">취소</button>
									<button type="submit" class="btn btn-success btn-xs">게시</button>
								</div>
							</div>
							</form:form>
				  	<hr/>
						<div class="btn-group btn-group-justified">
						  <a href="#" class="btn btn-primary btn-xs">FEED</a>
						  <a href="#" class="btn btn-primary btn-xs">LIKE</a>
						  <a href="#" class="btn btn-primary btn-xs">TAG</a>
						</div>
					</div>
				</div>

					</div>				
				</div>
				<!-- 피드 리스트 시작 -->
				<div class="feeds">
				<c:forEach items="${feeds}" var="feed">
					<div class="row">
						<div class="col-xs-12">
							<div class="[ panel panel-default ] panel-google-plus">
								<div class="dropdown">
									<span class="dropdown-toggle" type="button" data-toggle="dropdown">
										<span class="[ glyphicon glyphicon-chevron-down ]"></span>
									</span>
									<ul class="dropdown-menu" role="menu">
										<li role="presentation"><a role="menuitem" tabindex="-1" href="#">Action</a></li>
										<li role="presentation"><a role="menuitem" tabindex="-1" href="#">Another action</a></li>
										<li role="presentation"><a role="menuitem" tabindex="-1" href="#">Something else here</a></li>
										<li role="presentation" class="divider"></li>
										<li role="presentation"><a role="menuitem" tabindex="-1" href="#">Separated link</a></li>
									</ul>
								</div>
								<div class="panel-google-plus-tags">
									<ul>
										<li>#Millennials</li>
										<li>#Generation</li>
									</ul>
								</div>
								<div class="panel-heading">
									<img class="[ img-circle pull-left ]"
										src="https://lh3.googleusercontent.com/-CxXg7_7ylq4/AAAAAAAAAAI/AAAAAAAAAQ8/LhCIKQC5Aq4/s46-c-k-no/photo.jpg"
										alt="Mouse0270" />
									<h3>${feed.createdBy.username}</h3>
									<h5>
										<span>Shared publicly</span> - <span><fmt:formatDate pattern="M월 d일, yyyy" value="${feed.createdOn}" /></span>
									</h5>
								</div>
								<div class="panel-body">
									<p>${fn:replace(feed.message, crlf, "<br/>")}</p>
									<hr/>
									<dl class="clear">
									<c:forEach items="${feed.comments}" var="comment">
										<dd style="margin-bottom:4px;">
											<span class="label label-default">${comment.createdBy.username}</span>
											${comment.content}, <fmt:formatDate pattern="MM/dd/yyyy" value="${comment.createdOn}" />
											<a href="${contextPath}/comments/${comment.id}/remove.json" class="remove">×</a>
										</dd>
									</c:forEach>
									</dl>
								</div>
			
								<div class="panel-footer">
									<div class="input-placeholder">Add a comment...</div>
								</div>
			
								<div class="panel-google-plus-comment">
									<img class="img-circle" src="https://lh3.googleusercontent.com/uFp_tsTJboUY7kue5XAsGA=s46" alt="User Image" />
									<form:form modelAttribute="comment" action="${contextPath}/comments/add.json" method="post" class="panel-google-plus-textarea">
										<form:hidden path="feed.id" value="${feed.id}"/>
										<form:textarea path="content" rows="3"/>
										<button type="submit" class="[ btn btn-success disabled ]">Post comment</button>
										<button type="reset" class="[ btn btn-default ]">Cancel</button>
									</form:form>
									<div class="clearfix"></div>
								</div>
								
							</div>
						</div>
					</div>
				</c:forEach>
				</div>
				<!-- 피드 리스트 끝 -->
			</div>
			<div class="col-xs-7" id="message">
			
				<div class="panel panel-default">
				  <div class="panel-body">
Hello~!<br/>
<br/>
2014년 9월 말, 지금 나는 위한 개발이 아닌 남을 위한 서비스 개발에 몰두 하고 있다.<br/>
내 자신에게 필요한 서비스를 만들어 본적이 없다.<br/>
그래서 시작 했습니다!<br/>
<br/>
개발자들의 놀이터가 될수 있는 SNS을 만들어 보겠습니다!<br/>
<br/>
github를 통해 사이트 전체 소스코드를 받을 수 있습니다.<br/>
				  </div>
				</div>
				
				<div class="panel panel-default">
				  <div class="panel-body">
				  	<h3>To do</h3>
				  	<ol>
				  		<li>회원 가입/로그인/수정/탈퇴</li>
				  		<li>페이스북 로그인/공유</li>
				  		<li>피드 등록/삭제</li>
				  		<li>좋아요 추가/취소</li>
				  		<li>팔로워/팔로윙 추가/삭제</li>
				  	</ol>
				  </div>
				</div>

			</div>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function(){
  
  $(".remove").click(function (e) {
  	e.preventDefault();
    if(confirm("삭제하시겠습니까?")) {

  		var href = $(this).attr('href');
  		$.ajax({
  			url : href,
  			type : "get",
  			success : function(json, textStatus) {
  				if(isSuccess(json)) {
  					document.location.reload();
  				}
  			}
  		});

    }
  	
  	return false;
  });
  
});

$(window).scroll(function() {
  $('#message').css('top', $(this).scrollTop() + "px");
});

$(function() {

  $(".feeds").autolink();

  $(
      '.panel-google-plus > .panel-footer > .input-placeholder, .panel-google-plus > .panel-google-plus-comment > .panel-google-plus-textarea > button[type="reset"]')
      .on('click', function(event) {
        var $panel = $(this).closest('.panel-google-plus');
        $comment = $panel.find('.panel-google-plus-comment');

        $comment.find('.btn:first-child').addClass('disabled');
        $comment.find('textarea').val('');

        $panel.toggleClass('panel-google-plus-show-comment');

        if ($panel.hasClass('panel-google-plus-show-comment')) {
          $comment.find('textarea').focus();
        }
      });
  $('.panel-google-plus-comment > .panel-google-plus-textarea > textarea')
      .on('keyup', function(event) {
        var $comment = $(this).closest('.panel-google-plus-comment');

        $comment.find('button[type="submit"]').addClass('disabled');
        if ($(this).val().length >= 1) {
          $comment.find('button[type="submit"]').removeClass('disabled');
        }
      });
});
</script>