package ua.com.mediaportal.activities.dagger

import dagger.Module
import dagger.Provides
import org.jetbrains.annotations.NotNull
import ua.com.mediaportal.activities.GitUserActivity
import ua.com.mediaportal.activities.GitView
import ua.com.mediaportal.modules.RxTest
import javax.inject.Singleton

@Module
class RxGitModule {

    @Singleton
    @NotNull
    @Provides
    fun getRxTest(view: GitUserActivity) = RxTest(view as GitView)

}