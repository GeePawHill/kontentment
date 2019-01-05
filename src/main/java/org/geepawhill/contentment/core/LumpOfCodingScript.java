package org.geepawhill.contentment.core;

import static org.geepawhill.contentment.utility.JfxUtility.color;

import java.io.File;
import java.util.*;

import org.geepawhill.contentment.actor.Appearance;
import org.geepawhill.contentment.actors.*;
import org.geepawhill.contentment.flow.*;
import org.geepawhill.contentment.format.Format;
import org.geepawhill.contentment.geometry.*;
import org.geepawhill.contentment.grid.Grid;
import org.geepawhill.contentment.player.Script;
import org.geepawhill.contentment.position.*;
import org.geepawhill.contentment.rhythm.MediaRhythm;
import org.geepawhill.contentment.step.ScriptBuilder;
import org.geepawhill.contentment.style.*;
import org.geepawhill.contentment.utility.JfxUtility;

import javafx.scene.paint.Paint;
import javafx.scene.text.*;

public class LumpOfCodingScript extends ScriptBuilder<LumpOfCodingScript>
{

	private static final int INSET = 3;
	private static final double XMARGIN = 20;
	private static final double YMARGIN = 20;

	private FormatTable formats;

	private Format secondaryJumbo;
	private Format primaryJumbo;
	private Format secondaryNormal;
	private Format primaryNormal;
	private Format emphaticSmall;
	private Format emphaticJumbo;
	private Paint emphatic;
	private Format tertiaryNormal;
	private Format headerFormat;

	private final Grid master;
	private final Flow outline;
	private Grid viewport;

	public LumpOfCodingScript()
	{
//		super(new MediaRhythm(new File("D:\\GeePawHillDotOrg\\videos\\lumpOfCoding\\lumpOfCodingFaceover.mp4")));
		// new SimpleRhythm());

		master = new Grid();
		viewport = master.nested(INSET, 15, 100 - INSET, 100 - INSET);
		outline = new Flow(world, viewport.all());
		formats = new FormatTable();

		color(119, 187, 65);
		color(177, 140, 254);
		emphatic = color(255, 255, 0);

		primaryJumbo = formats.get(Size.Jumbo, Color.Primary);
		primaryNormal = formats.get(Size.Normal, Color.Primary);

		secondaryJumbo = formats.get(Size.Jumbo, Color.Secondary);
		secondaryNormal = formats.get(Size.Normal, Color.Secondary);

		formats.get(Size.Jumbo, Color.Tertiary);
		tertiaryNormal = formats.get(Size.Normal, Color.Tertiary);

		emphaticJumbo = formats.get(Size.Jumbo, Color.Emphatic);
		formats.get(Size.Normal, Color.Emphatic);
		emphaticSmall = new Format(formats.get(Size.Small, Color.Emphatic), Frames.frame(emphatic, 3d, .7d));

		Font header = Font.font("Chewed Pen BB", FontPosture.ITALIC, 80d);
		headerFormat = new Format(primaryJumbo,TypeFace.font(header, 3d, 1d),TypeFace.color(JfxUtility.color(180, 180, 180),1d));

	}

	public Script make()
	{
		leadIn();
		lumpOfCoding();
		behaviors();
		twoThings();
		proportions();
		outro();
		end();
		return script;
	}

	private void lumpOfCoding()
	{
		scene(10);
		fadeOut();
		header("The First Glance At TDD");
		outline.load(
				"snA Working Geek\n" 
						+ "ps   coding all day to make value\n" 
						+ "ps   somebody says to look into TDD\n"
						+ "ps   TDD means automated tests\n"
						+ "snWhat's Wrong?\n" 
						+ "ps   you already code all day to make value\n"
						+ "ps   automated tests mean you spend more time coding\n"
						+ "ps   TDD doesn't mean less work, it means *more* work\n"
						+ "snThe Lump Of Coding Fallacy\n"
						+ "ps   we think of coding as one lump\n"
						+ "ps   and testing is just more lump\n"
						+ "ps   but coding isn't one lump\n"
						+ "ps   seeing it that way makes the fallacy\n");

		sync(6);
		outline.letters(0).sketch();
		sync(2);
		outline.letters(1).sketch();
		sync(8);
		outline.letters(2).sketch();
		sync(10);
		outline.letters(3).sketch();
		sync(18);
		outline.letters(4).sketch();
		sync(4);
		outline.letters(5).sketch();
		sync(4);
		outline.letters(6).sketch();
		sync(10);
		outline.letters(7).sketch();
		sync(14);
		assume(emphaticJumbo);
		Appearance<Letters> wilkins = letters("AIN'T NOBODY GOT\n TIME FOR THAT! ").centered(1250, 700).appear();
		sync(5);
		wilkins.fadeOut();
		sync(7);
		outline.letters(8).sketch();
		sync(4);
		outline.letters(9).sketch();
		sync(8);
		outline.letters(10).sketch();
		sync(10);
		outline.letters(11).sketch();
		sync(4);
		outline.letters(12).sketch();
	}

