package com.dbs.order.service.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.ws.rs.NotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dbs.order.service.entity.OrderInfo;
import com.dbs.order.service.feign.client.ItemClient;
import com.dbs.order.service.model.Order;
import com.dbs.order.service.repository.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private ItemClient itemClient;

	@Transactional
	public OrderInfo createOrder(Order order) {
		OrderInfo orderInfo = new OrderInfo(order);
		List<Double> costList = new ArrayList<>();
		order.getItems().entrySet().stream().forEach(item -> {
			costList.add(item.getValue() * itemClient.getItemCost(item.getKey()));
			itemClient.updateItemQuantity(item.getKey(), item.getValue());
		});
		orderInfo.setTotal(costList.stream().collect(Collectors.summingDouble(Double::doubleValue)));
		return orderRepository.save(orderInfo);
	}

	public OrderInfo getOrderInfo(Long id) {
		Optional<OrderInfo> orderInfo = orderRepository.findById(id);
		if (orderInfo.isPresent())
			return orderInfo.get();
		throw new NotFoundException("Order Not Found");
	}

	public List<OrderInfo> getAllItems() {
		List<OrderInfo> orders = new ArrayList<>();
		orderRepository.findAll().iterator().forEachRemaining(orders::add);
		return orders;
	}

}
