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
			<div class="col-xs-2 text-right float">

				<dl>
					<dt>그룹</dt>
					<dd><a href="#">아이폰</a></dd>
					<dd><a href="#">안드로이드</a></dd>
					<dd><a href="#">자바</a></dd>
					<dd><a href="#">구인구직</a></dd>
				</dl>

				<dl>
					<dt>관계</dt>
					<dd><a href="#">팔로우</a></dd>
					<dd><a href="#">팔로윙</a></dd>
					<dd><a href="#">친구</a></dd>
				</dl>

				<dl>
					<dt>필터</dt>
					<dd><a href="#">피드</a></dd>
					<dd><a href="#">코멘트</a></dd>
					<dd><a href="#">좋아요</a></dd>
					<dd><a href="#">그룹</a></dd>
				</dl>

			</div>
	
			<div class="col-xs-6">
				<div class="row">
					<div class="col-xs-12">

						<div class="panel panel-default">
						  <div class="panel-body">
						  	<form:form cssClass="form-horizontal" action="${contextPath}/feeds/add.json" id="feed-form">
							    <div class="form-group">
							      <div class="col-xs-12">
								  		<textarea name="message" placeholder="지금 하고 싶은 이야기나 공유하고 싶은 정보를 적어주세요!" rows="3" cols="200" class="form-control"></textarea>
								  	</div>
						  		</div>
									<div class="form-group">
										<div class="col-lg-10 col-lg-offset-2 text-right">
											<button class="btn btn-link btn-xs">취소</button>
											<button type="submit" class="btn btn-success btn-xs">게시</button>
										</div>
									</div>
									</form:form>
							</div>
						</div>

					</div>

				  <hr/>

				</div>

				<!-- 피드 리스트 시작 -->
				<div class="feeds">
				<c:forEach items="${feeds.content}" var="feed">
					<div class="row">
						<div class="col-xs-12">
							<div class="[ panel panel-default ] panel-google-plus">
								<div class="dropdown">
									<span class="dropdown-toggle" type="button" data-toggle="dropdown">
										<span class="[ glyphicon glyphicon-chevron-down ]"></span>
									</span>
									<ul class="dropdown-menu" role="menu">
										<li role="presentation"><a role="menuitem" tabindex="-1" href="${contextPath}/feeds/${feed.id}">이 게시물 주소복사</a></li>
										<li role="presentation"><a role="menuitem" tabindex="-1" href="#">이 게시물 신고</a></li>
										<c:if test="${feed.createdBy.id eq user.id}">
										<li role="presentation" class="divider"></li>
											<li role="presentation"><a role="menuitem" tabindex="-1" href="${contextPath}/feeds/${feed.id}/remove.json">삭제</a></li>
										</c:if>
									</ul>
								</div>
								<div class="panel-google-plus-tags">
									<ul>
										<li>안드로이드 개발</li>
										<li>#Generation</li>
									</ul>
								</div>
								<div class="panel-heading">
									<img class="[ img-circle pull-left ]"
										src="https://lh3.googleusercontent.com/-CxXg7_7ylq4/AAAAAAAAAAI/AAAAAAAAAQ8/LhCIKQC5Aq4/s46-c-k-no/photo.jpg"
										alt="Mouse0270" />
									<h3>${feed.createdBy.username}</h3>
									<h5>
										<span class="timeago" title="${feed.createdOn}">${feed.createdOn}</span>
									</h5>
								</div>
								<div class="panel-body">
									<p>${fn:replace(feed.message, crlf, "<br/>")}</p>
									<p>
										<c:if test="${fn:length(feed.likes) ne 0}">
												<c:if test="${feed.liked eq false}">
													<a href="${contextPath}/feeds/${feed.id}/like.json">좋아요</a>
												</c:if>
												<c:if test="${feed.liked eq true}">
													<a href="${contextPath}/feeds/${feed.id}/unlike.json">좋아요 취소</a>
												</c:if>												
											${fn:length(feed.likes)}명이 좋아해요.
										</c:if>
										<c:if test="${fn:length(feed.likes) eq 0}">
											제일 처음
											<a href="${contextPath}/feeds/${feed.id}/like.json">좋아요</a>
										</c:if>
									</p>
									<hr/>
									<dl class="clear">
									<c:forEach items="${feed.comments}" var="comment">
										<dd style="margin-bottom:4px;">
											<span class="label label-default">${comment.createdBy.username}</span>
											${comment.content} 
											<small class="timeago" title="${comment.createdOn}"></small><br/>
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
			<div class="col-xs-4 float">
			
				<div class="panel panel-default">
				  <div class="panel-body">
				  <span>
헬로 헬로 헬로우~~~!<br/>
<br/>
2014년 9월 말, 지금 나는 위한 개발이 아닌 남을 위한 서비스 개발에 몰두 하고 있다.<br/>
내 자신에게 필요한 서비스를 만들어 본적이 없다.<br/>
그래서 시작 했습니다!<br/>
<br/>
개발자들의 놀이터가 될수 있는 SNS을 만들어 보겠습니다!<br/>
기필코 다 만들어버리겠다!!!<br/>
<br/>
github를 통해 사이트 전체 소스코드를 받을 수 있습니다.<br/>
<br/>
</span>
<hr/>
						<div class="well">

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
</div>
<script type="text/javascript">
$("#feed-form").submit(function() {
  
	var frm = $(this).serialize();

	$.ajax({
		url : "${contextPath}/feeds/add.json",
		type : "post",
		data : frm,
		success : function(json, textStatus) {
			if(isSuccess(json)) {
				document.location.reload();
			}
		}
	});
  
	return false;
});

$(document).ready(function(){
  
  $(".timeago").timeago();

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
  $('.float').css('top', $(this).scrollTop() + "px");
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