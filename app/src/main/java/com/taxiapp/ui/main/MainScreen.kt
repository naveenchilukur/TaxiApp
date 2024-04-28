package com.taxiapp.ui.main

import android.annotation.SuppressLint
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.taxiapp.R
import com.taxiapp.routing.Screen
import com.taxiapp.ui.model.TaxiModel
import com.taxiapp.ui.taxi_preference.TaxiPreference
import com.taxiapp.ui.theme.TaxiAppTheme
import com.taxiapp.ui.theme.white
import com.taxiapp.ui.theme.yellow
import com.taxiapp.utils.ApiService
import com.taxiapp.utils.GradientButton
import com.taxiapp.utils.RoundedButton
import com.taxiapp.utils.createClient
import com.taxiapp.utils.drawer.DrawerBody
import com.taxiapp.utils.drawer.DrawerHeader
import com.taxiapp.utils.drawer.TopBar
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(navController: NavController) {
    val context = LocalContext.current
    val preference = remember {
        TaxiPreference(context)
    }
    val scope = rememberCoroutineScope()
    var isLogout by remember { mutableStateOf(false) }
    val scaffoldState = rememberScaffoldState()
    val scrollState = rememberScrollState()
    var search by remember { mutableStateOf("") }
    val apiService: ApiService = createClient().create(ApiService::class.java)

    val list = arrayListOf<TaxiModel>().apply {
        add(
            TaxiModel(
                fromAddress = "Jaipur",
                toAddress = "Mumbai",
                image = "",
                name = "Mumbai Tour & Travels",
                mobile = "9898989898"
            )
        )
        add(
            TaxiModel(
                fromAddress = "Jaipur",
                toAddress = "Mumbai",
                image = "",
                name = "Jaipur Tour & Travels",
                mobile = "9898989898"
            )
        )
        add(
            TaxiModel(
                fromAddress = "Jaipur",
                toAddress = "Mumbai",
                image = "",
                name = "Delhi Express",
                mobile = "9898989898"
            )
        )
        add(
            TaxiModel(
                fromAddress = "Jaipur",
                toAddress = "Mumbai",
                image = "",
                name = "Jammu Travels",
                mobile = "9898989898"
            )
        )
        add(
            TaxiModel(
                fromAddress = "Jaipur",
                toAddress = "Mumbai",
                image = "",
                name = "Mumbai Tour & Travels",
                mobile = "9898989898"
            )
        )
        add(
            TaxiModel(
                fromAddress = "Jaipur",
                toAddress = "Mumbai",
                image = "",
                name = "Jaipur Tour & Travels",
                mobile = "9898989898"
            )
        )
        add(
            TaxiModel(
                fromAddress = "Jaipur",
                toAddress = "Mumbai",
                image = "",
                name = "Delhi Express",
                mobile = "9898989898"
            )
        )
        add(
            TaxiModel(
                fromAddress = "Jaipur",
                toAddress = "Mumbai",
                image = "",
                name = "Jammu Travels",
                mobile = "9898989898"
            )
        )

        add(
            TaxiModel(
                fromAddress = "Jaipur",
                toAddress = "Mumbai",
                image = "",
                name = "Mumbai Tour & Travels",
                mobile = "9898989898"
            )
        )
        add(
            TaxiModel(
                fromAddress = "Jaipur",
                toAddress = "Mumbai",
                image = "",
                name = "Jaipur Tour & Travels",
                mobile = "9898989898"
            )
        )
        add(
            TaxiModel(
                fromAddress = "Jaipur",
                toAddress = "Mumbai",
                image = "",
                name = "Delhi Express",
                mobile = "9898989898"
            )
        )
        add(
            TaxiModel(
                fromAddress = "Jaipur",
                toAddress = "Mumbai",
                image = "",
                name = "Jammu Travels",
                mobile = "9898989898"
            )
        )

    }
    /*val call: Call<Response<String>> = apiService.yourData
    call.enqueue(object : Callback<Response<String>> {
        override fun onResponse(
            call: Call<Response<String>?>,
            response: Response<Response<String>?>
        ) {
            if (response.isSuccessful) {

            }
        }

        override fun onFailure(call: Call<Response<String>?>, t: Throwable) {

        }
    })*/
    TaxiAppTheme {
        androidx.compose.material.Scaffold(
            scaffoldState = scaffoldState,
            topBar = {
                TopBar(
                    navController = navController,
                    onNavigationIconClick = {
                        scope.launch {
                            scaffoldState.drawerState.open()
                        }
                    }
                )
            },
            modifier = Modifier.background(color = yellow),
            drawerContent = {
                DrawerHeader()
                DrawerBody(onLogout = {
                    isLogout = true
                    scope.launch {
                        scaffoldState.drawerState.close()
                    }
                })
            },
            backgroundColor = yellow,
            contentColor = yellow,
            drawerBackgroundColor = yellow
        ) { paddingValues ->
            Modifier.padding(
                bottom = paddingValues.calculateBottomPadding()
            )
            Column(
                modifier = Modifier
                    .background(color = yellow)
                    .verticalScroll(scrollState)
            ) {

                Column {
                    list.forEachIndexed { index, taxiModel ->
                        Card(
                            modifier = Modifier
                                .padding(bottom = 10.dp, start = 10.dp, end = 10.dp)
                                .fillMaxWidth()
                                .height(180.dp),
                            shape = RoundedCornerShape(10.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.White),
                            elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
                        ) {
                            Spacer(Modifier.height(10.dp))
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Image(
                                    painter = painterResource(id = R.drawable.ic_taxi),
                                    contentDescription = "Image",
                                    contentScale = ContentScale.Fit,
                                    modifier = Modifier
                                        .width(40.dp)
                                        .height(40.dp)
                                )
                                Column {
                                    Text(
                                        taxiModel.name ?: "",
                                        fontSize = 14.sp,
                                        color = Color.Black,
                                        modifier = Modifier
                                            .padding(vertical = 5.dp, horizontal = 10.dp)
                                    )
                                    Text(
                                        taxiModel.mobile ?: "",
                                        fontSize = 14.sp,
                                        color = Color.Black,
                                        modifier = Modifier
                                            .padding(vertical = 5.dp, horizontal = 10.dp)
                                    )
                                }
                            }
                            Spacer(Modifier.height(10.dp))
                            Divider(
                                thickness = 1.5.dp,
                                color = Color.Gray,
                                modifier = Modifier.fillMaxWidth()
                            )
                            Spacer(Modifier.height(10.dp))
                            Row(
                                modifier = Modifier
                            ) {
                                GradientButton(
                                    onClick = {
                                        navController.navigate(Screen.Detail.route + "/${taxiModel.name}" + "/${taxiModel.mobile}")
                                    },
                                    modifier = Modifier.padding(vertical = 5.dp).fillMaxWidth(),
                                    textColor = white,
                                    isEnabled = true,
                                    gradient = Brush.horizontalGradient(listOf(yellow, yellow)),
                                    text = "Continue"
                                )
                            }

                        }
                    }
                }
            }
        }
        if (isLogout) {
            AlertDialog(
                onDismissRequest = {
                    isLogout = false
                },
                title = { Text(stringResource(id = R.string.app_name)) },
                text = { Text("Are you sure you want to logout ?") },
                confirmButton = {
                    GradientButton(
                        onClick = {
                            isLogout = false
                        },
                        modifier = Modifier.padding(vertical = 5.dp).fillMaxWidth(),
                        textColor = white,
                        isEnabled = true,
                        gradient = Brush.horizontalGradient(listOf(yellow, yellow)),
                        text =  "Cancel"
                    )
                },
                dismissButton = {
                    GradientButton(
                        onClick = {
                            preference.saveData("isLogin", false)
                            navController.navigate(
                                Screen.LoginScreen.route
                            ) {
                                popUpTo(Screen.MainScreen.route) {
                                    inclusive = true
                                }
                            }
                            isLogout = false
                        },
                        modifier = Modifier.padding(vertical = 5.dp).fillMaxWidth(),
                        textColor = white,
                        isEnabled = true,
                        gradient = Brush.horizontalGradient(listOf(yellow, yellow)),
                        text =  "Logout"
                    )


                }
            )
        }

    }

}