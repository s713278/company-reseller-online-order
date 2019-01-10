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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
public class SearchOrderController implements IController<PurchaseOrderRequest, PurchaseOrderResponse> {

	@Autowired
	private OrderService orderService;
	
	@Override
	public boolean preHandleRequest(PurchaseOrderRequest request, PurchaseOrderResponse response) {
		return true;
	}

	@Override
	public boolean postHandleRequest(PurchaseOrderRequest request, PurchaseOrderResponse response) {
		return true;
	}

	
	@ApiOperation("Search order by orderID")
	@ResponseBody
	@GetMapping(value="/getOrder/{orderId}", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<PurchaseOrderResponse> handleRequest(PurchaseOrderRequest h) {
		PurchaseOrderResponse orderResponse = new PurchaseOrderResponse();
		ResponseEntity<PurchaseOrderResponse> responseEntity = null;
		OrderVO orderVO = orderService.findOrderById(h.getSellerId());
		if(orderVO == null) {
			responseEntity = new ResponseEntity<PurchaseOrderResponse>(HttpStatus.NOT_FOUND);
		}else {
			orderResponse.setOrderVO(orderVO);
			responseEntity = new ResponseEntity<PurchaseOrderResponse>(orderResponse,HttpStatus.FOUND);
		}
		return responseEntity;
	}

}
