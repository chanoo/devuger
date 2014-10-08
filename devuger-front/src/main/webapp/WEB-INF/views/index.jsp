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
	<div class="feeds">
	<c:forEach items="${feeds}" var="feed">
		<div class="row">
			<div class="col-xs-5">
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
</div>
<script>
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