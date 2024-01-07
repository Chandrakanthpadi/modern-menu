package com.modernmenu.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.modernmenu.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, String> {

  public Optional<Category> findByRestaurantIdAndCategoryName(String restaurnatId,
      String categoryName);

  public List<Category> findByRestaurantId(String restaurnatId);

  public Category findByCategoryName(String categoryName);
}
