package com.magaramova.projectfilm

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

//В конструктор класс передается layout, который мы создали(film_item.xml)
class FilmViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    //Привязываем View из layout к переменным
    private val title = itemView.findViewById<TextView>(R.id.title)
    private val poster = itemView.findViewById<ImageView>(R.id.poster)
    private val description = itemView.findViewById<TextView>(R.id.description)
    public val itemContainer = itemView.findViewById<CardView>(R.id.item_container)

    //В этом методе кладем данные из Film в наши View
    fun bind(film: Film) {
        title.text = film.title             //Устанавливаем заголовок
        poster.setImageResource(film.poster)         //Устанавливаем постер
        description.text = film.description         //Устанавливаем описание
    }
}