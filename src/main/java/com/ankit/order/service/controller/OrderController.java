package com.ankit.order.service.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ankit.order.service.entity.OrderInfo;
import com.ankit.order.service.model.Order;
import com.ankit.order.service.service.OrderService;

@RestController
@RequestMapping("/order")
@CrossOrigin
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
		OrderInfo orderInfo = orderService.updateItemQuantity(order);
		return orderService.createOrder(orderInfo);
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

	/**
	 * Get all orders.
	 * 
	 * @return
	 */
	@GetMapping("/all/orders")
	public List<OrderInfo> getAllOrders() {
		return orderService.getAllItems();
	}
}
