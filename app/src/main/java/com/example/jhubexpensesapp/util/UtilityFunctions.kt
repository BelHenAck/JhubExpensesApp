package com.example.jhubexpensesapp.util

import android.content.Context
import android.graphics.Bitmap
import android.media.ExifInterface
import android.net.Uri
import androidx.core.content.FileProvider
import androidx.core.net.toUri
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class UtilityFunctions {

    fun saveImage(context: Context, bitmap: Bitmap): Uri{

        val file = File(context.filesDir, "receipt_${System.currentTimeMillis()}.jpg")

        FileOutputStream(file).use {
            output ->
            bitmap.compress(Bitmap.CompressFormat.JPEG,90,output)
        }

        return file.toUri()

    }

    fun getImageDate(context: Context, uri: Uri): Long? {

        val inputStream = context.contentResolver.openInputStream(uri)
            ?: return null

        val exif = ExifInterface(inputStream)

        val dateString = exif.getAttribute(
            ExifInterface.TAG_DATETIME_ORIGINAL
        )

        inputStream.close()

        return dateString?.let{

            val formatter = SimpleDateFormat(

                "yyyy:MM:dd HH:mm:ss",
                Locale.getDefault()

            )

            formatter.parse(it)?.time

        }
    }

    fun formatDate(timeStamp: Long?): String {

        if(timeStamp == null){

            return("No date available")

        }
        val formatter = SimpleDateFormat(

            "yyyy:MM:dd HH:mm:ss",
            Locale.getDefault()

        )

        return formatter.format(Date(timeStamp))
    }

    fun createImageUri(context: Context): Uri {

        val imageFile = File(
            context.cacheDir,
            "camera_${System.currentTimeMillis()}.jpg"
        )

        return FileProvider.getUriForFile(
            context,
            "${context.packageName}.fileprovider",
            imageFile
        )
    }

}