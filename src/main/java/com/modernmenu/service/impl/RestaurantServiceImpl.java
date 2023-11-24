package com.modernmenu.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import com.modernmenu.entity.Restaurant;
import com.modernmenu.exception.ResourceAlreadyExists;
import com.modernmenu.repository.CategoryRepository;
import com.modernmenu.repository.RestaurantRepository;
import com.modernmenu.service.RestaurantService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

	
	private final RestaurantRepository restaurantRepository;
	private final CategoryRepository categoryRepository;

	@Override
	public Optional<Restaurant> getRestaurant(String  restaurantName) {
		
		return restaurantRepository.findByRestaurantName(restaurantName);
		
	}

	@Override
	public Restaurant addRestaurant(Restaurant restaurant) {
		
		Optional<Restaurant> isRestaurnatExits = restaurantRepository.findByRestaurantName(restaurant.getRestaurantName());
		
		if(isRestaurnatExits.isPresent()) {
			throw new ResourceAlreadyExists();
		}
		
		return restaurantRepository.saveAndFlush(restaurant);
		
	}

	@Override
	public void removeRestaurant(Restaurant restaurant) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateRestaurant(String restaurantName) {
		Restaurant restaurant = restaurantRepository.findByRestaurantName(restaurantName).get();
		restaurant.setOpen(!restaurant.isOpen());
		restaurantRepository.saveAndFlush(restaurant);
	}

	@Override
	public Restaurant findByrestaurantId(String restaurantId) {
		
		Restaurant restaurant =  restaurantRepository.findById(restaurantId).get();
		System.out.println(restaurant.toString());
		return restaurant;
		
	}

	@Override
	public List<String> getCategory(String restaurantId) {
		return categoryRepository.findByRestaurantId(restaurantId).stream().map(cateogry -> {
			return cateogry.getCategoryName();
		}).collect(Collectors.toList());
	}
	
}
