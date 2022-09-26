package br.com.devdojo.essentials.service;

import br.com.devdojo.essentials.domain.Anime;
import br.com.devdojo.essentials.dto.AnimePostRequestBody;
import br.com.devdojo.essentials.dto.AnimePutRequestBody;
import br.com.devdojo.essentials.repository.AnimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AnimeService {
    private final AnimeRepository animeRepository;
    @Autowired
    public AnimeService(AnimeRepository animeRepository) {
        this.animeRepository = animeRepository;
    }

    public List<Anime> listAll(){
        return animeRepository.findAll();
    }

    public Anime findById(long id){
        return animeRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Anime not Fund"));
    }

    public Anime save(AnimePostRequestBody animePostRequestBody){
        Anime anime = Anime.builder().name(animePostRequestBody.getName()).build();
        return animeRepository.save(anime);
    }

    public void delete(long id){
        animeRepository.delete(findById(id));
    }

    public void replace(AnimePutRequestBody animePutRequestBody){
        findById(animePutRequestBody.getId());
        Anime anime = Anime.builder()
                .id(animePutRequestBody.getId())
                .name(animePutRequestBody.getName())
                .build();

        animeRepository.save(anime);

    }








//    Service usando Array
//    private static List<Anime> animes;
//
//    static {
//        animes = new ArrayList<>( List.of(new Anime(1L,"BDZ"), new Anime(2L,"Berserk")));
//    }



//    public List<Anime> listAll(){
//        return animes;
//    }
//
//    public Anime findById(long id){
//        return animes.stream()
//                .filter(anime -> anime.getId().equals(id))
//                .findFirst()
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Anime not Found"));
//
//    }
//
//    public Anime save(Anime anime) {
//        anime.setId(ThreadLocalRandom.current().nextLong(3,10000));
//        animes.add(anime);
//        return anime;
//    }
//
//    public void delete(long id) {
//        animes.remove(findById(id));
//    }
//
//    public Anime replace(Anime anime) {
//        delete(anime.getId());
//        animes.add(anime);
//        return anime;
//    }
}
