package br.com.devdojo.essentials.impl;

import br.com.devdojo.essentials.domain.Anime;
import br.com.devdojo.essentials.domain.User;
import br.com.devdojo.essentials.dto.AnimeDto;
import br.com.devdojo.essentials.dto.UserDto;
import br.com.devdojo.essentials.exception.BadRequestException;
import br.com.devdojo.essentials.repository.AnimeRepository;
import br.com.devdojo.essentials.service.AnimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class AnimeServiceImpl implements AnimeService {
    @Autowired
    private AnimeRepository animeRepository;


    @Override
    public AnimeDto salvar(AnimeDto entidade) {

        Anime anime = this.dtoToAnime(entidade);
        Anime savedAnime = this.animeRepository.save(anime);
        return this.animeToDto(savedAnime);
    }

    @Override
    public Page<AnimeDto> listarTodos(Pageable pageable) {
        Page<Anime> animes = this.animeRepository.findAll(pageable);
        Page<AnimeDto> animeDtos = (Page<AnimeDto>) animes.stream().map(this::animeToDto).collect(Collectors.toList());
        return animeDtos;
    }

    @Override
    public List<AnimeDto> listarTodosNotPage() {
        List<Anime> animes = this.animeRepository.findAll();
        List<AnimeDto> animeDtos = animes.stream().map(this::animeToDto).collect(Collectors.toList());
        return animeDtos;
    }

    @Override
    public AnimeDto buscar(Integer id) {
        Anime anime = this.animeRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Anime não encontrado"));
        return this.animeToDto(anime);
    }

    @Override
    public AnimeDto atualizar(AnimeDto animeDto, Integer id) {
        Anime anime = this.animeRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Anime não encontrado"));
        anime.setName(animeDto.getName());

        Anime updateAnime = this.animeRepository.save(anime);

        return this.animeToDto(updateAnime);
    }

    @Override
    public void deletar(Integer id) {
        Anime anime = this.animeRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Anime não encontrado"));
        this.animeRepository.delete(anime);

    }

    public Anime dtoToAnime(AnimeDto animeDto){
        Anime anime = new Anime();
        anime.setId(animeDto.getId());
        anime.setName(animeDto.getName());
        return anime;
    }

    public AnimeDto animeToDto(Anime anime){
        AnimeDto animeDto = new AnimeDto();
        animeDto.setId(anime.getId());
        animeDto.setName(anime.getName());
        return animeDto;
    }
}
