package exercise.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import exercise.dto.AuthorCreateDTO;
import exercise.dto.AuthorDTO;
import exercise.dto.AuthorUpdateDTO;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.AuthorMapper;
import exercise.repository.AuthorRepository;

@Service
public class AuthorService {
    // BEGIN
    @Autowired
    private AuthorRepository repository;

    @Autowired
    private AuthorMapper mapper;

    public List<AuthorDTO> findAll() {
        var authors = repository.findAll();
        var result = authors.stream()
            .map(mapper::map)
            .toList();
        return result;
    }

    public AuthorDTO create(AuthorCreateDTO data) {
        var author = mapper.map(data);
        repository.save(author);
        var dto = mapper.map(author);
        return dto;
    }

    public AuthorDTO findById(Long id) {
        var author = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Not Found: " + id));
        var dto = mapper.map(author);
        return dto;
    }

    public AuthorDTO update(AuthorUpdateDTO updateData, Long id) {
        var author = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Not Found: " + id));
        mapper.update(updateData, author);
        repository.save(author);
        var dto = mapper.map(author);
        return dto;
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
    // END
}
