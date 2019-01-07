package org.mysoft.common;

import org.mysoft.vo.OrderVO;

import lombok.Data;

@Data
public class PurchaseOrderResponse {

	private Boolean success;
	
	private String userMessage;
	
	private String errorCode;
	
	private String errorDesc;
	
	private String errorMessage;
	
	/**
	 * This will be populated if success is TRUE
	 */
	private OrderVO orderVO;
	
}
