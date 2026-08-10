package com.example.jhubexpensesapp.screens

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
import androidx.compose.ui.unit.sp
import com.example.jhubexpensesapp.viewModel.ExpenseViewModel


@Composable
fun ApdateExpenseScreen(viewModel: ExpenseViewModel){

    var title by remember {
        mutableStateOf("")
    }

    var cost by remember {
        mutableStateOf("")
    }

    var date by remember{
        mutableStateOf("")
    }

    Column(modifier = Modifier.fillMaxSize()
        .background(MaterialTheme.colorScheme.primary)) {
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
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                color = MaterialTheme.colorScheme.tertiary
            )

            Spacer(
                modifier = Modifier.weight(1f)
            )

            // Total cost
            Text(
                "£175.65",
                fontSize = 24.sp,
                color = MaterialTheme.colorScheme.secondary
            )

        }

        // Entries for title, cost and date
        Column(modifier = Modifier.padding(start = 8.dp,
            top = 30.dp)) {


            OutlinedTextField(
                title,
                onValueChange = {title = it},
                label = {Text("Title")}
            )

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            OutlinedTextField(
                cost,
                onValueChange = {cost = it},
                label = {Text("Cost")}
            )

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            // date will be taken from metadata
            OutlinedTextField(
                date,
                onValueChange = {date = it},
                label = {Text("Date")}
            )

        }


        // Where the image will be uploaded
        Box(
            modifier = Modifier
                .padding(start = 8.dp,
                    top = 100.dp)
                .size(300.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(MaterialTheme.colorScheme.onPrimary)

        ) {

            Image(
                painter = painterResource(R.drawable.blank_receipt),
                contentDescription = "Blank receipt image",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillBounds
            )

        }

        // Upload from gallery button
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            OutlinedButton (
                onClick = {
                    /* TODO */
                    // Open Gallery picker
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

            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
            ) {
                ElevatedButton(
                    onClick = {
                        // Submit
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

            IconButton(
                onClick = {
                    // Open camera
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