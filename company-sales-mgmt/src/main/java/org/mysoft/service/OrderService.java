package org.mysoft.service;

import org.mysoft.common.PurchaseOrderRequest;
import org.mysoft.common.PurchaseOrderResponse;

public interface OrderService {

	public PurchaseOrderResponse createOrder(PurchaseOrderRequest purchaseOrderRequest);
}
