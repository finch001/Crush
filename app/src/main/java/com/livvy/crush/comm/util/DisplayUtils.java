/**
 * Copyright © vyou Technologies Co., Ltd. 2013. All rights reserved
 */
package com.livvy.crush.comm.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

/**
 * @author finch
 * @date 2016年12月6日
 */
public class DisplayUtils
{

    /**
     * 让当前屏幕始终在最前方
     *
     * @param activity
     */
    public static void keepScreenOn(Activity activity, boolean enable)
    {
        // 设置屏幕在最前方，且全屏显示
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }


    @SuppressLint("NewApi")
    private static void hideSystemUI(View view)
    {
        view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }

    @SuppressLint("NewApi")
    private static void showSystemUI(View view)
    {
        view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
    }

    /**
     * 控制titlebar的显示隐藏
     *
     * @param activity
     * @param enable
     */
    public static void disableTitleBar(Activity activity, boolean enable)
    {
        if (enable)
        {
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
            activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        }
        else
        {
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
            activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
    }

    /**
     * 获取当前显示的分辨率的情况
     */
    @SuppressLint("NewApi")
    public static DisplayMetrics getDisplaySize(Context content)
    {
        DisplayMetrics dm = new DisplayMetrics();
        if (isJelly_Bean_Mr1OrLater())
        {
            WindowManager wm = (WindowManager)content.getSystemService(Context.WINDOW_SERVICE);
            Display display = wm.getDefaultDisplay();
            Point size = new Point();
            display.getRealSize(size);
            dm.widthPixels = size.x;
            dm.heightPixels = size.y;
        }
        else
        {
            dm = content.getResources().getDisplayMetrics();
        }

        return dm;
    }

    public static int dip2px(Context context, float dipValue)
    {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int)(dipValue * scale + 0.5f);
    }


    public static boolean isJelly_Bean_Mr1OrLater()
    {
        return Build.VERSION.SDK_INT >= 17;
    }

}
