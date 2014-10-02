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
	<link rel="stylesheet" href="<c:url value="/resources/style/custom.css" />">
</head>
<body style="background-color:transparent;padding-top:20px;" >

	<div onclick="parent.jQuery.colorbox.close();" style="position:absolute; top:0; left:0; width:100%; height:100%;"></div>

	<div class="container">

<%-- 		<c:if test="${message ne null}">
			<div class="row">
				<div class="col-xs-9">
					<div class="alert alert-dismissable alert-success">
						<button type="button" class="close" data-dismiss="alert">×</button>
						<span id="message">${message}</span>
					</div>
				</div>
			</div>
		</c:if>
 --%>
		<tiles:insertAttribute name="layout.content" />
	</div>

	<script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
	<script src="${contextPath}/resources/js/jquery.wookmark.min.js"></script>
	<script src="${contextPath}/resources/js/jquery.colorbox-min.js"></script>
	<script src="${contextPath}/resources/js/noty/packaged/jquery.noty.packaged.min.js"></script>
	<script src="${contextPath}/resources/js/common.js"></script>
	<script src="${contextPath}/resources/js/bigSlide.min.js"></script>
	<script type="text/javascript">	
		var options = {
		    align:"left",
		    autoResize: true, // This will auto-update the layout when the browser window is resized.
		    container: $('#product-images'), // Optional, used for some extra CSS styling
		    offset: 10, // Optional, the distance between grid items
		    itemWidth: 277, // Optional, the width of a grid item
		    resizeDelay: 50,
		    flexibleWidth: '0' // Optional, the maximum width of a grid item
		};
	
		// 핀터레스트 스타일로 바꾸기
		function applyLayout() {
			options.container.imagesLoaded(function() {
				// Create a new layout handler when images have loaded.
				var handler = $('#product-images li');
				handler.wookmark(options);
			});
		};
		
		$(function() {
			
            $('.menu-link').bigSlide({
				'side': 'right' // The side of the navigation menu (either 'right' or 'left')
				});
			
			bindAjax();
		});

//		top.history.pushState(null, document.title, document.location.href);
	</script>
</body>
</html>