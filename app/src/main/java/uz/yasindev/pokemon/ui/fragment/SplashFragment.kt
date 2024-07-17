package uz.yasindev.pokemon.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import uz.yasindev.pokemon.R
import uz.yasindev.pokemon.databinding.FragmentSplashBinding


class SplashFragment : Fragment() {


    private val binding by lazy { FragmentSplashBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            delay(1500)
            findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToMainFragment())
            findNavController().popBackStack(R.id.splashFragment, true)
        }

    }


}