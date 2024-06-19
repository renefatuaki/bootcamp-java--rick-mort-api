package dev.elfa.rickmortyapi.controller;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.IOException;

@SpringBootTest
@AutoConfigureMockMvc
class RickMortyControllerTest {

    @Autowired
    private MockMvc mvc;

    private static MockWebServer mockWebServer;

    @BeforeAll
    static void setup() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();
    }

    @AfterAll
    static void shutDown() throws IOException {
        mockWebServer.shutdown();
    }

    @DynamicPropertySource
    static void backendProperties(DynamicPropertyRegistry registry) {
        registry.add("RICK_URL", () -> mockWebServer.url("/").toString());
    }

    @Test
    void getCharacter() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .addHeader("Content-Type", "application/json")
                .setBody("""
                        {
                            "id": 1,
                            "name": "Rick Sanchez",
                            "status": "Alive",
                            "species": "Human",
                            "type": "",
                            "gender": "Male",
                            "origin": {
                                "name": "Earth (C-137)",
                                "url": "https://rickandmortyapi.com/api/location/1"
                            },
                            "location": {
                                "name": "Citadel of Ricks",
                                "url": "https://rickandmortyapi.com/api/location/3"
                            },
                            "image": "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
                            "episode": [
                                "https://rickandmortyapi.com/api/episode/1"
                            ],
                            "url": "https://rickandmortyapi.com/api/character/1",
                            "created": "2017-11-04T18:48:46.250Z"
                        }
                        """)
        );

        mvc.perform(MockMvcRequestBuilders.get("/api/character/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("""
                        {
                            "id": "1",
                            "name": "Rick Sanchez",
                            "species": "Human"
                        }
                        """));
    }

    @Test
    void getCharacters() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .addHeader("Content-Type", "application/json")
                .setBody("""
                        {
                            "info": {
                                "count": 826,
                                "pages": 42,
                                "next": "https://rickandmortyapi.com/api/character?page=2",
                                "prev": null
                            },
                            "results": [
                                {
                                    "id": 1,
                                    "name": "Rick Sanchez",
                                    "status": "Alive",
                                    "species": "Human",
                                    "type": "",
                                    "gender": "Male",
                                    "origin": {
                                        "name": "Earth (C-137)",
                                        "url": "https://rickandmortyapi.com/api/location/1"
                                    },
                                    "location": {
                                        "name": "Citadel of Ricks",
                                        "url": "https://rickandmortyapi.com/api/location/3"
                                    },
                                    "image": "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
                                    "episode": [
                                        "https://rickandmortyapi.com/api/episode/1"
                                    ],
                                    "url": "https://rickandmortyapi.com/api/character/1",
                                    "created": "2017-11-04T18:48:46.250Z"
                                },
                                {
                                    "id": 2,
                                    "name": "Morty Smith",
                                    "status": "Alive",
                                    "species": "Human",
                                    "type": "",
                                    "gender": "Male",
                                    "origin": {
                                        "name": "unknown",
                                        "url": ""
                                    },
                                    "location": {
                                        "name": "Citadel of Ricks",
                                        "url": "https://rickandmortyapi.com/api/location/3"
                                    },
                                    "image": "https://rickandmortyapi.com/api/character/avatar/2.jpeg",
                                    "episode": [
                                        "https://rickandmortyapi.com/api/episode/1",
                                        "https://rickandmortyapi.com/api/episode/2",
                                        "https://rickandmortyapi.com/api/episode/3"
                                    ],
                                    "url": "https://rickandmortyapi.com/api/character/2",
                                    "created": "2017-11-04T18:50:21.651Z"
                                }
                            ]
                        }
                        """)
        );

        mvc.perform(MockMvcRequestBuilders.get("/api/characters"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("""
                        [
                            {
                                "id": "1",
                                "name": "Rick Sanchez",
                                "species": "Human"
                            },
                            {
                                "id": "2",
                                "name": "Morty Smith",
                                "species": "Human"
                            }
                        ]
                        """));
    }
}