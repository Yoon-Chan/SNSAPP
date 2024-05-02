package com.example.data.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.content.pm.ServiceInfo
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.lifecycle.LifecycleService
import androidx.lifecycle.lifecycleScope
import com.example.data.model.BoardParam
import com.example.data.model.BoardParcel
import com.example.data.model.ContentParam
import com.example.data.retrofit.BoardService
import com.example.domain.model.ACTION_POSTED
import com.example.domain.usecase.file.UploadImageUseCase
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PostingService : LifecycleService() {
    
    @Inject
    lateinit var uploadImageUseCase: UploadImageUseCase
    
    @Inject
    lateinit var boardService: BoardService
    
    companion object {
        const val EXTRA_BOARD= "extra_board"
        const val CHANNEL_ID = "게시글 업로드"
        const val CHANNEL_NAME = "게시글 업로드 채널"
        const val FOREGROUND_NOTIFICATION_ID = 1000
    }
    
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        
        createNotificationChannel()
        startForeground()
        intent?.let {
            if(it.hasExtra(EXTRA_BOARD)) {
                val boardParcel = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    it.getParcelableExtra(EXTRA_BOARD, BoardParcel::class.java)
                } else {
                    it.getParcelableExtra(EXTRA_BOARD)
                }
                
                boardParcel?.run {
                    lifecycleScope.launch(Dispatchers.IO) {
                        uploadPostBoard(this@run)
                    }
                }
            }
        }
        
        return super.onStartCommand(intent, flags, startId)
        
    }
    
    
    private suspend fun uploadPostBoard(boardParcel: BoardParcel) {
        val uploadedImage = boardParcel.images.mapNotNull { image -> uploadImageUseCase(image).getOrNull() }
        
        val content = ContentParam(
            boardParcel.content,
            uploadedImage
        )
        
        val boardParam = BoardParam(title = boardParcel.title, content.toJson())
        val requestBody = boardParam.toRequestBody()
        
        boardService.postBoard(requestBody)
        sendBroadcast(
            Intent(
                ACTION_POSTED
            ).apply {
                setPackage(packageName)
            }
        )
        stopForeground(STOP_FOREGROUND_DETACH)
    }
    
    private fun startForeground() {
        val notification = NotificationCompat.Builder(this, CHANNEL_ID).build()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            startForeground(
                FOREGROUND_NOTIFICATION_ID,
                notification,
                ServiceInfo.FOREGROUND_SERVICE_TYPE_SHORT_SERVICE
            )
        }else {
            startForeground(
                FOREGROUND_NOTIFICATION_ID,
                notification,
            )
        }
        
        
    }
    
    private fun createNotificationChannel() {
        val channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT)
        
        channel.description = "백그라운드에서 글을 업로드 합니다."
        val notificationManager = getSystemService(NotificationManager::class.java)
        notificationManager.createNotificationChannel(channel)
        
        
    }
}
