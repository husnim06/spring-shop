package ru.husnim.spring_shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import ru.husnim.spring_shop.dto.UserDTO;
import ru.husnim.spring_shop.dto.UserResponseDTO;
import ru.husnim.spring_shop.model.User;
import ru.husnim.spring_shop.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(user -> {
                    UserResponseDTO dto = new UserResponseDTO();
                    dto.setId(user.getId());
                    dto.setName(user.getName());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    public UserResponseDTO getUserById(Long id) {
        return (UserResponseDTO) userRepository.findById(id).stream()
                .map(user -> {
                    UserResponseDTO dto = new UserResponseDTO();
                    dto.setId(user.getId());
                    dto.setName(user.getName());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    public UserResponseDTO getUserByName(String name) {
        return (UserResponseDTO) userRepository.findByName(name).stream()
                .map(user -> {
                    UserResponseDTO dto = new UserResponseDTO();
                    dto.setId(user.getId());
                    dto.setName(user.getName());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    public User createUser(UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        return userRepository.save(user);
    }

    public User updateUser(Long id, UserDTO userDTO) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

}
