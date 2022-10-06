package br.com.devdojo.essentials.service;

import br.com.devdojo.essentials.domain.User;
import br.com.devdojo.essentials.dto.UserDto;
import org.springframework.stereotype.Service;

@Service
public interface UserService extends BaseService<UserDto>{

    User findByEmail(String email);
}
