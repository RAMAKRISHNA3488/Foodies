package com.Foodies.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Foodies.Exception.CartException;
import com.Foodies.Exception.OrderException;
import com.Foodies.Exception.RestaurantException;
import com.Foodies.Exception.UserException;
import com.Foodies.model.Address;
import com.Foodies.model.Cart;
import com.Foodies.model.CartItem;
import com.Foodies.model.Notification;
import com.Foodies.model.Order;
import com.Foodies.model.OrderItem;
import com.Foodies.model.PaymentResponse;
import com.Foodies.model.Restaurant;
import com.Foodies.model.User;
import com.Foodies.repository.AddressRepository;
import com.Foodies.repository.CartRepository;
import com.Foodies.repository.OrderItemRepository;
import com.Foodies.repository.OrderRepository;
import com.Foodies.repository.RestaurantRepository;
import com.Foodies.repository.UserRepository;
import com.Foodies.request.CreateOrderRequest;
import com.stripe.exception.StripeException;
@Service
public class OrderServiceImplementation implements OrderService {
	
	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private CartSerive cartService;
	@Autowired
	private OrderItemRepository orderItemRepository;
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private RestaurantRepository restaurantRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PaymentService paymentSerive;
	
	@Autowired
	private NotificationService notificationService;
	

	

//	@Override
//	public PaymentResponse createOrder(CreateOrderRequest order,User user) throws UserException, RestaurantException, CartException, StripeException {
//		
//	    Address shippAddress = order.getDeliveryAddress();
//
//	    
//	    Address savedAddress = addressRepository.save(shippAddress);
//	    
//	    if(!user.getAddresses().contains(savedAddress)) {
//	    	user.getAddresses().add(savedAddress);
//	    }
//	    
//		
//		System.out.println("user addresses --------------  "+user.getAddresses());
//		   
//		 userRepository.save(user);
//	    
//	    Optional<Restaurant> restaurant = restaurantRepository.findById(order.getRestaurantId());
//	    if(restaurant.isEmpty()) {
//	    	throw new RestaurantException("Restaurant not found with id "+order.getRestaurantId());
//	    }
//	    
//	    Order createdOrder = new Order();
//	    
//	    createdOrder.setCustomer(user);
//	    createdOrder.setDeliveryAddress(savedAddress);
//	    createdOrder.setCreatedAt(new Date());
//	    createdOrder.setOrderStatus("PENDING");
//	    createdOrder.setRestaurant(restaurant.get());
//
//        Cart cart = cartService.findCartByUserId(user.getId());
//        
//	    List<OrderItem> orderItems = new ArrayList<>();
//	    
//	    for (CartItem cartItem : cart.getItems()) {
//	        OrderItem orderItem = new OrderItem();
//	       orderItem.setFood(cartItem.getFood());
//	       orderItem.setIngredients(cartItem.getIngredients());
//	       orderItem.setQuantity(cartItem.getQuantity());
//	        orderItem.setTotalPrice(cartItem.getFood().getPrice()* cartItem.getQuantity());
//
//	        OrderItem savedOrderItem = orderItemRepository.save(orderItem);
//	        orderItems.add(savedOrderItem);
//	    }
//   
//	     Long totalPrice = cartService.calculateCartTotals(cart);
//
//	    createdOrder.setTotalAmount(totalPrice);
//	    createdOrder.setRestaurant(restaurant.get());
//  
//	    createdOrder.setItems(orderItems);
//	    Order savedOrder = orderRepository.save(createdOrder);
//
//	   restaurant.get().getOrders().add(savedOrder);
//	   
//	   restaurantRepository.save(restaurant.get());
//	   
//
//	   
//	   PaymentResponse res=paymentSerive.generatePaymentLink(savedOrder);
//	   return res;
//
//	}

	@Override
	public void cancelOrder(Long orderId) throws OrderException {
           Order order =findOrderById(orderId);
           if(order==null) {
        	   throw new OrderException("Order not found with the id "+orderId);
           }
		
		    orderRepository.deleteById(orderId);
		
	}
	
	public Order findOrderById(Long orderId) throws OrderException {
		Optional<Order> order = orderRepository.findById(orderId);
		if(order.isPresent()) return order.get();
		
		throw new OrderException("Order not found with the id "+orderId);
	}

	@Override
	public List<Order> getUserOrders(Long userId) throws OrderException {
		List<Order> orders=orderRepository.findAllUserOrders(userId);
		return orders;
	} 

	@Override
	public List<Order> getOrdersOfRestaurant(Long restaurantId,String orderStatus) throws OrderException, RestaurantException {
		
			List<Order> orders = orderRepository.findOrdersByRestaurantId(restaurantId);
			
			if(orderStatus!=null) {
				orders = orders.stream()
						.filter(order->order.getOrderStatus().equals(orderStatus))
						.collect(Collectors.toList());
			}
			
			return orders;
	}
//    private List<MenuItem> filterByVegetarian(List<MenuItem> menuItems, boolean isVegetarian) {
//    return menuItems.stream()
//            .filter(menuItem -> menuItem.isVegetarian() == isVegetarian)
//            .collect(Collectors.toList());
//}
	
	

	@Override
	public Order updateOrder(Long orderId, String orderStatus) throws OrderException {
		Order order=findOrderById(orderId);
		
		System.out.println("--------- "+orderStatus);
		
		if(orderStatus.equals("OUT_FOR_DELIVERY") || orderStatus.equals("DELIVERED") 
				|| orderStatus.equals("COMPLETED") || orderStatus.equals("PENDING")) {
			order.setOrderStatus(orderStatus);
			Notification notification=notificationService.sendOrderStatusNotification(order);
			return orderRepository.save(order);
		}
		else throw new OrderException("Please Select A Valid Order Status");
		
		
	}
	
	

}
