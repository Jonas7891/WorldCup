package com.worldcup.worldcup.modules.role.service;

import com.worldcup.worldcup.modules.role.dto.RoleRequest;
import com.worldcup.worldcup.modules.role.dto.RoleResponse;
import com.worldcup.worldcup.modules.role.entity.Role;
import com.worldcup.worldcup.modules.role.mapper.RoleMapper;
import com.worldcup.worldcup.modules.role.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class RoleServiceImpl implements RoleService{

    private final RoleRepository repository;
    private final RoleMapper mapper;

    @Override
    public RoleResponse findById(Long id) {
        Role entity= repository.findById(id)
                .orElseThrow(()-> new RuntimeException("Role not found"));
        return mapper.toResponse(entity);
    }

    @Override
    public List<RoleResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public RoleResponse save(RoleRequest request) {
        Role entity = mapper.toEntity(request);
        Role saved = repository.save(entity);
        return mapper.toResponse(saved);
    }

    @Override
    public RoleResponse update(Long id, RoleRequest request) {
        Role existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found"));
        // Update fields if needed, but since mapper.toDomain creates new, perhaps merge
        Role updated = mapper.toDomain(request);
        updated.setId(id); // Assuming Action has setId
        Role saved = repository.save(updated);
        return mapper.toResponse(saved);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
