package br.com.devdojo.essentials.client;

import br.com.devdojo.essentials.domain.Anime;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@Log4j2
public class SpringClient {
    public static void main(String[] args) {

        ResponseEntity<Anime> anime = new RestTemplate()
                .getForEntity("http://localhost:8080/animes/1", Anime.class);
        log.info(anime);

        Anime entity = new RestTemplate()
                .getForEntity("http://localhost:8080/animes/{id}", Anime.class,2).getBody();
        assert entity != null;
        log.info(entity.getClass());
        log.info(entity.getId());
        log.info(entity.getName());

        Anime object = new RestTemplate()
                .getForObject("http://localhost:8080/animes/1", Anime.class);
        log.info(object);

    }
}
