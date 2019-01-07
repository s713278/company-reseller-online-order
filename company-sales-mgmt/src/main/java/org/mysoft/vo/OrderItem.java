package org.mysoft.vo;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;

@Data
@Entity
@Table(name="ORDER_ITEM_INFO")
public class OrderItem {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@OneToOne(fetch=FetchType.LAZY)
	private ProductVO productVO;
	
	//Seller requested product quantity
	private Long sellerQuanity;
	
	//Company allocated product quantity
	private Long allocatedQuanity;
	
	private Double unitPrice;
	
	//allocatedQuanity * unitPrice
	private Double itemPrice;
	
	@Transient
	private Boolean allItemsAvailable;

	/**
	 * @return the isAllItemsAvailable
	 */
	public Boolean IsAllItemsAvailable() {
		return (sellerQuanity == allocatedQuanity);
	}
}
