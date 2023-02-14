package org.mozilla.fenix.home

import mozilla.components.feature.top.sites.DefaultTopSitesStorage
import mozilla.components.feature.top.sites.TopSite
import mozilla.components.lib.state.Middleware
import mozilla.components.lib.state.MiddlewareContext
import org.mozilla.fenix.components.appstate.AppAction
import org.mozilla.fenix.components.appstate.AppState
import org.mozilla.fenix.settings.SupportUtils
import org.mozilla.fenix.utils.Settings

class AndroidDeveloperMiddleware(val settings: Settings, val topSitesStorage: DefaultTopSitesStorage) : Middleware<AppState, AppAction> {
    override fun invoke(
        context: MiddlewareContext<AppState, AppAction>,
        next: (AppAction) -> Unit,
        action: AppAction,
    ) {
        when (action) {
            is AppAction.TopSitesChange -> {
                if(settings.androidDeveloperTopSite) {
                    next(action.copy(action.topSites + TopSite.Default(
                        id = 2312,
                        title = "Rebeca",
                        url = "https://developer.android.com/",
                        createdAt = null,
                    )))
                } else {
                    next(action)
                }
            }
            is AppAction.VisibilityButtonChange -> {
                settings.androidDeveloperHomescreenButton = false
                context.dispatch(AppAction.VisibilityAndroidDeveloperTopSiteChange(false))
                next(action)
            }
            is AppAction.VisibilityAndroidDeveloperTopSiteChange -> {
                settings.androidDeveloperTopSite = false
                if(!action.isVisible) {
                    context.dispatch(AppAction.TopSitesChange(context.state.topSites.filter { it.url != SupportUtils.ANDROID_DEVELOPER_URL}))
                }

                next(action)
            }
            else -> {
                next(action)
            }
        }

    }


}
