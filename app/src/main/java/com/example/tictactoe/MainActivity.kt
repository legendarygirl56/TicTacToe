package com.example.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() , View.OnClickListener{

    var player=true
    var count=0
    var boardstatus=Array(3){IntArray(3)}

    lateinit var board:Array<Array<Button>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        board= arrayOf(
            arrayOf(b1,b2,b3),
            arrayOf(b4,b5,b6),
            arrayOf(b7,b8,b9)
        )

        for (i in board)
        {
            for(button in i)
            {
                button.setOnClickListener(this)
            }
        }
        for(i in 0..2)
        {
            for(j in 0..2)
            {
                boardstatus[i][j]=-1
            }
        }

        reset.setOnClickListener{
            initializeboardstatus()
            count=0
            player=true
            tex.setText("Player X Turn")

        }

    }

    private fun initializeboardstatus()
    {
        for(i in 0..2)
        {
            for(j in 0..2)
            {
                boardstatus[i][j]=-1
            }
        }
        for (i in board)
        {
            for(button in i)
            {
                button.isEnabled=true
                button.text=""
            }
        }

    }

    private fun updateval(row:Int, col:Int, Player:Boolean)
    {
        val text = if(Player) "X" else "0"
        val value = if(Player) 1 else 0
        board[row][col].apply {
            isEnabled=false
            setText(text)
        }
        boardstatus[row][col]=value
    }

    override fun onClick(view: View) {
        when(view.id)
        {
            R.id.b1 -> {
                updateval(row=0, col=0, Player=player)
            }
            R.id.b2 -> {
                updateval(row=0, col=1, Player=player)
            }
            R.id.b3 -> {
                updateval(row=0, col=2, Player=player)
            }
            R.id.b4 -> {
                updateval(row=1, col=0, Player=player)
            }
            R.id.b5 -> {
                updateval(row=1, col=1, Player=player)
            }
            R.id.b6 -> {
                updateval(row=1, col=2, Player=player)
            }
            R.id.b7 -> {
                updateval(row=2, col=0, Player=player)
            }
            R.id.b8 -> {
                updateval(row=2, col=1, Player=player)
            }
            R.id.b9 -> {
                updateval(row=2, col=2, Player=player)
            }
        }
        count++
        player=!player
        if (player)
        {
            updatedisplay("Player X Turn")
        }
        else
        {
            updatedisplay("Player 0 Turn")
        }
        if(count==9)
        {
            updatedisplay("Draw game")
        }

        checkwinner()
    }

    private fun updatedisplay(t:String)
    {
        tex.text=t
        if(t.contains("wins"))
        {
            disabled()
        }
    }

    private fun checkwinner()
    {
        for(i in 0..2)
        {
            if((boardstatus[i][0] == boardstatus[i][1]) && (boardstatus[i][1]== boardstatus[i][2]))
            {
                if(boardstatus[i][0]==1)
                {
                    updatedisplay("Player X wins")
                    break
                }
                else if(boardstatus[i][0]==0)
                {
                    updatedisplay("Player 0 wins")

                    break
                }
            }
        }

        for(j in 0..2)
        {
            if((boardstatus[0][j] == boardstatus[1][j]) && (boardstatus[0][j]== boardstatus[2][j]))
            {
                if(boardstatus[0][j]==1)
                {
                    updatedisplay("Player X wins")
                    break
                }
                else if(boardstatus[0][j]==0)
                {
                    updatedisplay("Player 0 wins")

                    break
                }
            }
        }

        if((boardstatus[0][0]==boardstatus[1][1]) && (boardstatus[0][0]==boardstatus[2][2]))
        {
            if(boardstatus[0][0]==1)
            {
                updatedisplay("Player X wins")
            }
            else if(boardstatus[0][0]==0)
            {
                updatedisplay("Player 0 wins")
            }
        }

        if((boardstatus[0][2]==boardstatus[1][1]) && (boardstatus[1][1]==boardstatus[2][0]))
        {
            if(boardstatus[0][2]==1)
            {
                updatedisplay("Player X wins")
            }
            else if(boardstatus[0][2]==0)
            {
                updatedisplay("Player 0 wins")
            }
        }


    }
    private fun disabled()
    {
        for (i in board)
        {
            for(button in i)
            {
               button.isEnabled=false
            }
        }
    }
}
