package br.com.devdojo.essentials.repository;

import br.com.devdojo.essentials.domain.Anime;
import lombok.extern.log4j.Log4j2;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
@Log4j2
@DisplayName("Tests para Repositório Anime")
class AnimeRepositoryTest {
    @Autowired
    private AnimeRepository animeRepository;

    @Test
    @DisplayName("Save creates anime when Successful")
    void save_PersistAnime_WhenSuccessful(){
        Anime animeToBeSaved = createAnime();
        Anime animeSaved = this.animeRepository.save(animeToBeSaved);

        Assertions.assertThat(animeSaved).isNotNull();
        Assertions.assertThat(animeSaved.getId()).isNotNull();
        Assertions.assertThat(animeSaved.getName()).isEqualTo(animeToBeSaved.getName());

    }

    @Test
    @DisplayName("Save update anime when Successful")
    void save_UpdateAnime_WhenSuccessful(){
        Anime animeToBeSaved = createAnime();
        Anime animeSaved = this.animeRepository.save(animeToBeSaved);

        animeSaved.setName("Overlord");
        Anime animeUpdate = this.animeRepository.save(animeSaved);

        Assertions.assertThat(animeUpdate).isNotNull();
        Assertions.assertThat(animeUpdate.getId()).isNotNull();
        Assertions.assertThat(animeUpdate.getName()).isEqualTo(animeSaved.getName());

    }

    @Test
    @DisplayName("Delete remove anime when Successful")
    void delete_RemoveAnime_WhenSuccessful(){
        Anime animeToBeSaved = createAnime();
        Anime animeSaved = this.animeRepository.save(animeToBeSaved);

        this.animeRepository.delete(animeSaved);
        Optional<Anime> animeOpc = this.animeRepository.findById(animeSaved.getId());

        Assertions.assertThat(animeOpc.isEmpty()).isTrue();
        Assertions.assertThat(animeOpc).isEmpty();

    }

    public Anime createAnime(){
        return Anime.builder()
                .name("Hajime no Ippo")
                .build();
    }

}