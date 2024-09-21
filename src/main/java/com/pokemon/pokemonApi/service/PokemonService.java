package com.pokemon.pokemonApi.service;

import com.pokemon.pokemonApi.dto.PokemonDTO;
import com.pokemon.pokemonApi.entity.Pokemon;
import com.pokemon.pokemonApi.repository.PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PokemonService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private PokemonRepository pokemonRepository;

    private final String POKE_API_URL = "https://pokeapi.co/api/v2/pokemon/";

    public Pokemon getPokemonById(int id) {
        // Llamada a la PokéAPI para obtener los datos del Pokémon usando el DTO
        String url = POKE_API_URL + id;
        PokemonDTO pokemonDTO = restTemplate.getForObject(url, PokemonDTO.class);

        // Convierte el DTO a tu entidad Pokemon y guarda en la base de datos
        if (pokemonDTO != null) {
            Pokemon pokemon = mapToEntity(pokemonDTO);
            pokemonRepository.save(pokemon);

            return pokemon;
        }

        return null;
    }

    private Pokemon mapToEntity(PokemonDTO pokemonDTO) {
        Pokemon pokemon = new Pokemon();
        pokemon.setId((long) pokemonDTO.getId());
        pokemon.setName(pokemonDTO.getName());
        pokemon.setHeight(pokemonDTO.getHeight());
        pokemon.setWeight(pokemonDTO.getWeight());

        // Aquí podrías mapear otras propiedades como habilidades, etc.
        return pokemon;
    }
}
