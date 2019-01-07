/**
 * 
 */
package org.mysoft.controller;

import org.mysoft.common.PurchaseOrderRequest;
import org.mysoft.common.PurchaseOrderResponse;
import org.mysoft.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Swamy K
 *
 */
@RestController
@RequestMapping("/api")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@ResponseBody
	@PostMapping(value="/createOrder", consumes=MediaType.APPLICATION_JSON_UTF8_VALUE ,  produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public PurchaseOrderResponse createOrder(@RequestBody PurchaseOrderRequest purchaseOrderRequest)
	{
		return orderService.createOrder(purchaseOrderRequest);
	}
}
