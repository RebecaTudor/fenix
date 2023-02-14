package org.mozilla.fenix.settings

import android.os.Bundle
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreference
import org.mozilla.fenix.R
import org.mozilla.fenix.components.appstate.AppAction
import org.mozilla.fenix.ext.components
import org.mozilla.fenix.ext.settings

class AndroidDeveloperVisibilityFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.android_developer_visibility_preferances, rootKey)

        requirePreference<SwitchPreference>(R.string.pref_key_show_android_developer_top_site).apply {
            isVisible = true
            isChecked = context.settings().androidDeveloperTopSite
           // onPreferenceChangeListener = SharedPreferenceUpdater()
            onPreferenceChangeListener = object : SharedPreferenceUpdater() {
                override fun onPreferenceChange(preference: Preference, newValue: Any?): Boolean {
                    if(newValue == false){
                        context.components.appStore.dispatch(AppAction.VisibilityAndroidDeveloperTopSiteChange(false))
                    }
                    return super.onPreferenceChange(preference, newValue)
                }
            }
        }

        requirePreference<SwitchPreference>(R.string.pref_key_show_android_developer_home_button).apply {
            isVisible = true
            isChecked = context.settings().androidDeveloperHomescreenButton
            onPreferenceChangeListener = SharedPreferenceUpdater()
        }
    }
}
