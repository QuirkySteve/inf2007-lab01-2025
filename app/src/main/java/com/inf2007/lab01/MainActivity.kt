package com.inf2007.lab01

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.inf2007.lab01.ui.theme.Lab01Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MainScreen()
        }
    }
}

@Composable
fun MainScreen() {
    Lab01Theme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            var username by remember { mutableStateOf("") }
            var showGreeting by remember { mutableStateOf(false) }

            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // TextField for user input
                UserInput(
                    name = username,
                    onNameChange = { username = it }
                )

                // Submit button
                Button(
                    onClick = {
                        // Show greeting if the username is not blank
                        showGreeting = username.isNotBlank()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("submitButton")
                ) {
                    Text("Submit")
                }

                // Greeting message
                if (showGreeting) {
                    Greeting(
                        name = username,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun UserInput(name: String, onNameChange: (String) -> Unit, modifier: Modifier = Modifier) {
    TextField(
        value = name,
        onValueChange = { onNameChange(it) },
        label = { Text("Enter your Name") },
        modifier = modifier
            .fillMaxWidth()
            .testTag("UserInput")
    )
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!, Welcome to INF2007!",
        modifier = modifier
            .fillMaxWidth()
            .testTag("greeting")
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MainScreen()
}
