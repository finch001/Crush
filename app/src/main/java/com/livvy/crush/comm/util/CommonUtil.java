/**
 * Copyright © vyou Technologies Co., Ltd. 2013-2014. All rights reserved
 */
package com.livvy.crush.comm.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Environment;
import android.os.StatFs;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author loulis.Yu
 * @date 2014-4-29
 */
public class CommonUtil
{
	/** 压缩文件时的缓冲区 */
	public static final int BUFF_SIZE = 1024 * 512; // 512k Byte
	public static final String ZIP_SUFFIX = ".zip";

	/**
	 * 检测sdcard是否可用
	 *
	 * @return true为可用，否则为不可用
	 */
	public static boolean sdCardIsAvailable()
	{
		String status = Environment.getExternalStorageState();
		if (!status.equals(Environment.MEDIA_MOUNTED))
		{
			return false;
		}
		else
		{
			return true;
		}
	}

	/**
	 * Checks if there is enough Space on SDCard
	 *
	 * @param needSize Size to Check
	 * @return True if the Update will fit on SDCard, false if not enough space on SDCard Will also return false, if the
	 *         SDCard is not mounted as read/write
	 */
	public static boolean enoughSpaceOnSdCard(long needSize)
	{
		String status = Environment.getExternalStorageState();
		if (!status.equals(Environment.MEDIA_MOUNTED))
		{
			return false;
		}
		return (needSize < getRealSizeOnSdcard());
	}

	/**
	 * get the space is left over on sdcard
	 */
	public static long getRealSizeOnSdcard()
	{
		File path = new File(Environment.getExternalStorageDirectory().getAbsolutePath());
		StatFs stat = new StatFs(path.getPath());
		long blockSize = stat.getBlockSize();
		long availableBlocks = stat.getAvailableBlocks();
		return availableBlocks * blockSize;
	}

	/**
	 * Checks if there is enough Space on phone self
	 */
	public static boolean enoughSpaceOnPhone(long updateSize)
	{
		return getRealSizeOnPhone() > updateSize;
	}

	/**
	 * get the space is left over on phone self
	 */
	public static long getRealSizeOnPhone()
	{
		File path = Environment.getDataDirectory();
		StatFs stat = new StatFs(path.getPath());
		long blockSize = stat.getBlockSize();
		long availableBlocks = stat.getAvailableBlocks();
		return blockSize * availableBlocks;
	}

	/**
	 * 根据手机分辨率从dp转成px
	 *
	 * @param context
	 * @param dpValue
	 * @return
	 */
	public static int dip2px(Context context, float dpValue)
	{
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	/**
	 * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
	 */
	public static int px2dip(Context context, float pxValue)
	{
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f) - 15;
	}

