package ru.husnim.spring_shop.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.util.Set;
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

    @NotEmpty(message = "Список категорий не может быть пустым")
    private Set<Long> categoryIds; // Добавляем поле для ID категорий

}
