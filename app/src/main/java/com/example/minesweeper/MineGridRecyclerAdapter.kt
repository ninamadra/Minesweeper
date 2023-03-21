package com.example.minesweeper

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MineGridRecyclerAdapter(private val tiles: ArrayList<Tile>, var listener: OnTileClicked):
    RecyclerView.Adapter<MineGridRecyclerAdapter.MineTileViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MineTileViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.tile_view, parent, false)
        return MineTileViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MineTileViewHolder, position: Int) {
        holder.bind(tiles[position])
        holder.setIsRecyclable(false)
    }

    override fun getItemCount(): Int { return tiles.size }

    inner class MineTileViewHolder(itemView: View ): RecyclerView.ViewHolder(itemView) {
        private val textView: TextView = itemView.findViewById(R.id.tile_val)
        fun bind(tile: Tile) {
            itemView.setBackgroundColor(Color.DKGRAY)
            itemView.setOnClickListener(View.OnClickListener {
                listener.onTileClick(tile)
            })
        }
    }
}

