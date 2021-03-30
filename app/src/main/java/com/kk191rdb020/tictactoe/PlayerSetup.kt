package com.kk191rdb020.tictactoe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView

class PlayerSetup : AppCompatActivity() {
    lateinit var p1namefield: EditText
    lateinit var p2namefield: EditText
    lateinit var p1Name: String
    lateinit var p2Name: String
    lateinit var gamemode: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_setup)
        gamemode = intent.getStringExtra("GAMEMODE").toString()
        p1namefield = findViewById(R.id.editTextTextPersonName)
        p2namefield = findViewById(R.id.editTextTextPersonName2)

        if(gamemode=="PVE"){
            val p2nt: TextView = findViewById(R.id.textView3)
            p2nt.visibility=View.INVISIBLE
            p2namefield.visibility=View.INVISIBLE
            p2Name="CPU"
        }

    }

    fun submitPlayerData(view: View){
        p1Name = p1namefield.text.toString()
        if(gamemode=="PVP") p2Name = p2namefield.text.toString()
        if(p1Name==""){
            p1namefield.setError("Name is required!")
        }else if(p2Name==""){
            p2namefield.setError("Name is required!")
        }else {
            val submitD = Intent(this, gameScreenActivity::class.java).apply {
                putExtra("GAMEMODE",gamemode)
                putExtra("P1NAME", p1Name)
                putExtra("P2NAME", p2Name)
            }
            startActivity(submitD)
        }
    }
}