package ua.com.mediaportal.activities.dagger

import dagger.Component
import ua.com.mediaportal.activities.GitUserActivity
import javax.inject.Inject
import javax.inject.Singleton

@Component(modules = arrayOf(RxGitModule::class))
@Singleton
interface GitComponent {
    fun injectGitActivity(activity: GitUserActivity)
}