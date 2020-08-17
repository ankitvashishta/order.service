package com.dbs.order.service.model;

import java.util.Map;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class Order {

	@NotBlank(message = "Empty Customer Name!")
	private String customerName;
	@NotBlank(message = "Empty Shipping Address!")
	private String shippingAddress;
	@NotNull
	private Map<Long, Integer> items;

}
