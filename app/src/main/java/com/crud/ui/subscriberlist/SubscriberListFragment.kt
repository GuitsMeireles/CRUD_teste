package com.crud.ui.subscriberlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.crud.R
import com.crud.data.db.AppDataBase
import com.crud.data.db.dao.SubscriberDAO
import com.crud.databinding.FragmentSubscriberListBinding
import com.crud.repository.DataBaseDataSource
import com.crud.repository.SubscriberRepository

class SubscriberListFragment : Fragment(R.layout.fragment_subscriber_list) {

    private val viewModel: SubscriberListViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val subscriberDAO: SubscriberDAO =
                    AppDataBase.getInstance(requireContext()).subscriberDAO

                val repository: SubscriberRepository = DataBaseDataSource(subscriberDAO)
                return SubscriberListViewModel(repository) as T
            }
        }
    }
    private lateinit var binding: FragmentSubscriberListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSubscriberListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeViewModelEvents()
        configureViewListeners()

    }

    private fun observeViewModelEvents() {
        viewModel.allSubscriberEvent.observe(viewLifecycleOwner) { allSubscriber ->

            val subscriberListAdapter = SubscriberListAdapter(allSubscriber)

            binding.recyclerSubs.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(requireContext())
                adapter = subscriberListAdapter
            }
        }
    }

    private fun configureViewListeners() {
        binding.addSubscriber.setOnClickListener {
            findNavController().navigate(R.id.subscriberFragment)
        }
    }
}
