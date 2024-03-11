package com.example.nfl

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.nfl.databinding.ListItemTeamBinding

class nflHolder(
    private val binding: ListItemTeamBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(team : NFLTeam, onTeamClicked: (teamId: String) -> Unit) {
        binding.teamName.text = team.teamName
        binding.teamStadium.text = team.stadium
        binding.root.setOnClickListener {
            onTeamClicked(team.teamID)
        }
        val resourceName = team.logoFile.substringBeforeLast(".")
        val resourceId = itemView.context.resources.getIdentifier(resourceName, "drawable", itemView.context.packageName)
        binding.imageView.setImageResource(resourceId)
    }
}

class NFLListAdapter(private val teams: List<NFLTeam>, private val onTeamClicked: (teamId: String) -> Unit) : RecyclerView.Adapter<nflHolder>() {
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
        holder.bind(team, onTeamClicked)
    }

    override fun getItemCount() = teams.size
}