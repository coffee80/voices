package com.generation.voices.service;

import com.generation.voices.dto.BlogDTO;
import com.generation.voices.mapper.BlogMapper;
import com.generation.voices.model.Blog;
import com.generation.voices.repository.BlogRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class BlogService {

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private BlogMapper blogMapper;

    public List<BlogDTO> findAll() {
        return blogMapper.toDTOs(blogRepository.findAll());
    }

    public BlogDTO findById(Integer id) {
        Blog blog = blogRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Blog not found with id: " + id));
        return blogMapper.toDTO(blog);
    }

    public BlogDTO save(@Valid BlogDTO blogDTO) {
        Blog blog = blogMapper.toEntity(blogDTO);
        blog = blogRepository.save(blog);
        return blogMapper.toDTO(blog);
    }

    public BlogDTO update(Integer id, @Valid BlogDTO blogDTO) {
        // Verifica che il blog esista prima di aggiornare
        blogRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Blog not found with id: " + id));
        Blog blog = blogMapper.toEntity(blogDTO);
        blog.setId(id);
        blog = blogRepository.save(blog);
        return blogMapper.toDTO(blog);
    }

    public void deleteById(Integer id) {
        blogRepository.deleteById(id);
    }

}
