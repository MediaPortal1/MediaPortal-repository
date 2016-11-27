package ua.com.mediaportal.modules

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import ua.com.mediaportal.modules.model.GithubAccount


interface GithubRequest {

    @GET("users/{user}")
    fun getGitHubUser(@Path("user") user: String): Observable<GithubAccount>

}