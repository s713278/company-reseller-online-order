package org.mysoft.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel
@Data
public class SellerItemInfo{
	
	@ApiModelProperty(example="1")
	Long productId;
	
	@ApiModelProperty(example="10")
	Long quanity;
}