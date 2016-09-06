/**
 * 版权声明：bee 版权所有 违者必究 2016
 * Copyright: Copyright (c) 2016 
 * 
 * @project_name: bee-common
 * @Title: UnsafeByteArrayInputStream.java
 * @Package com.alacoder.bee.common.io
 * @Description: 
 * @author jimmy.zhong
 * @date 2016年7月29日 下午4:14:29
 * @version V1.0
 */

package com.alacoder.bee.common.io;

/**
 * @ClassName: UnsafeByteArrayInputStream
 * @Description: 
 * @author jimmy.zhong
 * @date 2016年7月29日 下午4:14:29
 *
 */

import java.io.IOException;
import java.io.InputStream;

public class UnsafeByteArrayInputStream extends InputStream
{
	protected byte mData[];

	protected int mPosition, mLimit, mMark = 0;

	public UnsafeByteArrayInputStream(byte buf[])
	{
		this(buf, 0, buf.length);
	}

	public UnsafeByteArrayInputStream(byte buf[], int offset)
	{
		this(buf, offset, buf.length-offset);
    }

	public UnsafeByteArrayInputStream(byte buf[], int offset, int length)
	{
    	mData = buf;
    	mPosition = mMark = offset;
        mLimit = Math.min(offset+length, buf.length);
    }

	public int read()
	{
		return ( mPosition < mLimit ) ? ( mData[mPosition++] & 0xff ) : -1;
    }

	public int read(byte b[], int off, int len)
	{
		if( b == null )
		    throw new NullPointerException();
		if( off < 0 || len < 0 || len > b.length - off )
		    throw new IndexOutOfBoundsException();
		if( mPosition >= mLimit )
		    return -1;
		if( mPosition + len > mLimit )
		    len = mLimit - mPosition;
		if( len <= 0 )
		    return 0;
		System.arraycopy(mData, mPosition, b, off, len);
		mPosition += len;
		return len;
    }

	public long skip(long len)
    {
		if( mPosition + len > mLimit )
			len = mLimit - mPosition;
		if( len <= 0 )
			return 0;
		mPosition += len;
		return len;
    }

	public int available()
	{
		return mLimit - mPosition;
	}

	public boolean markSupported()
    {
    	return true;
	}

	public void mark(int readAheadLimit)
	{
		mMark = mPosition;
	}

	public void reset()
	{
		mPosition = mMark;
	}

	public void close() throws IOException
	{}

	public int position()
	{
		return mPosition;
	}

	public void position(int newPosition)
	{
		mPosition = newPosition;
	}
	
	public int size() {
		return mData == null ? 0 : mData.length;
	}
}