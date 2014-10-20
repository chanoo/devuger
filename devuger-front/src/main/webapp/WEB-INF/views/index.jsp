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

				<!-- 피드 리스트 시작 -->
				<div id="feeds">
				</div>
				<!-- 피드 리스트 끝 -->
			</div>
			<div class="col-xs-4">
				<div class="float" data-spy="affix" data-offset-top="60">			
			
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
	html += '  		<textarea name="source.code" placeholder="코드를 입력해주세요." rows="5" cols="200" class="form-control"></textarea>';
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

$(function() {

  feeds(currentPage);

  $('pre code').each(function(i, block) {
    hljs.highlightBlock(block);
  });

  $("#feeds").autolink();
  $(".timeago").timeago();
  $('.float').affix();
  $('pre').readmore({
    speed: 75,
    heightMargin: 100,
    moreLink: '<a href="#" class="btn-link btn-xs">더보기</a>',
		lessLink: '<a href="#" class="btn-link btn-xs">닫기</a>'
  });
  
  $(window).scroll(function() {
    if($(window).scrollTop() >= $(document).height() - $(window).height() && isLastPage == false) {
      feeds(currentPage);
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

function feeds(page) {

  $.ajax({
      url : "${contextPath}/feeds?page={0}".format(page),
      type : "get",
    	dataType : "html",
      async : false,
      success : function(result) {
        
        $(result).appendTo("#feeds");
        currentPage++;

      }
    });

}
</script>