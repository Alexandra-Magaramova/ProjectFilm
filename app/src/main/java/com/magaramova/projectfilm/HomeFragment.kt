package com.magaramova.projectfilm

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.magaramova.projectfilm.databinding.FragmentHomeBinding
import java.util.Locale

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var filmsAdapter: FilmListRecyclerAdapter
    //база данных
    val filmsDataBase = listOf(
        Film(
            "The lion king",
            R.drawable.lion,
            "Lion prince Simba and his father are targeted by his bitter uncle, who wants to ascend the throne himself.", 10.0f
        ),
        Film(
            "Dune",
            R.drawable.duna,
            "Paul Atreides unites with Chani and the Fremen while seeking revenge against the conspirators who destroyed his family.",
            7.3f
        ),
        Film(
            "The lord of the ring",
            R.drawable.lord,
            "A meek Hobbit from the Shire and eight companions set out on a journey to destroy the powerful One Ring and save Middle-earth from the Dark Lord Sauron.", 6.9f
        ),
        Film(
            "Interstellar",
            R.drawable.interstellar,
            "When Earth becomes uninhabitable in the future, a farmer and ex-NASA pilot, Joseph Cooper, is tasked to pilot a spacecraft, along with a team of researchers, to find a new planet for humans.", 5.7f
        ),
        Film(
            "Eternal Sunshine of the Spotless Mind",
            R.drawable.sunshine,
            "When their relationship turns sour, a couple undergoes a medical procedure to have each other erased from their memories forever.",8.4f
        ),
        Film(
            "A Beautiful Mind",
            R.drawable.mind,
            "A mathematical genius, John Nash made an astonishing discovery early in his career and stood on the brink of international acclaim. But the handsome and arrogant Nash soon found himself on a harrowing journey of self-discovery.",5.2f
        ),
        Film(
            "Hachiko: A Dog's Tale",
            R.drawable.hachiko,
            "Professor Wilson discovers a lost Akita puppy on his way home. Despite objections from his wife, Hachi endears himself to the family and grows to be Parker's loyal companion. As their bond grows deeper, a beautiful relationship unfolds.", 9.1f
        ),
        Film(
            "The Killer",
            R.drawable.killer,
            "An assassin tries to make amends in an effort to restore the sight of a beautiful young singer.", 2.4f
        ),
        Film(
            "Harry Potter and the Sorcerer's Stone",
            R.drawable.potter,
            "An orphaned boy enrolls in a school of wizardry, where he learns the truth about himself, his family and the terrible evil that haunts the magical world.",4.6f
        ),
        Film(
            "The Age of Adaline",
            R.drawable.adaline,
            "A young woman, born at the turn of the 20th century, is rendered ageless after an accident. After many solitary years, she meets a man who complicates the eternal life she has settled into.", 7.5f
        )
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        inflater.inflate(R.layout.fragment_home, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        AnimationHelper.performFragmentCircularRevealAnimation(binding.homeFragmentRoot, requireActivity(), 1)

        //Устанавливаем появление клавиатуры при нажатии на все поле поиска, а не только на иконку поиска
        binding.searchView.setOnClickListener {
            binding.searchView.isIconified = false
        }

        //Подключаем слушателя изменений введенного текста в поиска
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            //Этот метод отрабатывает при нажатии кнопки "поиск" на софт клавиатуре
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }
            //Этот метод отрабатывает на каждое изменения текста.
            // при каждом изменении текста ищем в БД схожие названия,
            // создаем из этого новый лист и кладем это все в адаптер
            override fun onQueryTextChange(newText: String): Boolean {
                //Если ввод пуст то вставляем в адаптер всю БД
                if (newText.isEmpty()) {
                    filmsAdapter.addItems(filmsDataBase)
                    return true
                }
                //Фильтруем список на поискк подходящих сочетаний
                val result = filmsDataBase.filter {
                    //Чтобы все работало правильно, нужно и запрос, и имя фильма приводить к нижнему регистру
                    it.title.toLowerCase(Locale.getDefault()).contains(newText.toLowerCase(Locale.getDefault()))
                }
                //Добавляем в адаптер
                filmsAdapter.addItems(result)
                return true
            }
        })

        initRecyckler()
        //Кладем нашу БД в RV
        filmsAdapter.addItems(filmsDataBase)
    }

    private fun initRecyckler() {
        binding.mainRecycler.apply {
            filmsAdapter =
                FilmListRecyclerAdapter(object : FilmListRecyclerAdapter.OnItemClickListener {
                    override fun click(film: Film) {
                        (requireActivity() as MainActivity).launchDetailsFragment(film)
                    }
                })
            //Присваиваем адаптер
            adapter = filmsAdapter
            //Присвоили layoutmanager
            layoutManager = LinearLayoutManager(requireContext())
            //Применяем декоратор для отступов
            val decorator = TopSpacingItemDecoration(8)
            addItemDecoration(decorator)
        }
    }

}