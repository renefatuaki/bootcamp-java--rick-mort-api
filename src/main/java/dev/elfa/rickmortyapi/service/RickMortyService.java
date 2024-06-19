package dev.elfa.rickmortyapi.service;

import dev.elfa.rickmortyapi.model.RickMortyCharacter;
import dev.elfa.rickmortyapi.model.RickMortyResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.io.IOException;
import java.util.List;

@Service
public class RickMortyService {
    private final RestClient client = RestClient.builder()
            .baseUrl("https://rickandmortyapi.com/api")
            .build();

    public List<RickMortyCharacter> getAllRickMortyCharacters() throws IOException {
        RickMortyResponse response = client.get()
                .uri("/character")
                .retrieve()
                .body(RickMortyResponse.class);

        if (response != null) {
            return response.results();
        } else {
            throw new IOException("No data found.");
        }
    }
}
