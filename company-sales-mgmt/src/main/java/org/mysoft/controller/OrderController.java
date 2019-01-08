/**
 * 
 */
package org.mysoft.controller;

import org.mysoft.common.PurchaseOrderRequest;
import org.mysoft.common.PurchaseOrderResponse;
import org.mysoft.service.OrderService;
import org.mysoft.vo.OrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

/**
 * @author Swamy K
 *This API intending for creating 
 *	- Order
 *	- Find order
 *  - Update Order
 *  - Get orders by sellerId
 */
@RestController
@RequestMapping("/api")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@ApiOperation("Create Seller Order")
	@ResponseBody
	@PostMapping(value="/createOrder", consumes=MediaType.APPLICATION_JSON_UTF8_VALUE ,  produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<PurchaseOrderResponse> createOrder(@RequestBody PurchaseOrderRequest purchaseOrderRequest)
	{
		PurchaseOrderResponse response = orderService.createOrder(purchaseOrderRequest);
		ResponseEntity<PurchaseOrderResponse> responseEntity = new ResponseEntity<PurchaseOrderResponse>(response,HttpStatus.OK);
		return responseEntity;
	}
	
	@ApiOperation("Search order by orderID")
	@ResponseBody
	@GetMapping(value="/getOrder/{orderId}", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<OrderVO> getOrderById(@PathVariable long orderId){
		ResponseEntity<OrderVO> responseEntity = null;
		OrderVO orderVO = orderService.findOrderById(orderId);
		if(orderVO == null) {
			responseEntity = new ResponseEntity<OrderVO>(HttpStatus.NOT_FOUND);
		}else {
			responseEntity = new ResponseEntity<OrderVO>(orderVO,HttpStatus.FOUND);
		}
		return responseEntity;
	}
}