	/**
	 * 批量压缩文件（夹）
	 *
	 * @param resFileList 要压缩的文件（夹）列表
	 * @param zipFile 生成的压缩文件
	 * @param zipUnFolderName 压缩包内的文件夹名
	 * @throws IOException 当压缩过程出错时抛出
	 */
	public static void zipFiles(List<File> resFileList, File zipFile, String zipUnFolderName)
	{
		ZipOutputStream zipout = null;
		try
		{
			zipout = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(zipFile), BUFF_SIZE));
			for (File resFile : resFileList)
			{
				zipFile(resFile, zipout, zipUnFolderName); // FIXME 压缩的目录，直接放到根目录中
			}

		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if (zipout != null)
				{
					zipout.close();
				}
			}
			catch (IOException e)
			{
				Log.e("CommonUtil.zipFiles", e.toString());
			}
		}
	}

	/**
	 * 压缩文件
	 *
	 * @param resFile 需要压缩的文件（夹）
	 * @param zipout 压缩的目的文件
	 * @param rootpath 压缩的文件路径
	 * @throws FileNotFoundException 找不到文件时抛出
	 * @throws IOException 当压缩过程出错时抛出
	 */
	private static void zipFile(File resFile, ZipOutputStream zipout, String rootpath)
	{
		rootpath = rootpath + (rootpath.trim().length() == 0 ? "" : File.separator) + resFile.getName();
		if (resFile.isDirectory())
		{
			File[] fileList = resFile.listFiles();
			if (fileList == null)
			{
				return;
			}
			for (File file : fileList)
			{
				zipFile(file, zipout, rootpath);
			}
		}
		else
		{
			byte buffer[] = new byte[BUFF_SIZE];
			BufferedInputStream in;
			try
			{
				in = new BufferedInputStream(new FileInputStream(resFile), BUFF_SIZE);
				zipout.putNextEntry(new ZipEntry(rootpath));
				int realLength;
				while ((realLength = in.read(buffer)) != -1)
				{
					zipout.write(buffer, 0, realLength);
				}
				// if (in != null)
				// {
				in.close();
				// }
				zipout.flush();
				zipout.closeEntry();
			}
			catch (FileNotFoundException e)
			{
				Log.e("CommonUtil.zipFile", e.toString());
			}
			catch (IOException e)
			{
				Log.e("CommonUtil.zipFile", e.toString());
			}
		}
	}

	/**
	 * 版本格式xx.xx.xx.xx
	 *
	 * @param ver1
	 * @param ver2
	 * @return ver1>ver2==1 ver1<ver2==-1 ver1=ver2==0
	 */
	public static int versionCompare(String ver1, String ver2)
	{

		String[] splitVers1 = ver1.split("\\.");
		String[] splitVers2 = ver2.split("\\.");
		try
		{
			for (int i = 0; i < splitVers1.length; i++)
			{
				if (isAllNum(splitVers1[i]) && isAllNum(splitVers2[i]))
				{
					int verValue1 = Integer.parseInt(splitVers1[i]);
					int verValue2 = Integer.parseInt(splitVers2[i]);

					if (verValue1 > verValue2)
					{
						return 1;
					}
					else if (verValue1 < verValue2)
					{
						return -1;
					}
				}
				else
				{
					int cmpValue = splitVers1[i].toLowerCase().compareTo(splitVers2[i].toLowerCase());
					if (0 != cmpValue)
					{
						return cmpValue > 0 ? 1 : -1;
					}
				}
			}
		}
		catch (Exception e)
		{
			Log.e("versionCompare", e.toString());
		}
		return 0; // 异常情况也反馈0
	}

	private static boolean isAllNum(String str)
	{
		if (!TextUtils.isEmpty(str))
		{
			try
			{
				Integer.valueOf(str);
				return true;
			}
			catch (Exception e)
			{

			}
		}

		return false;
	}

	/**
	 * 通过文件名截取时间：L_20150730190721_50.MP4
	 *
	 * @param filename
	 * @return
	 */
	public static long getVedioTimeByFileName(String filename)
	{
		// 截取时间段
		String[] spl = filename.split("_");
		// if (spl != null)
		// {
		for (int i = 0; i < spl.length; i++)
		{
			try
			{
				return Long.parseLong(spl[i]);
			}
			catch (Exception e)
			{
			}
		}
		// }
		return 0;
	}

	/**
	 * 从buf流中读取一个4个字节的long型
	 *
	 * @param b
	 * @param s
	 * @return
	 */
	public static long readUint32(byte[] b, int s)
	{
		long result = 0;
		result |= ((b[s + 0] << 24) & 0xFF000000);
		result |= ((b[s + 1] << 16) & 0xFF0000);
		result |= ((b[s + 2] << 8) & 0xFF00);
		result |= ((b[s + 3]) & 0xFF);
		return result;
	}

	/**
	 * 设备是否支持摄像机
	 *
	 * @param activity
	 * @return
	 */
	public static boolean hasCamera(Activity activity)
	{
		PackageManager packageManager = activity.getPackageManager();
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		List<ResolveInfo> list = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
		return list.size() > 0;
	}
}
