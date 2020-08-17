package com.dbs.order.service.feign.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "item", url = "localhost:8070/item")
public interface ItemClient {

	@PutMapping("/{id}/update")
	public boolean updateItemQuantity(@PathVariable Long id, @RequestParam Integer quantity);

	@GetMapping("/{id}/cost")
	public Double getItemCost(@PathVariable Long id);
}
