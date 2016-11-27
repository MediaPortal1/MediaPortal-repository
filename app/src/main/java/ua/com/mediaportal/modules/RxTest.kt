package ua.com.mediaportal.modules

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ua.com.mediaportal.activities.GitView

class RxTest (val view: GitView) {

    fun getGithubResponse(){
        RestObj.instance.getGitHubUser("mediaportal1")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({v-> view.showUser(v)})
    }
}