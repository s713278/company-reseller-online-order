package org.mysoft.schedulars;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.mysoft.service.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class PaymentServiceSchedular implements GenericSchedular{

	private static final Logger LOGGER = LoggerFactory.getLogger(PaymentServiceSchedular.class);

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("HH:mm:ss");
    
    @Autowired
    PaymentService paymentService;

    @Scheduled(cron = "${scheduling.duepayments.job.cron}")
	@Override
	public void processJob() {
    	LOGGER.info("The time is now {}", DATE_FORMAT.format(new Date()));
    	paymentService.processPayment();
	}
}
