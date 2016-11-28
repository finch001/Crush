package com.livvy.crush.util;

import android.database.Cursor;
import android.os.ParcelFileDescriptor;
import android.util.Log;

import java.io.Closeable;
import java.io.RandomAccessFile;
import java.net.Socket;

/**
 * Created by Finch on 2016/11/23 0023.
 */

public class IOUtils
{
	public IOUtils()
	{
	}

	public static void closeSilently(Closeable var0)
	{
		if (var0 != null)
		{
			try
			{
				var0.close();
			}
			catch (Throwable var2)
			{
				Log.w("IOUtils", "fail to close", var2);
			}

		}
	}

	public static void closeSilently(ParcelFileDescriptor var0)
	{
		if (var0 != null)
		{
			try
			{
				var0.close();
			}
			catch (Throwable var2)
			{
				Log.w("IOUtils", "fail to close", var2);
			}

		}
	}

	public static void closeSilently(Cursor var0)
	{
		try
		{
			if (var0 != null)
			{
				var0.close();
			}
		}
		catch (Throwable var2)
		{
			Log.w("IOUtils", "fail to close", var2);
		}

	}

	public static void closeRandomAccessFile(RandomAccessFile var0)
	{
		try
		{
			if (var0 != null)
			{
				var0.close();
			}
		}
		catch (Throwable var2)
		{
			Log.w("IOUtils", "fail to close", var2);
		}

	}

	public static void closeSocket(Socket var0)
	{
		try
		{
			if (var0 != null)
			{
				var0.close();
			}
		}
		catch (Throwable var2)
		{
			Log.w("IOUtils", "fail to close", var2);
		}
	}
}
