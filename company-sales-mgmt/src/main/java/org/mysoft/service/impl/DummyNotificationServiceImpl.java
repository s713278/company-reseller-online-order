package org.mysoft.service.impl;

import org.mysoft.service.NotificationService;
import org.mysoft.vo.ProductVO;

public class DummyNotificationServiceImpl implements NotificationService<ProductVO> {

	@Override
	public void sendEMailNotification(ProductVO productVo) {
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>> Notified COMPANY for product >>>>>>>>"+productVo);
	}

	@Override
	public void sendSMSNotification(String message) {
		
	}

}
