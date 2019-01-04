package org.geepawhill.contentment.fragments;

import org.geepawhill.contentment.core.*;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.value.*;
import javafx.scene.text.Text;

public class ClockWatcher implements Fragment
{

	private SimpleLongProperty source;
	public final Text text;

	public ClockWatcher(SimpleLongProperty source)
	{
		this.source = source;
		text = new Text();
		source.addListener((p, o, n) -> changed(n));
		changed(source.getValue());
		}
	
	public void changed(Number value)
	{
		text.setText(value.toString());
	}

	@Override
	public void prepare(Context context)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public boolean interpolate(Context context, double fraction)
	{
		// TODO Auto-generated method stub
		return false;
	}

}
