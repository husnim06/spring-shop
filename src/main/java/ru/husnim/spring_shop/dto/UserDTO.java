package ru.husnim.spring_shop.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDTO {

    @Size(min = 1)
    @NotBlank(message = "Имя не может быть пустым")
    private String name;

    @NotBlank
    @Size(min = 1)
    @Email(message = "Некорректный email")
    private String email;

}
