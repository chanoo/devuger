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
			<tiles:insertAttribute name="layout.content" />
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