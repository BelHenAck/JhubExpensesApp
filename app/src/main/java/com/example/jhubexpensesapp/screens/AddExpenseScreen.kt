package com.example.jhubexpensesapp.screens

import android.net.Uri
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.jhubexpensesapp.R
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.jhubexpensesapp.database.Expense
import com.example.jhubexpensesapp.util.UtilityFunctions
import com.example.jhubexpensesapp.viewModel.ExpenseViewModel
import androidx.compose.foundation.layout.navigationBarsPadding



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddExpenseScreen(viewModel: ExpenseViewModel, navController: NavController) {

    val utilF = UtilityFunctions()

    var title by remember {
        mutableStateOf("")
    }

    var cost by remember {
        mutableStateOf("")
    }

    var date by remember {
        mutableStateOf<Long?>(null)
    }

    var selectedImageUri by remember {
        mutableStateOf<Uri?>(null)
    }

    var cameraImageUri by remember {

        mutableStateOf<Uri?>(null)

    }

    val totalCost by viewModel.getAllCosts.collectAsState(initial = 0.0)

    val context = LocalContext.current

    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) {

            uri: Uri? ->

        uri?.let {

            date = utilF.getImageDate(context, it)

            val bitmap = MediaStore.Images.Media.getBitmap(context.contentResolver, it)

            selectedImageUri = utilF.saveImage(context, bitmap)
        }

    }

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture()
    ) { success ->

        if (success) {

            cameraImageUri?.let { uri ->

                selectedImageUri = uri

                date = utilF.getImageDate(
                    context,
                    uri
                )
            }
        }
    }
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            "Add Expense",
                            fontSize = 28.sp,
                            color = MaterialTheme.colorScheme.tertiary
                        )
                    },
                    actions = {
                        Text(
                            "£%.2f".format(totalCost),
                            fontSize = 24F.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.secondary,
                            modifier = Modifier.padding(end = 16.dp)
                        )
                    })
            },

            bottomBar = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.primary)
                        .navigationBarsPadding()
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Back
                    IconButton(
                        onClick = { navController.popBackStack() }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.back_arrow_icon),
                            contentDescription = "Back"
                        )
                    }
                    //Submit
                    ElevatedButton(
                        onClick = {
                            viewModel.insertExpense(
                                Expense(
                                    expenseTitle = title,
                                    cost = cost.toDoubleOrNull() ?: 0.0,
                                    metaDataDate = System.currentTimeMillis(),
                                    imageUri = selectedImageUri?.toString()
                                )
                            )
                            navController.popBackStack()
                        }
                    ) {
                        Text("Submit",
                            color = MaterialTheme.colorScheme.secondary)
                    }
                    //Camera
                    IconButton(
                        onClick = {
                            val uri = utilF.createImageUri(context)
                            cameraImageUri = uri
                            cameraLauncher.launch(uri)
                        }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.outline_photo_camera_24),
                            contentDescription = "Camera"
                        )
                    }
                }
            }

        )

        { paddingValues ->

            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .padding(horizontal = 8.dp)
            ) {

                OutlinedTextField(
                    title,
                    onValueChange = { title = it },
                    label = { Text("Title") }
                )

                Spacer(
                    modifier = Modifier.height(16.dp)
                )

                OutlinedTextField(
                    cost,
                    onValueChange = { cost = it },
                    label = { Text("Cost") }
                )

                Spacer(
                    modifier = Modifier.height(16.dp)
                )

                // date will be taken from metadata
                OutlinedTextField(
                    value = utilF.formatDate(date),
                    onValueChange = {},
                    label = { Text("Date") },
                    readOnly = true
                )

                // Where the image will be uploaded
                Box(
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .size(250.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.5f)),
                    contentAlignment = Alignment.Center

                ) {

                    if (selectedImageUri != null) {

                        AsyncImage(
                            selectedImageUri,
                            contentDescription = "Receipt",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )

                    } else {
                        Image(
                            painter = painterResource(R.drawable.no_photo_image),
                            contentDescription = "Receipt",
                            modifier = Modifier.size(150.dp),
                            contentScale = ContentScale.FillBounds
                        )
                    }

                }

                // Upload from gallery button
                    OutlinedButton(
                        onClick = {
                            galleryLauncher.launch("image/*")
                        },
                        modifier = Modifier.padding(top = 8.dp),
                        shape = RoundedCornerShape(4.dp)
                    ) {

                        Icon(
                            painter = painterResource(R.drawable.upload_icon),
                            "Upload from gallery Icon"
                        )

                        Spacer(
                            modifier = Modifier.width(4.dp)
                        )

                        Text("Gallery")

                    }


                }

            }





        }