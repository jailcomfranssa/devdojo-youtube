package br.com.devdojo.essentials.impl;

import br.com.devdojo.essentials.domain.Anime;
import br.com.devdojo.essentials.domain.User;
import br.com.devdojo.essentials.dto.AnimeDto;
import br.com.devdojo.essentials.dto.UserDto;
import br.com.devdojo.essentials.exception.BadRequestException;
import br.com.devdojo.essentials.repository.AnimeRepository;
import br.com.devdojo.essentials.service.AnimeService;
import br.com.devdojo.essentials.util.DateUtil;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
@Service
@Log4j2
public class AnimeServiceImpl implements AnimeService {
    @Autowired
    private AnimeRepository animeRepository;

    @Autowired
    private DateUtil dateUtil;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public AnimeDto salvar(AnimeDto entidade) {

        Anime anime = this.dtoToAnime(entidade);
        Anime savedAnime = this.animeRepository.save(anime);
        log.info("SAVE: "+dateUtil.formatLocalDateTimeToDatabesseStyle(LocalDateTime.now()));
        return this.animeToDto(savedAnime);
    }

    @Override
    public List<AnimeDto> listarTodos(Integer pageNumber, Integer pageSize) {

        Pageable p = PageRequest.of(pageNumber,pageSize);
        Page<Anime> animes = this.animeRepository.findAll(p);
        List<Anime> allAnime = animes.getContent();

        List<AnimeDto> animeDtos = allAnime.stream().map(this::animeToDto).collect(Collectors.toList());
        log.info("LIST-ALL: "+dateUtil.formatLocalDateTimeToDatabesseStyle(LocalDateTime.now()));
        return animeDtos;
    }


    @Override
    public List<AnimeDto> listarTodosNotPage() {
        List<Anime> animes = this.animeRepository.findAll();
        List<AnimeDto> animeDtos = animes.stream().map(this::animeToDto).collect(Collectors.toList());
        log.info("ListAll: "+dateUtil.formatLocalDateTimeToDatabesseStyle(LocalDateTime.now()));
        return animeDtos;
    }

    @Override
    public AnimeDto buscar(Integer id) {
        Anime anime = this.animeRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Anime não encontrado"));
        log.info("ListByID: "+dateUtil.formatLocalDateTimeToDatabesseStyle(LocalDateTime.now()));
        return this.animeToDto(anime);
    }

    @Override
    public AnimeDto atualizar(AnimeDto animeDto, Integer id) {
        Anime anime = this.animeRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Anime não encontrado"));
        anime.setName(animeDto.getName());

        Anime updateAnime = this.animeRepository.save(anime);
        log.info("UPDATE: "+dateUtil.formatLocalDateTimeToDatabesseStyle(LocalDateTime.now()));
        return this.animeToDto(updateAnime);
    }

    @Override
    public void deletar(Integer id) {
        Anime anime = this.animeRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Anime não encontrado"));
        log.info("DELETE: "+dateUtil.formatLocalDateTimeToDatabesseStyle(LocalDateTime.now()));
        this.animeRepository.delete(anime);

    }
    public Anime dtoToAnime(AnimeDto animeDto){
        Anime anime = this.modelMapper.map(animeDto,Anime.class);
        return anime;
    }

    public AnimeDto animeToDto(Anime anime){
        AnimeDto animeDto = this.modelMapper.map(anime,AnimeDto.class);
        return animeDto;
    }

//    public Anime dtoToAnime(AnimeDto animeDto){
//        Anime anime = new Anime();
//        anime.setId(animeDto.getId());
//        anime.setName(animeDto.getName());
//        return anime;
//    }
//
//    public AnimeDto animeToDto(Anime anime){
//        AnimeDto animeDto = new AnimeDto();
//        animeDto.setId(anime.getId());
//        animeDto.setName(anime.getName());
//        return animeDto;
//    }
}
