package com.utils;

// 类 JQPageInfo：该类设计用于存储查询的分页和排序参数，通常用于服务器端处理。
public class JQPageInfo {
	// 私有成员变量，用于存储页码、每页记录数（限制）、排序索引（列）、排序方向和偏移量。
	private Integer page;      // 当前页码
	private Integer limit;     // 每页的记录数
	private String sidx;       // 排序索引：按哪个列名排序
	private String order;      // 排序方向：'asc' 升序，'desc' 降序
	private Integer offset;    // 当前页上的第一条记录的偏移量

	// 获取页码的getter方法：返回当前页码
	public Integer getPage() {
		return page;
	}

	// 设置页码的setter方法：设置当前页码
	public void setPage(Integer page) {
		this.page = page;
	}

	// 获取限制（每页记录数）的getter方法：返回每页的记录数
	public Integer getLimit() {
		return limit;
	}

	// 设置限制（每页记录数）的setter方法：设置每页的记录数
	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	// 获取排序索引的getter方法：返回用于排序的列名
	public String getSidx() {
		return sidx;
	}

	// 设置排序索引的setter方法：设置排序的列名
	public void setSidx(String sidx) {
		this.sidx = sidx;
	}

	// 获取排序方向的getter方法：返回排序方向（'asc' 或 'desc'）
	public String getOrder() {
		return order;
	}

	// 设置排序方向的setter方法：设置排序方向
	public void setOrder(String order) {
		this.order = order;
	}

	// 获取偏移量的getter方法：返回当前页上第一条记录的偏移量
	public Integer getOffset() {
		return offset;
	}

	// 设置偏移量的setter方法：设置当前页上第一条记录的偏移量
	public void setOffset(Integer offset) {
		this.offset = offset;
	}

}