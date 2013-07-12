package com.whalecar.persistence.tools;

public class PaginationUtils {

	/**
	 * 默认每页记录数
	 */
	public final static int DEFAULT_PAGESIZE = 12;
	
	/**
	 * 计算数据起始位置
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public static int getStartIndex(int pageIndex,int pageSize){
		return pageIndex * pageSize;
	}
}
