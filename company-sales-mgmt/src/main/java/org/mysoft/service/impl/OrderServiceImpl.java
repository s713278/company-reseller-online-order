package org.mysoft.service.impl;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;

import javax.transaction.Transactional;

import org.mysoft.common.PaymentStatus;
import org.mysoft.common.PaymentType;
import org.mysoft.common.PurchaseOrderRequest;
import org.mysoft.common.PurchaseOrderResponse;
import org.mysoft.common.SellerItemInfo;
import org.mysoft.repository.OrderRepository;
import org.mysoft.repository.ProductRepository;
import org.mysoft.repository.SellerRepository;
import org.mysoft.service.OrderService;
import org.mysoft.vo.CartItem;
import org.mysoft.vo.OrderVO;
import org.mysoft.vo.PaymentVO;
import org.mysoft.vo.ProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

	@Autowired // Field Injection
	private ProductRepository productRepository;

	@Autowired // Field Injection
	private OrderRepository orderRepository;

	@Autowired
	private SellerRepository sellerRepository;

	/**
	 * 
	 */
	@Override
	public PurchaseOrderResponse createOrder(PurchaseOrderRequest purchaseOrderRequest) {
		List<SellerItemInfo> products = purchaseOrderRequest.getProducts();
		PurchaseOrderResponse response = new PurchaseOrderResponse();
		Map<Long, Long> productIdQtyMap = new ConcurrentHashMap<Long, Long>();
		if (products == null || products.isEmpty()) {
			response.setSuccess(Boolean.FALSE);
			response.setErrorCode("1000");
			response.setErrorMessage("No product ids found in request");
			return response;
		}
		ArrayList<Long> ids = new ArrayList<Long>();
		for (SellerItemInfo productVO : products) {
			ids.add(productVO.getProductId());
			productIdQtyMap.put(productVO.getProductId(), productVO.getQuantity());
		}

		Iterable<ProductVO> dbProducts = productRepository.findAllById(ids);
		OrderVO orderVO = new OrderVO();
		orderVO.setNotesFromSeller(purchaseOrderRequest.getNotesToCompany());
		orderVO.setSeller(sellerRepository.findById(purchaseOrderRequest.getSellerId()).get());
		createOrderItems(orderVO, dbProducts, productIdQtyMap);
		// Create PO and set it.
		createPO(orderVO);
		orderVO = orderRepository.save(orderVO);
		response.setSuccess(Boolean.TRUE);
		response.setOrderVO(orderVO);
		response.setUserMessage("Order created successfully..");
		return response;
	}

	/**
	 * <p>
	 * 
	 * </p>
	 * 
	 * @param orderVO
	 * @param dbProducts
	 * @return
	 */
	private OrderVO createOrderItems(OrderVO orderVO, Iterable<ProductVO> dbProducts, Map<Long, Long> productIdQtyMap) {
		List<CartItem> cartItems = new ArrayList<CartItem>();
		double orderSubTotal = 0d;
		double tax = 0;
		double disccount = 0;
		double total = 0;
		Boolean allProductsInStock;
		double itemPrice = 0d;

		ProductVO dbProductVO = null;
		CartItem orderItem = null;
		for (Iterator<ProductVO> iterator = dbProducts.iterator(); iterator.hasNext();) {
			orderItem = new CartItem();
			dbProductVO = iterator.next();
			Long sellerqty = productIdQtyMap.get(dbProductVO.getId());
			Long dbQuanity = dbProductVO.getStockLevel();
			itemPrice = sellerqty * dbProductVO.getPrice();

			// Setting the seller info
			orderItem.setProductVO(dbProductVO);
			orderItem.setUnitPrice(dbProductVO.getPrice());
			orderItem.setSellerQuanity(sellerqty);
			if (dbQuanity == 0) {
				orderItem.setAllocatedQuanity(0L);
				// TODO : Send notification to Company admin/marketting team
			} else if (dbProductVO.getStockLevel() >= sellerqty) {
				orderItem.setAllocatedQuanity(sellerqty);
			} else {
				// If stock level is lesser than the seller requested quantity
				orderItem.setAllocatedQuanity(dbQuanity);
			}
			//start : Updating the stock level
			dbProductVO.setStockLevel(dbQuanity-sellerqty);
			//end : Updating the stock level
			orderItem.setItemPrice(Double.valueOf(itemPrice));
			cartItems.add(orderItem);
			// Reset
			orderSubTotal = orderSubTotal + itemPrice;
			itemPrice = 0;
		}

		total = (orderSubTotal + tax - disccount);

		// Setting Order price info
		orderVO.setSubTotal(orderSubTotal);
		orderVO.setTax(tax);
		orderVO.setDisccount(disccount);
		orderVO.setTotal(total);
		orderVO.setCartItems(cartItems);
		return orderVO;
	}

	/**
	 * <p>
	 * This method will create PO for created seller order and that would be paid on
	 * each month of 30.
	 * </P>
	 * 
	 * @param orderVO
	 */
	private void createPO(OrderVO orderVO) {
		PaymentVO paymentVO = new PaymentVO();
		paymentVO.setPaymentType(PaymentType.PO_GENERATED.toString());
		paymentVO.setPaymentStatus(PaymentStatus.PENDING.toString());
		paymentVO.setAmout(orderVO.getTotal());
		Calendar calendar = Calendar.getInstance();
		int currentDate = calendar.get(Calendar.DATE);
		int noOfDaysFordueDate = 0;
		if (currentDate <= 30) {
			noOfDaysFordueDate = 30 - currentDate;
		}
		calendar.add(Calendar.DATE, noOfDaysFordueDate);
		TimeZone tz = calendar.getTimeZone();
		ZoneId zid = tz == null ? ZoneId.systemDefault() : tz.toZoneId();
		paymentVO.setDueDate(LocalDateTime.ofInstant(calendar.toInstant(), zid));
		orderVO.setPaymentInfo(paymentVO);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.mysoft.service.OrderService#findOrderById(java.lang.Long)
	 */
	@Override
	public OrderVO findOrderById(Long orderId) {
		Optional<OrderVO> optional = orderRepository.findById(orderId);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

}
