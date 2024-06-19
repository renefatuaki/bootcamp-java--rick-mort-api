package dev.elfa.rickmortyapi.controller;

import dev.elfa.rickmortyapi.model.RickMortyCharacter;
import dev.elfa.rickmortyapi.service.RickMortyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    // Passing Data
    @GetMapping("/character")
    public List<RickMortyCharacter> getAllCharactersByStatus(@RequestParam String status) throws IOException {
        return service.getAllRickMortyCharactersByStatus(status);
    }

    // Searching by ID
    @GetMapping("/character/{id}")
    public RickMortyCharacter getCharacter(@PathVariable String id) throws IOException {
        return service.getRickMortyCharacter(id);
    }
}
