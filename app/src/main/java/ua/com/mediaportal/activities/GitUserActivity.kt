package ua.com.mediaportal.activities

import kotlinx.android.synthetic.main.content_gituser.*
import ua.com.mediaportal.App
import ua.com.mediaportal.R
import ua.com.mediaportal.modules.RxTest
import ua.com.mediaportal.modules.model.GithubAccount
import ua.com.mediaportal.templates.AbstractBaseActivity
import ua.com.mediaportal.templates.ActivitySettings.CONTENT_CUSTOM
import javax.inject.Inject


class GitUserActivity() : AbstractBaseActivity(), GitView {


    private lateinit var module: RxTest

    override fun getContentType() = CONTENT_CUSTOM

    override fun isDrawerEnabled() = false

    override fun isFABEnabled() = false

    override fun getActivityTitle() = R.string.app_name

    override fun isBackButtonEnabled() = false

    override fun isContentCustom() = true

    override fun getCustomContentLayout() = R.layout.content_gituser

    override fun initViews() {
    }

    override fun showUser(user: GithubAccount) {
        git_id.text=user.id.toString()
        git_user.text = user.user
    }
}