package dev.elfa.rickmortyapi.controller;

import dev.elfa.rickmortyapi.model.RickMortyCharacter;
import dev.elfa.rickmortyapi.service.RickMortyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RickMortyController {
    private final RickMortyService service;

    // Passing Data
    @GetMapping("/characters")
    public List<RickMortyCharacter> getAllCharacters() throws IOException {
        return service.getAllRickMortyCharacters();
    }

    // Searching by ID
    @GetMapping("/character/{id}")
    public RickMortyCharacter getCharacter(@PathVariable String id) throws IOException {
        return service.getRickMortyCharacter(id);
    }
}
