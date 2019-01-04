package org.geepawhill.contentment.step;

import java.util.ArrayList;

import org.geepawhill.contentment.core.*;

interface SlowPlayer
{

	void play(Context context, OnFinished onFinished, ArrayList<Gesture> gestures);

}