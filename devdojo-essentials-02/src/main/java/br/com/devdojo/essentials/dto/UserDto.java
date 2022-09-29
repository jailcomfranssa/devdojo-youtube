package br.com.devdojo.essentials.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {

    private int id;

    @Size(min = 4, max = 100)
    @NotEmpty(message = "campo nome não pode ser nulo")
    private String name;

    @NotBlank(message = "Email é requerido")
    @Email
    private String email;

    @NotEmpty(message = "campo password não pode ser nulo")
    private String password;

    @NotEmpty(message = "campo about não pode ser nulo")
    private String about;
}
