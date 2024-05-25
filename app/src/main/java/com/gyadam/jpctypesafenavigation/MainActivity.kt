package com.gyadam.jpctypesafenavigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.gyadam.jpctypesafenavigation.ui.theme.JPCTypeSafeNavigationTheme
import kotlinx.serialization.Serializable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JPCTypeSafeNavigationTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = FirstScreen) {
                    composable<FirstScreen> {
                        FirstScreen(navController = navController)
                    }
                    composable<SecondScreenModel> {
                        val args = it.toRoute<SecondScreenModel>()
                        SecondScreen(secondScreen = args, navController = navController)
                    }
                }
            }
        }
    }
}


@Composable
fun FirstScreen(modifier: Modifier = Modifier, navController: NavHostController) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Type Safe Navigation Test project",
            style = MaterialTheme.typography.titleLarge
        )
        Divider()
        Text(
            text = "First Screen",
            style = MaterialTheme.typography.titleMedium
        )
        Button(onClick = {
            navController.navigate(SecondScreenModel("Test Elek", 10))
        }) {
            Text(text = "Go to next screen")
        }
    }
}

@Composable
fun SecondScreen(
    modifier: Modifier = Modifier,
    secondScreen: SecondScreenModel,
    navController: NavHostController,
) {
    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Type Safe Navigation Test project from ${secondScreen.name}",
            style = MaterialTheme.typography.titleLarge.copy(textAlign = TextAlign.Center)
        )
        Divider()
        Text(
            text = "Second Screen : ${secondScreen.randomNumber}",
            style = MaterialTheme.typography.titleMedium
        )
        Button(onClick = { navController.navigate(FirstScreen) }) {
            Text(text = "Go back to first screen")
        }
    }
}

