package br.com.devdojo.essentials.dto;

import lombok.*;

import javax.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
public class AnimeDto {
    private int id;


    @Size(min = 4, max = 100)
    @NotEmpty(message = "campo nome não pode ser nulo")
    private String name;
}

// validar regexp
//@Pattern(regexp = "^[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}$")