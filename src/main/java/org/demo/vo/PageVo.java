package org.demo.vo;

import java.io.Serializable;
import java.util.List;

public class PageVo<T> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long totalAmount;
	private int totalPages;
	private int currentPage;
	private List<T> content;
	
	public Long getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Long totalAmount) {
		this.totalAmount = totalAmount;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public List<T> getContent() {
		return content;
	}
	public void setContent(List<T> content) {
		this.content = content;
	}
}
