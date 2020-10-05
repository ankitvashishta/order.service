package com.dbs.order.service.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbs.order.service.entity.OrderInfo;
import com.dbs.order.service.exception.ItemNotFoundException;
import com.dbs.order.service.feign.client.ItemClient;
import com.dbs.order.service.model.Order;
import com.dbs.order.service.repository.OrderRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.exception.HystrixRuntimeException;
import com.netflix.hystrix.exception.HystrixRuntimeException.FailureType;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private ItemClient itemClient;

	public OrderInfo createOrder(OrderInfo orderInfo) {
		return orderRepository.save(orderInfo);
	}

	@HystrixCommand(fallbackMethod = "updateItemQuantity_Fallback")
	public OrderInfo updateItemQuantity(Order order) {
		OrderInfo orderInfo = new OrderInfo(order);
		List<Double> costList = new ArrayList<>();
		order.getItems().entrySet().forEach(item -> {
			costList.add(item.getValue() * itemClient.getItemCost(item.getKey()));
			itemClient.updateItemQuantity(item.getKey(), item.getValue());
		});
		orderInfo.setTotal(costList.stream().collect(Collectors.summingDouble(Double::doubleValue)));
		return orderInfo;
	}

	@SuppressWarnings("unused")
	private OrderInfo updateItemQuantity_Fallback(Order order) {
		throw new HystrixRuntimeException(FailureType.TIMEOUT, null, "Item client service down!! Fallback initiated.",
				null, null);
	}

	public OrderInfo getOrderInfo(Long id) {
		Optional<OrderInfo> orderInfo = orderRepository.findById(id);
		if (orderInfo.isPresent())
			return orderInfo.get();
		throw new ItemNotFoundException();
	}

	public List<OrderInfo> getAllItems() {
		List<OrderInfo> orders = new ArrayList<>();
		orderRepository.findAll().iterator().forEachRemaining(orders::add);
		return orders;
	}

}
