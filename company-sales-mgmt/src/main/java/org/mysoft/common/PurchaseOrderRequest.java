package org.mysoft.common;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 
 * @author Swamy K
 *
 */
@Data
@ApiModel
public class PurchaseOrderRequest {

	private List<SellerItemInfo> products;
	
	/**
	 * This is for holding if seller wants to complete the order even if order's products or OOS.
	 */
	private Boolean optForOOSProducts;
	
//	private SellerVO seller;
	
	//Registered seller id
	@ApiModelProperty(value="sellerId",example="100")
	Long sellerId;
	private String notesToCompany;
	
}

