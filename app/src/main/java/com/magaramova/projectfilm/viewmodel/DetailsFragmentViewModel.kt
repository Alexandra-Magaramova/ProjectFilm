package com.magaramova.projectfilm.viewmodel

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.ViewModel
import java.net.URL
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class DetailsFragmentViewModel: ViewModel() {
    //Помечаем функцию как suspend, т.к. у нас будет логика показа Прогресс-бара,
    // а также тоста в конце, и нам необходима прерывающаяся функция, которая будет возвращать объект Bitmap.
    suspend fun loadWallpaper(url: String): Bitmap {
        //нам нужен объект Continuation, поэтому мы используем метод suspendCoroutine, чтобы получить к нему доступ
        return suspendCoroutine {
            //загружаем файл из Сети
            //В URL из представления передаем адрес картинки
            val url = URL(url)
            val bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream())
            //возвращем объект Bitmap
            it.resume(bitmap)
        }
    }
}