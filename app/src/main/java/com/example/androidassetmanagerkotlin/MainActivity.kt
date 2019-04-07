package com.example.androidassetmanagerkotlin

import android.graphics.drawable.Drawable
import android.media.MediaPlayer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var mediaPlayer = MediaPlayer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView.text = "Click to load text to read text file saved in assets folder"

        loadText.setOnClickListener(View.OnClickListener {
            textView.text = assets.open("mytext.txt").bufferedReader().readText()
        })
        loadImage.setOnClickListener(View.OnClickListener {
            val inputStream = assets.open("myimage.jpg")
            val drawable = Drawable.createFromStream(inputStream, null)
            imageView.setImageDrawable(drawable)
        })
        playAudio.setOnClickListener(View.OnClickListener {
            if (mediaPlayer.isPlaying) {
                mediaPlayer.stop()
                mediaPlayer.release()
                mediaPlayer = MediaPlayer()
                playAudio.text = "Play Audio"
            } else {
                val assetFileDescriptor = assets.openFd("myaudio.mp3")
                mediaPlayer.setDataSource(
                    assetFileDescriptor.fileDescriptor,
                    assetFileDescriptor.startOffset, assetFileDescriptor.length
                )
                mediaPlayer.prepare()
                mediaPlayer.start()
                playAudio.text = "Stop Audio"
            }
        })
    }
}
