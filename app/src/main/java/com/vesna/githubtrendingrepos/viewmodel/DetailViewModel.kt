package com.vesna.githubtrendingrepos.viewmodel

import androidx.lifecycle.ViewModel
import com.vesna.githubtrendingrepos.data.TrendingProjectsListItem

class DetailViewModel: ViewModel() {

    var textValue= ""
    var textLink = ""
    var textLanguage = ""
    var textDescription = ""

    fun setStringValues(repo: TrendingProjectsListItem){
        textValue = repo.name
        textLink = repo.link
        textLanguage = repo.language
        textDescription = repo.description


    }

}