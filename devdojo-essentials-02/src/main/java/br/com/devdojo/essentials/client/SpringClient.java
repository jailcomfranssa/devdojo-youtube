package br.com.devdojo.essentials.client;

import br.com.devdojo.essentials.domain.Anime;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

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

        Anime[] animeList =  new RestTemplate().getForObject("http://localhost:8080/animes/all",Anime[].class);
        log.info(Arrays.toString(Arrays.stream(animeList).toArray()));

        ResponseEntity<List<Anime>> exchange = new RestTemplate()
                .exchange("http://localhost:8080/animes/all", HttpMethod.GET, null, new ParameterizedTypeReference<List<Anime>>() {
                });
        int x = 0;
        while (x< exchange.getBody().size()){
            log.info(exchange.getBody().get(x).getName());
            x++;
        }


    }
}
