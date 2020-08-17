package com.dbs.order.service.entity;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.dbs.order.service.model.Order;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "order_info")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class OrderInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotBlank(message = "Empty Customer Name!")
	private String customer;
	@NotNull
	private Date order_date;
	@NotBlank(message = "Empty Shipping Address!")
	private String shipping_address;
	@NotBlank(message = "Empty Order Items List!")
	private String order_items;
	@Min(value = 1, message = "Not Valid Total Cost!")
	private Double total;

	public OrderInfo(Order order) {
		this.customer = order.getCustomerName();
		this.order_date = Date.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime()));
		this.shipping_address = order.getShippingAddress();
		this.order_items = order.getItems().keySet().stream().map(item -> String.valueOf(item))
				.collect(Collectors.joining(","));
		this.total = 0.0;
	}

}
