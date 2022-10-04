package util;

import br.com.devdojo.essentials.dto.AnimeDto;

public class PutRequestBodyCreator {

    public static AnimeDto createAnimePutRequestBody(){
        return AnimeDto.builder()
                .id(AnimeCreator.creatValidUpdateAnime().getId())
                .name(AnimeCreator.creatValidAnime().getName())
                .build();
    }
}
