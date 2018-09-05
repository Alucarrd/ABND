package com.petina.android.newsapp;

import android.content.SharedPreferences;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
    }


    public static class NewsArticlePreferenceFragment extends PreferenceFragment implements Preference.OnPreferenceChangeListener {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.settings_main);

            //collect preference setting changes
            Preference section = findPreference(getString(R.string.settings_section_key));
            bindPreferenceSummaryToValue(section);

            Preference fromdate = findPreference(getString(R.string.settings_fromdate_key));
            bindPreferenceSummaryToValue(fromdate);

            Preference todate = findPreference(getString(R.string.settings_todate_key));
            bindPreferenceSummaryToValue(todate);

            Preference orderBy = findPreference(getString(R.string.settings_orderby_key));
            bindPreferenceSummaryToValue(orderBy);
        }

        //method that updates the preference
        @Override
        public boolean onPreferenceChange(Preference preference, Object value) {
            String stringValue = value.toString();
            //preference.setSummary(stringValue);
            if (preference instanceof ListPreference) {
                ListPreference listPreference = (ListPreference) preference;
                int prefIndex = listPreference.findIndexOfValue(stringValue);
                if (prefIndex >= 0) {
                    CharSequence[] labels = listPreference.getEntries();
                    preference.setSummary(labels[prefIndex]);

                }

            } else {
                preference.setSummary(stringValue);
            }
            return true;
        }

        //Upon preference update, this function will bind the change back to the preference fragment
        private void bindPreferenceSummaryToValue(Preference preference) {
            preference.setOnPreferenceChangeListener(this);
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(preference.getContext());
            String preferenceString = preferences.getString(preference.getKey(), "");
            onPreferenceChange(preference, preferenceString);
        }
    }
}
