package org.geepawhill.contentment.rhythm;

import javafx.beans.property.LongProperty;
import javafx.scene.media.MediaPlayer;

public interface Rhythm
{

	static final long MAX = Long.MAX_VALUE;

	LongProperty beatProperty();

	long beat();

	void seekHard(long ms);

	void update();

	void play();
	
	void pause();

	boolean isPlaying();

	boolean isAtEnd();

	MediaPlayer getMediaPlayer();
}