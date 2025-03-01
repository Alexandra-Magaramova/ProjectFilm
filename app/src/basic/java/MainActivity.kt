package com.magaramova.projectfilm.view

import android.content.BroadcastReceiver
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import com.magaramova.projectfilm.App
import com.magaramova.projectfilm.R
import com.magaramova.projectfilm.databinding.ActivityMainBinding
import com.magaramova.projectfilm.data.Entity.Film
import com.magaramova.projectfilm.receivers.ConnectionChecker
import com.magaramova.projectfilm.view.fragments.DetailsFragment
import com.magaramova.projectfilm.view.fragments.FavoritesFragment
import com.magaramova.projectfilm.view.fragments.HomeFragment
import com.magaramova.projectfilm.view.fragments.SelectionsFragment
import com.magaramova.projectfilm.view.fragments.SettingsFragment
import com.magaramova.projectfilm.view.fragments.WatchLaterFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var receiver: BroadcastReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initNavigation()

        //Запускаем фрагмент при старте
        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment_placeholder, HomeFragment())
            .addToBackStack(null)
            .commit()

        receiver = ConnectionChecker()
        //фильтры для того, чтобы слушать низкий заряд батареи и подключение кабеля
        val filters = IntentFilter().apply {
            addAction(Intent.ACTION_POWER_CONNECTED)
            addAction(Intent.ACTION_BATTERY_LOW)
        }
        //Регистрируем ресивер
        registerReceiver(receiver, filters)

       moveToFilmFromNotification()

        if (!App.instance.isPromoShown) {
            //Получаем доступ к Remote Config
            val firebaseRemoteConfig = FirebaseRemoteConfig.getInstance()
            //Устанавливаем настройки
            val configSettings = FirebaseRemoteConfigSettings.Builder()
                .setMinimumFetchIntervalInSeconds(0)
                .build()
            firebaseRemoteConfig.setConfigSettingsAsync(configSettings)
            //Вызываем метод, который получит данные с сервера и вешаем слушатель
            firebaseRemoteConfig.fetch()
                .addOnCompleteListener {
                    //Если все получилось успешно
                    if (it.isSuccessful) {
                        //активируем последний полученный конфиг с сервера
                        firebaseRemoteConfig.activate()
                        //Получаем ссылку
                        val filmLink = firebaseRemoteConfig.getString("film_link")
                        //Если поле не пустое
                        if (filmLink.isNotBlank()) {
                            //Ставим флаг, что уже промо показали
                            App.instance.isPromoShown = true
                            //Включаем промо верстку
                            binding.promoViewGroup.apply {
                                //Делаем видимой
                                visibility = View.VISIBLE
                                //Анимируем появление
                                animate()
                                    .setDuration(1500)
                                    .alpha(1f)
                                    .start()
                                //Вызываем метод, который загрузит постер в ImageView
                                setLinkForPoster(filmLink)
                                //Кнопка, по нажатии на которую промо уберется (желательно сделать отдельную кнопку с крестиком)
                                watchButton.setOnClickListener {
                                    visibility = View.GONE
                                }
                            }
                        }
                    }
                }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)
    }


    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 1) {
            AlertDialog.Builder(this)
                .setTitle("Вы действительно хотите покинуть приложение?")
                .setIcon(R.drawable.round_error)
                .setPositiveButton("Да") { _, _ ->
                    finish()
                }
                .setNegativeButton("Нет") { _, _ ->

                }
                .show()
        } else super.onBackPressed()

    }

    fun moveToFilmFromNotification(){
        val fragmentToOpen = intent.getStringExtra("fragmentToOpen")
        val filmToOpen = intent.getParcelableExtra("filmToOpen") as Film?
        if(fragmentToOpen!=null){
            if (filmToOpen != null) {
                launchDetailsFragment(filmToOpen)
            }
        }
    }


    fun launchDetailsFragment(film: Film) {
        //Создаем "посылку"
        val bundle = Bundle()
        //Кладем наш фильм в "посылку"
        bundle.putParcelable("film", film)
        //Кладем фрагмент с деталями в перменную
        val fragment = DetailsFragment()
        //Прикрепляем нашу "посылку" к фрагменту
        fragment.arguments = bundle

        //Запускаем фрагмент
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_placeholder, fragment)
            .addToBackStack(null)
            .commit()
    }



        private fun initNavigation() {

            binding.topAppBar.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.settings -> {
                        Toast.makeText(this, "Настройки", Toast.LENGTH_SHORT).show()
                        true
                    }

                    else -> false
                }
            }

            binding.bottomNavigation.setOnNavigationItemSelectedListener {

                when (it.itemId) {
                    R.id.home -> {
                        val tag = "home"
                        val fragment = checkFragmentExistence(tag)
                        //В первом параметре, если фрагмент не найден и метод вернул null, то с помощью
                        //элвиса мы вызываем создание нвого фрагмента
                        changeFragment( fragment?: HomeFragment(), tag)
                        true
                    }
                    R.id.favorites -> {
                        Toast.makeText(this, "Доступно в Pro версии", Toast.LENGTH_SHORT).show()
                        true
                    }
                    R.id.watch_later -> {
                        val tag = "watch_later"
                        val fragment = checkFragmentExistence(tag)
                        changeFragment( fragment?: WatchLaterFragment(), tag)
                        true
                    }
                    R.id.selections -> {
                        Toast.makeText(this, "Доступно в Pro версии", Toast.LENGTH_SHORT).show()
                        true
                    }
                    R.id.settings -> {
                    val tag = "settings"
                    val fragment = checkFragmentExistence(tag)
                    changeFragment( fragment?: SettingsFragment(), tag)
                    true
                }
                    else -> false
                }
            }
        }

        //Ищем фрагмент по тэгу, если он есть то возвращаем его, если нет - то null
        private fun checkFragmentExistence(tag: String): Fragment? = supportFragmentManager.findFragmentByTag(tag)

        private fun changeFragment(fragment: Fragment, tag: String) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_placeholder, fragment, tag)
                .addToBackStack(null)
                .commit()
        }
    }