package com.turing.alan.pokemonotravezconfragmentos.ui.detail


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.turing.alan.pokemonotravezconfragmentos.R
import com.turing.alan.pokemonotravezconfragmentos.data.model.Pokemon
import com.turing.alan.pokemonotravezconfragmentos.databinding.FragmentPokemonDetailBinding

class PokemonDetailFragment : Fragment() {
    private val args: PokemonDetailFragmentArgs by navArgs()
    private val viewModel: PokemonDetailViewModel by viewModels()
    private lateinit var binding: FragmentPokemonDetailBinding

    val observer = Observer<Pokemon>{
        binding.topAppBar.setNavigationOnClickListener(){
            findNavController().popBackStack(R.id.pokemonListFragment, false)
        }
        binding.pokemonName.text = it.name
        binding.height.text = (it.height/10).toString() + " m"
        binding.weight.text = (it.weight/10).toString() + " kg"
        binding.pokemonImg.load(it.bigImg)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPokemonDetailBinding.inflate(inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetch(args.name)
        viewModel.pokemonUi.observe(viewLifecycleOwner,observer)
    }

}