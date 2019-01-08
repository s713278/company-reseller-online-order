package org.mysoft.service;

public interface NotificationService<T> {

	void sendEMailNotification(T t);
	void sendSMSNotification(String message);
}

