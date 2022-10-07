package br.com.devdojo.essentials.controller;

import br.com.devdojo.essentials.dto.AnimeDto;
import br.com.devdojo.essentials.dto.ApiResponse;
import br.com.devdojo.essentials.service.AnimeService;
import br.com.devdojo.essentials.util.DateUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/api/animes")
public class AnimeController {
    @Autowired
    private AnimeService animeService;



    @PostMapping()
    //@PreAuthorize("hasRole('ADMIN')")
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
    @ApiResponses( value = {
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "204", description = "Operação realizada com sucesso."),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Este anime não existe na base de dado")
    })
    public ResponseEntity<ApiResponse> deletarAnime(@PathVariable("id") Integer id){
        this.animeService.deletar(id);
        return new ResponseEntity<>(new ApiResponse("Anime Deleted Successfully", true), HttpStatus.OK);

    }
    @GetMapping("/")
    public ResponseEntity<List<AnimeDto>> getAllAnimeNotPage(){
        return ResponseEntity.ok(this.animeService.listarTodosNotPage());
    }
    @GetMapping("/list")
    @Operation(summary = "Lista todos os animes com paginação "
                ,description = "{pageNumber defaultValue = 0} e {pageSize defaultValue =2}"
                ,tags = {"anime"}) //configuração do swagger
    public ResponseEntity<List<AnimeDto>> getAllAnimeDto(
            @RequestParam(value = "pageNumber",defaultValue = "0",required = false) Integer pageNumber,
            @RequestParam(value = "pageSize",defaultValue = "2",required = false) Integer pageSize
    ){
        List<AnimeDto> allAnime = this.animeService.listarTodos(pageNumber,pageSize);
        return new ResponseEntity<List<AnimeDto>>(allAnime,HttpStatus.OK);

    }
    @GetMapping("/{animeId}")
    public ResponseEntity<AnimeDto>getSingle(@PathVariable Integer animeId){
        return ResponseEntity.ok(this.animeService.buscar(animeId));
    }
    //@PreAuthorize("hasRole('ADMIN')")
    @GetMapping("by-id/{animeId}")
    public ResponseEntity<AnimeDto>getById(@PathVariable Integer animeId, @AuthenticationPrincipal UserDetails userDetails){
        log.info(userDetails);
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
