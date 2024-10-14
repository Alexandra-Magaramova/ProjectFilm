package com.magaramova.projectfilm.view.viewholders

import android.animation.ObjectAnimator
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.magaramova.projectfilm.R
import com.magaramova.projectfilm.domain.Film
import com.magaramova.projectfilm.view.customviews.RatingDonutView

//В конструктор класс передается layout, который мы создали(film_item.xml)
class FilmViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    //Привязываем View из layout к переменным
    private val title = itemView.findViewById<TextView>(R.id.title)
    private val poster = itemView.findViewById<ImageView>(R.id.poster)
    private val description = itemView.findViewById<TextView>(R.id.description)
    public val itemContainer = itemView.findViewById<ConstraintLayout>(R.id.item_container)

    //Вот здесь мы находим в верстке наш прогресс бар для рейтинга
    public val ratingDonut = itemView.findViewById<RatingDonutView>(R.id.rating_donut)

    //В этом методе кладем данные из film в наши view
    fun bind(film: Film) {
        //Устанавливаем заголовок
        title.text = film.title
        //Устанавливаем постер
        //Указываем контейнер, в которм будет "жить" наша картинка
        Glide.with(itemView)
            //Загружаем сам ресурс
            .load(film.poster)
            //Центруем изображение
            .centerCrop()
            //Указываем ImageView, куда будем загружать изображение
            .into(poster)
        //Устанавливаем описание
        description.text = film.description
        //Устанавливаем рэйтинг c анимацией
        ratingDonut.setProgress((film.rating * 10).toInt())
        val anim = ObjectAnimator.ofFloat(ratingDonut, View.ALPHA, 0F, 1F)
        anim.duration = 1500
        anim.start()


    }
}