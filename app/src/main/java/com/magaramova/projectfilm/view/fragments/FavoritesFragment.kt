package com.magaramova.projectfilm.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.magaramova.projectfilm.view.adapters.FilmListRecyclerAdapter
import com.magaramova.projectfilm.R
import com.magaramova.projectfilm.view.viewholders.TopSpacingItemDecoration
import com.magaramova.projectfilm.databinding.FragmentFavoritesBinding
import com.magaramova.projectfilm.domain.Film
import com.magaramova.projectfilm.utils.AnimationHelper
import com.magaramova.projectfilm.view.MainActivity

class FavoritesFragment : Fragment() {
    private lateinit var binding: FragmentFavoritesBinding
    private lateinit var filmsAdapter: FilmListRecyclerAdapter
    //var favoritesList = ArrayList<Film>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        inflater.inflate(R.layout.fragment_details, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Получаем список при транзакции фрагмента
        val favoritesList: List<Film> = emptyList()

       AnimationHelper.performFragmentCircularRevealAnimation(
            binding.favoritesFragmentRoot,
            requireActivity(),
            2
        )


        binding.favoritesRecycler
            .apply {
                filmsAdapter =
                    FilmListRecyclerAdapter(object : FilmListRecyclerAdapter.OnItemClickListener {
                        override fun click(film: Film) {
                            (requireActivity() as MainActivity).launchDetailsFragment(film)
                        }
                    })
                //Присваиваем адаптер
                adapter = filmsAdapter
                //Присваиваем layoutmanager
                layoutManager = LinearLayoutManager(requireContext())
                //Применяем декоратор для отступов
                val decorator = TopSpacingItemDecoration(8)
                addItemDecoration(decorator)
            }
        //Кладем нашу БД в RV
        /*val bundleFilm = arguments?.get("filmFavorite")

        if (bundleFilm != null) {
            favoritesList.add(bundleFilm as Film)
        }*/

        filmsAdapter.addItems(favoritesList)
    }
}