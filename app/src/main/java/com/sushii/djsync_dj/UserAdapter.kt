package com.sushii.djsync_dj


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UserAdapter: RecyclerView.Adapter<UserAdapter.UserViewHolder>() {
    private var usersList = mutableListOf<User>()

    fun setUserList(usersList: List<User>) {
        this.usersList.clear()
        this.usersList.addAll(usersList)

    }
    inner class UserViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
    fun bind(user: User) {
        itemView.findViewById<TextView>(R.id.name).text = user.name
        itemView.findViewById<TextView>(R.id.song1).text = user.selectedSong0
        itemView.findViewById<TextView>(R.id.song2).text = user.selectedSong1
        itemView.findViewById<TextView>(R.id.song3).text = user.selectedSong2
        itemView.findViewById<TextView>(R.id.song4).text = user.selectedSong3
        itemView.findViewById<TextView>(R.id.song5).text = user.selectedSong4

    }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_items, parent, false)
        return UserViewHolder(view)
    }

    override fun getItemCount(): Int {
        return usersList.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = usersList[position]
        holder.bind(user)

    }
}