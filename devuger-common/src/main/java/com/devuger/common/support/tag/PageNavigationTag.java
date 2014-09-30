package com.devuger.common.support.tag;

import java.io.IOException;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class PageNavigationTag extends TagSupport
{
	private static final long serialVersionUID = -4523450261010080425L;

	private Long pageRange = 10L;
	private Long pageSize = 20L;
	private Long currentPage = 1L;
	private Long lastPage = 0L;
	private Long totalElements = 0L;

	@Override 
	public int doStartTag()
	{
	  if(totalElements == null)
	    return SKIP_BODY;
		// 모든 게시물 수를 바탕으로 마지막 페이지를 구한다.
		if(totalElements > 0L)
		{
			lastPage = totalElements / pageSize;
			if(totalElements % pageSize > 0) {
				lastPage++;
			}
		} else if(totalElements.equals(0L)) {
			lastPage = 0L;
		}
		JspWriter out = pageContext.getOut();

		try {
			if(!lastPage.equals(0L))
			{
				out.println("<ul class='pagination'>");

				if(currentPage != 1) {
					out.println("<li><a href='#page_1' title='1'><i class='fa fa-lg fa-angle-double-left'></i></a></li>");
				}

				if(lastPage <= pageRange) {
						for(Long i = 1L; i <= lastPage; i++) {
							if(currentPage.equals(i)) {
								out.println("<li class='active'><a href='#' title='" + i + "'>" + i + "</a></li>");
							} else {
								out.println("<li><a href='#page_" + i + "' title='" + i + "'>" + i + "</a></li>");
							}
						}
				} else {
					if(currentPage < pageRange / 2 + 1) {
						for(Long i = 1L; i <= pageRange; i++) {
							if(currentPage.equals(i)) {
								out.println("<li class='active'><a href='#' title='" + i + "' class='page_no'>" + i + "</a></li>");
							} else {
								out.println("<li><a href='#page_" + i + "' title='" + i + "'>" + i + "</a></li>");
							}
						}
					} else if(currentPage > lastPage - pageRange / 2) {
						for(Long i = lastPage - pageRange + 1; i <= lastPage; i++) {
							if(currentPage.equals(i)) {
								out.println("<li class='active'><a href='#' title='" + i + "' class='page_no'>" + i + "</a></li>");
							} else {
								out.println("<li><a href='#page_" + i + "' title='" + i + "'>" + i + "</a></li>");
							}
						}
					} else {
						for(Long i = currentPage - (pageRange/2 - 1); i <= currentPage + (pageRange / 2); i++) {
							if(currentPage.equals(i)) {
								out.println("<li class='active'><a href='#' title='" + i + "' class='page_no'>" + i + "</a></li>");
							} else {
								out.println("<li><a href='#page_" + i + "' title='" + i + "'>" + i + "</a></li>");
							}
						}
					}
				}
				if(!currentPage.equals(lastPage)) {
					out.println("<li><a href='#page_" + lastPage + "' title='" + lastPage + "'><i class='fa fa-lg fa-angle-double-right'></i></a></li>");
				}
				out.println("</ul>");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return SKIP_BODY;
	}

	public Long getPageRange() {
		return pageRange;
	}
	public void setPageRange(Long pageRange) {
		this.pageRange = pageRange;
	}
	public Long getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Long currentPage) {
		this.currentPage = currentPage;
	}
	public Long getLastPage() {
		return lastPage;
	}
	public void setLastPage(Long lastPage) {
		this.lastPage = lastPage;
	}
	public Long getTotalElements() {
		return totalElements;
	}
	public void setTotalElements(Long totalElements) {
		this.totalElements = totalElements;
	}
	public Long getPageSize() {
		return pageSize;
	}
	public void setPageSize(Long pageSize) {
		this.pageSize = pageSize;
	}
}
