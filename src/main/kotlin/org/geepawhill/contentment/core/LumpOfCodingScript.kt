package org.geepawhill.contentment.core

import javafx.scene.paint.Paint
import javafx.scene.text.Font
import javafx.scene.text.FontPosture
import org.geepawhill.contentment.flow.Color
import org.geepawhill.contentment.flow.Flow
import org.geepawhill.contentment.flow.FormatTable
import org.geepawhill.contentment.flow.Size
import org.geepawhill.contentment.format.Format
import org.geepawhill.contentment.geometry.Point
import org.geepawhill.contentment.grid.Grid
import org.geepawhill.contentment.player.Script
import org.geepawhill.contentment.position.*
import org.geepawhill.contentment.step.ScriptBuilder
import org.geepawhill.contentment.style.Frames
import org.geepawhill.contentment.style.TypeFace
import org.geepawhill.contentment.utility.JfxUtility
import org.geepawhill.contentment.utility.JfxUtility.color
import java.util.*

class LumpOfCodingScript : ScriptBuilder<LumpOfCodingScript>() {

    private val formats: FormatTable

    private val secondaryJumbo: Format
    private val primaryJumbo: Format
    private val secondaryNormal: Format
    private val primaryNormal: Format
    private val emphaticSmall: Format
    private val emphaticJumbo: Format
    private val emphatic: Paint
    private val tertiaryNormal: Format
    private val headerFormat: Format

    private val master: Grid
    private val outline: Flow
    private val viewport: Grid

    init {
        //		super(new MediaRhythm(new File("D:\\GeePawHillDotOrg\\videos\\lumpOfCoding\\lumpOfCodingFaceover.mp4")));
        // new SimpleRhythm());

        master = Grid()
        viewport = master.nested(INSET, 15, 100 - INSET, 100 - INSET)
        outline = Flow(world, viewport.all())
        formats = FormatTable()

        color(119, 187, 65)
        color(177, 140, 254)
        emphatic = color(255, 255, 0)

        primaryJumbo = formats.get(Size.Jumbo, Color.Primary)
        primaryNormal = formats.get(Size.Normal, Color.Primary)

        secondaryJumbo = formats.get(Size.Jumbo, Color.Secondary)
        secondaryNormal = formats.get(Size.Normal, Color.Secondary)

        formats.get(Size.Jumbo, Color.Tertiary)
        tertiaryNormal = formats.get(Size.Normal, Color.Tertiary)

        emphaticJumbo = formats.get(Size.Jumbo, Color.Emphatic)
        formats.get(Size.Normal, Color.Emphatic)
        emphaticSmall = Format(formats.get(Size.Small, Color.Emphatic), Frames.frame(emphatic, 3.0, .7))

        val header = Font.font("Chewed Pen BB", FontPosture.ITALIC, 80.0)
        headerFormat = Format(primaryJumbo, TypeFace.font(header, 3.0, 1.0), TypeFace.color(JfxUtility.color(180, 180, 180), 1.0))

    }

    fun make(): Script {
        leadIn()
        lumpOfCoding()
        behaviors()
        twoThings()
        proportions()
        outro()
        end()
        return script
    }

    private fun lumpOfCoding() {
        scene(10)
        fadeOut()
        header("The First Glance At TDD")
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
                        + "ps   seeing it that way makes the fallacy\n")

