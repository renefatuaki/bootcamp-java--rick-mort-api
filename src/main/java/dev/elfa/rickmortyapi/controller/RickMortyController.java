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
    public List<RickMortyCharacter> getAllCharacters(@RequestParam(required = false) String status) throws IOException {
        if (status != null) {
            // Filtering
            return service.getAllRickMortyCharactersByStatus(status);
        } else {
            return service.getAllRickMortyCharacters();
        }
    }

    // Searching by ID
    @GetMapping("/character/{id}")
    public RickMortyCharacter getCharacter(@PathVariable String id) throws IOException {
        return service.getRickMortyCharacter(id);
    }

    // Statistics
    @GetMapping("/species-statistic")
    public int getNumberOfSpecies(@RequestParam String species) throws IOException {
        return service.getNumberOfSpecies(species);
    }
}
