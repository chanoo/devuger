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
	<link rel="stylesheet" href="${contextPath}/resources/js/ResponsiveSlides/responsiveslides.css" />
	<link rel="stylesheet" href="<c:url value="/resources/style/custom.css" />">
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
		<div class="col-xs-12">
			<div class="alert alert-dismissable alert-${og.result}">
			  <button type="button" class="close" data-dismiss="alert">×</button>
			  ${og.message}
			</div>
		</div>
	</div>
	</c:if>		
	
	<div class="row">
		<div class="col-xs-12">
			<tiles:insertAttribute name="layout.content" />
		</div>
	</div>

	<footer>
		<div class="row">

			<hr />

			<div class="col-xs-12">

				<ul class="list-unstyled">
					<li><a href="${contextPath}/terms/service">서비스이용약관</a></li>
					<li><a href="${contextPath}/terms/user">개인정보취급방침</a></li>
					<li><a href="${contextPath}/terms/seller">판매회원약관</a></li>
					<li><a href="${contextPath}/help">도움말</a></li>
					<li class="pull-right"><a href="#top">맨 위로</a></li>
				</ul>
			</div>

		</div>

		<div class="row">
			<div class="col-xs-12">
			
				<div class="pull-left">				
					<p><small>(주)무한연구소, 대표이사 성찬우 | 사업자등록번호: 113-86-85110 | 통신판매업신고: <a href="javascript:onopen();">제2014-서울구로-0979호</a></small></p>
					<p><small>서울시 구로구 디지털로 288, 207호 (대륭포스트타워1차)</small></p>
					<p><small>상담가능시간: 오전 10시~오후 6시 (토요일 및 공휴일 휴무) | 고객센터: 070-7583-3833 | 메일: <a href="mailto:hello@8hlab.com">hello@8hlab.com</a></small></p>
					<p><small>© Copyright 2014 fastball - All rights reserved.</small></p>
				</div>

			</div>
		</div>

	</footer>

</div>
</compress:html>
<script src="//getbootstrap.com/dist/js/bootstrap.min.js"></script>
<script src="${contextPath}/resources/js/waypoint/waypoints.min.js"></script>
<script src="${contextPath}/resources/js/waypoint/waypoints-infinite.min.js"></script>
<script src="${contextPath}/resources/js/ResponsiveSlides/responsiveslides.min.js"></script>
<script src="${contextPath}/resources/js/jquery.wookmark.min.js"></script>
<script src="${contextPath}/resources/js/jquery.colorbox-min.js"></script>
<script src="${contextPath}/resources/js/noty/packaged/jquery.noty.packaged.min.js"></script>
<script src="${contextPath}/resources/js/common.js"></script>
<script src="${contextPath}/resources/js/history/history.js"></script>
<script src="${contextPath}/resources/js/noty/packaged/jquery.noty.packaged.min.js"></script>
<script src="${contextPath}/resources/js/bigSlide.min.js"></script>
<script type="text/javascript"> 
function onopen()
{
	var url = "http://www.ftc.go.kr/info/bizinfo/communicationViewPopup.jsp?wrkr_no=1138685110";
	window.open(url, "communicationViewPopup", "width=750, height=700;");
}

//Just to fix IE issues when console isn't defined, only used for the demo - not required for the slider
if (typeof console === "undefined" || typeof console.log === "undefined") {
    console = { log: function(){} };
}

$(function() {
	bindAjax();
	bindReloadAjax();
	
	colorbox();
	applyLayout();
 	slider();
});

function clean() {
	$("#main div.product").remove();
}

function slider() {
    $(".slides").responsiveSlides({
        auto: false,
        pager: false,
        nav: true,
        speed: 500,
        namespace: "transparent-btns",
        after: function () {
			applyLayout();
		}
      });
}

// 핀터레스트 스타일로 바꾸기
function applyLayout() {

	$('#main').imagesLoaded().always(function(instance) {
	}).done(function(instance) {
	}).fail(function() {
	}).progress(function(instance, image) {
		$('#main .product').wookmark({
			align : "left",
			itemWidth : 283,
			flexibleWidth : true,
			container : $('#main')
		});
	});

};

