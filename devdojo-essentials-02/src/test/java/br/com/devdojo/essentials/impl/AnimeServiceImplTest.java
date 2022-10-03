package br.com.devdojo.essentials.impl;

import br.com.devdojo.essentials.dto.AnimeDto;
import br.com.devdojo.essentials.service.AnimeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;


import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class AnimeServiceImplTest {
    @Autowired
    private AnimeService animeService;

    @Test
    void salvarSucesso() {

        //Test., Execução
        AnimeDto response = createAnime();

        //verificação
        assertNotNull(response.getId());

    }

    @Test
    void listarTodos() {
        //Cenário
        AnimeDto response = createAnime();

        //Test., Execução
        List<AnimeDto> listAnime = this.animeService.listarTodosNotPage();

        //verificação
        assertNotNull(listAnime.get(0).getId());
        assertEquals(listAnime.get(0).getName(),response.getName());



    }


//
//    @Test
//    void listarTodosNotPage() {
//    }
//
//    @Test
//    void buscar() {
//    }
//
//    @Test
//    void atualizar() {
//    }
//
//    @Test
//    void deletar() {
//    }

    public AnimeDto createAnime(){
        AnimeDto animeDto = new AnimeDto();
        animeDto.setName("Shin Ikki Tousen");
        return animeService.salvar(animeDto);
    }
}