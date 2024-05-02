package com.example.presentation.main

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.domain.model.ACTION_POSTED
import com.example.presentation.main.board.BoardViewModel
import com.example.presentation.ui.theme.SnsProjectTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    
    
    private val receiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            intent?.let {
                if(it.action == ACTION_POSTED){
                    boardViewModel.load()
                }
            }
        }
    }
    
    private val boardViewModel : BoardViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SnsProjectTheme {
                MainNavHost(boardViewModel)
            }
        }
        
        ContextCompat.registerReceiver(
            this,
            receiver,
            IntentFilter(ACTION_POSTED),
            ContextCompat.RECEIVER_NOT_EXPORTED
        )
    }
    
}
