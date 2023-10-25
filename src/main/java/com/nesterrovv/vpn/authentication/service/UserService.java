package com.nesterrovv.vpn.authentication.service;

import com.nesterrovv.vpn.authentication.dto.UserCreateDto;
import com.nesterrovv.vpn.authentication.entity.User;
import com.nesterrovv.vpn.authentication.mapper.UserCreateMapper;
import com.nesterrovv.vpn.authentication.repository.UserRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final UserCreateMapper userCreateMapper;

    public void create(UserCreateDto userCreateDto) {
        userRepository.save(userCreateMapper.dtoToEntity(userCreateDto));
    }

    public List<User> listAll() {
        return (List<User>) userRepository.findAll();
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User findById(Integer id) {
        return userRepository.findById(id).get();
    }

    public boolean delete(String username) {
        return userRepository.findByUsername(username)
            .map(entity -> {
                userRepository.delete(entity);
                return true;
            })
            .orElse(false);
    }
}
