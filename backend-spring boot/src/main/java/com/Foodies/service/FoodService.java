package com.Foodies.service;

import java.util.List;

import com.Foodies.Exception.FoodException;
import com.Foodies.Exception.RestaurantException;
import com.Foodies.model.Category;
import com.Foodies.model.Food;
import com.Foodies.model.Restaurant;
import com.Foodies.request.CreateFoodRequest;

public interface FoodService {

	public Food createFood(CreateFoodRequest req,Category category,
						   Restaurant restaurant) throws FoodException, RestaurantException;

	void deleteFood(Long foodId) throws FoodException;
	
	public List<Food> getRestaurantsFood(Long restaurantId,
			boolean isVegetarian, boolean isNonveg, boolean isSeasonal,String foodCategory) throws FoodException;
	
	public List<Food> searchFood(String keyword);
	
	public Food findFoodById(Long foodId) throws FoodException;

	public Food updateAvailibilityStatus(Long foodId) throws FoodException;
}
