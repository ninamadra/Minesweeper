package com.example.minesweeper

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MineGridRecyclerAdapter( var tiles: ArrayList<Tile>, var listener: OnTileClicked):
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
    @SuppressLint("NotifyDataSetChanged")
    fun setTilesB(tiles: ArrayList<Tile>) {
        this.tiles = tiles
        notifyDataSetChanged()
    }

    inner class MineTileViewHolder(itemView: View ): RecyclerView.ViewHolder(itemView) {
        private val textView: TextView = itemView.findViewById(R.id.tile_val)
        @SuppressLint("SetTextI18n")
        fun bind(tile: Tile) {
            itemView.setBackgroundColor(Color.LTGRAY)
            textView.setTypeface(null, Typeface.BOLD)
            itemView.setOnClickListener {
                listener.onTileClick(tile)
            }

            when (tile.state) {
                State.BOMB -> {
                    textView.text = "\uD83D\uDCA3"//R.string.bomb
                }
                State.EMPTY -> {
                    textView.text = ""
                    itemView.setBackgroundColor(Color.GRAY)
                }
                else -> {
                    textView.text = tile.value.toString()
                    when (tile.value) {
                        1 -> textView.setTextColor(Color.CYAN)
                        2 -> textView.setTextColor(Color.GREEN)
                        3 -> textView.setTextColor(Color.RED)
                        4 -> textView.setTextColor(Color.BLUE)
                        5 -> textView.setTextColor(Color.MAGENTA)
                        6 -> textView.setTextColor(Color.BLACK)
                        7 -> textView.setTextColor(Color.YELLOW)
                        8 -> textView.setTextColor(Color.BLACK )

                    }
                }
            }
        }
    }
}

