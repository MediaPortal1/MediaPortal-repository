package ua.com.mediaportal.activities

import ua.com.mediaportal.modules.model.GithubAccount

/**
 * Created by Alex Poltavets on 16.11.2016.
 */
interface GitView {
    fun showUser(user: GithubAccount)
}