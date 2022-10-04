package util;


import br.com.devdojo.essentials.dto.AnimeDto;
import br.com.devdojo.essentials.service.AnimeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class AnimeCreator {

    @Autowired
    private static AnimeService animeService;

    //    public static AnimeDto creatAnimeToBeServe(){
//        return AnimeDto.builder()
//                .name("Hajime no Ippo")
//                .build();
//    }
    @Test
    public static AnimeDto creatAnimeToBeServe() {
        AnimeDto animeDto = new AnimeDto();
        animeDto.setName("Shin Ikki Tousen");
        return animeService.salvar(animeDto);
    }

    public static AnimeDto creatValidAnime() {
        return AnimeDto.builder()
                .name("Hajime no Ippo")
                .id(1)
                .build();
    }

    public static AnimeDto creatValidUpdateAnime() {
        return AnimeDto.builder()
                .name("Hajime no Ipso 22")
                .id(1)
                .build();
    }
}