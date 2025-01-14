package com.magaramova.projectfilm.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate

//Класс для приема сообщений
class ConnectionChecker : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        //Если интента нет, то выходим из метода
        if (intent == null) return
        //Проверяем, какой пришел action
        when (intent.action) {
            //Если пришел низкий заряд батарее
            Intent.ACTION_BATTERY_LOW -> {
                Toast.makeText(context, "Батарея разряжена", Toast.LENGTH_SHORT).show()
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
           // Intent.ACTION_BATTERY_LOW -> Intent.
                //Если пришло подключение к зарядке
            Intent.ACTION_POWER_CONNECTED -> {
                Toast.makeText(context, "Зарядка подключена", Toast.LENGTH_SHORT).show()
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
    }

}