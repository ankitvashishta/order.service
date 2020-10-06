package com.ankit.order.service.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ankit.order.service.entity.OrderInfo;

@Repository
public interface OrderRepository extends CrudRepository<OrderInfo, Long> {
}
