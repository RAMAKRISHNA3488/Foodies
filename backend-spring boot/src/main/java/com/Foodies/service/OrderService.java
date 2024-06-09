package com.Foodies.service;

import java.util.List;

import com.Foodies.Exception.CartException;
import com.Foodies.Exception.OrderException;
import com.Foodies.Exception.RestaurantException;
import com.Foodies.Exception.UserException;
import com.Foodies.model.Order;
import com.Foodies.model.PaymentResponse;
import com.Foodies.model.User;
import com.Foodies.request.CreateOrderRequest;
import com.stripe.exception.StripeException;

public interface OrderService {
	
	// public PaymentResponse createOrder(CreateOrderRequest order, User user) throws UserException, RestaurantException, CartException, StripeException;
	 
	 public Order updateOrder(Long orderId, String orderStatus) throws OrderException;
	 
	 public void cancelOrder(Long orderId) throws OrderException;
	 
	 public List<Order> getUserOrders(Long userId) throws OrderException;
	 
	 public List<Order> getOrdersOfRestaurant(Long restaurantId,String orderStatus) throws OrderException, RestaurantException;
	 

}
