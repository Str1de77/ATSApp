package com.str1de.atsapp

import Fragments.RandomTripFragment
import Fragments.TimeFragment
import android.content.DialogInterface
import android.os.Bundle
import android.view.WindowManager
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.str1de.atsapp.databinding.RandomPosBinding


class RandomPositionActivity: AppCompatActivity() {

    val listOfFragments = listOf(RandomTripFragment.newInstance(), TimeFragment.newInstance())
    val listOfTitlesTab = listOf("Random Trip", "Time your trip")
    val randomTripFr = RandomTripFragment()
    val timeFr = TimeFragment()
    var activeFragment: Fragment = randomTripFr

    private lateinit var binding: RandomPosBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = RandomPosBinding.inflate(layoutInflater)
        setContentView(binding.root)



        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
/*
        supportFragmentManager.beginTransaction().apply {
            supportFragmentManager.beginTransaction().hide(timeFr).commit()
            true
        }

        binding.tabLayout2.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when(tab?.id) {
                    R.id.random_trip -> {
                        supportFragmentManager.beginTransaction().hide(timeFr).show(randomTripFr).commit()
                        activeFragment = randomTripFr
                        true
                    }
                    R.id.time_trip -> {
                        supportFragmentManager.beginTransaction().hide(randomTripFr).show(timeFr).commit()
                        activeFragment = timeFr
                        true
                    }
                    else -> false
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                return
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                return
            }
        })
*/
        var adapter = VP2Adapter(this, listOfFragments)
        binding.vp2.adapter = adapter
        TabLayoutMediator(binding.tabLayout2, binding.vp2) {
         tab, pos -> tab.text = listOfTitlesTab[pos]
        }.attach()

    }
    fun getRandomPosition() {
        var numberOfCellsEditText = findViewById<EditText>(R.id.inputCellsTextView)
        var numberOfListsEditText = findViewById<EditText>(R.id.inputListsTextView)
        var showNumberTrip = findViewById<TextView>(R.id.showNumberTrip)
        var showNumberCellTextView = findViewById<TextView>(R.id.showNumberCellTextView)
        var showNumberListTextView = findViewById<TextView>(R.id.showNumberListTextView)
        var numberOfCells = numberOfCellsEditText.text.toString()
        var numberOfLists = numberOfListsEditText.text.toString()
        if (numberOfCells.isEmpty() || numberOfLists.isEmpty()) {
            Toast.makeText(
                this,
                "You don't input a value. Please try again!",
                Toast.LENGTH_SHORT
            ).show()
        } else if (numberOfLists.toInt() > 5) {
            val dialog = AlertDialog.Builder(this)
            dialog.setTitle("Number of point in the last cell can't be less 5!")
            dialog.setMessage("Input a new value.")
            dialog.setCancelable(false)
            dialog.setPositiveButton("OK", DialogInterface.OnClickListener { dialog, which ->
                numberOfListsEditText.text.clear()
                dialog.cancel()
            }).show()
        } else {
            var randomTripNumber = (numberOfCells.toInt() * 5) - 5 + numberOfLists.toInt()
            var writeNumber = (1..randomTripNumber).random()
            showNumberTrip.text = writeNumber.toString()
            val valueOfCell = ((writeNumber / 5) + 1).toString()
            val valueOfList = (writeNumber % 5).toString()
            if (valueOfList.toInt() == 0) {
                showNumberCellTextView.text = (valueOfCell.toInt() - 1).toString()
                ((writeNumber / 5) + 1).toString()
                showNumberListTextView.text = (valueOfList.toInt() + 5).toString()
            } else {
                showNumberCellTextView.text = valueOfCell
                showNumberListTextView.text = valueOfList
            }
        }
    }
}