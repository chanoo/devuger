<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<ul class="nav nav-tabs" style="margin-bottom: 15px;">
  <li><a href="#shop-open" data-toggle="tab">상점 만들기</a></li>
  <li><a href="#product-add">판매상품 등록</a></li>
  <li class="active"><a href="#account-add">입금계좌 등록</a></li>
</ul>
<div id="myTabContent" class="tab-content">
  <div class="tab-pane fade active in" id="account-add">
  	계좌 등록
  	<a href="#close">다음에 등록</a>
  </div>
</div>