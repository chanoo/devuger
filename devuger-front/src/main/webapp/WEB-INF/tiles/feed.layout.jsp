<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="compress" uri="http://htmlcompressor.googlecode.com/taglib/compressor" %>
<!DOCTYPE html>
<html lang="ko">
<head>
	<tiles:insertAttribute name="layout.head" />
</head>
<body>
<compress:html>
<!--[if lt IE 8]>
<script type="text/javascript">
if(confirm("WIMITT에서 더 나은 쇼핑을 경험하기에 고객님은 10년된 브라우저를 사용하고 계십니다.\n최신 브라우저로 업데이트 하시면 더 나은 재미와 쇼핑을 즐길수 있습니다.\n\n업데이트 하러가시겠습니다?")) {
  window.location = 'http://windows.microsoft.com/ko-kr/internet-explorer/download-ie';
}
</script>
<![endif]-->
<tiles:insertAttribute name="layout.navbar" />

<div class="container">

	<c:if test="${og ne null}">
	<div class="row">
		<div class="col-lg-12">
			<div class="alert alert-dismissable alert-${og.result}">
			  <button type="button" class="close" data-dismiss="alert">×</button>
			  ${og.message}
			</div>
		</div>
	</div>
	</c:if>		
	
	<div class="row">
		<div class="col-lg-12">

<div class="container">
	<div class="row">
			<div class="col-lg-2">
				<div class="text-right float" data-spy="affix" data-offset-top="60">

					<dl>
						<dt>그룹</dt>
						<dd><a href="#">아이폰</a></dd>
						<dd><a href="#">안드로이드</a></dd>
						<dd><a href="#">프론트엔드</a></dd>
						<dd><a href="#">백엔드</a></dd>
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
			</div>
	
			<div class="col-lg-6">
				<div class="row">
					<div class="col-lg-12">

						<div class="panel panel-default">
						  <div class="panel-body animated">
						  	<form:form cssClass="form-horizontal" action="${contextPath}/feeds/add.json" id="feed-form">
							    <div class="form-group">
							      <div class="col-lg-12">
								  		<textarea name="message" placeholder="지금 하고 싶은 이야기나 공유하고 싶은 정보를 적어주세요!" rows="2" cols="200" class="form-control animated"></textarea>
								  	</div>
						  		</div>
						  		<div id="source">
						  		</div>
									<div class="form-group">
										<div class="col-lg-7">
											<a href="javascript:void(0);" id="add-file" class="btn btn-primary btn-xs">+ 파일</a>
											<a href="javascript:void(0);" id="add-code" class="btn btn-primary btn-xs">+ 코드</a>
										</div>
										<div class="col-lg-5 text-right">
											<button type="reset" class="btn btn-link btn-xs">취소</button>
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
				<div id="feeds">
					<tiles:insertAttribute name="layout.content" />
				</div>
				<!-- 피드 리스트 끝 -->
			</div>
			<div class="col-lg-4">
				<div class="float" data-spy="affix" data-offset-top="60">			
			
					<div class="panel panel-default">
					  <div class="panel-body">
						  <span>
						  	개발 진행중인 웹사이트입니다.<br/>
						  	<br/>
								2014년 9월 말, 사무실에 앉아서 계속 한가지 일만 하다보니 재미 없어서 또 다른 뭔가를 찾다가 SNS 프로젝트를 시작합니다!<br/>
								<br/>
								성능보다는 자바로 할수 있는 최대한 빠르고 쉬운 개발 방법을 택했습니다. 더 쉬운 방법을 알고 계시다면 공유 해주세요~<br/>
								<br/>
								github를 통해 사이트 전체 소스코드를 받을 수 있습니다.<br/>
								<br/>
							</span>
							<hr/>
							<div class="well">
	
						  	<h3>To do</h3>
						  	<ol>
						  		<li>회원 수정/탈퇴</li>
						  		<li>페이스북 로그인/공유</li>
						  		<li>피드 수정/삭제</li>
						  		<li>팔로워/팔로윙 추가/삭제</li>
						  	</ol>
	
							</div>
	
					  </div>
					</div>

				</div>
			</div>
	</div>
