package com.kk191rdb020.tictactoe

import android.content.Intent
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import java.util.*
import kotlin.collections.ArrayList


class gameScreenActivity : AppCompatActivity() {
    lateinit var buttons: Array<Array<ImageButton>>
    lateinit var availbuttons: ArrayList<ImageButton>
    lateinit var curPlayerDisp: TextView
    lateinit var playAgButton: Button
    lateinit var mmenuButton: Button
    var player1Turn: Boolean = true
    var gameInProgress: Boolean = true
    lateinit var gamemode: String
    lateinit var p1name: String
    lateinit var p2name: String

    var roundCount: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_screen)
        gamemode = intent.getStringExtra("GAMEMODE").toString()
        p1name = intent.getStringExtra("P1NAME").toString()
        p2name = intent.getStringExtra("P2NAME").toString()
        curPlayerDisp = findViewById(R.id.curPlayName)
        playAgButton = findViewById(R.id.playag_button)
        mmenuButton = findViewById(R.id.mmenu_button)
        buttons = Array(3){r->
            Array(3){c->
                initButtons(r,c)
            }
        }
        availbuttons = ArrayList<ImageButton>()
        for(i in 0..2){
            for(j in 0..2){
                availbuttons.add(initavButtons(i,j))
            }
        }


        curPlayerDisp.text = "$p1name, its your turn"
    }
    private fun initButtons(r:Int,c:Int): ImageButton {
        val btn:ImageButton = findViewById(resources.getIdentifier("button$r$c","id",packageName))
        btn.setOnClickListener{
            onBtnClick(btn)
        }
        return btn
    }
    private fun initavButtons(r:Int,c:Int): ImageButton {
        val btn:ImageButton = findViewById(resources.getIdentifier("button$r$c","id",packageName))
        return btn
    }

    private fun onBtnClick(btn: ImageButton) {
        if(btn.drawable != null || !gameInProgress) return
        if(player1Turn){
            btn.setImageResource(R.drawable.cross)
        }else{
            btn.setImageResource(R.drawable.circle)

        }
        roundCount++
        availbuttons.remove(btn)
        if(checkForWin()){
            if(player1Turn)curPlayerDisp.text="$p1name has won"
            else curPlayerDisp.text="$p2name has won"
            gameInProgress = false
            playAgButton.visibility = View.VISIBLE
            mmenuButton.visibility = View.VISIBLE
        }else if(roundCount == 9){
            curPlayerDisp.text="DRAW"
            gameInProgress = false
            playAgButton.visibility = View.VISIBLE
            mmenuButton.visibility = View.VISIBLE
        }else{
            if(player1Turn)curPlayerDisp.text="$p2name, its your turn"
            else curPlayerDisp.text="$p1name, its your turn"
            player1Turn = !player1Turn
            if(gamemode=="PVE"&&!player1Turn){
                val r = Random()
                val randIndex = r.nextInt(availbuttons.size-0)+0
                val chBut = availbuttons[randIndex]
                onBtnClick(chBut)
            }

        }


    }

    private fun checkForWin(): Boolean {
        val fields = Array(3){r->
            Array(3){c->
                getButSym(buttons[r][c])
            }
        }
        for(i in 0..2){
            if((fields[i][0] == fields[i][1])&&
                (fields[i][0] == fields[i][2])&&
                (fields[i][0] != null)
            )return true
        }
        for(i in 0..2){
            if((fields[0][i] == fields[1][i])&&
                (fields[0][i] == fields[2][i])&&
                (fields[0][i] != null)
            )return true
        }
        if((fields[0][0] == fields[1][1])&&
            (fields[0][0] == fields[2][2])&&
            (fields[0][0] != null)
        )return true
        if((fields[0][2] == fields[1][1])&&
            (fields[0][2] == fields[2][0])&&
            (fields[0][2] != null)
        )return true
        return false
    }
    private fun getButSym(ibutton :ImageButton): Char?{
        val drw: Drawable? = ibutton.drawable
        val drwCross: Drawable? = ResourcesCompat.getDrawable(resources,R.drawable.cross,null)
        val drwCircle: Drawable? = ResourcesCompat.getDrawable(resources,R.drawable.circle, null)
        return when(drw?.constantState){
            drwCross?.constantState -> 'x'
            drwCircle?.constantState -> 'o'
            else -> null
        }
    }

    fun playAgButtonClick (view: View){
        for (i in 0..2){
            for(j in 0..2){
                buttons[i][j].setImageResource(0)

            }
            roundCount = 0
            player1Turn = true
            curPlayerDisp.text="$p1name, its your turn"
            playAgButton.visibility = View.INVISIBLE
            mmenuButton.visibility = View.INVISIBLE
            gameInProgress = true
            if(gamemode=="PVE") {
                availbuttons.clear()
                for (i in 0..2) {
                    for (j in 0..2) {
                        availbuttons.add(initavButtons(i, j))
                    }
                }
            }
        }
    }
    fun homeButtonClick (view: View){
        val goHome = Intent(this, StartActivity::class.java)
        startActivity(goHome)
    }
}