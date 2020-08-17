package com.dbs.order.service.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dbs.order.service.entity.OrderInfo;
import com.dbs.order.service.model.Order;
import com.dbs.order.service.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService orderService;

	/**
	 * Create a new order.
	 * 
	 * @param order
	 * @return
	 */
	@PostMapping("/create")
	public OrderInfo createOrder(@Valid @RequestBody Order order) {
		return orderService.createOrder(order);
	}

	/**
	 * Retrieve an order info.
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}/info")
	public OrderInfo getOrderInfo(@PathVariable(value = "id") Long id) {
		return orderService.getOrderInfo(id);
	}
}
