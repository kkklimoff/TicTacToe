package com.kk191rdb020.tictactoe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
    }
    fun openCredits(view: View){
        val creditsInt = Intent(this,CreditsActivity::class.java)
        startActivity(creditsInt)
    }
    fun playPVP(view: View){
        val pvpSetupInt = Intent(this, PlayerSetup::class.java).apply {
            putExtra("GAMEMODE","PVP")
        }
        startActivity(pvpSetupInt)
    }
    fun playPVE(view: View){
        val pveSetupInt = Intent(this, PlayerSetup::class.java).apply {
            putExtra("GAMEMODE","PVE")
        }
        startActivity(pveSetupInt)
    }
}