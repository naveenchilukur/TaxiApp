package com.taxiapp.ui.enquiry

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import androidx.navigation.NavController
import com.taxiapp.R
import com.taxiapp.routing.Screen
import com.taxiapp.ui.theme.TaxiAppTheme
import com.taxiapp.ui.theme.yellow
import com.taxiapp.ui.theme.white
import com.taxiapp.utils.GradientButton
import com.taxiapp.utils.TaxiFormField
import com.taxiapp.utils.RoundedButton
import com.taxiapp.utils.isValidEmail

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun EnquiryScreen(navController: NavController) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var mobile by remember { mutableStateOf("") }
    var hours by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }
    var isBooked by remember { mutableStateOf(false) }
    var textTypeDropDown by remember { mutableStateOf("") }
    var taxiType by rememberSaveable { mutableStateOf(false) }
    val taxiTypeList =
        listOf(
            "Indica--100/- per hour",
            "Swift--110/- per hour",
            "Dzire--120/- per hour",
            "Etios--150/- per hour",
            "Xylo--180/- per hour",
            "Ertiga--200/- per hour",
            "Etios--220/- per hour",
            "Amaze--300/- per hour",
        )

    val icon = if (taxiType)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    var wayDropDown by remember { mutableStateOf("") }
    var wayType by rememberSaveable { mutableStateOf(false) }
    val wayTypeList =
        listOf(
            "One Way",
            "Round Trip"
        )
    val wayIcon = if (wayType)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown
    TaxiAppTheme {
        Scaffold {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = yellow)
                    .padding(top = 40.dp)
                    .verticalScroll(scrollState)
            ) {
                SmallTopAppBar(
                    title = {
                        Text(
                            text = "Book", color = Color.White,
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
                    TaxiFormField(
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
                    TaxiFormField(
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
                    TaxiFormField(
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
                    TaxiFormField(
                        value = message,
                        onValueChange = { text ->
                            message = text
                        },
                        placeholder = "Enter requirement",
                        keyboardType = KeyboardType.Text,
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        "Taxi Type",
                        modifier = Modifier.fillMaxWidth(),
                        style = TextStyle(color = white)
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    OutlinedTextField(
                        value = if (textTypeDropDown != "") textTypeDropDown else "Select taxi type",
                        onValueChange = { textTypeDropDown = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                            .clickable { taxiType = !taxiType },
                        enabled = false,
                        trailingIcon = {
                            Icon(
                                icon, "contentDescription", tint = white
                            )
                        },
                        textStyle = TextStyle(color = white),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = white,
                            unfocusedBorderColor = white,
                            disabledBorderColor = white
                        ),
                        shape = RoundedCornerShape(10.dp),
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        if (taxiType) {
                            Popup(
                                alignment = Alignment.TopCenter,
                                properties = PopupProperties(
                                    excludeFromSystemGesture = true,
                                ),
                                onDismissRequest = { taxiType = false }
                            ) {

                                Column(
                                    modifier = Modifier
                                        .heightIn(max = 220.dp)
                                        .verticalScroll(state = scrollState)
                                        .padding(10.dp)
                                        .border(width = 1.dp, color = Color.Gray),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                ) {

                                    taxiTypeList.onEachIndexed { index, item ->
                                        if (index != 0) {
                                            Divider(
                                                thickness = 1.dp,
                                                color = Color.LightGray
                                            )
                                        }
                                        Box(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .background(white)
                                                .padding(10.dp)
                                                .clickable {
                                                    textTypeDropDown = item
                                                    taxiType = !taxiType
                                                },
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Text(
                                                text = item,
                                                style = TextStyle(color = Color.Black)
                                            )
                                        }
                                    }

                                }
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        "Way",
                        modifier = Modifier.fillMaxWidth(),
                        style = TextStyle(color = white)
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    OutlinedTextField(
                        value = if (wayDropDown != "") wayDropDown else "Select way",
                        onValueChange = { wayDropDown = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                            .clickable { wayType = !wayType },
                        enabled = false,
                        trailingIcon = {
                            Icon(
                                wayIcon, "contentDescription", tint = white
                            )
                        },
                        textStyle = TextStyle(color = white),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = white,
                            unfocusedBorderColor = white,
                            disabledBorderColor = white
                        ),
                        shape = RoundedCornerShape(10.dp),
                    )

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        if (wayType) {
                            Popup(
                                alignment = Alignment.TopCenter,
                                properties = PopupProperties(
                                    excludeFromSystemGesture = true,
                                ),
                                onDismissRequest = { wayType = false }
                            ) {

                                Column(
                                    modifier = Modifier
                                        .heightIn(max = 220.dp)
                                        .verticalScroll(state = scrollState)
                                        .padding(10.dp)
                                        .border(width = 1.dp, color = Color.Gray),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                ) {

                                    wayTypeList.onEachIndexed { index, item ->
                                        if (index != 0) {
                                            Divider(
                                                thickness = 1.dp,
                                                color = Color.LightGray
                                            )
                                        }
                                        Box(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .background(white)
                                                .padding(10.dp)
                                                .clickable {
                                                    wayDropDown = item
                                                    wayType = !wayType
                                                },
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Text(
                                                text = item,
                                                style = TextStyle(color = Color.Black)
                                            )
                                        }
                                    }

                                }
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        "Number of hours",
                        modifier = Modifier.fillMaxWidth(),
                        style = TextStyle(color = white)
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    TaxiFormField(
                        value = hours,
                        onValueChange = { text ->
                            hours = text
                        },
                        placeholder = "Enter number of hours",
                        keyboardType = KeyboardType.Number,
                    )
                    Spacer(modifier = Modifier.height(30.dp))
                    Row(
                        modifier = Modifier
                    ) {
                        GradientButton(
                            onClick = {
                                if (name.isNotEmpty()) {
                                    if (email.isNotEmpty()) {
                                        if (!isValidEmail(email.trim())) {
                                            if (mobile.isNotEmpty()) {
                                                if (message.isNotEmpty()) {
                                                    if (textTypeDropDown.isNotEmpty()) {
                                                        if (wayDropDown.isNotEmpty()) {
                                                            if (hours.isNotEmpty()) {
                                                                navController.navigate(Screen.Payment.route)
                                                            } else {
                                                                Toast.makeText(
                                                                    context,
                                                                    "Please enter number of hours.",
                                                                    Toast.LENGTH_LONG
                                                                ).show()
                                                            }

                                                        } else {
                                                            Toast.makeText(
                                                                context,
                                                                "Please select way type.",
                                                                Toast.LENGTH_LONG
                                                            ).show()
                                                        }
                                                    } else {
                                                        Toast.makeText(
                                                            context,
                                                            "Please select taxi type.",
                                                            Toast.LENGTH_LONG
                                                        ).show()
                                                    }
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
                            },
                            modifier = Modifier.padding(vertical = 5.dp).fillMaxWidth(),
                            textColor = yellow,
                            isEnabled = true,
                            gradient = Brush.horizontalGradient(listOf(white, white)),
                            text = "Submit"
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))
                }

            }


        }
        if (isBooked) {
            AlertDialog(
                onDismissRequest = {
                    isBooked = false
                },
                title = { Text(stringResource(id = R.string.app_name)) },
                text = { Text("Successfully submitted!!!") },
                confirmButton = {
                    GradientButton(
                        onClick = {
                            navController.navigateUp()
                            isBooked = false
                        },
                        modifier = Modifier.padding(vertical = 5.dp).fillMaxWidth(),
                        textColor = white,
                        isEnabled = true,
                        gradient = Brush.horizontalGradient(listOf(yellow, yellow)),
                        text = "Ok"
                    )
                },
                dismissButton = {}
            )
        }

    }
}