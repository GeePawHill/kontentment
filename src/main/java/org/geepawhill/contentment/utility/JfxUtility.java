package org.geepawhill.contentment.utility;

import java.io.*;

import javax.imageio.ImageIO;

import org.geepawhill.contentment.core.GroupSource;

import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.VPos;
import javafx.scene.*;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.*;
import javafx.scene.text.Text;

public class JfxUtility
{
	public static void anchor(Node node)
	{
		AnchorPane.setTopAnchor(node, 0d);
		AnchorPane.setBottomAnchor(node, 0d);
		AnchorPane.setLeftAnchor(node, 0d);
		AnchorPane.setRightAnchor(node, 0d);
	}

	public static AnchorPane makeAnchorFor(Node node)
	{
		AnchorPane anchor = new AnchorPane();
		anchor(node);
		anchor.getChildren().add(node);
		return anchor;
	}

	public static boolean forEachDescendant(Node node, NodeProcessor processor)
	{
		if (!processor.accept(node)) return false;
		if (node instanceof Parent)
		{
			Parent parent = (Parent) node;
			for (Node child : parent.getChildrenUnmodifiable())
			{
				if (!forEachDescendant(child, processor)) return false;
			}
		}
		return true;
	}
	
	public static void capture(Node node)
	{
		WritableImage image = node.snapshot(new SnapshotParameters(), null);

		// TODO: probably use a file chooser here
		File file = new File("output_0.png");
		int i=1;
		while(file.exists()) file = new File("output_"+ i++ +".png");
		try
		{
			ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
		}
		catch (IOException e)
		{
		}
	}
	
	public static void addIfNeeded(Group destination, Node node)
	{
		if(!destination.getChildren().contains(node)) destination.getChildren().add(node);
	}


	public static void addIfNeeded(GroupSource destination, Node node)
	{
		addIfNeeded(destination.group(),node);
	}
	
	static public void setTopAlignment(Node node)
	{
		setVerticalAlignment(node,VPos.TOP);
	}
	
	static public void setVerticalAlignment(Node node,VPos vpos)
	{
		if(node instanceof Text)
		{
			((Text)node).setTextOrigin(vpos);
		}
	}


	static public Paint color(int r, int g, int b)
	{
		return new Color((double) r / 255d, (double) g / 255d, (double) b / 255d, 1d);
	}

	public static void removeIfNeeded(Group group, Node node)
	{
		if(group.getChildren().contains(node)) group.getChildren().remove(node);
		
	}
}
