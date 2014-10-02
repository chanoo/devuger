<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<!-- HELLO MAN : 만나서 반갑습니다! -->
<!-- 혹시 이 글을 보고 계신 당신이 개발자 또는 디자이너라면?? -->
<!-- 같이 더 멋찌게 만들어 보고 싶다면 메일 주세요! -->
<!-- hello@8hlab.com -->
<c:if test="${og eq null}">
<title>WIMITT - 우리가 만나는 새로운 경험</title>
</c:if>
<c:if test="${og ne null}">
<title>${og.title} - WIMITT</title>
<meta property="og:title" content="WIMITT, ${og.title}" />
<meta property="og:site_name" content="${og.siteName}" />
<meta property="og:image" content="${og.image}" />
<meta property="og:description" content="${og.description}" />
</c:if>	
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
<meta name="naver-site-verification" content="13d98f33dde0ef85a1c5c3fce11cf6d6a65cc2b5"/>
<link rel="stylesheet" href="<c:url value="/resources/css/font-awesome.min.css" />" />
<link rel="stylesheet" href="<c:url value="/resources/assets/docs.css" />" />
<link rel="stylesheet" href="<c:url value="/resources/css/flag-icon.min.css" />" />
<link rel="stylesheet" href="http://bootswatch.com/assets/css/bootswatch.min.css">
<link rel="stylesheet" href="<c:url value="/resources/style/bootstrap.css" />">
<link rel="stylesheet" href="<c:url value="/resources/style/colorbox/colorbox.css" />">
<link rel="stylesheet" href="<c:url value="/resources/style/wookmark/main.css" />">
<link rel="stylesheet" href="<c:url value="/resources/style/responsiveslides.css" />">
<script type="text/javascript" src="<c:url value="/resources/js/jquery-1.11.1.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/imagesloaded.pkgd.min.js"/>"></script>
<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
<script src="${contextPath}/resources/js/respond.js"></script>
<![endif]-->
<script>
  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
  })(window,document,'script','//www.google-analytics.com/analytics.js','ga');

  ga('create', 'UA-50816776-2', 'auto');
  ga('send', 'pageview');

</script>