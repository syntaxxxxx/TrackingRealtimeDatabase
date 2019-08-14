package com.syntax.tutorialtrackingmvp.ui.user

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.syntax.tutorialtrackingmvp.R
import com.syntax.tutorialtrackingmvp.model.User
import kotlinx.android.synthetic.main.item_list_user.view.*

class ListUserAdapter(private var data: List<User>) :
    RecyclerView.Adapter<ListUserAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListUserAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list_user, parent, false)
        return ViewHolder(view)
    }

    @Suppress("USELESS_ELVIS")
    override fun getItemCount(): Int = data.size ?: 0

    override fun onBindViewHolder(holder: ListUserAdapter.ViewHolder, position: Int) {
        holder.bindItem(data.get(position))
    }

    class ViewHolder(view: View?) : RecyclerView.ViewHolder(view!!) {
        fun bindItem(get: User) {
            itemView.tv_name.text = get.name
            itemView.tv_email.text = get.email
        }
    }
}