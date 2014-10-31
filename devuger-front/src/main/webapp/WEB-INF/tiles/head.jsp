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
<title>Devuger - 코드로 이야기 하는 사람들</title>
</c:if>
<c:if test="${og ne null}">
<title>${og.title} - WIMITT</title>
<meta property="og:title" content="WIMITT, ${og.title}" />
<meta property="og:site_name" content="${og.siteName}" />
<meta property="og:image" content="${og.image}" />
<meta property="og:description" content="${og.description}" />
</c:if>	
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
<link rel="stylesheet" href="${contextPath}/resources/css/font-awesome.min.css" />
<link rel="stylesheet" href="${contextPath}/resources/js/highlight/styles/default.css" />
<link rel="stylesheet" href="${contextPath}/resources/style/bootstrap.css" />
<link rel="stylesheet" href="${contextPath}/resources/style/bootswatch.min.css" />
<link rel="stylesheet" href="${contextPath}/resources/style/custom.css" />
<script type="text/javascript" src="${contextPath}/resources/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="${contextPath}/resources/js/highlight/highlight.pack.js"></script>
<script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>
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

  ga('create', 'UA-50816776-3', 'auto');
  ga('send', 'pageview');

</script>