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
	@ApiModelProperty(example="false")
	private Boolean optForOOSProducts;
	
//	private SellerVO seller;
	
	//Registered seller id
	@ApiModelProperty(value="sellerId",example="100")
	Long sellerId;
	
	@ApiModelProperty(example="Do delivery by January-26,2019 without fail")
	private String notesToCompany;
	
}