	private void behaviors()
	{
		scene(127);
		fadeOut();
		header("Separate Behaviors In The Lump");
		outline.load("tnProgramming The Computer\n" + "ts     entering source\n" + "ts     designing source\n"
				+ "pnStudying What's There\n" + "ps     scanning rapidly\n" + "ps     reading in depth\n"
				+ "snGAK Activity (\"Geek-At-Keyboard\")\n" + "ss     inspection: running before a change\n"
				+ "ss     testing: running after a change\n" + "ss     debugging: running slowly when a change fails\n");
		sync(4);
		outline.letters(0).sketch();
		sync(4);
		outline.letters(1).sketch();
		sync(4);
		outline.letters(2).sketch();
		sync(10);
		outline.letters(3).sketch();
		sync(10);
		outline.letters(4).sketch();
		sync(5);
		outline.letters(5).sketch();
		sync(13);
		outline.letters(6).sketch();
		sync(13);
		outline.letters(7).sketch();
		sync(6);
		outline.letters(8).sketch();
		sync(8);
		outline.letters(9).sketch();

	}

	private void outro()
	{
		scene(473);
		fadeOut();
		header("GeePaw's Advice");
		outline.load("sjNotice Your Proportions\n" + "pn   programming computer\n" + "pn   studying source\n"
				+ "pn   gakking around\n" + "sjActual Effort\n" + "pn   do some lessons\n" + "pn   start with a toy\n"
				+ "pn   move to your own leaf classes\n" + "pn   grow in to hard testing problems\n");
		sync(6);
		outline.letters(0).sketch();
		sync(5);
		outline.letters(1).sketch();
		sync(2);
		outline.letters(2).sketch();
		sync(2);
		outline.letters(3).sketch();
		sync(8);
		outline.letters(4).sketch();
		sync(2);
		outline.letters(5).sketch();
		sync(11);
		outline.letters(6).sketch();
		sync(4);
		outline.letters(7).sketch();
		sync(16);
		outline.letters(8).sketch();
		sync(14);
		fadeOut();
	}

	private void leadIn()
	{
		scene(0);
		wipe();
		letters("GeePaw's Notebook:").format(primaryJumbo).at(new TopLeft(XMARGIN, YMARGIN)).called("header").appear();
		assume(secondaryJumbo);
		letters("The Lump Of Coding Fallacy\n(A Letter For Noobs)").centered(450, 450).appear();
		assume(emphaticSmall);
		letters("Copyright (C) 2018, GeePawHill. All rights reserved.").at(new TopLeft(20, 825)).appear();
	}

	private void twoThings()
	{
		scene(217);
		fadeOut();
		header("Two Points For Later");
		outline.load("sjTwinned Files\n" + "pn   shipping code + testing code\n" + "pn   both are in constant use\n"
				+ "pn   test code surrounds shipping code\n" + "sjSeparate Microtest App\n" + "pn   many small fast tests\n"
				+ "pn   not running your app\n" + "pn   partial tests focused on branching logic\n");

		sync(8);
		outline.letters(0).sketch();
		sync(8);
		outline.letters(1).sketch();
		sync(5);
		outline.letters(2).sketch();
		sync(5);
		outline.letters(3).sketch();
		sync(10);
		outline.letters(4).sketch();
		sync(3);
		outline.letters(5).sketch();
		sync(2);
		outline.letters(6).sketch();
		sync(5);
		outline.letters(7).sketch();
	}

