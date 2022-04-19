package com.stefano.api.awm.backend.repository.filter;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class ReleaseFilter {
	
	private String description;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dueDateOf;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate paymentDateUntil;
	
	public String getDescription() {
		return description;
	}
	public LocalDate getDueDateOf() {
		return dueDateOf;
	}
	public LocalDate getPaymentDateUntil() {
		return paymentDateUntil;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setDueDateOf(LocalDate dueDateOf) {
		this.dueDateOf = dueDateOf;
	}
	public void setPaymentDateUntil(LocalDate paymentDateUntil) {
		this.paymentDateUntil = paymentDateUntil;
	}
	
}
