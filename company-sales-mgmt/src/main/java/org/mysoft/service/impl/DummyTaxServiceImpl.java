package org.mysoft.service.impl;

import org.mysoft.service.TaxService;
import org.mysoft.vo.OrderVO;
import org.springframework.stereotype.Service;

@Service
public class DummyTaxServiceImpl implements TaxService<OrderVO>{

	@Override
	public void calculateTax(OrderVO t) {
		
	}

}
