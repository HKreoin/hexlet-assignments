package exercise.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import exercise.dto.BookCreateDTO;
import exercise.dto.BookDTO;
import exercise.dto.BookUpdateDTO;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.BookMapper;
import exercise.repository.BookRepository;

@Service
public class BookService {
    // BEGIN
    @Autowired
    private BookRepository repository;

    @Autowired
    private BookMapper mapper;

    public List<BookDTO> findAll() {
        var books = repository.findAll();
        var result = books.stream()
            .map(mapper::map)
            .toList();
        return result;
    }

    public BookDTO create(BookCreateDTO data) {
        var book = mapper.map(data);
        repository.save(book);
        var dto = mapper.map(book);
        return dto;
    }

    public BookDTO findById(Long id) {
        var book = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Not Found: " + id));
        var dto = mapper.map(book);
        return dto;
    }

    public BookDTO update(BookUpdateDTO updateData, Long id) {
        var book = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Not Found: " + id));
        mapper.update(updateData, book);
        repository.save(book);
        var dto = mapper.map(book);
        return dto;
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
    // END
}
