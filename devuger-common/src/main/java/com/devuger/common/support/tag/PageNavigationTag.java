package com.devuger.common.support.tag;

import java.io.IOException;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class PageNavigationTag extends TagSupport
{
	private static final long serialVersionUID = -4523450261010080425L;

	private int pageRange = 10;
	private int pageSize = 20;
	private int currentPage = 1;
	private int lastPage = 0;
	private int totalElements = 0;
	private String url;

	@Override 
	public int doStartTag()
	{
	  if(totalElements == 0)
	    return SKIP_BODY;
		// 모든 게시물 수를 바탕으로 마지막 페이지를 구한다.
		if(totalElements > 0L)
		{
			lastPage = totalElements / pageSize;
			if(totalElements % pageSize > 0) {
				lastPage++;
			}
		} else if(totalElements == 0) {
			lastPage = 0;
		}
		JspWriter out = pageContext.getOut();

		try {
			if(lastPage != 0)
			{
				out.println("<ul class='pagination'>");

				if(currentPage != 1) {
					out.println(String.format("<li><a href='%s1' title='1'><i class='fa fa-lg fa-angle-double-left'></i></a></li>", url));
				}

				if(lastPage <= pageRange) {
						for(Long i = 1L; i <= lastPage; i++) {
							if(currentPage == i) {
								out.println(String.format("<li class='active'><a href='%s%d'>%d</a></li>", url, i, i));
							} else {
								out.println(String.format("<li><a href='%s%d'>%d</a></li>", url, i, i));
							}
						}
				} else {
					if(currentPage < pageRange / 2 + 1) {
						for(Long i = 1L; i <= pageRange; i++) {
							if(currentPage == i) {
								out.println(String.format("<li class='active'><a href='%s%d' class='page_no'>%d</a></li>", url, i, i));
							} else {
								out.println(String.format("<li><a href='%s%d'>%d</a></li>", url, i, i));
							}
						}
					} else if(currentPage > lastPage - pageRange / 2) {
						for(int i = lastPage - pageRange + 1; i <= lastPage; i++) {
              if(currentPage == i) {
								out.println(String.format("<li class='active'><a href='%s%d' class='page_no'>%d</a></li>", url, i, i));
							} else {
								out.println(String.format("<li><a href='%s%d'>%d</a></li>", url, i, i));
							}
						}
					} else {
						for(int i = currentPage - (pageRange/2 - 1); i <= currentPage + (pageRange / 2); i++) {
              if(currentPage == i) {
								out.println(String.format("<li class='active'><a href='%s%d' class='page_no'>%d</a></li>", url, i, i));
							} else {
								out.println(String.format("<li><a href='%s%d'>%d</a></li>", url, i, i));
							}
						}
					}
				}
				if(currentPage != lastPage) {
					out.println(String.format("<li><a href='%s%d'><i class='fa fa-lg fa-angle-double-right'></i></a></li>", url, lastPage));
				}
				out.println("</ul>");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return SKIP_BODY;
	}

  public int getPageRange() {
    return pageRange;
  }
  public void setPageRange(int pageRange) {
    this.pageRange = pageRange;
  }
  public int getPageSize() {
    return pageSize;
  }
  public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
  }
  public int getCurrentPage() {
    return currentPage;
  }
  public void setCurrentPage(int currentPage) {
    this.currentPage = currentPage;
  }
  public int getLastPage() {
    return lastPage;
  }
  public void setLastPage(int lastPage) {
    this.lastPage = lastPage;
  }
  public int getTotalElements() {
    return totalElements;
  }
  public void setTotalElements(int totalElements) {
    this.totalElements = totalElements;
  }
  public String getUrl() {
    return url;
  }
  public void setUrl(String url) {
    this.url = url;
  }
}
