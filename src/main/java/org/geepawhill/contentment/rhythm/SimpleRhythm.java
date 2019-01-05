package org.geepawhill.contentment.rhythm;

import java.time.*;

import javafx.animation.AnimationTimer;
import javafx.beans.property.*;
import javafx.scene.media.MediaPlayer;

public class SimpleRhythm implements Rhythm
{
	private SimpleLongProperty beatProperty;
	private boolean isPlaying;
	private LocalDateTime startedPlayingAt;
	private long startedPauseAt;
	
	private AnimationTimer timer;
	
	
	public SimpleRhythm()
	{
		beatProperty = new SimpleLongProperty(0L);
		isPlaying=false;
		startedPauseAt=0L;
		timer = new AnimationTimer() {

			@Override
			public void handle(long now)
			{
				update();
				
			} };
	}

	@Override
	public LongProperty beatProperty()
	{
		return beatProperty;
	}
	
	@Override
	public long beat()
	{
		update();
		return beatProperty.get();
	}

	@Override
	public void seekHard(long ms)
	{
		if(isPlaying) pause();
		beatProperty.set(ms);
		startedPauseAt = ms;
	}
	
	@Override
	public void update()
	{
		long newBeat = getPlayerTime();
		beatProperty.set(newBeat);
	}

	private long getPlayerTime()
	{
		if(isPlaying) return startedPauseAt+Duration.between( startedPlayingAt, LocalDateTime.now()).toMillis();
		return startedPauseAt;
	}

	@Override
	public void play()
	{
		if(isPlaying) throw new RuntimeException("Can't play when already playing.");
		startedPlayingAt = LocalDateTime.now();
		isPlaying=true;
		timer.start();
	}

	@Override
	public void pause()
	{
		if(!isPlaying) throw new RuntimeException("Can't pause when not playing.");
		timer.stop();
		startedPauseAt = beat();
		isPlaying=false;
	}

	@Override
	public boolean isPlaying()
	{
		return isPlaying;
	}

	@Override
	public boolean isAtEnd()
	{
		return true;
	}
	
	@Override
	public MediaPlayer getMediaPlayer()
	{
		return null;
	}

}
