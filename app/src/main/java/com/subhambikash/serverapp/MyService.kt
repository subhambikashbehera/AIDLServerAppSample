package com.subhambikash.serverapp

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class MyService : Service() {
    companion object{
        const val TAG = "service1"
    }

    val myPid = android.os.Process.myPid()
    val threadName = Thread.currentThread().name
    val threadId = Thread.currentThread().id

    private val mBinder = object : IMyAidlInterface.Stub(){
        override fun sendMessage(message: String?) {
            Log.d(TAG, "getMessage: $message")
            Log.d(TAG, "service thread id: $threadId")
            Log.d(TAG, "service thread name: $threadName")
            Log.d(TAG, "processIdService: $myPid")
        }

    }

    override fun onBind(intent: Intent?): IBinder? {
        return mBinder
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "service thread id: ${Thread.currentThread().id}")
        Log.d(TAG, "service thread name: ${Thread.currentThread().name}")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: ")
    }
}