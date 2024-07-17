package uz.yasindev.pokemon.core.data

import uz.yasindev.pokemon.R
import uz.yasindev.pokemon.core.models.CardModel

object LocalStorage {

    private val data = ArrayList<CardModel>()

    fun getAllData():ArrayList<CardModel>{
        this.data.clear()
        data.add(CardModel(1, R.drawable.img, "Bulbasaur", 53, 15, 6, 17, 10, 130, 90))
        data.add(CardModel(2, R.drawable.img_1, "Charmeleon ", 7, 15, 6, 11, 13, 120, 60))
        data.add(CardModel(3, R.drawable.img_2, "Charizard ", 9, 12, 3, 7, 15, 140, 70))
        data.add(CardModel(4, R.drawable.img_3, "Squirtle", 10, 5, 17, 9, 6, 150, 80))
        data.add(CardModel(5, R.drawable.img_4, "Wartortle ", 13, 3, 17, 11, 10, 160, 30))
        data.add(CardModel(6, R.drawable.img_5, "Blastoise", 10, 8, 19, 7, 15, 170, 20))
        data.add(CardModel(6, R.drawable.img_6, "Caterpie", 10, 8, 19, 7, 15, 170, 20))
        data.add(CardModel(6, R.drawable.img_7, "Butterfree ", 10, 8, 19, 7, 15, 170, 20))
        return data
    }

}