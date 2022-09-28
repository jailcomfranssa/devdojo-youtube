package br.com.devdojo.essentials.service;

import br.com.devdojo.essentials.domain.Anime;
import br.com.devdojo.essentials.dto.AnimePostRequestBody;
import br.com.devdojo.essentials.dto.AnimePutRequestBody;
import br.com.devdojo.essentials.exception.BadRequestException;
import br.com.devdojo.essentials.mapper.AnimeMapper;
import br.com.devdojo.essentials.repository.AnimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AnimeService {
    private final AnimeRepository animeRepository;
    @Autowired
    public AnimeService(AnimeRepository animeRepository) {
        this.animeRepository = animeRepository;
    }

    public Page<Anime> listAll(Pageable pageable){
        return animeRepository.findAll(pageable);
    }

    public List<Anime> findByName(String name){
        return animeRepository.findByName(name);
    }

    public Anime findById(long id){
        return animeRepository.findById(id)
                .orElseThrow(()-> new BadRequestException("Anime not Fund"));
    }
    @Transactional
    public Anime save(AnimePostRequestBody animePostRequestBody){
        Anime anime = AnimeMapper.INSTANCE.toAnime(animePostRequestBody);
        return animeRepository.save(anime);
    }

    public void delete(long id){
        animeRepository.delete(findById(id));
    }

    public Anime replace(AnimePutRequestBody animePutRequestBody){
        findById(animePutRequestBody.getId());
        Anime anime = AnimeMapper.INSTANCE.toAnime(animePutRequestBody);
        anime.setId(anime.getId());
        animeRepository.save(anime);

        return anime;
    }

    public List<Anime> listAllNotPageable() {
        return animeRepository.findAll();
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
