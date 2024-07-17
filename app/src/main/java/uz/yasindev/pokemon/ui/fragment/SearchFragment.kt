package uz.yasindev.pokemon.ui.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import uz.yasindev.pokemon.core.adapters.SearchRvAdapter
import uz.yasindev.pokemon.core.data.LocalStorage
import uz.yasindev.pokemon.core.models.CardModel
import uz.yasindev.pokemon.databinding.FragmentSearchBinding


class SearchFragment : Fragment() {

    private val binding by lazy { FragmentSearchBinding.inflate(layoutInflater) }
    private val adapter = SearchRvAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setLinesToRvItems()
        listenSearch()
        loadAction()

    }

    private fun setLinesToRvItems() {

        val dividerItemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        binding.rvSearch.addItemDecoration(dividerItemDecoration)

    }

    private fun loadAction() {
        binding.backBtn.setOnClickListener {
            findNavController().popBackStack()
        }

        adapter.onItemClickListener = { cardmodel ->
            findNavController().navigate(
                SearchFragmentDirections.actionSearchFragmentToInformationFragment(
                    cardModel = cardmodel
                )
            )
        }
    }

    private fun listenSearch() {
        val dataAdapter = ArrayList<CardModel>()

        binding.autoCompleteTvSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                LocalStorage.getAllData().forEach { cardModel ->
                    if (cardModel.name.contains(s.toString(), ignoreCase = true)) {
                        dataAdapter.clear()
                        dataAdapter.add(cardModel)
                        Log.d("TAGll", "onTextChanged: $dataAdapter")
                        adapter.setData(dataAdapter)
                        binding.rvSearch.adapter = adapter
                        dataAdapter.clear()
                    } else {
                        dataAdapter.clear()
                        adapter.setData(dataAdapter)
                    }
                }

            }

            override fun afterTextChanged(s: Editable?) {
                LocalStorage.getAllData().forEach { cardModel ->
                    if (cardModel.name.contains(s.toString(), ignoreCase = true)) {
                        dataAdapter.add(cardModel)
                        adapter.setData(dataAdapter)
                        binding.rvSearch.adapter = adapter
                    }
                }
                if (s!!.isEmpty()) {
                    dataAdapter.clear()
                    adapter.setData(dataAdapter)
                }
            }

        })
    }


}