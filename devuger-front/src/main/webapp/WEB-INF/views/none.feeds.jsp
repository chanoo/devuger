<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<jsp:scriptlet>
	pageContext.setAttribute("crlf", "\n");
</jsp:scriptlet>
<c:forEach items="${feeds.content}" var="feed">
	<div class="row" id="feed-${feed.id}">
		<div class="col-lg-12">
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
						<span class="timeago" title="${feed.createdOn}">${feed.createdOn}</span>
					</h5>
				</div>
				<div class="panel-body">
					<p>${fn:replace(feed.message, crlf, "<br/>")}</p>
					<c:forEach items="${feed.sources}" var="source">
					<span>${source.comment}</span>
					<pre>
						<code>
${source.codeEscape}
						</code>
					</pre>
					</c:forEach>
					<br/>
					<p>
						<c:if test="${fn:length(feed.likes) ne 0}">
							<c:if test="${feed.liked eq true}">
								<a href="${contextPath}/feeds/${feed.id}/unlike.json">좋아요 취소</a>
							</c:if>
							<c:if test="${feed.liked eq false}">
								<a href="${contextPath}/feeds/${feed.id}/like.json">좋아요</a>
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
							${comment.content}, <span class="timeago" title="${comment.createdOn}">${comment.createdOn}</span>
							<a href="${contextPath}/comments/${comment.id}/remove.json" class="remove">×</a>
						</dd>
					</c:forEach>
					</dl>
				</div>

				<div class="panel-footer">
					<div class="input-placeholder">코멘트 작성...</div>
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