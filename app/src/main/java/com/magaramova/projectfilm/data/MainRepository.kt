package com.magaramova.projectfilm.data

import com.magaramova.projectfilm.R
import com.magaramova.projectfilm.domain.Film

class MainRepository {
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
}