package com.modernmenu.dto;

import java.util.List;

import jakarta.validation.constraints.NotBlank;

public record ItemsAddRequestRecord(

    @NotBlank(message = "RestaurantId cannot be blank") String restaurantId,
    @NotBlank(message = "Category name cannot be blank") String categoryName,
    @NotBlank(message = "Add aleast one item") List<ItemDTO> items

) {
}
