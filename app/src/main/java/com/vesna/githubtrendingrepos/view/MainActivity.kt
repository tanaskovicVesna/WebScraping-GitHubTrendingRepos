package com.vesna.githubtrendingrepos.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.vesna.githubtrendingrepos.R
import com.vesna.githubtrendingrepos.adapter.RecyclerViewAdapter
import com.vesna.githubtrendingrepos.data.TrendingProjectsListItem

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ListFragment())
                .commitNow()
        }

    }
    val listener = object : RecyclerViewAdapter.OnItemClickListener {
        override fun onItemClick(position: Int) {

            val bundle = Bundle()
            bundle.putInt("position",position)

            val detailFragment = DetailFragment()
            detailFragment.arguments = bundle

            supportFragmentManager.beginTransaction()
                .replace(R.id.container, detailFragment)
                .commitNow()


        }
    }

    fun getRepos(): ArrayList<TrendingProjectsListItem> {
        val listFragment = ListFragment()

        return listFragment.getReposList()
    }

}