package com.maksimyurau.android.qrcodegenerator

import android.content.Context
import android.graphics.Bitmap
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.WriterException
import com.google.zxing.common.BitMatrix
import com.journeyapps.barcodescanner.BarcodeEncoder
import java.lang.IllegalArgumentException

class MainActivity : AppCompatActivity() {
    private lateinit var etInput: EditText
    private lateinit var btGenerate: Button
    private lateinit var ivOutput: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeViews()
        btGenerateOnClickListener()
    }

    private fun initializeViews() {
        etInput = findViewById(R.id.et_input)
        btGenerate = findViewById(R.id.bt_generate)
        ivOutput = findViewById(R.id.iv_output)
    }

    private fun btGenerateOnClickListener() {
        btGenerate.setOnClickListener {
            val sText: String = etInput.text.toString().trim()
            val writer = MultiFormatWriter()
            try {
                val matrix: BitMatrix = writer.encode(sText, BarcodeFormat.QR_CODE, 350, 350)
                val encoder = BarcodeEncoder()
                val bitmap: Bitmap = encoder.createBitmap(matrix)
                ivOutput.setImageBitmap(bitmap)
                val manager: InputMethodManager =
                    getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                manager.hideSoftInputFromWindow(etInput.applicationWindowToken, 0)
            } catch (e: WriterException) {
                e.printStackTrace()
            } catch (e: IllegalArgumentException) {
                e.printStackTrace()
            }
        }
    }
}