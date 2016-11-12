package ua.com.mediaportal.activities

import ua.com.mediaportal.R
import ua.com.mediaportal.templates.AbstractBuilderActivity
import ua.com.mediaportal.templates.ActivitySettings.ACTIVITY_TOOLBAR_DRAWER
import ua.com.mediaportal.templates.ActivitySettings.CONTENT_RECYCLER

class MainActivity() : AbstractBuilderActivity() {

    override fun getActivityType() = ACTIVITY_TOOLBAR_DRAWER

    override fun getContentType() = CONTENT_RECYCLER

    override fun isFABEnabled() = false

    override fun isBackButtonEnabled() = false

    override fun getActivityTitle() = R.string.app_name

    override fun initViews() {

    }
}
