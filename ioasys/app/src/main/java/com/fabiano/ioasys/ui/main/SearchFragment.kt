package com.fabiano.ioasys.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.fabiano.ioasys.R
import com.fabiano.ioasys.adapter.EnterpriseListAdapter
import com.fabiano.ioasys.data.remote.`interface`.ClickListener
import com.fabiano.ioasys.databinding.SearchFragmentBinding
import com.fabiano.ioasys.model.HeaderData
import com.fabiano.ioasys.utils.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private lateinit var viewModel: SearchViewModel
    private lateinit var binding: SearchFragmentBinding
    private lateinit var adapter: EnterpriseListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SearchFragmentBinding.inflate(inflater, container, false)
        if (!::viewModel.isInitialized) {
            viewModel = ViewModelProvider(this).get(SearchViewModel::class.java)
        }

        adapter = EnterpriseListAdapter(generateItemClickListener(), requireContext())
        binding.enterprisesRecyclerView.adapter = adapter

        searchBarClickListeners()
        enableObservers()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.initilalizeList(requireArguments()[Constants.HEADER_DATA_KEY] as HeaderData)
    }


    private fun generateItemClickListener(): ClickListener {
        return object : ClickListener {
            override fun onClick(pos: Int) {
                var position = pos
                if (pos == adapter.currentList.size) {
                    position -= 1
                }
                val item = adapter.currentList[position]
                val bundle = Bundle()
                bundle.putSerializable(Constants.CURRENT_ENTERPRISE_KEY, item)
                binding.root.findNavController()
                    .navigate(R.id.action_searchFragment_to_enterpriseOverviewFragment, bundle)
            }
        }
    }

    private fun enableObservers() {
        viewModel.isCaching.observe(viewLifecycleOwner) {
            if (it) {
                binding.loadingScreen.visibility = View.VISIBLE
            } else {
                binding.loadingScreen.visibility = View.GONE
            }
        }

        viewModel.visibleList.observe(viewLifecycleOwner) {
            if (::adapter.isInitialized) {
                adapter.submitList(it)
                if (binding.textView.visibility == View.VISIBLE) {
                    binding.textView.visibility = View.GONE
                }
            }
        }

        viewModel.seedingError.observe(viewLifecycleOwner) {
            if (it.isNotEmpty() && it.isNotBlank()) {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun searchBarClickListeners() {
        binding.searchBar.setOnSearchClickListener {
            binding.ioasysLogo.visibility = View.GONE
        }
        binding.searchBar.setOnQueryTextListener(generateQueryListener(viewModel) )
    }

    private fun generateQueryListener(viewModel: SearchViewModel) = object: SearchView.OnQueryTextListener{
        override fun onQueryTextSubmit(query: String?): Boolean {
            return true
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            viewModel.filterList(newText ?: "")
           return true
        }
    }
}