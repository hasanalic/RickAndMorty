package com.hasanalic.rickandmorty.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.hasanalic.rickandmorty.adapter.CharacterAdapter
import com.hasanalic.rickandmorty.adapter.LocationAdapter
import com.hasanalic.rickandmorty.databinding.FragmentListBinding
import com.hasanalic.rickandmorty.util.*
import com.hasanalic.rickandmorty.viewmodel.ListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment: Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: ListViewModel

    private val locationAdapter by lazy {
        LocationAdapter()
    }

    private val characterAdapter by lazy {
        CharacterAdapter()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentListBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(ListViewModel::class.java)

        // Adapters
        binding.recyclerViewLocations.adapter = locationAdapter
        binding.recyclerViewLocations.layoutManager = LinearLayoutManager(requireContext(),
            LinearLayoutManager.HORIZONTAL,false)
        binding.recyclerViewCharacters.adapter = characterAdapter
        binding.recyclerViewCharacters.layoutManager = LinearLayoutManager(requireContext(),
            LinearLayoutManager.VERTICAL,false)

        locationAdapter.setOnItemClickListener {
            viewModel.getSingleLocation(it)
        }
        characterAdapter.setOnItemClickListener {
            val action = ListFragmentDirections.actionListFragmentToDetailFragment(it)
            Navigation.findNavController(view).navigate(action)
        }
        viewModel.getLocations()
        observer()
    }

    private fun observer() {
        viewModel.locations.observe(viewLifecycleOwner) {
            when(it.status) {
                Status.SUCCESS -> {
                    binding.progressBarList.hide()
                    binding.textViewError.hide()
                    val locations = it.data?.locations ?: arrayListOf()
                    locationAdapter.locations = locations
                }
                Status.ERROR -> {
                    binding.progressBarList.hide()
                    binding.textViewError.show()
                }
                Status.LOADING -> {
                    binding.progressBarList.show()
                    binding.textViewError.hide()
                }
            }
        }
        viewModel.singleLocation.observe(viewLifecycleOwner) {
            when(it.status) {
                Status.SUCCESS -> {
                    binding.progressBarList.hide()
                    binding.textViewError.hide()
                    locationAdapter.changeLocation(it.data ?: -1)
                }
                Status.ERROR -> {
                    binding.progressBarList.hide()
                    binding.textViewError.text = "${it.message}! Try again."
                    binding.textViewError.show()
                }
                Status.LOADING -> {
                    binding.progressBarList.show()
                    binding.textViewError.hide()
                }
            }
        }
        viewModel.characters.observe(viewLifecycleOwner) {
            when(it.status) {
                Status.SUCCESS -> {
                    binding.progressBarList.hide()
                    binding.textViewError.hide()
                    val characters = it.data ?: arrayListOf()
                    characterAdapter.characters = characters
                }
                Status.ERROR -> {
                    binding.progressBarList.hide()
                    binding.textViewError.text = "${it.message}! Try again."
                    binding.textViewError.show()
                }
                Status.LOADING -> {
                    binding.progressBarList.show()
                    binding.textViewError.hide()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}