</div>
<script type="text/javascript">

$("#add-file").click(function() {
  
  alert("파일 추가");
  
});

$("#add-code").click(function() {

  var html = "";
	html += '<div class="well">';
	html += '  <div class="form-group">';
	html += '    <div class="col-lg-12">';
	html += '  		<textarea name="source.code" placeholder="코드를 입력해주세요." rows="4" cols="200" class="form-control animated"></textarea>';
	html += '  	</div>';
	html += '	</div>';
	html += '  <div class="form-group">';
	html += '    <div class="col-lg-12">';
	html += '  		<input type="text" name="source.comment" placeholder="코드 코멘트" class="form-control"></textarea>';
	html += '  	</div>';
	html += '	</div>';
	html += ' <div class="text-right"><button type="button" class="btn btn-link btn-xs remove-code">삭제</button></div>';
	html += '</div>';

	$(html).appendTo("#source");
	$('textarea').autosize();
});

$("#source").on("click", ".remove-code", function() {
  $(this).parent().parent().remove();
});

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

var currentPage = 1;
var isLastPage = false;
var isLoad = false;

$(function() {

  $('.float').affix();
	$('textarea').autosize();

  $('div[data-spy="affix"]').each(function() {
    $(this).width($(this).parent().width());
  });
  
  $("#feeds").autolink();
  $("#feeds .timeago").timeago();
  $("#feeds pre code").each(function(i, block) {
    hljs.highlightBlock(block);
  });
  
  $("#feeds pre").readmore({
    speed: 75,
    heightMargin: 100,
    moreLink: '<a href="javascript:void(0);" class="btn-link btn-xs">더보기</a>',
		lessLink: '<a href="javascript:void(0);" class="btn-link btn-xs">닫기</a>'
  });

  $('.panel-google-plus > .panel-footer > .input-placeholder, .panel-google-plus > .panel-google-plus-comment > .panel-google-plus-textarea > button[type="reset"]').on('click', function(event) {

    var $panel = $(this).closest('.panel-google-plus');
    $comment = $panel.find('.panel-google-plus-comment');

    $comment.find('.btn:first-child').addClass('disabled');
    $comment.find('textarea').val('');

    $panel.toggleClass('panel-google-plus-show-comment');

    if ($panel.hasClass('panel-google-plus-show-comment')) {
      $comment.find('textarea').focus();
    }
  });

	$('.panel-google-plus-comment > .panel-google-plus-textarea > textarea').on('keyup', function(event) {
    var $comment = $(this).closest('.panel-google-plus-comment');

    $comment.find('button[type="submit"]').addClass('disabled');
    if ($(this).val().length >= 1) {
      $comment.find('button[type="submit"]').removeClass('disabled');
    }
  });

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
</script>
<div id="temp"></div>

		</div>
	</div>

	<footer>

		<div class="row">
			<div class="col-lg-12">
				<hr/>
				<div class="pull-left">
					<p><small>© Copyright 2014 Devuger - All rights reserved.</small></p>
				</div>

			</div>
		</div>

	</footer>

</div>
</compress:html>
<script src="${contextPath}/resources/js/jquery.autolink.js"></script>
<script src="${contextPath}/resources/js/jquery.autosize.min.js"></script>
<script src="${contextPath}/resources/js/timeago/jquery.timeago.js"></script>
<script src="${contextPath}/resources/js/timeago/locales/jquery.timeago.ko.js"></script>
<script src="${contextPath}/resources/js/readmore.min.js"></script>
<script src="${contextPath}/resources/style/bootstrap.min.js"></script>
<script src="${contextPath}/resources/js/common.js"></script>
<script type="text/javascript"> 
//Just to fix IE issues when console isn't defined, only used for the demo - not required for the slider
if (typeof console === "undefined" || typeof console.log === "undefined") {
    console = { log: function(){} };
}
</script> 
</body>
</html>