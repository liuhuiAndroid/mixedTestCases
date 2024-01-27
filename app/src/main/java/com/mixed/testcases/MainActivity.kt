package com.mixed.testcases

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.mixed.testcases.audio.AudioPlayer
import com.mixed.testcases.native.NativeLib
import com.mixed.testcases.ui.theme.MixedTestCasesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MixedTestCasesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    Greeting()
                }
            }
        }
    }
}

@Composable
fun Greeting() {
    Column {
        val nativeLib = NativeLib()
        Text("Native Library says: ${nativeLib.stringFromJNI()}")
        Button(onClick = { /*TODO*/ }) {
            Text(text = "上电系统指示灯测试")
        }
        Button(onClick = { /*TODO*/ }) {
            Text(text = "复位开关机测试")
        }
        Button(onClick = { /*TODO*/ }) {
            Text(text = "RS485测试")
        }
        Button(onClick = { /*TODO*/ }) {
            Text(text = "UART RS232测试")
        }
        Button(onClick = { /*TODO*/ }) {
            Text(text = "WIFI-BT 测试")
        }
        Button(onClick = { /*TODO*/ }) {
            Text(text = "显示屏测试")
        }
        Button(onClick = { /*TODO*/ }) {
            Text(text = "触摸屏测试")
        }
        Button(onClick = { /*TODO*/ }) {
            Text(text = "外部存储器测试")
        }
        Button(onClick = { /*TODO*/ }) {
            Text(text = "U盘测试")
        }
        Button(onClick = { /*TODO*/ }) {
            Text(text = "网络连接测试")
        }
        Button(onClick = { /*TODO*/ }) {
            Text(text = "无线网络测试")
        }
        AudioPlayerButton()
        BrightnessAdjustButton()
        Button(onClick = { /*TODO*/ }) {
            Text(text = "CAN接口通信测试")
        }
        Button(onClick = { /*TODO*/ }) {
            Text(text = "USB devices测试")
        }
        Button(onClick = { /*TODO*/ }) {
            Text(text = "RTC设备测试")
        }
        Button(onClick = { /*TODO*/ }) {
            Text(text = "GPIO测试")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MixedTestCasesTheme {
        Greeting()
    }
}

@Composable
fun BrightnessAdjustButton() {
    val context = LocalContext.current
    val activity = context as? Activity
    Button(onClick = {
        adjustBrightness(activity, 0.5f) // 设置屏幕亮度为 50%
    }) {
        Text("调整亮度")
    }
}

fun adjustBrightness(activity: Activity?, brightness: Float) {
    activity?.let {
        val layoutParams = it.window.attributes
        layoutParams.screenBrightness = brightness
        it.window.attributes = layoutParams
    }
}

@Composable
fun AudioPlayerButton() {
    val context = LocalContext.current
    val audioPlayer = remember { AudioPlayer(context) }
    // 当 Composable 从组合中移除时，释放资源
    DisposableEffect(Unit) {
        onDispose {
            audioPlayer.releaseMediaPlayer()
        }
    }
    Button(onClick = {
        audioPlayer.playAudioFromResource(R.raw.sample12s)
    }) {
        Text(text = "音频audio测试")
    }
}