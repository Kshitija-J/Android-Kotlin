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
import androidx.navigation.fragment.navArgs
import com.example.nfl.databinding.FragmentTeamDetailBinding
import kotlinx.coroutines.launch
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

private const val TAG = "NFLTeamDetailsFragment"
private lateinit var nflTeam: NFLTeam
private lateinit var binding : FragmentTeamDetailBinding

class NFLTeamDetailFragment: Fragment(), OnMapReadyCallback {
   // private lateinit var binding: FragmentNflBinding
//    private var _binding: FragmentTeamDetailBinding? = null
    private val args : NFLTeamDetailFragmentArgs by navArgs()

    private lateinit var data: NFLTeam

    private lateinit var map: GoogleMap

    private var nflTeam: NFLTeam? = null // Change this line

    private val teamDetailVM : TeamDetailVM by viewModels {
        TeamDetailVMFactory(args.teamId)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTeamDetailBinding.inflate(inflater, container, false)
        binding.mapView.onCreate(savedInstanceState)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.mapView.getMapAsync(this)
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                teamDetailVM.team.collect { team ->
                    team?.let {
                        nflTeam = it // Set the nflTeam variable
                        updateUi(it)
                    }
                }
            }
        }
    }

    fun updateUi(nflTeam: NFLTeam){
        binding.apply {
            teamName.text = nflTeam.teamName
            teamDivision.text = nflTeam.division
            teamStadium.text = nflTeam.stadium
            Log.d(TAG, "The Resource location is ${nflTeam.logoFile}")
            val resourceName = nflTeam.logoFile.substringBeforeLast(".")
            val logoResourceId = resources.getIdentifier(resourceName.toLowerCase(), "drawable", context?.packageName)
            imageView.setImageResource(logoResourceId)
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        map.uiSettings.isZoomControlsEnabled = false
        map.uiSettings.isMyLocationButtonEnabled = false

        nflTeam?.let { team ->
            val nflTeamLocDisp = LatLng(team.latitude, team.longitude)
            map.addMarker(
                MarkerOptions()
                    .position(nflTeamLocDisp)
                    .visible(true)
            )
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(nflTeamLocDisp, 15f))
        }
        binding.mapView.onResume()
    }


}