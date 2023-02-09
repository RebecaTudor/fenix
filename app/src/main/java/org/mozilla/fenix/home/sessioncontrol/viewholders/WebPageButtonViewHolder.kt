package org.mozilla.fenix.home.sessioncontrol.viewholders

import android.view.View
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import mozilla.components.lib.state.ext.observeAsComposableState
import org.mozilla.fenix.R
import org.mozilla.fenix.components.components
import org.mozilla.fenix.compose.ComposeViewHolder
import org.mozilla.fenix.compose.button.TertiaryButton
import org.mozilla.fenix.home.sessioncontrol.AndroidDeveloperDocumentationInteractor
import org.mozilla.fenix.theme.FirefoxTheme
import org.mozilla.fenix.wallpapers.WallpaperState

/**
 * ViewHolder for a new button that will open a new web page for official android documentation.
 *
 * @param composeView parent in which this view will be shown.
 * @param viewLifecycleOwner parent [LifecycleOwner] which this view will react.
 * @param interactor [AndroidDeveloperDocumentationInteractor] callback for user interaction.
 *
 * @return [ComposeViewHolder] that can be added to a [RecyclerView].
 */
class WebPageButtonViewHolder(
    composeView: ComposeView,
    viewLifecycleOwner: LifecycleOwner,
    private val interactor: AndroidDeveloperDocumentationInteractor,
) : ComposeViewHolder(composeView, viewLifecycleOwner) {

    companion object {
        val LAYOUT_ID = View.generateViewId()
    }

    init {
        val horizontalPadding =
            composeView.resources.getDimensionPixelSize(R.dimen.home_item_horizontal_margin)
        composeView.setPadding(horizontalPadding, 0, horizontalPadding, 0)
    }

    @Composable
    override fun Content() {
        val wallpaperState = components.appStore
            .observeAsComposableState { state -> state.wallpaperState }.value
            ?: WallpaperState.default
        var buttonColor: Color = FirefoxTheme.colors.actionTertiary

        wallpaperState.composeRunIfWallpaperCardColorsAreAvailable { cardColorLight, cardColorDark ->
            buttonColor = if (isSystemInDarkTheme()) {
                cardColorDark
            } else {
                cardColorLight
            }
        }

        Column {
            Spacer(modifier = Modifier.height(18.dp))

            TertiaryButton(
                text = stringResource(R.string.browser_menu_android_developer),
                backgroundColor = buttonColor,
                onClick = interactor::onAndroidDeveloperClicked,
            )
        }
    }
}
