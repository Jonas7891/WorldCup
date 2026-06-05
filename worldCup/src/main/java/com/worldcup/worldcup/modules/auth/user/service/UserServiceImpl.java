package com.worldcup.worldcup.modules.auth.user.service;

import java.util.List;

import com.worldcup.worldcup.modules.auth.user.dto.UserRequest;
import com.worldcup.worldcup.modules.auth.user.dto.UserResponse;
import com.worldcup.worldcup.modules.auth.user.entity.AppUser;
import com.worldcup.worldcup.modules.auth.user.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.worldcup.worldcup.modules.auth.user.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final UserMapper mapper;


    @Override
    public UserResponse findById(Long id) {
        AppUser entity= repository.findById(id)
                .orElseThrow(()-> new RuntimeException("User not found"));
        return mapper.toResponse(entity);
    }

    @Override
    public List<UserResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public UserResponse save(UserRequest request) {
        AppUser entity = mapper.toEntity(request);
        AppUser saved = repository.save(entity);
        return mapper.toResponse(saved);
    }

    @Override
    public UserResponse update(Long id, UserRequest request) {
        AppUser entity = mapper.toEntity(request);
        AppUser saved = repository.save(entity);
        return mapper.toResponse(saved);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
