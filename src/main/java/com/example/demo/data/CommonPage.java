package com.example.demo.data;

import com.github.pagehelper.PageInfo;

import java.io.Serializable;

public class CommonPage implements Serializable {
	private static final long serialVersionUID = 7449150234491211106L;
	public CommonPage(){
		this.pageIndex = 0;
		this.pageSize = defaultPageSize();
	}
	public CommonPage(int pageIndex, int pageSize, int pageCount, long totalCount) {
		this.pageIndex = pageIndex;
		this.pageSize = pageSize;
		this.pageCount = pageCount;
		this.totalCount = totalCount;
	}
	public CommonPage(PageInfo page) {
		this(page.getPageNum(), page.getPageSize(), page.getPages(), page.getTotal());
	}

	private int pageIndex = 1;
	private int pageSize = 0;
	private int pageCount = 0;
	private long totalCount = 0;

	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

//	public boolean hasRequestIndex(){
//		if(totalCount < 1){
//			return false;
//		}
//		if(pageCount < pageIndex){
//			return false;
//		}
//		return true;
//	}


	protected int defaultPageSize() {
		return 5;
	}
}
