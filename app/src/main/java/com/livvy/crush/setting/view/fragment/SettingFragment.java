package com.livvy.crush.setting.view.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.widget.Toast;

import com.livvy.crush.R;
import com.livvy.crush.comm.util.ValueUtils;

/**
 * Created by Finch on 2016/12/6 0006.
 */

public class SettingFragment extends PreferenceFragment implements Preference.OnPreferenceChangeListener
{

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.perference);
        initView();
    }

    private void initView()
    {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getActivity());
        initBasic(sp);
        initDownLoad(sp);
        initAutoSave(sp);
    }

    private void initAutoSave(SharedPreferences sp)
    {
        CheckBoxPreference cbpf = (CheckBoxPreference)findPreference(getString(R.string.key_auto_save));
        cbpf.setOnPreferenceChangeListener(this);
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue)
    {
        if (preference.getKey().equals(getString(R.string.key_language)))
        {
            String language = ValueUtils.getLanguageString(getActivity(), (String)newValue);
            preference.setSummary(getString(R.string.now) + " : " + language);
            Toast.makeText(getActivity(), language + " changed", Toast.LENGTH_SHORT).show();
        }
        else if (preference.getKey().equals(getString(R.string.key_download_scale)))
        {
            String downLoadType = ValueUtils.getDownLoadScale(getActivity(), (String)newValue);
            preference.setSummary(getString(R.string.now) + ":" + downLoadType);
            Toast.makeText(getActivity(), downLoadType + " changed", Toast.LENGTH_SHORT).show();
        }
        else if (preference.getKey().equals(getString(R.string.key_auto_save)))
        {
            boolean isAutoSave = (boolean)newValue;
            Toast.makeText(getActivity(), isAutoSave + "  changed", Toast.LENGTH_SHORT).show();
        }

        return true;
    }

    private void initDownLoad(SharedPreferences sp)
    {
        ListPreference downLoadScale = (ListPreference)findPreference(getString(R.string.key_download_scale));
        String downLoadtype = sp.getString(getString(R.string.key_download_scale), "compress");
        String downTypeName = ValueUtils.getDownLoadScale(getActivity(), downLoadtype);
        downLoadScale.setSummary(getString(R.string.now) + " : " + downTypeName);
        downLoadScale.setOnPreferenceChangeListener(this);
    }

    private void initBasic(SharedPreferences sp)
    {
        // language.
        ListPreference language = (ListPreference)findPreference(getString(R.string.key_language));
        String languageValue = sp.getString(getString(R.string.key_language), "follow_system");
        String languageName = ValueUtils.getLanguageString(getActivity(), languageValue);
        language.setSummary(getString(R.string.now) + " : " + languageName);
        language.setOnPreferenceChangeListener(this);
    }
}
