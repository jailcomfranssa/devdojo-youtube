package br.com.devdojo.essentials.mapper;

import br.com.devdojo.essentials.domain.Anime;
import br.com.devdojo.essentials.dto.AnimePostRequestBody;
import br.com.devdojo.essentials.dto.AnimePutRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class AnimeMapper {

    public static final AnimeMapper INSTANCE = Mappers.getMapper(AnimeMapper.class);

    public abstract Anime toAnime(AnimePostRequestBody animePostRequestBody);
    public abstract Anime toAnime(AnimePutRequestBody animePostRequestBody);
}
