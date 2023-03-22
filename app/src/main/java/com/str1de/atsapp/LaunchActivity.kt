package com.str1de.atsapp
import android.animation.ValueAnimator
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.str1de.atsapp.databinding.LaunchActivityBinding
import java.util.*

class LaunchActivity : AppCompatActivity() {

    lateinit var binding: LaunchActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LaunchActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.welcomeTextView.alpha = 0f
        val timer = Timer()
        val timer_2 = Timer()
        timer.schedule(object : TimerTask() {
            override fun run() {
                runOnUiThread {
                    animateTextView()

                }
            }
        }, 1000)

        timer_2.schedule(object : TimerTask() {
            override fun run() {
                startActivity(Intent(applicationContext, MainActivity::class.java))
            }
        },2000)

    }


    fun animateTextView() {
        val animate = ValueAnimator.ofFloat(0f, 1f)
        animate.duration = 1000
        animate.start()


        animate.addUpdateListener(object : ValueAnimator.AnimatorUpdateListener {
            override fun onAnimationUpdate(animation: ValueAnimator?) {
                val animator = animation?.animatedValue as Float
                binding.welcomeTextView.alpha = animator
            }
        })
    }
}