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
import org.geepawhill.contentment.position.RightOf
import org.geepawhill.contentment.position.TopRight
import org.geepawhill.contentment.step.ScriptBuilder
import org.geepawhill.contentment.style.Frames
import org.geepawhill.contentment.style.TypeFace
import org.geepawhill.contentment.utility.JfxUtility
import org.geepawhill.contentment.utility.JfxUtility.color
import java.util.*

class OptimizingForCollaboration : ScriptBuilder<OptimizingForCollaboration>() {

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
        headerFormat = Format(primaryJumbo, TypeFace.font(header, 3.0, 1.0),
                TypeFace.color(JfxUtility.color(180, 180, 180), 1.0))

    }

    fun make(): Script {
        leadIn()
        geepaw()
        secondMission()
        secondMissionB()
        thirdMission()
        somePoints()
        pointDifference()
        pointVariation()
        pointConsensus()
        pointFsquaredDsquared()
        fourthMission()
        end()
        return script
    }

    private fun leadIn() {
        scene(0)
        wipe()
        header("Optimizing For Collaboration")
        pause()
        outline.load("sjYour First Mission\n"
                + "pn   Find someone you don't know, and exchange at least the \n"
                + "pn   following info with them.\n"
                + "en      * What's one movie in your top ten all time?\n"
                + "en      * Are you more likely to be early or late?\n"
                + "en      * How do your personal attributes shape your collaborations?\n")
        outlineAppear()
        pause()
        wipe()
    }


    private fun outlineAppear() {
        buildChord()
        for (i in 0 until outline.size()) {
            outline.letters(i).appear()
        }
        endChord()
    }

    private fun geepaw() {
        header("A Proper Introduction To GeePaw")
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
        )
        outlineAppear()
        pause()
        wipe()
    }

    private fun secondMission() {
        header("Ways To Collaborate")
        assume(secondaryJumbo)
        outline.load("snSecond Mission, Part One\n"
                + "pjwhat activity-collaboration pairings do you use?\n"
                + "sn   relaxed and inclusive, not canonical\n"
                + "sn   some samples...\n"
                + "pn      coding: solo, paired, ganged\n"
                + "pn      brushfire response: solo, paired, ganged\n"
                + "pn      reporting status: standup, meeting\n"
                + "pn      backchannel diplomacy: phone, hallway, slack\n"
                + "pn      understanding requirements: meeting\n")
        outlineAppear()
        pause()
        wipe()

    }

    private fun secondMissionB() {
        header("What we like and don't")
        assume(secondaryJumbo)
        outline.load("snSecond Mission, Part Two\n"
                + "pjwhat do we like and not like?\n"
                + "snfor each person...\n"
                + "sn   ...put an X next to least favorite\n"
                + "sn   ...and an O next to most favorite\n"
        )

        outlineAppear()
        pause()
        wipe()
    }

    private fun thirdMission() {
        header("The Best Wall In The Room")
        assume(secondaryJumbo)
        outline.load("snThird Mission\n"
                + "pjfor the whole table,\n"
                + "pjchoose the best wall in this room\n"
        )

        outlineAppear()
        pause()
        wipe()

    }

    private fun somePoints() {
        header("Some Points")
        outline.load("sjTo optimize for collaboration, we must...\n"
                + "pn   seek out difference\n"
                + "pn   provide continuous variation\n"
                + "pn   learn how to find consensus\n"
                + "pn   fight for F2D2\n")
        outlineAppear()
        pause()
        wipe()

    }

    private fun pointDifference() {
        header("Point: Why Collaborate?")
        assume(secondaryJumbo)
        outline.load("snWe collaborate because we are different\n"
                + "ps   different paths\n"
                + "ps   different strengths & weaknesses\n"
                + "ps   above all: different *ideas*\n"
                + "snIf we were moving coal, same would be just as good\n"
                + "ps   we are moving ideas\n"
                + "ps   two people with the same idea? that's one idea.\n")
        outlineAppear()
        pause()
        assume(emphaticJumbo)
        letters("           the differences between us\n"
                + "   are the most reliable source of ideas\n"
                + "         and we are in the idea business\n"
        ).centered(800, 750).appear()
        pause()
        wipe()
    }


    private fun pointVariation() {
        header("Point: Variation Is Central")
        assume(secondaryJumbo)
        outline.load("snNo table's X's and O's are the same\n"
                + "ps   part of the difference is in these modes\n"
                + "ps   there is not one best form of collaboration\n"
                + "snSo we must avoid uniformity\n"
                + "ps   learn how to choose at random\n"
                + "ps   resist schemas, rotate roles\n"
                + "ps   note and re-vitalize the stale\n")
        outlineAppear()
        pause()
        assume(emphaticJumbo)
        letters("   by constantly varying forms\n"
                + "  we constantly make things new: \n"
                + "new forms make different results\n"
        ).centered(800, 750).appear()
        pause()
        wipe()


    }

    fun pointConsensus() {
        header("Point: Consensus Is Difficult & Critical")
        assume(secondaryJumbo)
        outline.load("snThe keys\n"
                + "ps   consensus is not unanimity\n"
                + "ps   build consensus one on one\n"
                + "ps   'not-horribly-wrong' is the mission\n"
                + "ps   lower stakes means faster consensus\n"
                + "ps   blocks are temporary\n")
        outlineAppear()
        pause()
        assume(emphaticJumbo)
        letters("   none of us\n"
                + "is as smart as\n"
                + "   all of us\n"
        ).centered(800, 750).appear()
        pause()
        wipe()

    }

    fun pointFsquaredDsquared() {
        header("Point: Frequent Focused Direct Dialog")
        assume(secondaryJumbo)
        outline.load("snF-Squared D-Squared: Frequent, Focused, Direct, Dialog\n"
                + "ps   this takes real effort and energy\n"
                + "ps   when you need someone, go find them\n"
                + "ps   look at people and speak with them\n"
                + "ps   practice practice practice\n"
        )
        outlineAppear()
        pause()
        assume(emphaticJumbo)
        letters("   *nothing* beats\n"
                + " F-Squared D-Squared\n"
                + "  for collaboration"
        ).centered(800, 750).appear()
        pause()
        wipe()

    }

    fun fourthMission() {
        header("Let's Collaborate")
        assume(secondaryJumbo)
        outline.load("snFourth Mission\n"
                + "pjWhat are we thinking?\n"
                + "pn   Questions, Comments, Critique\n")
        outlineAppear()
        pause()
        wipe()

    }

    fun header(text: String) {
        letters(text).format(headerFormat).at(TopRight(master.point(100 - INSET, INSET))).called("header").appear()
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

    override fun downcast(): OptimizingForCollaboration {
        return this
    }

    companion object {

        private val INSET = 3
        private val XMARGIN = 20.0
        private val YMARGIN = 20.0
    }
}
