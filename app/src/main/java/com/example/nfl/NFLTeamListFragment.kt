package com.example.nfl

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nfl.databinding.FragmentNflListBinding
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


private const val TAG = "NFLTeamListFragment"
    class NFLTeamListFragment : Fragment() {

        private var _binding: FragmentNflListBinding? = null
        private var job: Job? = null
        private val binding
            get() = checkNotNull(_binding) {
                "Cannot access binding because it is null. Is the view visible?"
            }


        private val nFLTeamListViewModel: NFLTeamListViewModel by viewModels()

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
//            Log.d(TAG, "Total teams: ${nFLTeamListViewModel.teams.size}")
        }
        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            _binding = FragmentNflListBinding.inflate(inflater, container, false)
            binding.teamRecyclerView.layoutManager = LinearLayoutManager(context)
            return binding.root
        }
        override fun onDestroyView() {
            super.onDestroyView()
            _binding = null
        }
        override fun onStop() {
            super.onStop()
            job?.cancel()
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)

            viewLifecycleOwner.lifecycleScope.launch {
                viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                    nFLTeamListViewModel.nflFlow.collect { nflteams ->
                        binding.teamRecyclerView.adapter = NFLListAdapter(nflteams) { teamId ->
                            findNavController().navigate(
                                NFLTeamListFragmentDirections.showTeamDetail((teamId))
                            )
                        }

                    }
                }
            }

        }
    }

