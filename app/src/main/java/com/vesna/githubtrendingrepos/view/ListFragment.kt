package com.vesna.githubtrendingrepos.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.vesna.githubtrendingrepos.R
import com.vesna.githubtrendingrepos.adapter.RecyclerViewAdapter
import com.vesna.githubtrendingrepos.data.TrendingProjectsListItem
import kotlinx.android.synthetic.main.list_fragment.*
import org.jsoup.Jsoup
import kotlin.concurrent.thread

class ListFragment: Fragment() {


    companion object {
        fun newInstance() = ListFragment()
        val reposList = ArrayList<TrendingProjectsListItem>()
        const val github_trending_url = "https://github.com/trending"

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.list_fragment, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        retrieveWebInfo()
    }
    private fun retrieveWebInfo(){
        thread{
            // network call, so run it in the background
            val doc =
                Jsoup.connect(github_trending_url)
                    .get()

            val movieItems  = doc.getElementsByClass("Box-row")



            for(movieItem in movieItems){


                val name = movieItem.getElementsByTag("a")[1].text()
                val description = movieItem.getElementsByTag("p").text()
                var language:String =  getString(R.string.no_language)
                if (!movieItem.getElementsByTag("span")[1].text().contains("Built by")){
                    language = movieItem.getElementsByTag("span")[1].text()
                }
                val link:String = github_trending_url+"/"+name.replace("\\s+".toRegex(),"")

                reposList.add(TrendingProjectsListItem(name,description,language,link))
            }

            // can't access UI elements from the background thread
            activity?.runOnUiThread{

                val recyclerViewAdapter = RecyclerViewAdapter(reposList, (activity as MainActivity).listener)
                val linearLayoutManager = LinearLayoutManager(activity)

                recyclerView.layoutManager = linearLayoutManager
                recyclerView.adapter = recyclerViewAdapter
            }
        }
    }

    fun getReposList(): ArrayList<TrendingProjectsListItem> {
        return reposList
    }



}