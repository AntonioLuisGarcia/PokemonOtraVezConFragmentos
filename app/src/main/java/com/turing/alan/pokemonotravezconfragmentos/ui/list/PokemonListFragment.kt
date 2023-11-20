package com.turing.alan.pokemonotravezconfragmentos.ui.list


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.turing.alan.pokemonotravezconfragmentos.data.model.Pokemon
import com.turing.alan.pokemonotravezconfragmentos.databinding.FragmentPokemonListBinding
import com.turing.alan.pokemonotravezconfragmentos.ui.adapter.PokemonAdapter


class PokemonListFragment : Fragment() {
    private val viewModel:PokemonListViewModel by viewModels()
    private lateinit var binding: FragmentPokemonListBinding
    private lateinit var progressCircular: CircularProgressIndicator

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPokemonListBinding.inflate(inflater,
            container,
            false
        )
        progressCircular = binding.progressCircular
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressCircular.visibility = View.VISIBLE
        val adapter = PokemonAdapter()
        binding.pokemonListId.adapter = adapter

        viewModel.pokemonUi.observe(viewLifecycleOwner){
            pokemonLis -> adapter.submitList(pokemonLis)
            progressCircular.visibility = View.GONE
        }
    }
}