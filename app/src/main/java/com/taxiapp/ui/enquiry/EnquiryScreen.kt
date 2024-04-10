package com.taxiapp.ui.enquiry

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.taxiapp.routing.Screen
import com.taxiapp.ui.theme.TaxiAppTheme
import com.taxiapp.ui.theme.yellow
import com.taxiapp.ui.theme.white
import com.taxiapp.utils.OutlineFormField
import com.taxiapp.utils.RoundedButton

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun EnquiryScreen(navController: NavController) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var mobile by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }
    TaxiAppTheme {
        Scaffold {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = yellow)
                    .padding(top = 40.dp)
            ) {
                SmallTopAppBar(
                    title = {
                        Text(
                            text = "Enquiry", color = Color.White,
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
                Column(
                    Modifier
                        .fillMaxSize()
                        .background(color = yellow)
                ) {
                    Spacer(modifier = Modifier.height(30.dp))
                    Text(
                        "Name",
                        modifier = Modifier.fillMaxWidth(),
                        style = TextStyle(color = white)
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    OutlineFormField(
                        value = name,
                        onValueChange = { text ->
                            name = text
                        },
                        placeholder = "Enter name",
                        keyboardType = KeyboardType.Text,
                    )

                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        "Email",
                        modifier = Modifier.fillMaxWidth(),
                        style = TextStyle(color = white)
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    OutlineFormField(
                        value = email,
                        onValueChange = { text ->
                            email = text
                        },
                        placeholder = "Enter email",
                        keyboardType = KeyboardType.Email,
                    )

                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        "Mobile Number",
                        modifier = Modifier.fillMaxWidth(),
                        style = TextStyle(color = white)
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    OutlineFormField(
                        value = mobile,
                        onValueChange = { text ->
                            mobile = text
                        },
                        placeholder = "Enter mobile number",
                        keyboardType = KeyboardType.Phone,
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        "Requirement",
                        modifier = Modifier.fillMaxWidth(),
                        style = TextStyle(color = white)
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    OutlineFormField(
                        value = message,
                        onValueChange = { text ->
                            message = text
                        },
                        placeholder = "Enter requirement",
                        keyboardType = KeyboardType.Text,
                    )
                    Spacer(modifier = Modifier.height(30.dp))
                    Box(Modifier.padding(15.dp)) {
                        RoundedButton(
                            text = "Send Enquiry",
                            textColor = yellow,
                            onClick = {
                                if (name.isNotEmpty()) {
                                    if (email.isNotEmpty()) {
                                        if (mobile.isNotEmpty()) {
                                            if (message.isNotEmpty()) {
                                                Toast.makeText(
                                                    context,
                                                    "Successfully submitted..",
                                                    Toast.LENGTH_LONG
                                                ).show()
                                                navController.navigateUp()
                                            } else {
                                                Toast.makeText(
                                                    context,
                                                    "Please enter message.",
                                                    Toast.LENGTH_LONG
                                                ).show()
                                            }
                                        } else {
                                            Toast.makeText(
                                                context,
                                                "Please enter mobile number.",
                                                Toast.LENGTH_LONG
                                            ).show()
                                        }
                                    } else {
                                        Toast.makeText(
                                            context,
                                            "Please enter email.",
                                            Toast.LENGTH_LONG
                                        ).show()
                                    }
                                } else {
                                    Toast.makeText(
                                        context,
                                        "Please enter name.",
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                            }
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                }

            }


        }


    }
}