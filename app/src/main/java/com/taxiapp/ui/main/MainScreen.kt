package com.taxiapp.ui.main

import android.annotation.SuppressLint
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.taxiapp.R
import com.taxiapp.routing.Screen
import com.taxiapp.ui.model.TaxiModel
import com.taxiapp.ui.theme.TaxiAppTheme
import com.taxiapp.ui.theme.white
import com.taxiapp.ui.theme.yellow
import com.taxiapp.utils.CustomSearchView
import com.taxiapp.utils.RoundedButton

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(navController: NavController) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()
    var search by remember { mutableStateOf("") }
    val list = arrayListOf<TaxiModel>().apply {
        add(TaxiModel(fromAddress = "Jaipur", toAddress = "Mumbai", image = "", name = "Test Tour & Travels", mobile = "9898989898"))
        add(TaxiModel(fromAddress = "Jaipur", toAddress = "Mumbai", image = "", name = "Test Tour & Travels", mobile = "9898989898"))
        add(TaxiModel(fromAddress = "Jaipur", toAddress = "Mumbai", image = "", name = "Test Tour & Travels", mobile = "9898989898"))
        add(TaxiModel(fromAddress = "Jaipur", toAddress = "Mumbai", image = "", name = "Test Tour & Travels", mobile = "9898989898"))
        add(TaxiModel(fromAddress = "Jaipur", toAddress = "Mumbai", image = "", name = "Test Tour & Travels", mobile = "9898989898"))
        add(TaxiModel(fromAddress = "Jaipur", toAddress = "Mumbai", image = "", name = "Test Tour & Travels", mobile = "9898989898"))
        add(TaxiModel(fromAddress = "Jaipur", toAddress = "Mumbai", image = "", name = "Test Tour & Travels", mobile = "9898989898"))
        add(TaxiModel(fromAddress = "Jaipur", toAddress = "Mumbai", image = "", name = "Test Tour & Travels", mobile = "9898989898"))
        add(TaxiModel(fromAddress = "Jaipur", toAddress = "Mumbai", image = "", name = "Test Tour & Travels", mobile = "9898989898"))
        add(TaxiModel(fromAddress = "Jaipur", toAddress = "Mumbai", image = "", name = "Test Tour & Travels", mobile = "9898989898"))
        add(TaxiModel(fromAddress = "Jaipur", toAddress = "Mumbai", image = "", name = "Test Tour & Travels", mobile = "9898989898"))
        add(TaxiModel(fromAddress = "Jaipur", toAddress = "Mumbai", image = "", name = "Test Tour & Travels", mobile = "9898989898"))
    }
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
                            text = "Home", color = Color.White,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                            style = TextStyle(fontWeight = FontWeight.SemiBold, fontSize = 18.sp)
                        )
                    },
                    colors = TopAppBarDefaults.smallTopAppBarColors(
                        containerColor = yellow,
                        titleContentColor = Color.White
                    )
                )
                Spacer(Modifier.height(10.dp))

                Column {
                    list.forEachIndexed { index, candidateModel ->
                        Card(
                            modifier = Modifier
                                .padding(bottom = 10.dp, start = 10.dp, end = 10.dp)
                                .fillMaxWidth()
                                .height(190.dp),
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
                                        candidateModel.name ?: "",
                                        fontSize = 14.sp,
                                        color = Color.Black,
                                        modifier = Modifier
                                            .padding(vertical = 5.dp, horizontal = 10.dp)
                                    )
                                    Text(
                                        candidateModel.mobile ?: "",
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
                            Box(Modifier.padding(15.dp)) {
                                RoundedButton(
                                    text = "Continue",
                                    textColor = white,
                                    onClick = {
                                        navController.navigate(Screen.Detail.route)
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }


    }

}