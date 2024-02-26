package com.example.nfl

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nfl.databinding.FragmentNflListBinding


private const val TAG = "NFLTeamListFragment"
    class NFLTeamListFragment : Fragment() {

        private var _binding: FragmentNflListBinding? = null
        private val binding
            get() = checkNotNull(_binding) {
                "Cannot access binding because it is null. Is the view visible?"
            }


        private val nFLTeamListViewModel: NFLTeamListViewModel by viewModels()
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            Log.d(TAG, "Total teams: ${nFLTeamListViewModel.teams.size}")
        }
        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            _binding = FragmentNflListBinding.inflate(inflater, container, false)
            binding.nflteamRecyclerView.layoutManager = LinearLayoutManager(context)
            val teams = nFLTeamListViewModel.teams
            val adapters = NflListAdapter(teams)
            binding.nflteamRecyclerView.adapter = adapters
            return binding.root
        }
        override fun onDestroyView() {
            super.onDestroyView()
            _binding = null
        }
    }

