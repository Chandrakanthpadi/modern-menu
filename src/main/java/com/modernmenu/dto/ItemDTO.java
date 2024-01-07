package com.modernmenu.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ItemDTO(

    @NotBlank(message = "Item name cannot be blank") String itemName,
    @NotNull(message = "Price cannot be blank") double price,
    @NotBlank(message = "Type cannot be blank") String type

) {
}
