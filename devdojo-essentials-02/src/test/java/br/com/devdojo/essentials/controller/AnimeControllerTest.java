package br.com.devdojo.essentials.controller;

import br.com.devdojo.essentials.dto.AnimeDto;
import br.com.devdojo.essentials.dto.ApiResponse;
import br.com.devdojo.essentials.service.AnimeService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import util.AnimeCreator;
import util.AnimePostRequestBodyCreator;
import util.PutRequestBodyCreator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ExtendWith(SpringExtension.class)
class AnimeControllerTest {

    @InjectMocks
    private AnimeController animeController;

    @Mock
    private AnimeService animeServiceMock;

    @BeforeEach
    void setUp() {

        List<AnimeDto> animeDtos = new ArrayList<>(List.of(AnimeCreator.creatValidAnime()));
        BDDMockito.when(animeServiceMock.listarTodos(0, 2))
                .thenReturn(Collections.unmodifiableList((List<AnimeDto>) animeDtos));

        BDDMockito.when(animeServiceMock.listarTodosNotPage())
                .thenReturn(List.of(AnimeCreator.creatValidAnime()));

        BDDMockito.when(animeServiceMock.buscar(ArgumentMatchers.anyInt()))
                .thenReturn(AnimeCreator.creatValidAnime());

        BDDMockito.when(animeServiceMock.salvar(ArgumentMatchers.any(AnimeDto.class)))
                .thenReturn(AnimeCreator.creatValidAnime());
//
//        BDDMockito.doNothing().when(animeServiceMock).atualizar(ArgumentMatchers.any(AnimeDto.class),ArgumentMatchers.anyInt());

        BDDMockito.doNothing().when(animeServiceMock).deletar(ArgumentMatchers.anyInt());


    }

    @Test
    @DisplayName("Criar Anime")
    void createAnime() {
        AnimeDto animeDto = animeController.createAnime(AnimePostRequestBodyCreator.createAnimePostRequestBody()).getBody();
        assert animeDto != null;
        Assertions.assertThat(animeDto.getName()).isNotNull().isEqualTo(AnimeCreator.creatValidAnime().getName());
    }

//    @Test
//    @DisplayName("Atualizar ")
//    void updateAnime() {
//        Assertions.assertThatCode(()->animeController
//                .updateAnime(
//                        PutRequestBodyCreator.createAnimePutRequestBody()
//                        ,AnimeCreator.creatValidAnime().getId()
//                ));
//        ResponseEntity<AnimeDto> entity = animeController.updateAnime(
//                PutRequestBodyCreator.createAnimePutRequestBody()
//                ,AnimeCreator.creatValidAnime().getId()
//        );
//        Assertions.assertThat(entity).isNotNull();
//        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
//    }

    @Test
    @DisplayName("Delatar")
    void deletarAnime() {
        Assertions.assertThatCode(()-> animeController.deletarAnime(1))
                .doesNotThrowAnyException();
        ResponseEntity<ApiResponse> entity = animeController.deletarAnime(1);
        Assertions.assertThat(entity).isNotNull();
        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);

    }

    @Test
    @DisplayName("Listando sem Paginação")
    void getAllAnimeNotPage() {
        String expectativa = AnimeCreator.creatValidAnime().getName();
        List<AnimeDto> animeDtos = animeController.getAllAnimeNotPage().getBody();
        Assertions.assertThat(animeDtos)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);
        Assertions.assertThat(animeDtos.get(0).getName()).isEqualTo(expectativa);

    }

    @Test
    @DisplayName("Listando com paginação")
    void getAllAnimeDto() {
        String expectativa = AnimeCreator.creatValidAnime().getName();
        List<AnimeDto> animePage = this.animeController.getAllAnimeDto(0, 1).getBody();

        Assertions.assertThat(animePage).isNotNull();
        Assertions.assertThat(animePage).hasSize(0);


    }

    @Test
    @DisplayName("Buscar Anime por Id")
    void getSingle() {
         Integer expectativa = AnimeCreator.creatValidAnime().getId();
         AnimeDto animeDto = animeController.getSingle(1).getBody();

        Assertions.assertThat(animeDto).isNotNull();
        Assertions.assertThat(animeDto.getId()).isNotNull().isEqualTo(expectativa);
    }
}
