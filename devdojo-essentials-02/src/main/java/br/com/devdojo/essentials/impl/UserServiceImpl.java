package br.com.devdojo.essentials.impl;

import br.com.devdojo.essentials.domain.User;
import br.com.devdojo.essentials.dto.UserDto;
import br.com.devdojo.essentials.exception.BadRequestException;
import br.com.devdojo.essentials.repository.UserRepository;
import br.com.devdojo.essentials.service.UserService;
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
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DateUtil dateUtil;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public UserDto salvar(UserDto entidade) {

        User user = this.dtoToUser(entidade);
        User saveUser = this.userRepository.save(user);
        log.info("SAVE: "+dateUtil.formatLocalDateTimeToDatabesseStyle(LocalDateTime.now()));
        return this.userToDto(saveUser);

    }

    @Override
    public List<UserDto> listarTodos(Integer pageNumber, Integer pageSize) {

        Pageable p = PageRequest.of(pageNumber,pageSize);
        Page<User> users = this.userRepository.findAll(p);
        List<User> allUser = users.getContent();

        List<UserDto> userDtos = allUser.stream().map(this::userToDto).collect(Collectors.toList());
        log.info("LIST-ALL: "+dateUtil.formatLocalDateTimeToDatabesseStyle(LocalDateTime.now()));
        return userDtos;
    }

    @Override
    public List<UserDto> listarTodosNotPage() {
        List<User> users = this.userRepository.findAll();
        List<UserDto> userDtos = users.stream().map(this::userToDto).collect(Collectors.toList());
        log.info("LIST-ALL: "+dateUtil.formatLocalDateTimeToDatabesseStyle(LocalDateTime.now()));
        return userDtos;
    }

    @Override
    public UserDto buscar(Integer id) {
        User user = this.userRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Usuário não encontrado"));
        log.info("LIST-BY-ID: "+dateUtil.formatLocalDateTimeToDatabesseStyle(LocalDateTime.now()));
        return this.userToDto(user);
    }

    @Override
    public UserDto atualizar(UserDto userDto, Integer id) {
        User user = this.userRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Usuário não encontrado"));

        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());

        User updateUser = this.userRepository.save(user);
        log.info("UPDATE: "+dateUtil.formatLocalDateTimeToDatabesseStyle(LocalDateTime.now()));
        return this.userToDto(updateUser);
    }

    @Override
    public void deletar(Integer id) {
        User user = this.userRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Usuário não encontrado"));
        log.info("DELETE: "+dateUtil.formatLocalDateTimeToDatabesseStyle(LocalDateTime.now()));
        this.userRepository.delete(user);

    }

    public User dtoToUser(UserDto userDto){
        User user = this.modelMapper.map(userDto,User.class);
        return user;
    }

    public UserDto userToDto(User user){
        UserDto userDto = this.modelMapper.map(user,UserDto.class);
        return userDto;
    }

//    public User dtoToUser(UserDto userDto){
//        User user = new User();
//        user.setId(userDto.getId());
//        user.setName(userDto.getName());
//        user.setEmail(userDto.getEmail());
//        user.setAbout(userDto.getAbout());
//        user.setPassword(userDto.getPassword());
//        return user;
//    }


//    public UserDto userToDto(User user){
//        UserDto userDto = new UserDto();
//        userDto.setId(user.getId());
//        userDto.setName(user.getName());
//        userDto.setAbout(user.getAbout());
//        userDto.setEmail(user.getEmail());
//        userDto.setPassword(userDto.getPassword());
//        return userDto;
//    }
}
