package com.llprdctn.textrecognition_android

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.View
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions
import kotlinx.android.synthetic.main.activity_text_recognition.*
import java.io.File

class TextRecognition : AppCompatActivity() {

    private val TAG = "TextRecognition"


    // Bitmap
    private var selectedImage: Bitmap? = null




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_text_recognition)



        btnStart.setOnClickListener {
            startTextRecognition()
            progressBar.visibility = View.VISIBLE
        }

        imageView.setImageResource(R.drawable.test_image)
        selectedImage = BitmapFactory.decodeResource(resources, R.drawable.test_image)



    }


    private fun startTextRecognition() {

        val inputImage = InputImage.fromBitmap(selectedImage!!, 0)

        val recognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)
        recognizer.process(inputImage)
            .addOnSuccessListener {
                progressBar.visibility = View.GONE
                tvRecognizedText.text = it.text
                Log.d(TAG, "Successful")
            }
            .addOnFailureListener {
                Log.d(TAG, "Not Successful",it)
            }

    }




}