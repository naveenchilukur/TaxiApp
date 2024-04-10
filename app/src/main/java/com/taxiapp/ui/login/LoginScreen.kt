package com.taxiapp.ui.login

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.taxiapp.R
import com.taxiapp.routing.Screen
import com.taxiapp.ui.theme.TaxiAppTheme
import com.taxiapp.ui.theme.yellow
import com.taxiapp.ui.theme.white
import com.taxiapp.utils.OutlineFormField
import com.taxiapp.utils.RoundedButton

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LoginScreen(navController: NavController) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val db = Firebase.firestore
    TaxiAppTheme {
        Scaffold {
            Column(
                modifier = Modifier.fillMaxSize().background(yellow).padding(10.dp)
            ) {
                Spacer(modifier = Modifier.height(60.dp))
                Image(
                    painter = painterResource(id = R.drawable.ic_taxi),
                    contentDescription = "",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.width(120.dp).height(120.dp).align(Alignment.CenterHorizontally)
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    "Sign in to Get Started",
                    modifier =Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    style = TextStyle(color = white)
                )

                Spacer(modifier = Modifier.height(30.dp))
                Text(
                    "Email",
                    modifier =Modifier.fillMaxWidth(),
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
                    "Password",
                    modifier =Modifier.fillMaxWidth(),
                    style = TextStyle(color = white)
                )
                Spacer(modifier = Modifier.height(10.dp))
                OutlineFormField(
                    value = password,
                    onValueChange = { text ->
                        password = text
                    },
                    placeholder = "Enter Password",
                    keyboardType = KeyboardType.Password,
                    visualTransformation = PasswordVisualTransformation(),
                )

                Spacer(modifier = Modifier.height(30.dp))
                Row(
                    modifier = Modifier
                ) {
                    RoundedButton(
                        text = "Login",
                        textColor = yellow,
                        onClick = {
                            if (email.isNotEmpty()) {
                                if (password.isNotEmpty()) {
                                    db.collection("users")
                                        .get()
                                        .addOnSuccessListener { result ->
                                            if (result.isEmpty) {
                                                Toast.makeText(
                                                    context,
                                                    "Invalid user.",
                                                    Toast.LENGTH_LONG
                                                ).show()
                                                return@addOnSuccessListener
                                            } else {
                                                for (document in result) {
                                                    Log.e(
                                                        "TAG",
                                                        "setOnClick: $document"
                                                    )
                                                    if (document.data["email"] == email &&
                                                        document.data["password"] == password
                                                    ) {
                                                        Toast.makeText(
                                                            context,
                                                            "Login successfully.",
                                                            Toast.LENGTH_LONG
                                                        ).show()
                                                        navController.navigate(
                                                            Screen.MainScreen.route
                                                        ) {
                                                            popUpTo(Screen.LoginScreen.route) {
                                                                inclusive = true
                                                            }
                                                        }
                                                    } else {
                                                        Toast.makeText(
                                                            context,
                                                            "Invalid user.",
                                                            Toast.LENGTH_LONG
                                                        ).show()
                                                        return@addOnSuccessListener
                                                    }
                                                }
                                            }

                                        }
                                        .addOnFailureListener { exception ->
                                            Toast.makeText(
                                                context,
                                                exception.message.toString(),
                                                Toast.LENGTH_LONG
                                            ).show()
                                        }
                                } else {
                                    Toast.makeText(
                                        context,
                                        "Please enter password.",
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
                        }
                    )
                }




            }
            Column(
                modifier = Modifier.fillMaxSize().padding(bottom = 20.dp),
                verticalArrangement = Arrangement.Bottom
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        "Don't have an account?",
                        textAlign = TextAlign.End,
                        style = TextStyle(color = white)
                    )

                    Text(
                        " Register", modifier = Modifier.clickable {
                            navController.navigate(Screen.RegisterScreen.route)
                        }, textAlign = TextAlign.End,
                        style = TextStyle(color = white)
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
            }


        }
    }
}