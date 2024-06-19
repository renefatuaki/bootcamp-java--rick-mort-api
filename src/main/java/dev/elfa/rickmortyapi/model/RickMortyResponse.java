package dev.elfa.rickmortyapi.model;

import java.util.List;

public record RickMortyResponse(List<RickMortyCharacter> results, RickMortyInfo info) {
}
