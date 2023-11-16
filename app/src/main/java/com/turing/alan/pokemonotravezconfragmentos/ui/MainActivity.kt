package com.turing.alan.pokemonotravezconfragmentos.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.turing.alan.pokemonotravezconfragmentos.R
import com.turing.alan.pokemonotravezconfragmentos.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}