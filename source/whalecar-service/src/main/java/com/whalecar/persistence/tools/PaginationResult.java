package com.whalecar.persistence.tools;

import java.util.List;

public class PaginationResult<E> {

	/**
	 * 分页记录数
	 */
	private int pageSize = PaginationUtils.DEFAULT_PAGESIZE;

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
	 * 记录数据
	 */
	private List<E> items;
	
	public PaginationResult(int pageSize, int startIndex) {
		setPageSize(pageSize);
		setStartIndex(startIndex);

	}

	public PaginationResult(List<E> items, int totalCount) {
		setPageSize(PaginationUtils.DEFAULT_PAGESIZE);
		setTotalCount(totalCount);
		setItems(items);
		setStartIndex(0);

	}

	public PaginationResult(List<E> items, int totalCount, int startIndex) {
		setPageSize(PaginationUtils.DEFAULT_PAGESIZE);
		setTotalCount(totalCount);
		setItems(items);
		setStartIndex(startIndex);

	}

	public PaginationResult(List<E> items, int totalCount, int pageSize,
			int startIndex) {
		setPageSize(pageSize);
		setTotalCount(totalCount);
		setItems(items);
		setStartIndex(startIndex);
	}

	/**
	 * 设置总记录数
	 * @param totalCount
	 */
	public void setTotalCount(int totalCount) {
		if (totalCount > 0) {
			this.totalCount = totalCount;
			int count = totalCount / pageSize;
			if (totalCount % pageSize > 0)
				count++;
			indexes = new int[count];
			for (int i = 0; i < count; i++) {
				indexes[i] = pageSize * i;
			}
		} else {
			this.totalCount = 0;
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
	 * 设置每页起始位置列表
	 * @param indexes
	 */
	public void setIndexes(int[] indexes) {
		this.indexes = indexes;
	}

	/**
	 * 获取每页起始位置列表
	 * @return
	 */
	public int[] getIndexes() {
		return indexes;
	}

	/**
	 * 设置起始位置
	 * @param startIndex
	 */
	public void setStartIndex(int startIndex) {
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
	public int getPageCount() {
		int count = totalCount / pageSize;
		if (totalCount % pageSize > 0)
			count++;
		return count;
	}

	/**
	 * 获取当前页数
	 * @return
	 */
	public int getCurrentPage() {
		return getStartIndex() / pageSize + 1;
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
	 * 获取当前页大小
	 * @param pageSize
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * 获取数据内容
	 * @return
	 */
	public List<E> getItems() {
		return items;
	}

	/**
	 * 存储数据内容
	 * @param items
	 */
	public void setItems(List<E> items) {
		this.items = items;
	}

	
}
