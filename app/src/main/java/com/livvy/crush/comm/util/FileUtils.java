package com.livvy.crush.comm.util;

import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Finch on 2016/11/23 0023.
 */

public class FileUtils
{
	/**
	 * 创建文件 如果不存在
	 * 
	 * @param fileName
	 * @return
	 */
	public static boolean createIfNoExists(String fileName)
	{
		if (TextUtils.isEmpty(fileName))
		{
			return false;
		}
		else
		{
			File file = new File(fileName);
			boolean isOk = true;
			if (!file.exists())
			{
				isOk = file.mkdirs();
			}
			return isOk;
		}
	}

	/**
	 * 显示文件的大小
	 * 
	 * @param var0
	 * @return
	 */
	public static String showFileSize(long var0)
	{
		String var2;
		if ((double) var0 < 1024.0D)
		{
			var2 = var0 + "B";
		}
		else if ((double) var0 < 1048576.0D)
		{
			var2 = String.format("%.1f", new Object[] {Double.valueOf((double) var0 / 1024.0D)}) + "KB";
		}
		else if ((double) var0 < 1.073741824E9D)
		{
			var2 = String.format("%.1f", new Object[] {Double.valueOf((double) var0 / 1048576.0D)}) + "MB";
		}
		else
		{
			var2 = String.format("%.1f", new Object[] {Double.valueOf((double) var0 / 1.073741824E9D)}) + "GB";
		}

		return var2;
	}

	/**
	 * 删除文件
	 * 
	 * @param var0
	 * @return
	 */
	public static boolean deleteFile(String var0)
	{
		if (var0 == null)
		{
			return true;
		}
		else
		{
			File var1 = new File(var0);
			return var1.isFile() && var1.exists() ? var1.delete() : true;
		}
	}

	/**
	 * 文件是否存在
	 *
	 * @param var0
	 * @return
	 */
	public static boolean isFileExist(String var0)
	{
		if (TextUtils.isEmpty(var0))
		{
			return false;
		}
		else
		{
			File var1 = new File(var0);
			return var1.exists();
		}
	}

	/**
	 * 保存图片到文件
	 * 
	 * @param var0
	 * @param var1
	 * @param var2
	 * @return
	 */
	public static boolean saveBitmapToFile(Bitmap var0, int var1, String var2)
	{
		return saveBitmapToFile(var0, var1, 100, var2);
	}

	public static boolean saveBitmapToFile(Bitmap var0, int var1, int var2, String var3)
	{
		if (var0 == null)
		{
			return false;
		}
		else
		{
			BufferedOutputStream var4 = null;

			try
			{
				File var5 = new File(var3);
				var5.deleteOnExit();
				var5.createNewFile();
				var4 = new BufferedOutputStream(new FileOutputStream(var5));
				if (var1 == 0)
				{
					var0.compress(Bitmap.CompressFormat.JPEG, var2, var4);
				}
				else
				{
					var0.compress(Bitmap.CompressFormat.PNG, var2, var4);
				}

				var4.flush();
				boolean var6 = true;
				return var6;
			}
			catch (IOException var10)
			{
				Log.e("FileUtils", var10.toString());
			}
			finally
			{
				IOUtils.closeSilently(var4);
			}

			return false;
		}
	}

	public static long getFileSize(File var0)
	{
		long var1 = 0L;
		if (var0.isDirectory())
		{
			File[] var3 = var0.listFiles();
			if (null != var3 && var3.length > 0)
			{
				for (int var4 = 0; var4 < var3.length; ++var4)
				{
					var1 += getFileSize(var3[var4]);
				}
			}
		}
		else
		{
			var1 += var0.length();
		}

		return var1;
	}
}
