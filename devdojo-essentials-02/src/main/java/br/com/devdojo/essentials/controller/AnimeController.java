package br.com.devdojo.essentials.controller;

import br.com.devdojo.essentials.dto.AnimeDto;
import br.com.devdojo.essentials.dto.ApiResponse;
import br.com.devdojo.essentials.service.AnimeService;
import br.com.devdojo.essentials.util.DateUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequestMapping("/api/animes")
public class AnimeController {
    @Autowired
    private AnimeService animeService;



    @PostMapping()
    public ResponseEntity<AnimeDto> createAnime(@Valid @RequestBody AnimeDto animeDto){
        AnimeDto createAnimeDto = this.animeService.salvar(animeDto);
        return new ResponseEntity<>(createAnimeDto, HttpStatus.CREATED);

    }
    @PutMapping("/{animeId}")
    public ResponseEntity<AnimeDto> updateAnime(@Valid @RequestBody AnimeDto animeDto, @PathVariable("animeId") Integer animeId){
        AnimeDto updateAnime = this.animeService.atualizar(animeDto,animeId);
        return ResponseEntity.ok(updateAnime);
        
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deletarAnime(@PathVariable("id") Integer id){
        this.animeService.deletar(id);
        return new ResponseEntity<>(new ApiResponse("Anime Deleted Successfully", true), HttpStatus.OK);

    }
    @GetMapping("/")
    public ResponseEntity<List<AnimeDto>> getAllAnimeNotPage(){
        return ResponseEntity.ok(this.animeService.listarTodosNotPage());
    }
    @GetMapping("/list")
    public ResponseEntity<Page<AnimeDto>> getAllAnimeDto(Pageable pageable){
        return ResponseEntity.ok(this.animeService.listarTodos(pageable));

    }
    @GetMapping("/{animeId}")
    public ResponseEntity<AnimeDto>getSingle(@PathVariable Integer animeId){
        return ResponseEntity.ok(this.animeService.buscar(animeId));
    }





//
//    @GetMapping
//    public ResponseEntity<Page<Anime>> list(Pageable pageable){
//        log.info("LIST ALL: "+dateUtil.formatLocalDateTimeToDatabesseStyle(LocalDateTime.now()));
//        Page<Anime> anime = animeService.listAll(pageable);
//        return new ResponseEntity<>(anime, HttpStatus.OK);
//    }
//
//    @GetMapping(path = "/all")
//    public ResponseEntity<List<Anime>> listAll(){
//        log.info("LIST ALL: "+dateUtil.formatLocalDateTimeToDatabesseStyle(LocalDateTime.now()));
//        List<Anime> anime = animeService.listAllNotPageable();
//        return new ResponseEntity<>(anime, HttpStatus.OK);
//    }
//
//    @GetMapping(path = "/{id}")
//    public ResponseEntity<Anime>findById(@PathVariable long id){
//        log.info("LIST ID: "+dateUtil.formatLocalDateTimeToDatabesseStyle(LocalDateTime.now()));
//        Anime anime = animeService.findById(id);
//        return ResponseEntity.ok(anime);
//    }
//
//    @GetMapping(path = "/nome")
//    public ResponseEntity<List<Anime>>findByIdName(@RequestParam String name){
//        log.info("LIST ID: "+dateUtil.formatLocalDateTimeToDatabesseStyle(LocalDateTime.now()));
//       List<Anime> anime = animeService.findByName(name);
//        return ResponseEntity.ok(anime);
//    }
//
//
//    @PostMapping
//    public ResponseEntity<Anime>save(@RequestBody @Valid Anime anime){
//        log.info("SAVE: "+dateUtil.formatLocalDateTimeToDatabesseStyle(LocalDateTime.now()));
//        Anime animeseve = animeService.save(anime);
//        return ResponseEntity.ok(animeseve);
//    }
//
//    @DeleteMapping(path = "/{id}")
//    public ResponseEntity<Void>delete(@PathVariable long id){
//        log.info("DELETE: "+dateUtil.formatLocalDateTimeToDatabesseStyle(LocalDateTime.now()));
//        animeService.delete(id);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//
//    }
//
//    @PutMapping
//    public ResponseEntity<Anime>replace(@RequestBody Anime anime){
//        log.info("UPDATE: "+dateUtil.formatLocalDateTimeToDatabesseStyle(LocalDateTime.now()));
//        Anime animeseve = animeService.replace(anime);
//        return ResponseEntity.ok(animeseve);
//    }
}
