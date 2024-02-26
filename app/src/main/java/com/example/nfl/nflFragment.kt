package com.example.nfl

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.nfl.databinding.FragmentNflBinding

class nflFragment: Fragment() {
    private lateinit var binding: FragmentNflBinding
    private lateinit var data: nflData
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        data = nflData(
            teamID = "100",
            teamName = "Indianapolis Colts",
            logoFile = "colts",
            conference = "",
            division = "AFC South",
            stadium = "Lucas Oil Stadium",
            latitude = 190.090,
            longitude = 200.453
        )



    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNflBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Assuming 'colts' is the resource name of the image in your drawable folder
        val resourceId = resources.getIdentifier(data.logoFile.toLowerCase(), "drawable", context?.packageName)
        binding.imageView.setImageResource(resourceId)
        binding.teamName.text = data.teamName
        binding.teamDivision.text = data.division
        binding.teamStadium.text = data.stadium
    }


}