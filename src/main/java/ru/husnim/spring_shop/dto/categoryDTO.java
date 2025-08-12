package ru.husnim.spring_shop.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class categoryDTO {

    @Size(min = 1)
    @Column(unique = true)
    @NotBlank(message = "Название не может быть пустым")
    private String name;

}
