package com.vesna.githubtrendingrepos.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vesna.githubtrendingrepos.R
import com.vesna.githubtrendingrepos.data.TrendingProjectsListItem
import com.vesna.githubtrendingrepos.util.inflate
import kotlinx.android.synthetic.main.item_list.view.*

class RecyclerViewAdapter(private val reposList: List<TrendingProjectsListItem>, private var listener:OnItemClickListener) :
    RecyclerView.Adapter<RecyclerViewAdapter.SetHolder>() {


    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SetHolder {
        val inflatedView: View = parent.inflate(R.layout.item_list, false)
        return SetHolder(
            inflatedView
        )
    }

    override fun getItemCount(): Int {
        return reposList.size
    }

    override fun onBindViewHolder(holder: SetHolder, position: Int) {
        val itemCard = reposList[position]
        holder.bindSet(itemCard)
    }


    inner class SetHolder(v: View) : RecyclerView.ViewHolder(v),
        View.OnClickListener {
        private var view: View = v
        private var reposList: TrendingProjectsListItem? = null

        init {
            v.setOnClickListener(this)
        }

        override fun onClick(v: View) {

            var position:Int = adapterPosition

            if (position!=RecyclerView.NO_POSITION){
                listener?.onItemClick(position)
            }
        }


        fun bindSet(reposList: TrendingProjectsListItem) {
            this.reposList = reposList

            view.reposTitle.text = reposList.name
        }
    }

}
