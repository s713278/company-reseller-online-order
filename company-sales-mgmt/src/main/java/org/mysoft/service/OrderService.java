package org.mysoft.service;

import org.mysoft.common.PurchaseOrderRequest;
import org.mysoft.common.PurchaseOrderResponse;
import org.mysoft.vo.OrderVO;

public interface OrderService {

	public PurchaseOrderResponse createOrder(PurchaseOrderRequest purchaseOrderRequest);
	
	public OrderVO findOrderById(Long orderId);
}
