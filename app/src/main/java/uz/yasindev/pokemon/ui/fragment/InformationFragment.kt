package uz.yasindev.pokemon.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import uz.yasindev.pokemon.databinding.FragmentInformationBinding


class InformationFragment : Fragment() {

    private val binding by lazy { FragmentInformationBinding.inflate(layoutInflater) }
    private val args: InformationFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setData()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setData()


    }

    private fun setData() {

        binding.imgViewInfo.setImageResource(args.cardModel.image)
        binding.tvInfo.text = args.cardModel.name
        binding.heightTv.text = args.cardModel.height.toString()
        binding.weightTv.text = args.cardModel.weight.toString()

        binding.progressView1.progress = args.cardModel.hp.toFloat()
        binding.progressView2.progress += args.cardModel.atk.toFloat()
        binding.progressView3.progress += args.cardModel.def.toFloat()
        binding.progressView4.progress += args.cardModel.spo.toFloat()
        binding.progressView5.progress += args.cardModel.exp.toFloat()

        binding.progressView1.labelText = "HP ${args.cardModel.hp}%"
        binding.progressView2.labelText = "ATK ${args.cardModel.atk * 10}%"
        binding.progressView3.labelText = "DEF ${args.cardModel.def * 10}%"
        binding.progressView4.labelText = "SPO ${args.cardModel.spo * 10}%"
        binding.progressView5.labelText = "EXP ${args.cardModel.exp * 10}%"

        binding.back.setOnClickListener {
            findNavController().popBackStack()
        }
    }


}