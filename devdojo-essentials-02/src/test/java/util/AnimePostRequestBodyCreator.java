package util;

import br.com.devdojo.essentials.dto.AnimeDto;

public class AnimePostRequestBodyCreator {

    public static AnimeDto createAnimePostRequestBody(){
        return AnimeDto.builder()
                .name(AnimeCreator.creatAnimeToBeServe().getName())
                .build();

    }
}
