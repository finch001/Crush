package com.livvy.crush.comm.util;

import android.content.Context;

import com.livvy.crush.R;

/**
 * Created by Finch on 2016/12/6 0006.
 */

public class ValueUtils
{
    public static String getLanguageString(Context c, String key)
    {
        switch (key)
        {
            case "follow_system":
                return c.getResources().getStringArray(R.array.languages)[0];

            case "english":
                return c.getResources().getStringArray(R.array.languages)[1];

            case "chinese":
                return c.getResources().getStringArray(R.array.languages)[2];

            case "italian":
                return c.getResources().getStringArray(R.array.languages)[3];

            default:
                return "";
        }

    }

    public static String getDownLoadScale(Context c, String key)
    {
        switch (key)
        {
            case "compact":
                return c.getResources().getStringArray(R.array.download_types)[0];
            case "raw":
                return c.getResources().getStringArray(R.array.download_types)[1];

            default:
                return "";
        }
    }
}
