package com.taxiapp.ui.paymentScreen

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.taxiapp.R
import com.taxiapp.routing.Screen
import com.taxiapp.ui.theme.TaxiAppTheme
import com.taxiapp.ui.theme.white
import com.taxiapp.ui.theme.yellow
import com.taxiapp.utils.GradientButton

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "MutableCollectionMutableState")
@Composable
fun PaymentScreen(navController: NavController) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()
    val list = arrayListOf<String>().apply {
        clear()
        add("Pay via credit card / debit card ")
        add("Pay via net banking ")
        add("Pay via upi ")
        add("Pay via wallet ")
    }
    var option by remember { mutableStateOf("") }
    var isPlace by remember { mutableStateOf(false) }
    TaxiAppTheme {
        Scaffold {
            Column(
                modifier = Modifier
                    .background(color = yellow)
                    .padding(top = 40.dp)
                    .verticalScroll(scrollState)
            ) {
                SmallTopAppBar(
                    title = {
                        Text(
                            text = "Payment Screen", color = Color.White,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                            style = TextStyle(fontWeight = FontWeight.SemiBold, fontSize = 18.sp)
                        )
                    },
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                navController.navigateUp()
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.ArrowBack,
                                tint = Color.White,
                                contentDescription = "Back"
                            )
                        }
                    },
                    colors = TopAppBarDefaults.smallTopAppBarColors(
                        containerColor = yellow,
                        titleContentColor = Color.White
                    )
                )
                Spacer(Modifier.height(10.dp))

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = white)
                ) {

                    Spacer(Modifier.height(10.dp))
                    Text(
                        text = "select payment option : ", color = Color.Gray,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        style = TextStyle(
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 18.sp
                        )
                    )
                    Column(
                        modifier = Modifier.padding(8.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        list.forEach { name ->
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                RadioButton(
                                    selected = (name == option),
                                    onClick = { option = name },
                                    colors = RadioButtonDefaults.colors(selectedColor = Color.Black)

                                )
                                Text(
                                    text = name,
                                    style = TextStyle(color = Color.Black),
                                    color = Color.Black,
                                    modifier = Modifier
                                        .padding(start = 8.dp)
                                        .clickable {
                                            option = name
                                        }
                                )
                            }
                        }

                    }
                    Spacer(Modifier.height(10.dp))
                    GradientButton(
                        onClick = {
                            if (option.isEmpty()) {
                                Toast.makeText(
                                    context,
                                    "Please select payment option.",
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else {
                                isPlace = true
                            }
                        },
                        modifier = Modifier.padding(vertical = 5.dp).fillMaxWidth(),
                        textColor = white,
                        isEnabled = true,
                        gradient = Brush.horizontalGradient(listOf(yellow, yellow)),
                        text = "Submit"
                    )

                }


            }

            if (isPlace) {
                AlertDialog(
                    onDismissRequest = {
                        isPlace = false
                    },
                    title = { Text(stringResource(id = R.string.app_name)) },
                    text = { Text("You booking successfully done.") },
                    confirmButton = {
                        GradientButton(
                            onClick = {
                                navController.navigate(Screen.MainScreen.route) {
                                    popUpTo(Screen.SplashScreen.route) {
                                        inclusive = true
                                    }
                                }
                                isPlace = false
                            },
                            modifier = Modifier.padding(vertical = 5.dp).fillMaxWidth(),
                            textColor = white,
                            isEnabled = true,
                            gradient = Brush.horizontalGradient(listOf(yellow, yellow)),
                            text = "Ok"
                        )
                    },
                    dismissButton = { }
                )
            }
        }

    }
}

