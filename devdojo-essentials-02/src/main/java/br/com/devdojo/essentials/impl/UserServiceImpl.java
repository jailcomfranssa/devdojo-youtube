package br.com.devdojo.essentials.impl;

import br.com.devdojo.essentials.domain.User;
import br.com.devdojo.essentials.dto.UserDto;
import br.com.devdojo.essentials.exception.BadRequestException;
import br.com.devdojo.essentials.repository.UserRepository;
import br.com.devdojo.essentials.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDto salvar(UserDto entidade) {

        User user = this.dtoToUser(entidade);
        User saveUser = this.userRepository.save(user);
        return this.userToDto(saveUser);

    }

    @Override
    public Page<UserDto> listarTodos(Pageable pageable) {
        Page<User> users = this.userRepository.findAll(pageable);
        Page<UserDto> userDtos = (Page<UserDto>) users.stream().map(this::userToDto).collect(Collectors.toList());
        return userDtos;
    }

    @Override
    public List<UserDto> listarTodosNotPage() {
        List<User> users = this.userRepository.findAll();
        List<UserDto> userDtos = users.stream().map(this::userToDto).collect(Collectors.toList());

        return userDtos;
    }

    @Override
    public UserDto buscar(Integer id) {
        User user = this.userRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Usuário não encontrado"));
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

        return this.userToDto(updateUser);
    }

    @Override
    public void deletar(Integer id) {
        User user = this.userRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Usuário não encontrado"));
        this.userRepository.delete(user);

    }

    public User dtoToUser(UserDto userDto){
        User user = new User();
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setAbout(userDto.getAbout());
        user.setPassword(userDto.getPassword());
        return user;
    }

    public UserDto userToDto(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setAbout(user.getAbout());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(userDto.getPassword());
        return userDto;
    }
}
