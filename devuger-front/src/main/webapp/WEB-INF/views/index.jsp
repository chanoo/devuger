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
			<div class="col-xs-2">
				<div class="text-right float" data-spy="affix" data-offset-top="60">

					<dl>
						<dt>그룹</dt>
						<dd><a href="javascript:feeds(1, 1);">아이폰</a></dd>
						<dd><a href="javascript:feeds(2, 1);">안드로이드</a></dd>
						<dd><a href="javascript:feeds(3, 1);">자바</a></dd>
						<dd><a href="javascript:feeds(4, 1);">HTML/자바스크립트</a></dd>
						<dd><a href="javascript:feeds(5, 1);">백앤드서버</a></dd>
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
	
			<div class="col-xs-7">

				<div class="row">
					<div class="col-xs-12">

						<div class="panel panel-default">
						  <div class="panel-body animated">
						  	<form:form cssClass="form-horizontal" action="${contextPath}/feeds/add.json" id="feed-form">
						  		<input type="hidden" name="tag.id" id="tag.id" value="${tag.id}" />
							    <div class="form-group">
							      <div class="col-xs-12">
							      	<c:if test="${user eq null}">
									  		<textarea name="message" placeholder="로그인 후 작성 해주세요." disabled="disabled" rows="2" cols="200" class="form-control animated"></textarea>
							      	</c:if>
							      	<c:if test="${user ne null}">
									  		<textarea name="message" placeholder="지금 하고 싶은 이야기나 공유하고 싶은 정보를 적어주세요!" rows="2" cols="200" class="form-control animated"></textarea>
							      	</c:if>
								  	</div>
						  		</div>
						  		<div id="source">
						  		</div>
									<div class="form-group">
										<div class="col-xs-7">
											<a href="javascript:void(0);" id="add-file" class="btn btn-primary btn-xs">+ 파일</a>
											<a href="javascript:void(0);" id="add-code" class="btn btn-primary btn-xs">+ 코드</a>
										</div>
										<div class="col-xs-5 text-right">
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

				<div id="feeds">
					<div class="row google-ads">
						<div class="col-xs-12">
							<div class="panel panel-default">
								<div class="panel-body text-center">
									<ins class="adsbygoogle"
										style="display: inline-block; width: 468px; height: 60px"
										data-ad-client="ca-pub-3314889605614283" data-ad-slot="1330578984"></ins>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- 피드 리스트 끝 -->
			</div>
			<div class="col-xs-3">
				<div class="float" data-spy="affix" data-offset-top="60">			
			
					<div class="panel panel-default">
					  <div class="panel-body">
							<small>
						  	개발 진행중인 웹사이트입니다.<br/>
						  	<br/>
								2014년 9월 말, 사무실에 앉아서 계속 한가지 일만 하다보니 재미 없어서 또 다른 뭔가를 찾다가 SNS 프로젝트를 시작합니다!<br/>
								<br/>
								성능보다는 자바로 할수 있는 최대한 빠르고 쉬운 개발 방법을 택했습니다. 더 쉬운 방법을 알고 계시다면 공유 해주세요~<br/>
								<br/>
								github를 통해 사이트 전체 소스코드를 받을 수 있습니다.<br/>
								<br/>
							</small>	
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
	html += '    <div class="col-xs-12">';
	html += '  		<textarea name="source.code" placeholder="코드를 입력해주세요." rows="4" cols="200" class="form-control animated"></textarea>';
	html += '  	</div>';
	html += '	</div>';
	html += '  <div class="form-group">';
	html += '    <div class="col-xs-12">';
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

var currentTag = null;
var currentPage = 1;
var isLastPage = false;
var isLoad = false;

$(function() {

  feeds(currentTag, currentPage);
  $('.float').affix();
	$('textarea').autosize();

  $(window).scroll(function() {
    if($(window).scrollTop() >= $(document).height() - $(window).height() && isLastPage == false) {
      if (!isLastPage) {
        feeds(currentTag, currentPage);
      }
    }
  });
  
  $(document).on('click', '.panel-google-plus > .panel-footer > .input-placeholder, .panel-google-plus > .panel-google-plus-comment > .panel-google-plus-textarea > button[type="reset"]', function(event) {

    var $panel = $(this).closest('.panel-google-plus');
    $comment = $panel.find('.panel-google-plus-comment');
    $comment.find('.btn:first-child').addClass('disabled');
    $comment.find('textarea').val('');
    $panel.toggleClass('panel-google-plus-show-comment');
    if ($panel.hasClass('panel-google-plus-show-comment')) {
      $comment.find('textarea').focus();
    }
  });

	$(document).on('keyup', '.panel-google-plus-comment > .panel-google-plus-textarea > textarea', function(event) {

	  var $comment = $(this).closest('.panel-google-plus-comment');
    $comment.find('button[type="submit"]').addClass('disabled');
    if ($(this).val().length >= 1) {
      $comment.find('button[type="submit"]').removeClass('disabled');
    }
  });
  
  $('div[data-spy="affix"]').each(function() {
    $(this).width($(this).parent().width());
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

function feeds(tag, page) {

  this.currentTag = tag;
  this.currentPage = page;
  if (page == 1) {
    isLastPage = false;
    $("#feeds").html("");
    $("#tag\\.id").val(tag);
  }
  
  if (isLoad == true) {
    return; 
  } else {
    isLoad = true;
  }
  
  var feedUrl = "${contextPath}/feeds?layout=none&page={0}".format(page);
  if(tag != null)
    feedUrl = "${contextPath}/feeds?layout=none&tag.id={0}&page={1}".format(tag, page);

  $.ajax({
      url : feedUrl,
      type : "get",
    	dataType : "html",
      beforeSend: function( xhr ) {
        $('<div id="ajax-load-image" class="text-center"><img src="${contextPath}/resources/img/ajax-load.gif" /></div>').appendTo("#feeds");
      },
      success : function(result) {
        
        var html = result.trim();
        if(html.length == 0) {
          isLastPage = true;
        }

        initHtml(html);
        currentPage++;

        $("#ajax-load-image").remove();
        isLoad = false;
      }
    });
}

function initHtml(html) {
  
  $(html).appendTo("#temp");
  $("#temp").autolink();
  $("#temp .timeago").timeago();
  $("#temp pre code").each(function(i, block) {
    hljs.highlightBlock(block);
  });
  $('#temp .google-ads ins').each(function(){
    (adsbygoogle = window.adsbygoogle || []).push({});
	});
  html = $("#temp").html();
  $(html).appendTo("#feeds");  
  $("#temp").html("");

  $("#feeds pre").readmore({
    speed: 75,
    heightMargin: 100,
    moreLink: '<a href="javascript:void(0);" class="btn-link btn-xs">더보기</a>',
		lessLink: '<a href="javascript:void(0);" class="btn-link btn-xs">닫기</a>'
  });

}
</script>
<div id="temp"></div>