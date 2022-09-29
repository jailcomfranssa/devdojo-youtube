package br.com.devdojo.essentials.controller;

import br.com.devdojo.essentials.dto.ApiResponse;
import br.com.devdojo.essentials.dto.UserDto;
import br.com.devdojo.essentials.service.UserService;
import br.com.devdojo.essentials.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private DateUtil dateUtil;

    @Autowired
    private UserService userService;

    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto){
        UserDto createUserDto = this.userService.salvar(userDto);
        return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto, @PathVariable("userId") Integer userId ){
        UserDto updateUser = this.userService.atualizar(userDto,userId);
        return ResponseEntity.ok(updateUser);


    }
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse>deleteUser (@PathVariable("userId") Integer id){
        this.userService.deletar(id);
        return new ResponseEntity<>(new ApiResponse("User Deleted Successfully",true) ,HttpStatus.OK);
        //return new ResponseEntity<>(Map.of("message","Anime Deleted Successfully"),HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUserNotPage(){
        return ResponseEntity.ok(this.userService.listarTodosNotPage());
    }
    @GetMapping("/list")
    public ResponseEntity<Page<UserDto>> getAllUser(Pageable pageable){
        return ResponseEntity.ok(this.userService.listarTodos(pageable));

    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto>getSingle(@PathVariable Integer userId){
        return ResponseEntity.ok(this.userService.buscar(userId));
    }
}
