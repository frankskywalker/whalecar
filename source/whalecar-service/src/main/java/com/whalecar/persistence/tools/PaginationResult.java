package com.whalecar.persistence.tools;

import java.util.List;

public class PaginationResult<E> {

	/**
	 * 分页记录数
	 */
	private int pageSize = PaginationUtils.DEFAULT_PAGESIZE;
	
	/**
	 * 总页数
	 */
	private int pageCount;

	/**
	 * 总记录数
	 */
	private int totalCount;

	/**
	 * 记录起始位置
	 */
	private int startIndex;

	/**
	 * 每页的记录起始位置
	 */
	private int[] indexes = new int[0];
	
	/**
	 * 每页的页码，从1开始
	 */
	private int[] pageIndexs = new int[0];
	
	/**
	 * 当前页数，从1开始
	 */
	private int currentPage = 1;
	
	/**
	 * 是否为第一页
	 */
	private boolean isFirstPage;
	
	/**
	 * 是否是最后一页
	 */
	private boolean isLastPage;

	/**
	 * 记录数据
	 */
	private List<E> items;

	public PaginationResult(List<E> items, int totalCount, int startIndex) {
		init(items,totalCount,PaginationUtils.DEFAULT_PAGESIZE,startIndex);
	}
	
	public PaginationResult(List<E> items, int totalCount, int pageSize,
			int startIndex) {
		init(items, totalCount, pageSize, startIndex);
	}
	
	/**
	 * init
	 * @param items2
	 * @param totalCount2
	 * @param defaultPagesize
	 * @param startIndex2
	 */
	private void init(List<E> items, int totalCount,
			int pageSize, int startIndex) {
		this.pageSize = pageSize;
		setTotalCount(totalCount);
		this.items = items;
		setStartIndex(startIndex);
		setPageCount();
		setCurrentPage(getStartIndex() / pageSize + 1);
		
	}
	
	/**
	 * 设置当前页数并且设置 是否第一页和是否最后一页
	 * @param currentPage
	 */
	private void setCurrentPage(int currentPage){
		this.currentPage = currentPage;
		if(currentPage == 1){
			this.isFirstPage = true;
		}
		else{
			this.isFirstPage = false;
		}
		
		if(currentPage == this.pageCount){
			this.isLastPage = true;
		}
		else{
			this.isLastPage = false;
		}
	}

	/**
	 * 设置总记录数
	 * @param totalCount
	 */
	private void setTotalCount(int totalCount) {
		if (totalCount > 0) {
			this.totalCount = totalCount;
			int count = totalCount / pageSize;
			if (totalCount % pageSize > 0)
				count++;
			indexes = new int[count];
			for (int i = 0; i < count; i++) {
				indexes[i] = pageSize * i;
			}
			pageIndexs = new int[count];
			for (int i = 0; i < count; i++) {
				pageIndexs[i] = i + 1;
			}
		} else {
			this.totalCount = 0;
		}
	}
	
	/**
	 * 设置起始位置
	 * @param startIndex
	 */
	private void setStartIndex(int startIndex) {
		if (totalCount <= 0)
			this.startIndex = 0;
		else if (startIndex >= totalCount)
			this.startIndex = indexes[indexes.length - 1];
		else if (startIndex < 0)
			this.startIndex = 0;
		else {
			this.startIndex = indexes[startIndex / pageSize];
		}
	}

	/**
	 * 获取总记录数
	 * @return
	 */
	public int getTotalCount() {
		return totalCount;
	}

	/**
	 * 获取每页起始位置列表
	 * @return
	 */
	public int[] getIndexes() {
		return indexes;
	}

	/**
	 * 获取当前起始位置
	 * @return
	 */
	public int getStartIndex() {
		return startIndex;
	}

	/**
	 * 获得下一页的起始位置
	 * @return
	 */
	public int getNextIndex() {
		int nextIndex = getStartIndex() + pageSize;
		if (nextIndex >= totalCount)
			return getStartIndex();
		else
			return nextIndex;
	}

	/**
	 * 前一页的开始位置
	 * @return
	 */
	public int getPreviousIndex() {
		int previousIndex = getStartIndex() - pageSize;
		if (previousIndex < 0)
			return 0;
		else
			return previousIndex;
	}

	/**
	 * 获取总页数
	 * @return
	 */
	private void setPageCount() {
		int count = totalCount / pageSize;
		if (totalCount % pageSize > 0){
			count++;
		}
		this.pageCount = count;
	}

	/**
	 * 获取当前页数
	 * @return
	 */
	public int getCurrentPage() {
		return currentPage;
	}

	/**
	 * 获取页起始位置
	 * @return
	 */
	public int getLastIndex() {
		return indexes[indexes.length - 1];
	}

	/**
	 * 获取当前页数
	 * @return
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * 获取数据内容
	 * @return
	 */
	public List<E> getItems() {
		return items;
	}

	public int[] getPageIndexs() {
		return pageIndexs;
	}

	public boolean isFirstPage() {
		return isFirstPage;
	}

	public boolean isLastPage() {
		return isLastPage;
	}

	public int getPageCount() {
		return pageCount;
	}

	

}
