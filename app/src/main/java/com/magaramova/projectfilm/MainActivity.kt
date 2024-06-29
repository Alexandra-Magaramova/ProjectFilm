package com.magaramova.projectfilm

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.magaramova.projectfilm.databinding.ActivityMainBinding

class MainActivity : ComponentActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initMenuButtons()
    }

    fun initMenuButtons(){
        binding.buttonMenu.setOnClickListener{
            Toast.makeText(this, "Меню", Toast.LENGTH_SHORT).show()
        }
        binding.buttonFavourites.setOnClickListener{
            Toast.makeText(this, "Избранное", Toast.LENGTH_SHORT).show()
        }
        binding.buttonViewed.setOnClickListener{
            Toast.makeText(this, "Просмотренное", Toast.LENGTH_SHORT).show()
        }
        binding.buttonRecommendations.setOnClickListener{
            Toast.makeText(this, "Рекомендации", Toast.LENGTH_SHORT).show()
        }
        binding.buttonSettings.setOnClickListener{
            Toast.makeText(this, "Настройки", Toast.LENGTH_SHORT).show()
        }

    }


}