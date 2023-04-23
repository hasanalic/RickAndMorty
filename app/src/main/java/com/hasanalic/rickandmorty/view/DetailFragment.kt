package com.hasanalic.rickandmorty.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.hasanalic.rickandmorty.databinding.FragmentDetailBinding
import com.hasanalic.rickandmorty.util.*
import com.hasanalic.rickandmorty.viewmodel.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment: Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: DetailViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentDetailBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(DetailViewModel::class.java)

        binding.imageViewBack.setOnClickListener {
            findNavController().popBackStack()
        }

        arguments?.let {
            val characterID = DetailFragmentArgs.fromBundle(it).selectedCharacter
            viewModel.getCharacter(characterID)
        }

        observer()
    }

    private fun observer() {
        viewModel.character.observe(viewLifecycleOwner) {
            when(it.status) {
                Status.SUCCESS -> {
                    binding.progressBarDetail.hide()
                    var episodes: String = ""
                    it.data?.episode?.map { episode ->
                        episodes = episodes + episode.substringAfter(Constants.SUB_EPISODE) + ", "
                    }
                    episodes = episodes.removeSuffix(", ")
                    binding.textViewTitle.text = it.data?.name?:"null"
                    binding.imageViewCharacter.downloadFromUrl(it.data?.image?:"",
                        placeHolderProgressBar(requireContext())
                    )
                    binding.textViewStatusText.text = it.data?.status?:"null"
                    binding.textViewSpecyText.text = it.data?.species?:"null"
                    binding.textViewGenderText.text = it.data?.gender?:"null"
                    binding.textViewOriginText.text = it.data?.origin?.name?:"null"
                    binding.textViewLocationText.text = it.data?.location?.name?:"null"
                    binding.textViewEpisodesText.text = episodes
                    binding.textViewCreatedAtText.text = it.data?.created?.dateFormat(Constants.DATE_FORMAT)
                }
                Status.ERROR -> {
                    binding.progressBarDetail.hide()
                }
                Status.LOADING -> {
                    binding.progressBarDetail.show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}