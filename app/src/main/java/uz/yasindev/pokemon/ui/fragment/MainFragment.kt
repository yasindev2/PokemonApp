package uz.yasindev.pokemon.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import uz.yasindev.pokemon.R
import uz.yasindev.pokemon.core.adapters.RvAdapter
import uz.yasindev.pokemon.core.data.LocalStorage
import uz.yasindev.pokemon.core.models.CardModel
import uz.yasindev.pokemon.databinding.FragmentMainBinding


class MainFragment : Fragment() {

    private val binding by lazy { FragmentMainBinding.inflate(layoutInflater) }
    private val rvAdapter = RvAdapter()
    private val data = ArrayList<CardModel>()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        setAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        listenActions()

    }

    private fun listenActions() {

        binding.autoCompleteTv.setOnClickListener {
            findNavController().navigate(
                MainFragmentDirections.actionMainFragmentToSearchFragment()
            )
        }

        rvAdapter.onItemClickListener = { cardModel ->
            findNavController().navigate(
                MainFragmentDirections.actionMainFragmentToInformationFragment(
                    cardModel
                )
            )
        }

    }

    private fun setAdapter() {
        rvAdapter.setData(LocalStorage.getAllData())
        binding.rv.layoutManager = GridLayoutManager(context, 2)

        // Drag in drop
        val itemTouchHelper =
            ItemTouchHelper(object :
                ItemTouchHelper.Callback() {
                override fun getMovementFlags(
                    recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder
                ): Int {

                    val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN


                    val swipeFlags = ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT

                    return makeMovementFlags(dragFlags, swipeFlags)

                }

                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    rvAdapter.itemMove(viewHolder.adapterPosition, target.adapterPosition)
                    return true
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    rvAdapter.itemDismiss(viewHolder.adapterPosition)
                }

            })

        itemTouchHelper.attachToRecyclerView(binding.rv)

        binding.rv.adapter = rvAdapter
    }



}