package com.nesterrovv.vpn.authentication.service;

import com.nesterrovv.vpn.authentication.dto.UserResponseDto;
import com.nesterrovv.vpn.authentication.entity.User;
import com.nesterrovv.vpn.authentication.exception.UsernameOrPasswordException;
import com.nesterrovv.vpn.authentication.mapper.UserDtoMapper;
import com.nesterrovv.vpn.authentication.repository.UserRepository;
import jakarta.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserDtoMapper userDtoMapper;

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public List<UserResponseDto> listAll(Integer offset, Integer limit) {
        Page<User> userList = userRepository.findAll(PageRequest.of(offset, limit));
        List<UserResponseDto> responseList = new LinkedList<>();
        for (User user : userList) {
            responseList.add(userDtoMapper.entityToResponseDto(user));
        }
        return responseList;
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        Optional<User> user = Optional.ofNullable(findByUsername(username));
        if (user.isEmpty()) {
            throw new UsernameOrPasswordException();
        }
        return user.get();
    }
}
