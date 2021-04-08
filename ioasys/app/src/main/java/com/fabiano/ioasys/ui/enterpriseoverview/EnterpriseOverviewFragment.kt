package com.fabiano.ioasys.ui.enterpriseoverview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.fabiano.ioasys.R
import com.fabiano.ioasys.databinding.FragmentEnterpriseOverviewBinding
import com.fabiano.ioasys.model.EnterpriseData
import com.fabiano.ioasys.utils.Constants
import com.fabiano.ioasys.utils.Helpers


class EnterpriseOverviewFragment : Fragment() {

    private lateinit var binding: FragmentEnterpriseOverviewBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEnterpriseOverviewBinding.inflate(inflater, container, false)
        binding.toolbar2.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
        binding.toolbar2.setNavigationOnClickListener {
            binding.toolbar2.findNavController().popBackStack()
        }
        displayInfo()
        return binding.root
    }

    private fun displayInfo() {
        val currentEnterprise =
            arguments?.getSerializable(Constants.CURRENT_ENTERPRISE_KEY) as EnterpriseData
        binding.toolbar2.title = currentEnterprise.enterprise_name
        binding.enterpriseDescription.text = currentEnterprise.description
        Glide.with(requireContext())
            .load(Helpers.createImageUrl(currentEnterprise.photo))
            .placeholder(R.drawable.ic_loading)
            .into(binding.imageView)
    }
}