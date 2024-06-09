package com.Foodies.service;

import java.util.List;

import com.Foodies.model.Notification;
import com.Foodies.model.Order;
import com.Foodies.model.Restaurant;
import com.Foodies.model.User;

public interface NotificationService {
	
	public Notification sendOrderStatusNotification(Order order);
	public void sendRestaurantNotification(Restaurant restaurant, String message);
	public void sendPromotionalNotification(User user, String message);
	
	public List<Notification> findUsersNotification(Long userId);

}
