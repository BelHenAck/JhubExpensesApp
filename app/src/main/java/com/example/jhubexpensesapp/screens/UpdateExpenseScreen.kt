package com.example.jhubexpensesapp.screens

import android.net.Uri
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
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
import androidx.core.net.toUri
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.jhubexpensesapp.database.Expense
import com.example.jhubexpensesapp.ui.theme.JhubExpensesAppTheme
import com.example.jhubexpensesapp.util.UtilityFunctions
import com.example.jhubexpensesapp.viewModel.ExpenseViewModel
import kotlin.text.toDoubleOrNull


@Composable
fun UpdateExpenseScreen(expense: Expense, viewModel: ExpenseViewModel,navController: NavController) {

    var utilF = UtilityFunctions()

    var title by remember {
        mutableStateOf(expense.expenseTitle)
    }

    var cost by remember {
        mutableStateOf(expense.cost.toString())
    }

    var selectedImageUri by remember {
        mutableStateOf(expense.imageUri?.toUri())
    }

    var date by remember {
        mutableStateOf<Long?>(expense.metaDataDate)
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

    JhubExpensesAppTheme() {

        Column(
            modifier = Modifier.fillMaxSize()
                .background(MaterialTheme.colorScheme.primary)
                .padding(top = 24.dp)
        ) {
            // Header
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.primary)
                    .height(64.dp)
                    .padding(horizontal = 18.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Add Expense Header
                Text(
                    "Update Expense",
                    fontSize = 28.sp,
                    color = MaterialTheme.colorScheme.tertiary
                )

                Spacer(
                    modifier = Modifier.weight(1f)
                )

                // Total cost
                Text(
                    "£%.2f".format(totalCost),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.secondary
                )

            }

            // Entries for title, cost and date
            Column(
                modifier = Modifier.padding(
                    start = 8.dp,
                    top = 30.dp
                )
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
                    value = cost,
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

            }


            // Where the image will be uploaded
            Box(
                modifier = Modifier
                    .padding(
                        start = 8.dp,
                        top = 100.dp
                    )
                    .size(300.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(MaterialTheme.colorScheme.onPrimary)

            ) {

                if (selectedImageUri != null) {

                    AsyncImage(
                        model = selectedImageUri,
                        contentDescription = "Receipt image",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )

                } else {

                    Image(
                        painter = painterResource(R.drawable.blank_receipt),
                        contentDescription = "Blank receipt image",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.FillBounds
                    )
                }

            }

            // Upload from gallery button
            Row(
                modifier = Modifier.padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                OutlinedButton(
                    onClick = {
                        galleryLauncher.launch("image/*")
                    },
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

            Spacer(
                modifier = Modifier.weight(1f)
            )

            // Footer
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.primary)
                    .height(64.dp)
                    .padding(horizontal = 18.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                IconButton(
                    onClick = {
                        navController.popBackStack()
                    }
                ) {

                    Icon(painter = painterResource(R.drawable.back_arrow_icon),
                        contentDescription = "Back")

                }

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                ) {
                    ElevatedButton(
                        onClick = {
                            viewModel.updateExpense(
                                Expense(
                                    id = expense.id,
                                    expenseTitle = title,
                                    cost = cost.toDoubleOrNull() ?: 0.0,
                                    metaDataDate = date ?: expense.metaDataDate,
                                    imageUri = selectedImageUri?.toString()
                                )
                            )
                            navController.popBackStack()
                        },
                        modifier = Modifier.align(Alignment.Center),
                        elevation = ButtonDefaults.elevatedButtonElevation(8.dp),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(
                            "Submit",
                            color = MaterialTheme.colorScheme.secondary
                        )
                    }
                }

                // Camera
                IconButton(
                    onClick = {

                        val uri = utilF.createImageUri(context)

                        cameraImageUri = uri

                        cameraLauncher.launch(uri)
                    }
                ) {
                    Icon(
                        painter = painterResource(R.drawable.outline_photo_camera_24),
                        contentDescription = "Open Camera"
                    )
                }
            }


        }
    }
}