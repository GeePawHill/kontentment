package org.geepawhill.contentment.test;

import java.util.concurrent.CountDownLatch;

import javax.swing.SwingUtilities;

import org.geepawhill.contentment.core.Context;

import javafx.embed.swing.JFXPanel;

public class ContentmentTest
{
	public final JavaFxRunner runner = new JavaFxRunner();
	private static boolean javaFxRunning;
	
	public ContentmentTest()
	{
		runIfNeeded();
	}
	
	public Context getContext()
	{
		return runner.context;
	}

	private void runIfNeeded()
	{
		try
		{
			runJavaFx();
		}
		catch (InterruptedException e)
		{
			throw new RuntimeException(e);
		}
	}

	protected void runJavaFx() throws InterruptedException
	{
		if (javaFxRunning) return;
		long timeMillis = System.currentTimeMillis();
		final CountDownLatch latch = new CountDownLatch(1);
		SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{
				forceJavaFxStart();
				latch.countDown();
			}

			private void forceJavaFxStart()
			{
				new JFXPanel();
			}
		});

		latch.await();
		javaFxRunning = true;
	}

}
