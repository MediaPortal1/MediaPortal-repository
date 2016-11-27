package ua.com.mediaportal.modules.model

import com.google.gson.annotations.SerializedName


data class GithubAccount(@SerializedName("login") var user: String, @SerializedName("id") var id: Int)