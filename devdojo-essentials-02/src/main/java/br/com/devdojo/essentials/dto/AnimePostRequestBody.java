package br.com.devdojo.essentials.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class AnimePostRequestBody {

    @NotEmpty(message = "campo nome não pode ser nulo")
    @NotNull(message = "campo nome não pode ser nulo")
    private String name;
}
