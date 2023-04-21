package com.hasanalic.rickandmorty.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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

    private var isScrolling = false
    private var currentItems = 0
    private var totalItems = 0
    private var scrollOutItems = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentListBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(ListViewModel::class.java)

        // Adapters
        val manager = LinearLayoutManager(requireContext(),
            LinearLayoutManager.HORIZONTAL,false)

        binding.recyclerViewLocations.adapter = locationAdapter
        binding.recyclerViewLocations.layoutManager = manager
        binding.recyclerViewCharacters.adapter = characterAdapter
        binding.recyclerViewCharacters.layoutManager = LinearLayoutManager(requireContext(),
            LinearLayoutManager.VERTICAL,false)

        binding.recyclerViewLocations.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    println("newstate")
                    isScrolling = true
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                currentItems = manager.childCount
                totalItems = manager.itemCount
                scrollOutItems = manager.findFirstVisibleItemPosition()
                println("current items: $currentItems - total items: $totalItems - scroll items: $scrollOutItems")
                println("is Scrolling: $isScrolling")
                if (isScrolling && (currentItems + scrollOutItems == totalItems)) {
                    println("viewModel.getNextLocationPage(2)")
                    isScrolling = false
                    viewModel.getNextLocationPage(2)
                }
            }
        })

        locationAdapter.setOnItemClickListener {
            viewModel.getSingleLocation(it)
        }
        characterAdapter.setOnItemClickListener {
            val action = ListFragmentDirections.actionListFragmentToDetailFragment(it)
            Navigation.findNavController(view).navigate(action)
        }

        val customSharedPreferences = CustomSharedPreferences(requireContext())
        if (customSharedPreferences.getControl()!!) {
            viewModel.getSingleLocation(1)
            customSharedPreferences.setControl(false)
        }

        viewModel.getLocations()
        observer()
    }

    private fun observer() {
        viewModel.locations.observe(viewLifecycleOwner) {
            when(it.status) {
                Status.SUCCESS -> {
                    println("success")
                    binding.progressBarLocations.hide()
                    val locations = it.data?.locations ?: arrayListOf()
                    locationAdapter.locations = locations
                    locationAdapter.notifyDataSetChanged()
                }
                Status.ERROR -> {
                    println("error")
                    binding.progressBarLocations.hide()
                }
                Status.LOADING -> {
                    println("loading")
                    binding.progressBarLocations.show()
                }
            }
        }
        viewModel.singleLocation.observe(viewLifecycleOwner) {
            when(it.status) {
                Status.SUCCESS -> {
                    binding.progressBarList.hide()
                    locationAdapter.changeLocation(it.data ?: -1)
                }
                Status.ERROR -> {
                    binding.progressBarList.hide()
                    toast(requireContext(),it.message?:"error")
                }
                Status.LOADING -> {
                    binding.progressBarList.show()

                }
            }
        }
        viewModel.characters.observe(viewLifecycleOwner) {
            when(it.status) {
                Status.SUCCESS -> {
                    binding.progressBarList.hide()
                    val characters = it.data ?: arrayListOf()
                    characterAdapter.characters = characters
                }
                Status.ERROR -> {
                    binding.progressBarList.hide()
                    toast(requireContext(),it.message?:"error")
                    characterAdapter.characters = arrayListOf()
                }
                Status.LOADING -> {
                    binding.progressBarList.show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}