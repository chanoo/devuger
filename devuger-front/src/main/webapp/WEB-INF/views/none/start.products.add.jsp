<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<ul class="nav nav-tabs" style="margin-bottom: 15px;">
  <li><a href="#shop-open" data-toggle="tab">상점 만들기</a></li>
  <li class="active"i><a href="#product-add" data-toggle="tab">판매상품 등록</a></li>
  <li><a href="#account-add" data-toggle="tab">입금계좌 등록</a></li>
</ul>
<div id="myTabContent" class="tab-content">
  <div class="tab-pane fade active in" id="product-add">
	
	<div class="well bs-component">
		<form:form action="${contextPath}/products/add" modelAttribute="product" class="form-horizontal">
		<fieldset>
	    <legend>판매 상품 정보</legend>
	   		<div class="form-group">
				<form:label path="category" class="col-xs-2 control-label">카테고리</form:label>
				<div class="col-xs-10">
					<form:select path="category" class="form-control">
						<form:option value="">패션</form:option>
						<form:option value="">디지털/가전</form:option>
					</form:select>
				</div>
			</div>
			<hr />
			<div class="form-group">
				<label for="get_url" class="col-xs-2 control-label">주소(URL)</label>
				<div class="col-xs-10">
					<input id="get_url" type="text" class="form-control" placeholder="http://"><br/>
				</div>
			</div>
			<div class="form-group">
				<form:label path="title" class="col-xs-2 control-label">제목</form:label>
				<div class="col-xs-10">
					<form:input path="title" class="form-control" />
				</div>
			</div>
			<div class="form-group">
				<form:label path="description" class="col-xs-2 control-label">설명</form:label>
				<div class="col-xs-10">
					<form:textarea path="description" class="form-control"></form:textarea>
				</div>
			</div>
			<div class="form-group">
				<form:label path="price" class="col-xs-2 control-label">판매가</form:label>
				<div class="col-xs-10">
					<form:input path="price" class="form-control" />
				</div>
			</div>
			<div class="form-group">
				<form:label path="price" class="col-xs-2 control-label">제품 이미지</form:label>
				<div class="col-xs-10">
						<div id="og-images" class="list-unstyled"></div>
				</div>
			</div>
			<div class="form-group">
				<div class="col-xs-10 col-xs-offset-2">
					<button type="submit" class="btn btn-primary">등록</button>
				</div>
			</div>
			<div id="status" class="col-xs-12"></div>
		</fieldset>
		</form:form>
	
		<script src="http://masonry.desandro.com/masonry.pkgd.min.js"></script>	
		<script type="text/javascript">
		String.prototype.format = function() {
		    var formatted = this;
		    for( var arg in arguments ) {
		        formatted = formatted.replace("{" + arg + "}", arguments[arg]);
		    }
		    return formatted;
		};
	
		$(document).ready(function() {
	
		    var getUrl  = $('#get_url'); //url to extract from text field
		    
		    getUrl.keyup(function(e) { //user types url in text field        
		        
		        if (e.keyCode != 13) {
		        	return;
		        }
				var regex = new RegExp("^(http[s]?:\\/\\/(www\\.)?|ftp:\\/\\/(www\\.)?|www\\.){1}([0-9A-Za-z-\\.@:%_\+~#=]+)+((\\.[a-zA-Z]{2,3})+)(/(.)*)?(\\?(.)*)?");
		        
		        if (regex.test(getUrl.val())) {
	
	                var exeUrl = getUrl.val().match(regex)[0]; //extracted first url from text filed
	
	                $("#status").html("로딩중...");
	                var html = "";
	                
		    		$.ajax({
		    			url : "${contextPath}/good.json",
		    			type : "post",
		    			data : {'url': exeUrl},
		    			success : function(json, textStatus) {
		                	var og = json.og;
		                	var imgs = json.img;
							for(var i = 0; i < imgs.length; i++) {
								html += '<div class="item"><a href="{0}" target="_blank"><img src="{1}" alt="" width="210" style="padding:10px;margin:5px; border:1px solid #999;"/></a></div>'.format(imgs[i], imgs[i]);
							}
							$("#og-images").html(html);
							$("#title").val(og.title);
							$("#description").val(og.description);
							
			                $("#status").html("");
	
			                $('#og-images').imagesLoaded( function() {
				                var container = document.querySelector("#og-images");
				                var msnry = new Masonry( container, {
				                  // options...
				                  itemSelector: '.item',
				                  columnWidth: 220
				                });
		                	});
	
		    			}
		    		});
		        }
		    });
		});
		</script>
	</div>


  </div>
</div>