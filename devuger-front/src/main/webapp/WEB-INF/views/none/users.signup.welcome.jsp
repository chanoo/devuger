<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<script type="text/javascript">
$(function() {
	
	var redirect = "${redirect}";
	
	if(redirect.startsWith("/mypage") || redirect.startsWith("/manage")) {
		document.location.href = redirect;
	} else if(parent.document) {
		parent.jQuery.colorbox.close();
		parent.document.location.href = redirect;		
	} else {
		document.location.href = redirect;
	}
		

});
</script>