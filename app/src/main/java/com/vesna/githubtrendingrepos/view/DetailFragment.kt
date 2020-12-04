package com.vesna.githubtrendingrepos.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.vesna.githubtrendingrepos.R
import com.vesna.githubtrendingrepos.databinding.DetailFragmentBinding
import com.vesna.githubtrendingrepos.viewmodel.DetailViewModel

class DetailFragment: Fragment() {

    companion object {
        fun newInstance() = DetailFragment()
    }

    private lateinit var viewModel: DetailViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {


        val binding:DetailFragmentBinding = DataBindingUtil.inflate(inflater,
           R.layout.detail_fragment, container, false)


          viewModel = ViewModelProviders.of(this).get(DetailViewModel::class.java)

          binding.lifecycleOwner = this
          binding.viewModel = viewModel

      return binding.root

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val bundle = this.arguments
        if (bundle != null) {
            val position = bundle.getInt("position")
            val reposList = (activity as MainActivity).getRepos()
            var repo = reposList[position]


            viewModel.setStringValues(repo)
        }


    }


}