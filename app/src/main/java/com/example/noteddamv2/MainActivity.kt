package com.example.noteddamv2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatButton

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var ButtonAdaugaNotita:AppCompatButton = findViewById(R.id.ButtonAdaugaNotite)
        var ButtonVeziNotite:AppCompatButton = findViewById(R.id.ButtonNotite)

        ButtonAdaugaNotita.setOnClickListener{
            val intent = Intent(this, AddNoteActivity::class.java).apply {
            }
            startActivity(intent)
        }

        ButtonVeziNotite.setOnClickListener {
            val intent = Intent(this, VeziNoteActivity::class.java).apply {
            }
            startActivity(intent)
        }
    }
}
