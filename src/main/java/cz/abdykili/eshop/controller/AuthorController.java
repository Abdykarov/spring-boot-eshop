package cz.abdykili.eshop.controller;

import cz.abdykili.eshop.model.AuthorResponseDto;
import cz.abdykili.eshop.service.impl.AuthorServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/authors")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorServiceImp authorService;

    @GetMapping
    public List<AuthorResponseDto> findAll() {
        return authorService.findAll();
    }

    @GetMapping("{id}")
    public AuthorResponseDto findById(@PathVariable("id") Long id) {
        return authorService.findById(id);
    }


}
