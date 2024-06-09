package com.Foodies.service;

import com.Foodies.Exception.CartException;
import com.Foodies.Exception.CartItemException;
import com.Foodies.Exception.FoodException;
import com.Foodies.Exception.UserException;
import com.Foodies.model.Cart;
import com.Foodies.model.CartItem;
import com.Foodies.model.Food;
import com.Foodies.model.User;
import com.Foodies.request.AddCartItemRequest;
import com.Foodies.request.UpdateCartItemRequest;

public interface CartSerive {

	public CartItem addItemToCart(AddCartItemRequest req, String jwt) throws UserException, FoodException, CartException, CartItemException;

	public CartItem updateCartItemQuantity(Long cartItemId,int quantity) throws CartItemException;

	public Cart removeItemFromCart(Long cartItemId, String jwt) throws UserException, CartException, CartItemException;

	public Long calculateCartTotals(Cart cart) throws UserException;
	
	public Cart findCartById(Long id) throws CartException;
	
	public Cart findCartByUserId(Long userId) throws CartException, UserException;
	
	public Cart clearCart(Long userId) throws CartException, UserException;
	

	

}