	private void proportions()
	{
		scene(285);
		fadeOut();
		header("X Amount Of Value");

		Grid intermittent = viewport.nested(25, 0, 33, 85);
		Grid before = viewport.nested(36, 0, 60, 85);
		Grid after = viewport.nested(70, 0, 94, 85);

		Format programmingFormat = tertiaryNormal;
		Format studyingFormat = primaryNormal;
		Format gakkingFormat = secondaryNormal;

		Flow gakText = new Flow(world, viewport.area(0, 0, 100, 100));
		gakText.load("snGAK Activity\n" + "sn     Inspection\n" + "ss     Testing\n" + "ss     Debugging\n"
		// + "ss\n"
				+ "pnStudying Code\n" + "ps     Scanning\n" + "ps      Reading\n"
				// + "ps\n"
				+ "tnProgramming\n" + "ts     Entering\n" + "ts     Designing\n");
		buildChord();
		for (int i = 0; i < 10; i++)
			gakText.letters(i).fadeIn();
		endChord();
		sync(17);
		Random selector = new Random();
		for (int i = 3; i < 99; i += 3)
		{
			PointPair points = intermittent.area(0, i, 100, i);
			double probability = selector.nextDouble();
			if (probability < .2)
				assume(programmingFormat);
			else
				if (probability < .5)
					assume(studyingFormat);
				else
					assume(gakkingFormat);
			stroke(points).appear();
		}

		sync(11);
		assume(programmingFormat);
		Appearance<Marks> programmingBefore = box(before.area(0, 80, 100, 100).grow(-10));
		programmingBefore.sketch(2000);

		sync(10);
		assume(gakkingFormat);
		Appearance<Marks> gakBefore = box(before.area(0, 0, 100, 50).grow(-10));
		gakBefore.sketch(2000);

		sync(12);
		assume(studyingFormat);
		Appearance<Marks> studyBefore = box(before.area(0, 50, 100, 80).grow(-10));
		studyBefore.sketch(1500);

		scene(356);

		Point beforeHeader = before.all().north();
		beforeHeader = new Point(beforeHeader.x, beforeHeader.y - 50);
		letters("Before").format(emphaticJumbo).at(new BelowCenter(programmingBefore, 0)).appear();

		sync(8);
		assume(programmingFormat);
		PointPair programmingAfterBounds = after.area(0, 60, 100, 100).grow(-10);
		Appearance<Marks> programmingAfter = box(programmingAfterBounds);
		programmingAfter.sketch(2000);
		assume(emphaticSmall);

		Point afterHeader = after.all().north();
		afterHeader = new Point(afterHeader.x, afterHeader.y - 50);
		letters("After").format(emphaticJumbo).at(new BelowCenter(programmingAfter, 0)).appear();

		sync(12);
		Appearance<Connector> programmingLine = connector();
		programmingLine.actor.from(programmingBefore).to(programmingAfterBounds.west(), true);
		programmingLine.sketch();
		letters("Doubled").at(new AboveCenter(programmingLine, 20d)).appear();

		sync(16);
		assume(studyingFormat);
		PointPair studyingAfterBounds = after.area(0, 45, 100, 60).grow(-10);
		Appearance<Marks> afterStudy = box(studyingAfterBounds);
		afterStudy.sketch(2000);

		sync(19);
		assume(emphaticSmall);
		Appearance<Connector> studyLine = connector();
		studyLine.actor.from(studyBefore, false).to(studyingAfterBounds.west(), true);
		studyLine.format(emphaticSmall).sketch();
		letters("Halved").at(new AboveCenter(studyLine, 0d)).appear();

		sync(15);
		assume(gakkingFormat);
		PointPair gakAfterBounds = after.area(0, 38, 100, 45).grow(-10);
		Appearance<Marks> gakAfter = box(gakAfterBounds);
		gakAfter.sketch(2000);
		assume(emphaticSmall);
		
		sync(8);
		Appearance<Connector> gakLine = connector();
		gakLine.actor.from(gakBefore, false).to(gakAfterBounds.west(), true);
		gakLine.format(emphaticSmall).sketch();
		letters("Slashed!").at(new AboveCenter(gakLine, 0d)).appear();
	}

	public void header(String text)
	{
		letters(text).format(headerFormat).at(new TopRight(master.point(100-INSET, INSET))).called("header").fadeIn();
	}

	private void headerEnd(String end)
	{
		letters(end).format(secondaryJumbo).at(new RightOf(actor("header").entrance())).sketch();
	}

	Vector<Point> polygon(int sides, double radius, Point at)
	{
		Vector<Point> result = new Vector<>();
		for (int i = 0; i < sides; i += 1)
		{
			double angle = ((double) i / (double) sides) * 2 * Math.PI;
			double pointX = (Math.sin(angle) * radius) + at.x;
			double pointY = (Math.cos(angle) * radius) + at.y;
			result.add(new Point(pointX, pointY));
		}
		return result;
	}

	public LumpOfCodingScript downcast()
	{
		return this;
	}
}
