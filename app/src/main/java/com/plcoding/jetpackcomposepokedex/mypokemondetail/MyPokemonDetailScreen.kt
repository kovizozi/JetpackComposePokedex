package com.plcoding.jetpackcomposepokedex.mypokemondetail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltNavGraphViewModel
import androidx.navigation.NavController
import com.plcoding.jetpackcomposepokedex.data.remote.responses.Pokemon
import com.plcoding.jetpackcomposepokedex.util.Resource

@Composable
fun MyPokemonDetailScreen(
    dominantColor: Color,
    pokemonName: String,
    myNavController: NavController,
    topPadding: Dp = 0.dp,
    pokemonImageSize: Dp = 200.dp,
    viewModel: MyPokemonDetailViewModel = hiltNavGraphViewModel()
) {

    //state variable what has initial value while loading
    val pokemonInfo = produceState<Resource<Pokemon>>(initialValue = Resource.Loading()) {
        value = viewModel.MyGetPokemonInfo(pokemonName)
    }.value

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = dominantColor)
            .padding(bottom = 16.dp)
    ) {
        MyPokemonDetailTopSection(
            myNavController = myNavController,
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .align(Alignment.TopStart)
        )
MyPokemonDetailStateWraper(
    myPokemonInfo = pokemonInfo,
    modifier = Modifier
        .fillMaxSize()
        .padding(
            top=topPadding + pokemonImageSize/2f,
            start = 16.dp,
            end = 16.dp,
            bottom = 16.dp
        )
        .shadow(10.dp, RoundedCornerShape(15.dp))
        .clip(RoundedCornerShape(10.dp))
        .background(MaterialTheme.colors.surface)
        .padding(16.dp)
        .align(Alignment.BottomCenter),
    myLoadingModifier = Modifier
        .size(100.dp)
        .align(Alignment.Center)
        .padding(
            top= topPadding + pokemonImageSize /2f,
            start = 16.dp,
            end= 16.dp,
            bottom = 16.dp
        )
)

        //

    }

}

@Composable
fun MyPokemonDetailTopSection(
    myNavController: NavController,
    modifier: Modifier = Modifier
) {

        Box(
            contentAlignment = Alignment.TopStart,
            modifier = modifier
                .background(
                    Brush.verticalGradient(
                        listOf(Color.Black,Color.Transparent)
                    )
                )
        ){
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier
                    .size(36.dp)
                    .offset(16.dp, 16.dp)
                    .clickable {
                        myNavController.popBackStack()
                    }

            )
        }
}

@Composable
fun MyPokemonDetailStateWraper(
    myPokemonInfo: Resource<Pokemon>,
    modifier: Modifier = Modifier,
    myLoadingModifier: Modifier = Modifier
) {
    when(myPokemonInfo){
        is Resource.Success -> {
            MyPokemonDetailSection(
                myPokemonInfo=myPokemonInfo.data!!,
                modifier = modifier
                    .offset(y=(-50).dp)
            )
        }
        is Resource.Error -> {
            myPokemonInfo.message?.let {
                Text(
                    text = it,
                    color = Color.Red,
                    modifier = modifier
                ) }
        }
        is Resource.Loading -> {
            CircularProgressIndicator(
                color= MaterialTheme.colors.primary,
                modifier = myLoadingModifier
            )
        }
    }
}


@Composable
fun MyPokemonDetailSection(
    myPokemonInfo: Pokemon,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier=modifier
            .fillMaxSize()
            .offset(y = 100.dp)
            .verticalScroll(scrollState)
            ){
        Text(text = "This is my Column", color = Color.Yellow)

    }

}




























