package com.taxiapp.ui.register

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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import com.taxiapp.R
import com.taxiapp.routing.Screen
import com.taxiapp.ui.theme.TaxiAppTheme
import com.taxiapp.utils.OutlineFormField
import com.taxiapp.utils.RoundedButton
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.taxiapp.ui.taxi_preference.TaxiPreference
import com.taxiapp.ui.theme.yellow
import com.taxiapp.ui.theme.white
import com.taxiapp.utils.isValidEmail

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun RegisterScreen(navController: NavController) {
    val context = LocalContext.current
    val preference = remember {
        TaxiPreference(context)
    }
    val scrollState = rememberScrollState()
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val db = Firebase.firestore
    TaxiAppTheme {
        Scaffold {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(yellow)
                    .padding(10.dp)
                    .verticalScroll(scrollState)
            ) {
                Spacer(modifier = Modifier.height(60.dp))
                Image(
                    painter = painterResource(id = R.drawable.ic_taxi),
                    contentDescription = "",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .width(120.dp)
                        .height(120.dp)
                        .align(Alignment.CenterHorizontally)
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    "Register via Customer or Taxi Provider",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    style = TextStyle(color = white)
                )

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
                    "Password",
                    modifier = Modifier.fillMaxWidth(),
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
                        text = "Register",
                        textColor = yellow,
                        onClick = {
                            if (name.isNotEmpty()) {
                                if (email.isNotEmpty()) {
                                    if (!isValidEmail(email.trim())) {
                                        if (password.isNotEmpty()) {
                                            val user = hashMapOf(
                                                "name" to name,
                                                "email" to email,
                                                "password" to password
                                            )
                                            db.collection("users")
                                                .get()
                                                .addOnSuccessListener { result ->
                                                    if (result.isEmpty) {
                                                        db.collection("users")
                                                            .add(user)
                                                            .addOnSuccessListener { documentReference ->
                                                                preference.saveData(
                                                                    "isLogin",
                                                                    true
                                                                )
                                                                Toast.makeText(
                                                                    context,
                                                                    "Register successfully.",
                                                                    Toast.LENGTH_SHORT
                                                                ).show()
                                                                navController.navigate(
                                                                    Screen.MainScreen.route
                                                                ) {
                                                                    popUpTo(Screen.LoginScreen.route) {
                                                                        inclusive = true
                                                                    }
                                                                }
                                                            }
                                                            .addOnFailureListener { e ->
                                                                Toast.makeText(
                                                                    context,
                                                                    e.message.toString(),
                                                                    Toast.LENGTH_SHORT
                                                                ).show()
                                                            }
                                                    } else {
                                                        for (document in result) {
                                                            if (document.data["email"] == email &&
                                                                document.data["password"] == password
                                                            ) {
                                                                Toast.makeText(
                                                                    context,
                                                                    "Already exists.",
                                                                    Toast.LENGTH_SHORT
                                                                ).show()
                                                                return@addOnSuccessListener
                                                            } else {
                                                                db.collection("users")
                                                                    .add(user)
                                                                    .addOnSuccessListener { documentReference ->
                                                                        preference.saveData(
                                                                            "isLogin",
                                                                            true
                                                                        )
                                                                        Toast.makeText(
                                                                            context,
                                                                            "Register successfully.",
                                                                            Toast.LENGTH_SHORT
                                                                        ).show()
                                                                        navController.navigate(
                                                                            Screen.MainScreen.route
                                                                        ) {
                                                                            popUpTo(Screen.RegisterScreen.route) {
                                                                                inclusive = true
                                                                            }
                                                                        }
                                                                    }
                                                                    .addOnFailureListener { e ->
                                                                        Toast.makeText(
                                                                            context,
                                                                            e.message.toString(),
                                                                            Toast.LENGTH_SHORT
                                                                        ).show()
                                                                    }
                                                            }
                                                        }
                                                    }
                                                }
                                                .addOnFailureListener { exception ->
                                                    Toast.makeText(
                                                        context,
                                                        exception.message.toString(),
                                                        Toast.LENGTH_SHORT
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
                                            "Please enter valid email.",
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


            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 20.dp),
                verticalArrangement = Arrangement.Bottom
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        "Already have an account?",
                        textAlign = TextAlign.End,
                        style = TextStyle(color = white)
                    )

                    Text(
                        " Login", modifier = Modifier.clickable {
                            navController.navigate(Screen.LoginScreen.route) {
                                popUpTo(Screen.RegisterScreen.route) {
                                    inclusive = true
                                }
                            }
                        }, textAlign = TextAlign.End,
                        style = TextStyle(color = white)
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
            }


        }
    }
}