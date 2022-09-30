package br.com.devdojo.essentials.client;

import br.com.devdojo.essentials.domain.Anime;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Log4j2
public class SpringClient {
    public static void main(String[] args) {


        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl = "http://localhost:8080/api/animes";
        String fooResourceUrlAll = "http://localhost:8080/api/animes/list";

        //Lista com ID
        ResponseEntity<String> response
                = restTemplate.getForEntity(fooResourceUrl + "/2", String.class);
        log.info(response);

        //Lista com ID
        ResponseEntity<String> anime
                = restTemplate.getForEntity(fooResourceUrl + "/{id}", String.class,2);
        log.info(anime);

        //Lista todos
        ResponseEntity<String> listAll
                = restTemplate.getForEntity(fooResourceUrlAll , String.class);
        log.info(listAll);

        Anime animeEntity = restTemplate
                .getForObject(fooResourceUrl + "/2", Anime.class);
        log.info(animeEntity.getId());
        log.info(animeEntity.getName());

        // criando POST com restTemplate
        HttpEntity<Anime> request = new HttpEntity<>(new Anime("Harem Camp!"));
        String animeSave = restTemplate.postForObject(fooResourceUrl, request, String.class);
        log.info("Cadastro de Anime: "+ animeSave);









    }



//        ResponseEntity<Anime> anime = new RestTemplate()
//                .getForEntity("http://localhost:8080/animes/1", Anime.class);
//        log.info(anime);
//
//        Anime entity = new RestTemplate()
//                .getForEntity("http://localhost:8080/animes/{id}", Anime.class,2).getBody();
//        assert entity != null;
//        log.info(entity.getClass());
//        log.info(entity.getId());
//        log.info(entity.getName());
//
//        Anime object = new RestTemplate()
//                .getForObject("http://localhost:8080/animes/1", Anime.class);
//        log.info(object);
//
//        Anime[] animeList =  new RestTemplate().getForObject("http://localhost:8080/animes/all",Anime[].class);
//        log.info(Arrays.toString(Arrays.stream(animeList).toArray()));
//
//        ResponseEntity<List<Anime>> exchange = new RestTemplate()
//                .exchange("http://localhost:8080/animes/all", HttpMethod.GET, null, new ParameterizedTypeReference<List<Anime>>() {
//                });
//        int x = 0;
//        while (x< exchange.getBody().size()){
//            log.info(exchange.getBody().get(x).getName());
//            x++;
//        }
//
//        Anime kindom = Anime.builder().name("Kingdom").build();
//        Anime save = new RestTemplate().postForObject("http://localhost:8080/animes",kindom,Anime.class);
//        log.info(save);
//
}


