package com.plcoding.jetpackcomposepokedex.mypokemondetail

import androidx.lifecycle.ViewModel
import com.plcoding.jetpackcomposepokedex.data.remote.responses.Pokemon
import com.plcoding.jetpackcomposepokedex.repository.PokemonRepository
import com.plcoding.jetpackcomposepokedex.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MyPokemonDetailViewModel @Inject constructor(
    private val MyRepository: PokemonRepository
): ViewModel(){
    suspend fun MyGetPokemonInfo(pokemonName: String): Resource<Pokemon>{
        return MyRepository.getPokemonInfo(pokemonName)
    }
}