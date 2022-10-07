package br.com.devdojo.essentials.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnimeDto {
    private int id;


    @Size(min = 4, max = 100)
    @NotEmpty(message = "campo nome não pode ser nulo")
    @Schema(description = "este campo recebe o nome do anime", example = "you you hack-show")
    private String name;
}

// validar regexp
//@Pattern(regexp = "^[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}$")