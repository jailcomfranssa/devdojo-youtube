package br.com.devdojo.essentials.controller;

import br.com.devdojo.essentials.domain.Anime;
import br.com.devdojo.essentials.dto.AnimePostRequestBody;
import br.com.devdojo.essentials.dto.AnimePutRequestBody;
import br.com.devdojo.essentials.service.AnimeService;
import br.com.devdojo.essentials.util.DateUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Log4j2
@RestController
@RequestMapping("animes")
public class AnimeController {

    private final DateUtil dateUtil;

    private final AnimeService animeService;

    @Autowired
    public AnimeController(DateUtil dateUtil, AnimeService animeService) {
        this.dateUtil = dateUtil;
        this.animeService = animeService;

    }

    @GetMapping
    public ResponseEntity<List<Anime>> list(){
        log.info("LIST ALL: "+dateUtil.formatLocalDateTimeToDatabesseStyle(LocalDateTime.now()));
        List<Anime> anime = animeService.listAll();
        return new ResponseEntity<>(anime, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Anime>findById(@PathVariable long id){
        log.info("LIST ID: "+dateUtil.formatLocalDateTimeToDatabesseStyle(LocalDateTime.now()));
        Anime anime = animeService.findById(id);
        return ResponseEntity.ok(anime);
    }

    @GetMapping(path = "/nome")
    public ResponseEntity<List<Anime>>findByIdName(@RequestParam String name){
        log.info("LIST ID: "+dateUtil.formatLocalDateTimeToDatabesseStyle(LocalDateTime.now()));
       List<Anime> anime = animeService.findByName(name);
        return ResponseEntity.ok(anime);
    }


    @PostMapping
    public ResponseEntity<Anime>save(@RequestBody AnimePostRequestBody anime){
        log.info("SAVE: "+dateUtil.formatLocalDateTimeToDatabesseStyle(LocalDateTime.now()));
        Anime animeseve = animeService.save(anime);
        return ResponseEntity.ok(animeseve);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void>delete(@PathVariable long id){
        log.info("DELETE: "+dateUtil.formatLocalDateTimeToDatabesseStyle(LocalDateTime.now()));
        animeService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @PutMapping
    public ResponseEntity<Anime>replace(@RequestBody AnimePutRequestBody animePutRequestBody){
        log.info("UPDATE: "+dateUtil.formatLocalDateTimeToDatabesseStyle(LocalDateTime.now()));
        Anime animeseve = animeService.replace(animePutRequestBody);
        return ResponseEntity.ok(animeseve);
    }
}
