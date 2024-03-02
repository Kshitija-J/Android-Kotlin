package com.example.nfl

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.nfl.databinding.ListItemTeamBinding

class nflHolder(
    private val binding: ListItemTeamBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(team: NFLTeam) {
        binding.teamName.text = team.teamName
        binding.teamStadium.text = team.stadium
        val resourceName = team.logoFile.substringBeforeLast(".")
        val resourceId = itemView.context.resources.getIdentifier(resourceName, "drawable", itemView.context.packageName)
        binding.imageView.setImageResource(resourceId)

//        binding.root.setOnClickListener {
//            Toast.makeText(
//                binding.root.context,
//                "${team.teamName} clicked!",
//                Toast.LENGTH_SHORT
//            ).show()
//        }
    }
}

class NflListAdapter(
    private val teams: List<NFLTeam>
) : RecyclerView.Adapter<nflHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): nflHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemTeamBinding.inflate(inflater, parent, false)
        return nflHolder(binding)
    }

    override fun onBindViewHolder(holder: nflHolder, position: Int) {
        val team = teams[position]
//        holder.apply {
//            binding.teamName.text = team.teamName
//            binding.teamStadium.text = team.stadium
//        }
        holder.bind(team)
    }

    override fun getItemCount() = teams.size
}