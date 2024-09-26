package com.example.addimage

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResult
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.net.URI
import android.net.Uri

class MainActivity : AppCompatActivity() {
    private lateinit var photoImageView: ImageView
    private val PICK_IMAGE_REQUEST = 1
    private var imageUri:Uri? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        photoImageView = findViewById(R.id.imageView)
        val addbutton = findViewById<Button>(R.id.addbutton)
        val nameText = findViewById<EditText>(R.id.nameText)
        val addressText = findViewById<EditText>(R.id.addressText)
        val positionText = findViewById<EditText>(R.id.positionText)
        val submitbutton = findViewById<TextView>(R.id.submitbutton)
        val resultText = findViewById<TextView>(R.id.resultView)

        addbutton.setOnClickListener{
            openImageChooser()
        }
        submitbutton.setOnClickListener{
            val name = nameText.text.toString()
            val address = addressText.text.toString()
            val position = positionText.text.toString()

            if (name.isNotEmpty() && address.isNotEmpty() && position.isNotEmpty()){
                resultText.text = "Name: $name\nAddress: $address\nPosition: $position"
            } else{
                resultText.text = "Plese fill in all fields."
            }

        }
    }
    private fun openImageChooser() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, PICK_IMAGE_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.data != null) {
            imageUri=data.data
            photoImageView.setImageURI(imageUri)
        }
    }
}