        sync(6)
        outline.letters(0).sketch()
        sync(2)
        outline.letters(1).sketch()
        sync(8)
        outline.letters(2).sketch()
        sync(10)
        outline.letters(3).sketch()
        sync(18)
        outline.letters(4).sketch()
        sync(4)
        outline.letters(5).sketch()
        sync(4)
        outline.letters(6).sketch()
        sync(10)
        outline.letters(7).sketch()
        sync(14)
        assume(emphaticJumbo)
        val wilkins = letters("AIN'T NOBODY GOT\n TIME FOR THAT! ").centered(1250, 700).appear()
        sync(5)
        wilkins.fadeOut()
        sync(7)
        outline.letters(8).sketch()
        sync(4)
        outline.letters(9).sketch()
        sync(8)
        outline.letters(10).sketch()
        sync(10)
        outline.letters(11).sketch()
        sync(4)
        outline.letters(12).sketch()
    }

    private fun behaviors() {
        scene(127)
        fadeOut()
        header("Separate Behaviors In The Lump")
        outline.load("tnProgramming The Computer\n" + "ts     entering source\n" + "ts     designing source\n"
                + "pnStudying What's There\n" + "ps     scanning rapidly\n" + "ps     reading in depth\n"
                + "snGAK Activity (\"Geek-At-Keyboard\")\n" + "ss     inspection: running before a change\n"
                + "ss     testing: running after a change\n" + "ss     debugging: running slowly when a change fails\n")
        sync(4)
        outline.letters(0).sketch()
        sync(4)
        outline.letters(1).sketch()
        sync(4)
        outline.letters(2).sketch()
        sync(10)
        outline.letters(3).sketch()
        sync(10)
        outline.letters(4).sketch()
        sync(5)
        outline.letters(5).sketch()
        sync(13)
        outline.letters(6).sketch()
        sync(13)
        outline.letters(7).sketch()
        sync(6)
        outline.letters(8).sketch()
        sync(8)
        outline.letters(9).sketch()

    }

    private fun outro() {
        scene(473)
        fadeOut()
        header("GeePaw's Advice")
        outline.load("sjNotice Your Proportions\n" + "pn   programming computer\n" + "pn   studying source\n"
                + "pn   gakking around\n" + "sjActual Effort\n" + "pn   do some lessons\n" + "pn   start with a toy\n"
                + "pn   move to your own leaf classes\n" + "pn   grow in to hard testing problems\n")
        sync(6)
        outline.letters(0).sketch()
        sync(5)
        outline.letters(1).sketch()
        sync(2)
        outline.letters(2).sketch()
        sync(2)
        outline.letters(3).sketch()
        sync(8)
        outline.letters(4).sketch()
        sync(2)
        outline.letters(5).sketch()
        sync(11)
        outline.letters(6).sketch()
        sync(4)
        outline.letters(7).sketch()
        sync(16)
        outline.letters(8).sketch()
        sync(14)
        fadeOut()
    }

    private fun leadIn() {
        scene(0)
        wipe()
        letters("GeePaw's Notebook:").format(primaryJumbo).at(TopLeft(XMARGIN, YMARGIN)).called("header").appear()
        assume(secondaryJumbo)
        letters("The Lump Of Coding Fallacy\n(A Letter For Noobs)").centered(450, 450).appear()
        assume(emphaticSmall)
        letters("Copyright (C) 2018, GeePawHill. All rights reserved.").at(TopLeft(20.0, 825.0)).appear()
    }

    private fun twoThings() {
        scene(217)
        fadeOut()
        header("Two Points For Later")
        outline.load("sjTwinned Files\n" + "pn   shipping code + testing code\n" + "pn   both are in constant use\n"
                + "pn   test code surrounds shipping code\n" + "sjSeparate Microtest App\n" + "pn   many small fast tests\n"
                + "pn   not running your app\n" + "pn   partial tests focused on branching logic\n")

        sync(8)
        outline.letters(0).sketch()
        sync(8)
        outline.letters(1).sketch()
        sync(5)
        outline.letters(2).sketch()
        sync(5)
        outline.letters(3).sketch()
        sync(10)
        outline.letters(4).sketch()
        sync(3)
        outline.letters(5).sketch()
        sync(2)
        outline.letters(6).sketch()
        sync(5)
        outline.letters(7).sketch()
    }

    private fun proportions() {
        scene(285)
        fadeOut()
        header("X Amount Of Value")

        val intermittent = viewport.nested(25, 0, 33, 85)
        val before = viewport.nested(36, 0, 60, 85)
        val after = viewport.nested(70, 0, 94, 85)

        val programmingFormat = tertiaryNormal
        val studyingFormat = primaryNormal
        val gakkingFormat = secondaryNormal

        val gakText = Flow(world, viewport.area(0, 0, 100, 100))
        gakText.load("snGAK Activity\n" + "sn     Inspection\n" + "ss     Testing\n" + "ss     Debugging\n"
                // + "ss\n"
                + "pnStudying Code\n" + "ps     Scanning\n" + "ps      Reading\n"
                // + "ps\n"
                + "tnProgramming\n" + "ts     Entering\n" + "ts     Designing\n")
        buildChord()
        for (i in 0..9)
            gakText.letters(i).fadeIn()
        endChord()
        sync(17)
        val selector = Random()
        var i = 3
        while (i < 99) {
            val points = intermittent.area(0, i, 100, i)
            val probability = selector.nextDouble()
            if (probability < .2)
                assume(programmingFormat)
            else if (probability < .5)
                assume(studyingFormat)
            else
                assume(gakkingFormat)
            stroke(points).appear()
            i += 3
        }

        sync(11)
        assume(programmingFormat)
        val programmingBefore = box(before.area(0, 80, 100, 100).grow(-10.0))
        programmingBefore.sketch(2000.0)

        sync(10)
        assume(gakkingFormat)
        val gakBefore = box(before.area(0, 0, 100, 50).grow(-10.0))
        gakBefore.sketch(2000.0)

        sync(12)
        assume(studyingFormat)
        val studyBefore = box(before.area(0, 50, 100, 80).grow(-10.0))
        studyBefore.sketch(1500.0)

        scene(356)

        letters("Before").format(emphaticJumbo).at(BelowCenter(programmingBefore, 0.0)).appear()

        sync(8)
        assume(programmingFormat)
        val programmingAfterBounds = after.area(0, 60, 100, 100).grow(-10.0)
        val programmingAfter = box(programmingAfterBounds)
        programmingAfter.sketch(2000.0)
        assume(emphaticSmall)

        letters("After").format(emphaticJumbo).at(BelowCenter(programmingAfter, 0.0)).appear()

        sync(12)
        val programmingLine = connector()
        programmingLine.actor.from(programmingBefore).to(programmingAfterBounds.west(), true)
        programmingLine.sketch()
        letters("Doubled").at(AboveCenter(programmingLine, 20.0)).appear()

        sync(16)
        assume(studyingFormat)
        val studyingAfterBounds = after.area(0, 45, 100, 60).grow(-10.0)
        val afterStudy = box(studyingAfterBounds)
        afterStudy.sketch(2000.0)

        sync(19)
        assume(emphaticSmall)
        val studyLine = connector()
        studyLine.actor.from(studyBefore, false).to(studyingAfterBounds.west(), true)
        studyLine.format(emphaticSmall).sketch()
        letters("Halved").at(AboveCenter(studyLine, 0.0)).appear()

        sync(15)
        assume(gakkingFormat)
        val gakAfterBounds = after.area(0, 38, 100, 45).grow(-10.0)
        val gakAfter = box(gakAfterBounds)
        gakAfter.sketch(2000.0)
        assume(emphaticSmall)

        sync(8)
        val gakLine = connector()
        gakLine.actor.from(gakBefore, false).to(gakAfterBounds.west(), true)
        gakLine.format(emphaticSmall).sketch()
        letters("Slashed!").at(AboveCenter(gakLine, 0.0)).appear()
    }

    fun header(text: String) {
        letters(text).format(headerFormat).at(TopRight(master.point(100 - INSET, INSET))).called("header").fadeIn()
    }

    private fun headerEnd(end: String) {
        letters(end).format(secondaryJumbo).at(RightOf(actor("header").entrance())).sketch()
    }

    internal fun polygon(sides: Int, radius: Double, at: Point): Vector<Point> {
        val result = Vector<Point>()
        var i = 0
        while (i < sides) {
            val angle = i.toDouble() / sides.toDouble() * 2.0 * Math.PI
            val pointX = Math.sin(angle) * radius + at.x
            val pointY = Math.cos(angle) * radius + at.y
            result.add(Point(pointX, pointY))
            i += 1
        }
        return result
    }

    override fun downcast(): LumpOfCodingScript {
        return this
    }

    companion object {

        private val INSET = 3
        private val XMARGIN = 20.0
        private val YMARGIN = 20.0
    }
}
