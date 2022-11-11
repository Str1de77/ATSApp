package com.str1de.atsapp

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.str1de.atsapp.databinding.RandomPosBinding
import kotlin.random.Random


class RandomPositionActivity: AppCompatActivity() {

    private lateinit var binding: RandomPosBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = RandomPosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        binding.randomButton.setOnClickListener {
            var numberOfCells = (binding.inputCellsTextView.text).toString()
            var numberOfLists = (binding.inputLastLists.text).toString()
            var randomTripNumber = (numberOfCells.toInt() * 5) - 5 + numberOfLists.toInt()
            var writeNumber = (1..randomTripNumber).random()
            binding.showNumberTrip.text = writeNumber.toString()
            binding.showNumberCellTextView.text = ((writeNumber/5) + 1).toString()
            binding.showNumberListTextView.text = (writeNumber % 5).toString()



        }

    }
}