package org.mozilla.fenix.settings

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import org.mozilla.fenix.R

class AndroidDeveloperVisibilityFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.android_developer_visibility_preferances, rootKey)

        setupPreferences()
    }

    private fun setupPreferences() {
    }
}
