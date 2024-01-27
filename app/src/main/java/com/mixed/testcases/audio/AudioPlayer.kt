package com.mixed.testcases.audio

import android.content.Context
import android.media.MediaPlayer
import android.net.Uri

class AudioPlayer(private val context: Context) {
    private var mediaPlayer: MediaPlayer? = null

    // 从资源文件中播放音频
    fun playAudioFromResource(audioResourceId: Int) {
        // 释放任何之前的 MediaPlayer 实例
        releaseMediaPlayer()

        // 创建并配置 MediaPlayer
        mediaPlayer = MediaPlayer.create(context, audioResourceId).apply {
            // 设置音频播放完毕后的监听器
            setOnCompletionListener {
                // 音频播放完毕后，释放 MediaPlayer 资源
                releaseMediaPlayer()
            }
            start() // 开始播放音频
        }
    }

    // 从 URI 播放音频
    fun playAudioFromUri(audioUri: Uri) {
        // 释放之前的 MediaPlayer 实例
        releaseMediaPlayer()

        // 创建并配置 MediaPlayer
        mediaPlayer = MediaPlayer().apply {
            setDataSource(context, audioUri) // 设置音频数据源
            prepare() // 准备 MediaPlayer
            setOnCompletionListener {
                // 音频播放完毕后，释放 MediaPlayer 资源
                releaseMediaPlayer()
            }
            start() // 开始播放音频
        }
    }

    // 暂停音频播放
    fun pauseAudio() {
        mediaPlayer?.pause()
    }

    // 停止音频播放
    fun stopAudio() {
        mediaPlayer?.stop()
    }

    // 从暂停状态恢复音频播放
    fun resumeAudio() {
        mediaPlayer?.start()
    }

    // 释放 MediaPlayer 资源
    fun releaseMediaPlayer() {
        mediaPlayer?.release()
        mediaPlayer = null
    }
}
