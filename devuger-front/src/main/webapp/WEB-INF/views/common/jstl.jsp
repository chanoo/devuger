<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<fmt:formatNumber value="${product.price}" pattern="#,###원" />

<fmt:formatDate pattern="MM/dd/yyyy" value="${comment.createdOn}" />

<script>
$(document).on('click', '.fa-plus', function() {
	console.log("plus");
});
</script>