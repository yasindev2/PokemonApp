package uz.yasindev.pokemon.core.models

import java.io.Serializable

data class CardModel(
    val id: Int,
    val image: Int,
    val name: String,
    val hp: Int,
    val def: Int,
    val atk: Int,
    val spo: Int,
    val exp: Int,
    val weight: Int,
    val height: Int
) : Serializable