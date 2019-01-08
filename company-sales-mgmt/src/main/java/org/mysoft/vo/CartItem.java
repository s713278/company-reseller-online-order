package org.mysoft.vo;

import java.io.Serializable;

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
@Table(name="CART_ITEMS")
public class CartItem implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@OneToOne(fetch=FetchType.EAGER)
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
