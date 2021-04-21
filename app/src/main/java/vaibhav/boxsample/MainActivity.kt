package vaibhav.boxsample

import android.app.Dialog
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import java.util.*

class MainActivity : AppCompatActivity() {
    private var scoreText: TextView? = null
    private var orangeBox: CardView? = null
    private var blueBox: CardView? = null
    private var greenBox: CardView? = null
    private var redBox: CardView? = null
    private val mInterval = 1000
    private var mHandler: Handler? = null
    private var randomeNumber  = 0
    private var scoreValue = 0
    private var userClickOn: Boolean? = null
    private var postDelayWIllWorkOnly: Boolean? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        redBox = findViewById(R.id.redBox)
        greenBox = findViewById(R.id.greenBox)
        blueBox = findViewById(R.id.blueBox)
        orangeBox = findViewById(R.id.orangeBox)

        scoreText = findViewById(R.id.scoreText)

        mHandler = Handler(Looper.getMainLooper())
        startGameCustomDialog()

        setTouchListener()

    }

    private fun setTouchListener() {
        redBox?.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {

                userClickOn = true

                if (randomeNumber == 1 && postDelayWIllWorkOnly == true) {
                    scoreValue = scoreValue + 1
                    scoreText?.text = "Score " + (scoreValue)
                } else {
                    // game over
                    stopRepeatingTask()
                    showCustomDialog("Score " + (scoreValue))
                }

                return v?.onTouchEvent(event) ?: true
            }
        })

        greenBox?.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {

                userClickOn = true

                if (randomeNumber == 2 && postDelayWIllWorkOnly == true) {
                    scoreValue = scoreValue + 1
                    scoreText?.text = "Score " + (scoreValue)
                } else {
                    // game over
                    stopRepeatingTask()
                    showCustomDialog("Score " + (scoreValue))
                }

                return v?.onTouchEvent(event) ?: true
            }
        })

        blueBox?.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {

                userClickOn = true

                if (randomeNumber == 3 && postDelayWIllWorkOnly == true) {
                    scoreValue = scoreValue + 1
                    scoreText?.text = "Score " + (scoreValue)
                } else {
                    // game over
                    stopRepeatingTask()
                    showCustomDialog("Score " + (scoreValue))
                }

                return v?.onTouchEvent(event) ?: true
            }
        })

        orangeBox?.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {

                userClickOn = true

                if (randomeNumber == 4 && postDelayWIllWorkOnly == true) {
                    scoreValue = scoreValue + 1
                    scoreText?.text = "Score " + (scoreValue)
                } else {
                    // game over
                    stopRepeatingTask()
                    showCustomDialog("Score " + (scoreValue))
                }

                return v?.onTouchEvent(event) ?: true
            }
        })
    }

    private fun showCustomDialog(score: String) {

        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.custom_layout)
        val body = dialog.findViewById(R.id.tvBody) as TextView
        body.text = score
        val yesBtn = dialog.findViewById(R.id.yesBtn) as Button
        val noBtn = dialog.findViewById(R.id.noBtn) as TextView
        yesBtn.setOnClickListener {
            startRepeatingTask()
            dialog.dismiss()
        }
        noBtn.setOnClickListener { dialog.dismiss() }
        dialog.show()

    }

    private fun startGameCustomDialog() {

        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.custom_start_gamelayout)
        val yesBtn = dialog.findViewById(R.id.yesBtn) as Button
        val noBtn = dialog.findViewById(R.id.noBtn) as TextView
        yesBtn.setOnClickListener {
            startRepeatingTask()
            dialog.dismiss()
        }
        noBtn.setOnClickListener { finish() }
        dialog.show()

    }

    private fun getRandomNumber(){
        val min = 1
        val max = 4
        val r = Random()
        randomeNumber =  r.nextInt(max - min + 1) + min
    }

    private fun setUIColor(){

        if(userClickOn == false){
            runOnUiThread{
                // game over
                stopRepeatingTask()
                showCustomDialog("Score " + (scoreValue))
            }
        }
        else{
            getRandomNumber()

            if(randomeNumber ==  1){
                redBox?.setCardBackgroundColor(Color.GRAY)
                greenBox?.setCardBackgroundColor(Color.GREEN)
                blueBox?.setCardBackgroundColor(Color.BLUE)
                orangeBox?.setCardBackgroundColor(Color.YELLOW)

            }
            else if(randomeNumber == 2){
                redBox?.setCardBackgroundColor(Color.RED)
                greenBox?.setCardBackgroundColor(Color.GRAY)
                blueBox?.setCardBackgroundColor(Color.BLUE)
                orangeBox?.setCardBackgroundColor(Color.YELLOW)
            }
            else if(randomeNumber == 3){
                redBox?.setCardBackgroundColor(Color.RED)
                greenBox?.setCardBackgroundColor(Color.GREEN)
                blueBox?.setCardBackgroundColor(Color.GRAY)
                orangeBox?.setCardBackgroundColor(Color.YELLOW)
            }
            else if(randomeNumber == 4){
                redBox?.setCardBackgroundColor(Color.RED)
                greenBox?.setCardBackgroundColor(Color.GREEN)
                blueBox?.setCardBackgroundColor(Color.BLUE)
                orangeBox?.setCardBackgroundColor(Color.GRAY)
            }
       }



    }

    override fun onDestroy() {
        super.onDestroy()
        stopRepeatingTask()
    }

    var mStatusChecker: Runnable = object : Runnable {
        override fun run() {
            try {
                setUIColor()
                userClickOn = false

            } finally {
                if(postDelayWIllWorkOnly == true)
                mHandler!!.postDelayed(this, mInterval.toLong())
            }
        }
    }

    fun startRepeatingTask() {
        scoreValue = 0
        userClickOn = true
        postDelayWIllWorkOnly = true
        runOnUiThread{
            scoreText?.text = "Score " + (scoreValue)
        }
        mStatusChecker.run()
    }

    fun stopRepeatingTask() {
        postDelayWIllWorkOnly = false
        mHandler!!.removeCallbacks(mStatusChecker)
    }
}