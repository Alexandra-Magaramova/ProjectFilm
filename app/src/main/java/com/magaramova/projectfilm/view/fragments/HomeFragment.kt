package com.magaramova.projectfilm.view.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.magaramova.projectfilm.view.adapters.FilmListRecyclerAdapter
import com.magaramova.projectfilm.R
import com.magaramova.projectfilm.view.viewholders.TopSpacingItemDecoration
import com.magaramova.projectfilm.databinding.FragmentHomeBinding
import com.magaramova.projectfilm.data.Entity.Film
import com.magaramova.projectfilm.utils.AnimationHelper
import com.magaramova.projectfilm.utils.AutoDisposable
import com.magaramova.projectfilm.utils.addTo
import com.magaramova.projectfilm.view.MainActivity
import com.magaramova.projectfilm.viewmodel.HomeFragmentViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers



import java.util.Locale

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var filmsAdapter: FilmListRecyclerAdapter
    private val viewModel by lazy {
        ViewModelProvider.NewInstanceFactory().create(HomeFragmentViewModel::class.java)
    }
    private val autoDisposable = AutoDisposable()
    private var filmsDataBase = listOf<Film>()

        //Используем backing field
        set(value) {
            //Если придет такое же значение, то мы выходим из метода
            if (field == value) return
            //Если пришло другое значение, то кладем его в переменную
            field = value
            //Обновляем RV адаптер
            filmsAdapter.addItems(field)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        autoDisposable.bindTo(lifecycle)
        retainInstance = true
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        inflater.inflate(R.layout.fragment_home, container, false)
        return binding.root
    }

    @SuppressLint("CheckResult")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        AnimationHelper.performFragmentCircularRevealAnimation(
            binding.homeFragmentRoot,
            requireActivity(),
            1
        )

        initSearchView()
        initPullToRefresh()
        //находим наш RV
        initRecyckler()
        viewModel.filmsListData

        //Кладем нашу БД в RV
        viewModel.filmsListData
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { list ->
                filmsAdapter.addItems(list)
                filmsDataBase = list
            }
            .addTo(autoDisposable)

        viewModel.showProgressBar
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                binding.progressBar.isVisible = it
            }
            .addTo(autoDisposable)

    }


    private fun initSearchView() {
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
                    it.title.toLowerCase(Locale.getDefault())
                        .contains(newText.toLowerCase(Locale.getDefault()))
                }
                //Добавляем в адаптер
                filmsAdapter.addItems(result)
                return true
            }
        })
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

    private fun initPullToRefresh() {
        //Вешаем слушатель, чтобы вызвался pull to refresh
        binding.pullToRefresh.setOnRefreshListener {
            //Чистим адаптер(items нужно будет сделать паблик или создать для этого публичный метод)
            filmsAdapter.items.clear()
            //Делаем новый запрос фильмов на сервер
            viewModel.getFilms()
            //Убираем крутящееся колечко
            binding.pullToRefresh.isRefreshing = false
        }
    }

}