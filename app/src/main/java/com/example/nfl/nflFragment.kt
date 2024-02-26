package com.example.nfl

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.nfl.databinding.FragmentNflBinding

class nflFragment: Fragment() {
   // private lateinit var binding: FragmentNflBinding
    private var _binding: FragmentNflBinding? = null
    private val binding
        get() = checkNotNull(_binding) {
            "Cannot access binding because it is null. Is the view visible?"
        }
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

        _binding =
            FragmentNflBinding.inflate(layoutInflater, container, false)
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
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}