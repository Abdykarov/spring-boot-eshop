package cz.abdykili.eshop.controller;

import cz.abdykili.eshop.model.GenreResponseDto;
import cz.abdykili.eshop.service.impl.GenreServiceImp;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/genres")
public class GenreController {

    private final GenreServiceImp genreService;

    @GetMapping
    public List<GenreResponseDto> getGenres(){
        return genreService.findAll();
    }

}
