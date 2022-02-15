package edu.us.ischool.zarkoc.awty

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val startButton : Button = findViewById<Button>(R.id.buttonStart)
        val inputMessage : EditText = findViewById<EditText>(R.id.inputMessage)
        val inputPhone : EditText = findViewById<EditText>(R.id.inputPhone)
        val inputInterval : EditText = findViewById<EditText>(R.id.inputInterval)
        var messageTimer : Timer = Timer()

        startButton.setOnClickListener {

            if (startButton.text == "Start!") {
                if (inputMessage.text.toString() != "" &&
                        inputPhone.text.toString() != "" &&
                        inputInterval.text.toString() != "" &&
                        inputInterval.text.toString().toInt() > 0) {
                    startButton.text = "Stop"

                    val text = inputPhone.text.toString() + ": " + inputMessage.text
                    val duration = Toast.LENGTH_SHORT
                    val interval : Long = inputInterval.text.toString().toLong() * 600000

                    messageTimer.schedule(object : TimerTask() {
                        override fun run() {
                            Log.i("TESTING", "SCHEDULE WORKING")
                            this@MainActivity.runOnUiThread(java.lang.Runnable {
                                Toast.makeText(applicationContext, text, duration).show()
                            })
                        }
                    }, 0, interval)
                } else {
                    when {
                        inputInterval.text.toString() == "" -> Toast.makeText(applicationContext, "Must input interval value", Toast.LENGTH_SHORT).show()
                        inputInterval.text.toString().toInt() <= 0 -> Toast.makeText(applicationContext, "Interval value must be > 0", Toast.LENGTH_SHORT).show()
                        inputMessage.text.toString() == "" -> Toast.makeText(applicationContext, "Must input message", Toast.LENGTH_SHORT).show()
                        inputPhone.text.toString() == "" -> Toast.makeText(applicationContext, "Must input phone number", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                startButton.text = "Start!"
                messageTimer.cancel()
                messageTimer = Timer()
            }
        }
    }
}