function colorbox() {
	
 	$(".colorbox").colorbox({
 		fixed: true,
 		iframe : true,
 		close : false,
		width : "100%",
		height : "100%",
		onComplete:function() {
			$('body').css({'overflow':'hidden'});
			$(document).bind('scroll',function () { 
				window.scrollTo(0,0); 
			});
		},
		onClosed:function() {
			$(document).unbind('scroll'); 
			$('body').css({'overflow':'visible'});
			if("${userSession.id}".length == 0)
				document.location.reload();
			
		}
	});

}

$(window).scroll(function() {
    $('#menu').css('top', $(this).scrollTop() + "px");
});

function getProducts(page, category) {

  currentPage = page;
  currentCategory = category;

  if(page == 1) {
		clean();
		isLastPage = false;
	}

	$.ajax({
		url : "${contextPath}/products.json?page={0}&category={1}".format(currentPage, currentCategory),
		type : "get",
    async:   false,
			success : function(json, textStatus) {
			
			if(!isSuccess(json))
				return;
			
			var content = json.pageImpl.content;

			var j = 0;

			var html = "";
			$.each(content, function(index, product){

				j++;

				var favorCount = "";
				if(product.favorCount > 0)
					favorCount = product.favorCount;
				
					html += '<div class="col-xs-3 item product">';
					html += '	<div class="panel panel-default">';
					html += '		<div class="panel-body" style="padding-top:0;">';
					html += '			<div class="row" style="position:relative;">';
//					html += '				<a href="${contextPath}/posts/{0}" target="_blank">'.format(product.id);
					html += '					<div class="rslides_container">';
					html += '						<ul class="rslides slides">';
					$.each(product.mainImages, function(m, mainImage){
						if(m > 2)  return false;
					html += '							<li style="displaye:block;width:263px; height:{0};">'.format(mainImage.id, parseInt(mainImage.height * 263 / mainImage.width));;
					html += '								<img src="${contextPath}/attachments/{0}/ratio/263" width="263" height="{1}" />'.format(mainImage.id, parseInt(mainImage.height * 263 / mainImage.width));
					html += '							</li>';
					});
					html += '						</ul>';
					html += '					</div>';
					html += '					<span class="price-bg"></span>';
					html += '					<span class="price-label text-right">{0}</span>'.format(product.retailPriceLabel);
//					html += '				</a>';
					html += '			</div>';
				  html += '			<div class="row" style="padding-top:10px; height:100px;">';
				  html += '				<div class="col-xs-12"><p><a href="${contextPath}/posts/{0}" target="_blank"><small>{1}</small></a></p><hr/></div>'.format(product.id, product.title);
			    html += '				<div class="col-xs-12">';
			    html += '					<a href="${contextPath}/favorites/add.json?product.id={0}" class="ajax btn btn-icon btn-xs"><i class="fa fa-heart"></i> {1}</a>&nbsp;'.format(product.id, favorCount);
			    if(product.commentCount > 0)
					html += '					<button class="btn btn-icon btn-xs"><i class="fa fa-comment"></i> {0}</button>&nbsp;'.format(product.commentCount);
				  html += '					<a href="${contextPath}/fb/share.json?product.id={0}" class="ajax btn btn-icon btn-xs"><i class="fa fa-facebook-square"></i> </a>&nbsp;'.format(product.id);
			    if(product.buyCount > 0)
			    html += '					<span class="btn btn-icon btn-xs" title="판매 수량"><i class="fa fa-credit-card"></i> {0}</span>&nbsp;'.format(product.buyCount);
					html += '					<button class="btn btn-icon btn-xs" title="수입 국가"><span class="flag-icon flag-icon-{0}" style="border:1px solid #ddd;"></span></button>&nbsp;'.format(product.country.iso2.toLowerCase());
				  html += '				</div>';
				  html += '			</div>';
				  html += '		</div>';
					html += '	</div>';
					html += '</div>';
			});

      $(html).hover(function() {
        $(this).css("border-color", "#ff828a");
      }, function() {
        $(this).css("border-color", "#ddd");
      });

			$(html).appendTo("#main");

			if(json.pageImpl.lastPage) {
			  isLastPage = true;
			} else {
			  isLastPage = false;
			  currentPage++;
			}

      slider();
      applyLayout();
      colorbox();
      bindAjax();


    }
  });
  }
</script> 
</body>
</html>