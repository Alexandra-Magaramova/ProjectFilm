package com.magaramova.projectfilm.view.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.magaramova.projectfilm.R
import com.magaramova.projectfilm.databinding.FragmentDetailsBinding
import com.magaramova.projectfilm.domain.Film


class DetailsFragment : Fragment() {
    private lateinit var binding: FragmentDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        val view = binding.root
        inflater.inflate(R.layout.fragment_details, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Получаем наш фильм из переданного бандла
        val film = arguments?.get("film") as Film
        //Устанавливаем заголовок
        binding.detailsToolbar.title = film.title
        //Устанавливаем картинку
        binding.detailsPoster.setImageResource(film.poster)
        //Устанавливаем описание
        binding.detailsDescription.text = film.description

        //установка иконки избранного при запуске фрагмента
        binding.detailsFabFavorites.setImageResource(
            if (film.isInFavorites) R.drawable.round_favorite_24
            else R.drawable.round_favorite_border_24
        )

        //обработка кликов кнопки избранного
        binding.detailsFabFavorites.setOnClickListener {
            if (!film.isInFavorites) {
                binding.detailsFabFavorites.setImageResource(R.drawable.round_favorite_24)
                film.isInFavorites = true
               /* //оформляем посылку в избранное по нажатию на фаб во фрагменте деталей
                val bundle = Bundle()
                val fragment = FavoritesFragment()
                bundle.putParcelable("filmFavorite", film)

                //Прикрепляем нашу "посылку" к фрагменту
                fragment.arguments = bundle*/
            } else {
                binding.detailsFabFavorites.setImageResource(R.drawable.round_favorite_border_24)
                film.isInFavorites = false
            }
        }

        binding.detailsFabShare.setOnClickListener {
            //Создаем интент
            val intent = Intent()
            //Указываем action с которым он запускается
            intent.action = Intent.ACTION_SEND
            //Кладем данные о нашем фильме
            intent.putExtra(
                Intent.EXTRA_TEXT,
                "Check out this film: ${film.title} \n\n ${film.description}"
            )
            //Указываем MIME тип, чтобы система знала, какое приложения предложить
            intent.type = "text/plain"
            //Запускаем наше активити
            startActivity(Intent.createChooser(intent, "Share To:"))
        }
    }
}