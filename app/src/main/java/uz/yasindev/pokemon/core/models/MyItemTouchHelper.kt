package uz.yasindev.pokemon.core.models

interface MyItemTouchHelper {

    fun itemMove(fromPosition:Int,toPosition:Int)

    fun itemDismiss(position:Int)

}