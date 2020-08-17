package com.dbs.order.service.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.dbs.order.service.entity.OrderInfo;
import com.dbs.order.service.feign.client.ItemClient;
import com.dbs.order.service.model.Order;
import com.dbs.order.service.repository.OrderRepository;

@SpringBootTest
class OrderServiceTest {

	// @Autowired
	@InjectMocks
	private OrderService orderService;

	@Mock
	private ItemClient itemClient;

	@Mock
	private OrderRepository orderRepository;

	private static Order order;
	private static OrderInfo orderInfo;
	private static Long itemId1 = 1L;
	private static Long itemId2 = 2L;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		order = new Order();
		order.setCustomerName("Customer1");
		order.setShippingAddress("Singapore");
		order.setItems(new HashMap<Long, Integer>() {
			{
				put(itemId1, 2);
				put(itemId2, 3);
			}
		});
		orderInfo = new OrderInfo(order);
		orderInfo.setId(1L);
	}

	@BeforeEach
	public void setUpBeforeEach() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void testCreateOrder() {
		when(itemClient.getItemCost(itemId1)).thenReturn(10.0);
		when(itemClient.updateItemQuantity(1L, 2)).thenReturn(true);
		when(itemClient.getItemCost(itemId2)).thenReturn(20.0);
		when(itemClient.updateItemQuantity(2L, 3)).thenReturn(true);
		when(orderRepository.save(Mockito.anyObject())).thenReturn(orderInfo);
		assertEquals(1L, orderService.createOrder(order).getId());
	}

	@Test
	void testGetOrderInfo() {
		Optional<OrderInfo> optionalOrderInfo = Optional.of(orderInfo);
		when(orderRepository.findById(1L)).thenReturn(optionalOrderInfo);
		assertEquals("Customer1", orderService.getOrderInfo(1L).getCustomer());
	}

}
