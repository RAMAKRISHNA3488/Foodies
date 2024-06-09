package com.Foodies.service;

import java.util.List;

import com.Foodies.Exception.FoodException;
import com.Foodies.Exception.RestaurantException;
import com.Foodies.model.Food;
import com.Foodies.model.IngredientCategory;
import com.Foodies.model.IngredientsItem;
import com.Foodies.repository.IngredientsCategoryRepository;

public interface IngredientsService {
	
	public IngredientCategory createIngredientsCategory(
			String name,Long restaurantId) throws RestaurantException;

	public IngredientCategory findIngredientsCategoryById(Long id) throws Exception;

	public List<IngredientCategory> findIngredientsCategoryByRestaurantId(Long id) throws Exception;
	
	public List<IngredientsItem> findRestaurantsIngredients(
			Long restaurantId);

	
	public IngredientsItem createIngredientsItem(Long restaurantId, 
			String ingredientName,Long ingredientCategoryId) throws Exception;

	public IngredientsItem updateStoke(Long id) throws Exception;
	
}
