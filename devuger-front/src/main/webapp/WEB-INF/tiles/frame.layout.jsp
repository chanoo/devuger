<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<tiles:insertAttribute name="layout.head" />
	<style type="text/css">
		html, body{height: 100%; overflow:hidden;}
	</style>
</head>
<body>
<!--[if lt IE 8]>
<script>
alert("최신 브라우저를 사용 해주세요.");
</script>
<![endif]-->
<div class="navbar navbar-default navbar-fixed-top" style="border-bottom: 5px solid #1ab69e;">
	<div class="container">
		<div class="navbar-header">
			<a href="${contextPath}/" class="navbar-brand"><img src="${contextPath}/resources/img/wimitt_logo.png" alt="" style="position:relative;top:-3px;display:block;" /></a>
		</div>
		<div class="navbar-collapse collapse" id="navbar-main">
			<ul class="nav navbar-nav">
				<li class="divider"></li>
				<li class="overflow"><p style="white-space: nowrap;"><a href="${contextPath}/posts/${product.id}" class="navbar-brand" style="text-overflow: ellipsis; width:600px;overflow: hidden;">${og.title}</a></p></li>
			</ul>

			<ul class="nav navbar-nav navbar-right">
				<li>
					<a href="${contextPath}/carts">
						<i class="fa fa-shopping-cart fa-lg"></i>
						<c:if test="${userGraph.cart ne 0}">
							<span class="badge">${userGraph.cart}</span>
						</c:if>
						<c:if test="${userGraph.cart eq 0}">
							Cart
						</c:if>
					</a>
				</li>
				<li><a href="${contextPath}/favorites/add.json?product.id=${product.id}" class="ajax">
				<!--  style="color:#fc838c;" -->
					<span class="fa fa-heart fa-lg"></span>
					<c:if test="${product.favorCount ne 0}">
						<span class="badge">${product.favorCount}</span>
					</c:if>		 
				</a></li>
				<li><a href="${contextPath}/fb/share.json?product.id=${product.id}" class="ajax btn-link">
					<span class="fa fa-facebook-square fa-lg"></span>
				</a></li>
				<li><a href="${contextPath}/comments/product/${product.id}" id="comments-menu" class="colorbox btn-success">
					<span class="fa fa-comment fa-lg"></span>			
					<c:if test="${fn:length(comments) ne 0}">
						<span class="badge">${fn:length(comments)}</span>
					</c:if>
				</a></li>
	            <li class="divider"></li>
				<li><a href="#" class="btn-danger" id="buy">${retailPriceLabel} 구매하기</a></li>
			</ul>

		</div>

	</div>
</div>
<tiles:insertAttribute name="layout.content" />
<div class="col-xs-5" id="product-options" style="position:absolute; display: none; top: 60px; right: 124px; width:500px;">
	<div class="panel panel-danger">
		<div class="panel-body">
	
			<form:form action="${contextPath}/carts/add" modelAttribute="cart" id="cart-form" class="form-horizontal">			
			<h1 style="text-align:right;">${retailPriceLabel}</h1>
	   		<div class="form-group">
				<div class="col-xs-12">
					<form:select path="option" class="form-control">
						<form:option value="">선택하세요.</form:option>
					<c:forEach items="${product.options}" var="option">
						<form:option value="${option.id}">
							${option.name}, <fmt:formatNumber value="${option.retailPrice}" pattern="#,###원" />
							(${option.stock}개 구매가능)
						</form:option>
					</c:forEach>
					</form:select>
				</div>
			</div>
			<div id="added-options"></div>
			<small>재고수량이 남아 있더라도 다른분이 먼저 구매하시면 구매불가능합니다.</small>
			<hr/>
			<button type="submit" class="btn btn-default btn-block">장바구니 담기</button>
			</form:form>
		
		</div>
	</div>
</div>
<script src="http://getbootstrap.com/dist/js/bootstrap.min.js"></script>
<script src="${contextPath}/resources/js/jquery.wookmark.min.js"></script>
<script src="${contextPath}/resources/js/jquery.colorbox-min.js"></script>
<script src="${contextPath}/resources/js/noty/packaged/jquery.noty.packaged.min.js"></script>
<script src="${contextPath}/resources/js/common.js"></script>
<script src="${contextPath}/resources/js/history/history.js"></script>
<script type="text/javascript">
$(function() {
	bindAjax();
	
});

function plus(id) {

	var $optionQuantity = $("#option-quantity-{0}".format(id));
	var qty = $optionQuantity.val();
	qty++;
	$optionQuantity.val(qty);

}

function minus(id) {

	var $optionQuantity = $("#option-quantity-{0}".format(id));
	var quantity = $optionQuantity.val();
	if(quantity == 1)
		return;

	quantity--;
	$optionQuantity.val(quantity);

}

$("#comment-form").submit(function() {
	
	var frm = $(this).serialize();

	$.ajax({
		url : "${contextPath}/comments/product/${product.id}.json",
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

function comments() {
	
 	$.colorbox({
 		href: "${contextPath}/comments/product/${product.id}",
 		transition: "none",
 		opacity: "0.01",
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
		}
	});

}

$("#comments-menu").hover(
	function() { // handlerIn
		comments();
		$("#product-options").css("display", "none");
	},
	function() { // handlerOut
	}
);

$("#buy").hover(
	function() { // handlerIn
		$("#comments-view").css("display", "none");
		$("#product-options").show();
	},
	function() { // handlerOut
	}
);

$("#product-options").hover(
	function() { // handlerIn
		$('#option').mouseleave(function(event) { event.stopPropagation(); });
	},
	function() { // handlerOut
		$('#option').mouseleave(function(event) { event.stopPropagation(); });
		$("#product-options").hide();
	}
);

$("#cart-form").submit(function() {
	
	var frm = $(this).serialize();

	$.ajax({
		url : "${contextPath}/carts/add.json",
		type : "post",
		data : frm,
		success : function(json, textStatus) {
		  if(isSuccess(json))
				notyAlert("success", json.message);
		}
	});

	return false;
});

$("#option").change(function(e, value) {
	
	var selected = $("option:selected", value).val(); 
	if(selected.length == 0)
		return;

	var is = false;
	$("#added-options .option-id").each(function(i, value) {
		if($(value).val() == selected)
			is = true;
	});
	
	if(is) {
		notyAlert("warning", "이미 추가된 옵션입니다.");
		return;
	}

	var html  = '<div class="form-group">';
		html += '	<label class="col-xs-8 control-label">{0}</label>'.format($("option:selected", this).text());
		html += '	<div class="col-xs-4">';
		html += '		<input type="hidden" name="option.id" class="option-id" value="{0}" />'.format($(this).val());
		html += '		<div class="input-group">';
		html += '			<span class="input-group-btn"><a href="javascript:minus({0});" class="btn btn-default"><i class="fa fa-minus"></i></a></span>'.format($(this).val());
		html += '			<input type="text" name="option.quantity" value="{0}" class="form-control text-center" id="option-quantity-{1}" />'.format("1", $(this).val());
		html += '			<span class="input-group-btn"><a href="javascript:plus({0});" class="btn btn-default"><i class="fa fa-plus"></i></i></a></span>'.format($(this).val());
		html += '		</div>';
		html += '	</div>';
		html += '</div>';
		$("#added-options").append(html);;
});
</script> 
</body>
</html>