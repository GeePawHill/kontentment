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

public class OptimizingForCollaboration extends ScriptBuilder<OptimizingForCollaboration>
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

	public OptimizingForCollaboration()
	{
		// super(new MediaRhythm(new
		// File("D:\\GeePawHillDotOrg\\videos\\lumpOfCoding\\lumpOfCodingFaceover.mp4")));
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
		headerFormat = new Format(primaryJumbo, TypeFace.font(header, 3d, 1d),
				TypeFace.color(JfxUtility.color(180, 180, 180), 1d));

	}

	public Script make()
	{
		leadIn();
		leadIn2();
		geepaw();
		secondMission();
		secondMissionB();
		thirdMission();
		somePoints();
		pointDifference();
		pointVariation();
		pointConsensus();
		pointFsquaredDsquared();
		fourthMission();
		end();
		return script;
	}
	
	private void leadIn()
	{
		scene(0);
		wipe();
		header("Optimizing For Collaboration");
//		timer(1.0).appear().actor.start();
	}
	
	

//	private Appearance<Timer> timer(double d)
//	{
//		return null;
//	}

	private void leadIn2()
	{
		scene(5);
		wipe();
		header("Optimizing For Collaboration");
		assume(secondaryJumbo);
		outline.load("sjYour First Mission\n"
				+ "pn   Find someone you don't know, and exchange at least the \n"
				+ "pn   following info with them.\n"
				+ "en      * What's one movie in your top ten all time?\n" 
				+ "en      * Are you more likely to be early or late?\n"
				+ "en      * How do your personal attributes shape your collaborations?\n");
		outlineAppear();
	}
	private void outlineAppear()
	{
		for (int i = 0; i < outline.size(); i++)
		{
			outline.letters(i).appear();
		}
	}

	private void geepaw()
	{
		scene(10);
		wipe();
		header("A Proper Introduction To GeePaw");
		outline.load("snGeePaw Hill\n" 
				+ "pn   yes, it's really 'GeePaw'\n" 
				+ "pn   short for 'GrandPa', which I became at a young age\n"
				+ "snGeek, Teacher, Coach\n"
				+ "pn   professional geek for 38 years\n"
				+ "pn   i live with developers and support them as they grow\n"
				+ "snContact me:\n" 
				+ "pn   Twitter: @GeePawHill\n"
				+ "pn   Website: GeePawHill.Org\n"
				+ "pn   Email: GeePawHill@GeePawHill.Org\n"
				);
		outlineAppear();
	}

	private void secondMission()
	{
		scene(50);
		wipe();
		header("Ways To Collaborate");
		assume(secondaryJumbo);
		outline.load("snSecond Mission, Part One\n"
				+ "pjwhat activity-collaboration pairings do you use?\n"
				+ "sn   relaxed and inclusive, not canonical\n"
				+ "sn   some samples...\n"
				+ "pn      coding: solo, paired, ganged\n"
				+ "pn      brushfire response: solo, paired, ganged\n"
				+ "pn      reporting status: standup, meeting\n"
				+ "pn      backchannel diplomacy: phone, hallway, slack\n"
				+ "pn      understanding requirements: meeting\n");
		outlineAppear();
	}
	
	private void secondMissionB()
	{
		scene(70);
		wipe();
		header("What we like and don't");
		assume(secondaryJumbo);
		outline.load("snSecond Mission, Part Two\n"
				+ "pjwhat do we like and not like?\n"
				+ "snfor each person...\n"
				+ "sn   ...put an X next to least favorite\n"
				+ "sn   ...and an O next to most favorite\n"
				);

		outlineAppear();
	}

	private void thirdMission()
	{
		scene(80);
		wipe();
		header("The Best Wall In The Room");
		assume(secondaryJumbo);
		outline.load("snThird Mission\n"
				+ "pjfor the whole table,\n"
				+ "pjchoose the best wall in this room\n"
				);

		outlineAppear();
	}
	
	private void somePoints()
	{
		scene(90);
		wipe();
		header("Some Points");
		outline.load("sjTo optimize for collaboration, we must...\n"
				+ "pn   seek out difference\n"
				+ "pn   provide continuous variation\n"
				+ "pn   learn how to find consensus\n"
				+ "pn   fight for F2D2\n");
		outlineAppear();
		
	}
	
	private void pointDifference()
	{
		scene(100);
		wipe();
		header("Point: Why Collaborate?");
		assume(secondaryJumbo);
		outline.load("snWe collaborate because we are different\n"
				+ "ps   different paths\n"
				+ "ps   different strengths & weaknesses\n"
				+ "ps   above all: different *ideas*\n"
				+ "snIf we were moving coal, same would be just as good\n" 
				+ "ps   we are moving ideas\n"
				+ "ps   two people with the same idea? that's one idea.\n");
		outlineAppear();
		scene(105);
		assume(emphaticJumbo);
		letters(  "           the differences between us\n"
				+ "   are the most reliable source of ideas\n"
				+ "         and we are in the idea business\n"
				).centered(800, 750).appear();
	}


	private void pointVariation()
	{
		scene(110);
		wipe();
		header("Point: Variation Is Central");
		assume(secondaryJumbo);
		outline.load("snNo table's X's and O's are the same\n"
				+ "ps   part of the difference is in these modes\n"
				+ "ps   there is not one best form of collaboration\n"
				+ "snSo we must avoid uniformity\n" 
				+ "ps   learn how to choose at random\n"
				+ "ps   resist schemas, rotate roles\n"
				+ "ps   note and re-vitalize the stale\n");
		outlineAppear();
		scene(105);
		assume(emphaticJumbo);
		letters(  "   by constantly varying forms\n"
				+ "  we constantly make things new: \n"
				+ "new forms make different results\n"
				).centered(800, 750).appear();
		
	}
	
	public void pointConsensus()
	{
		scene(120);
		wipe();
		header("Point: Consensus Is Difficult & Critical");
		assume(secondaryJumbo);
		outline.load("snThe keys\n"
				+ "ps   consensus is not unanimity\n"
				+ "ps   build consensus one on one\n"
				+ "ps   'not-horribly-wrong' is the mission\n"
				+ "ps   lower stakes means faster consensus\n"
				+ "ps   blocks are temporary\n");
		outlineAppear();
		scene(125);
		assume(emphaticJumbo);
		letters(  "   none of us\n"
				+ "is as smart as\n"
				+ "   all of us\n"
				).centered(800, 750).appear();
		
	}
	
	public void pointFsquaredDsquared()
	{
		scene(130);
		wipe();
		header("Point: Frequent Focused Direct Dialog");
		assume(secondaryJumbo);
		outline.load("snF-Squared D-Squared: Frequent, Focused, Direct, Dialog\n"
				+ "ps   this takes real effort and energy\n"
				+ "ps   when you need someone, go find them\n"
				+ "ps   look at people and speak with them\n"
				+ "ps   practice practice practice\n"
				);
		outlineAppear();
		scene(125);
		assume(emphaticJumbo);
		letters(  "   *nothing* beats\n"
				+ " F-Squared D-Squared\n"
				+ "  for collaboration"
				).centered(800, 750).appear();
	}
	
	public void fourthMission()
	{
		scene(150);
		wipe();
		header("Let's Collaborate");
		assume(secondaryJumbo);
		outline.load("snFourth Mission\n"
				+ "pjWhat are we thinking?\n"
				+ "pn   Questions, Comments, Critique\n");
		outlineAppear();
	}
	
	public void header(String text)
	{
		letters(text).format(headerFormat).at(new TopRight(master.point(100 - INSET, INSET))).called("header").fadeIn();
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

	public OptimizingForCollaboration downcast()
	{
		return this;
	}
}
