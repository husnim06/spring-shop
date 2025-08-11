package ru.husnim.spring_shop.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProductDTO {

    @Size(min = 1)
    @NotBlank(message = "Название не может быть пустым")
    private String name;

    @Positive(message = "Цена должна быть положительной")
    private float price;

    @NotBlank
    @Size(min = 1)
    private String description;

}
