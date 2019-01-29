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
import org.geepawhill.contentment.position.TopLeft
import org.geepawhill.contentment.position.TopRight
import org.geepawhill.contentment.style.Frames
import org.geepawhill.contentment.style.TypeFace
import org.geepawhill.contentment.utility.JfxUtility
import org.geepawhill.contentment.utility.JfxUtility.color
import step.ScriptBuilder
import java.util.*

class ReachingPastProcess : ScriptBuilder<ReachingPastProcess>() {

    private val formats: FormatTable

    private val secondaryJumbo: Format
    private val primaryJumbo: Format
    private val secondaryNormal: Format
    private val primaryNormal: Format
    private val emphaticSmall: Format
    private val emphaticNormal: Format
    private val emphaticJumbo: Format
    private val emphatic: Paint
    private val tertiaryNormal: Format
    private val headerFormat: Format

    private val master: Grid
    private val outline: Flow
    private val viewport: Grid

    init {
        // super(new MediaRhythm(new
        // File("D:\\GeePawHillDotOrg\\videos\\lumpOfCoding\\lumpOfCodingFaceover.mp4")));
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
        emphaticNormal = formats.get(Size.Normal, Color.Emphatic)
        emphaticSmall = Format(formats.get(Size.Small, Color.Emphatic), Frames.frame(emphatic, 3.0, .7))

        val header = Font.font("Chewed Pen BB", FontPosture.ITALIC, 80.0)
        headerFormat = Format(primaryJumbo, TypeFace.font(header, 3.0, 1.0),
                TypeFace.color(JfxUtility.color(180, 180, 180), 1.0))

    }

    fun make(): Script {
        leadIn()
        geepaw()
        goingAgileIsEasy()
        structure()
        definition()
        autopoeisis()
        change()
        threeMs()
        workingWithStories()
        storiesAndDefinition()
        tdd()
        tddAndDefinition()
//
//        somePoints()
//        pointDifference()
//        pointVariation()
//        pointConsensus()
//        iterateAndIncrementProcess()
//        health()
        end()
        return script
    }

    private fun leadIn() {
        scene(0)
        wipe()
        letters("GeePaw's Notebook:").format(primaryJumbo).at(TopLeft(XMARGIN, YMARGIN)).called("header").appear()
        assume(secondaryJumbo)
        letters("Reaching Past Process\nFinding Your Way In Agility").centered(450, 450).appear()
        assume(emphaticSmall)
        letters("Copyright (C) 2018, GeePawHill. All rights reserved.").at(TopLeft(20.0, 825.0)).appear()
    }

    private fun outlineAppear() {
        for (i in 0 until outline.size()) {
            outline.letters(i).appear()
        }
    }

    private fun geepaw() {
        scene(10)
        wipe()
        header("Who *Is* This Guy?")
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
    }

    private fun goingAgileIsEasy() {
        scene(50)
        wipe()
        header("Going Agile Is Easy")
    }

    private fun structure() {
        scene(70)
        wipe()
        header("Packaged Vs Actual Agility")
        assume(secondaryJumbo)
        outline.load("pnAgile newcomers are given tons of \"process\"\n" +
                "sn   structures             roles\n" +
                "sn    meetings           algorithms\n" +
                "sn    methods            techniques\n" +
                "sn    reports              metrics\n" +
                "pnThe light is better under method\n" +
                "sn   easier to write down\n" +
                "sn   easier to direct\n" +
                "sn   easier to buy & sell\n"
        )
        outlineAppear()
        assume(emphaticJumbo)
        letters("agility is not process"
        ).centered(1200, 400).appear()
        assume(emphaticNormal)
        letters("to get there we have to reach past\n" +
                "      structure and method"
        ).centered(1200, 550).appear()

    }

    private fun definition() {
        scene(90)
        wipe()
        header("A Working Definition Of Agility")
        assume(secondaryJumbo)
        letters(
                "  agility is an autopoeitic community\n" +
                        "            embracing change\n" +
                        "      by a triple-balance between \n" +
                        "the made, the making, and the maker"
        ).centered(800, 450).appear()
    }

    private fun autopoeisis() {
        scene(110)
        wipe()
        header("Autopoeisis")
        outline.load("pnLiterally, \"self-making\"\n" +
                "sn   coined in '79\n" +
                "sn   derives from theory of biology\n" +
                "pnAutopoeitic entities\n" +
                "sn   no fixed parts\n" +
                "sn   no fixed structure\n" +
                "sn   no fixed mechanisms\n" +
                "pncontinual re-creation\n")
        outlineAppear()
        assume(emphaticNormal)
        letters("A Good Synonym For Now:\n").centered(1100, 400).appear()
        assume(emphaticJumbo)
        letters("Living").centered(1100, 500).appear()
    }


    private fun change() {
        scene(130)
        wipe()
        header("Embrace Change")
        outline.load("pnOld-school approaches seek to control change\n" +
                "ss   change is seen as avoidable/controllable risk\n" +
                "ss   and that's just code, it's even worse beyond that\n" +
                "pnAgile approaches welcome change at every level\n" +
                "ss   in the made thing\n" +
                "ss   in the market and the domain\n" +
                "ss   in the personnel, structure, and roles\n" +
                "ss   in the code, and the coding\n")

        outlineAppear()
        assume(emphaticNormal)
        letters("agilists welcome change as normal and positive").centered(800, 700).appear()
        assume(emphaticJumbo)
        letters("our mantra: *surf* the change").centered(800, 775).appear()
    }

    private fun threeMs() {
        scene(150)
        wipe()
        header("The Made, The Making, & The Makers")
        outline.load("ssMade: the product\n" +
                "ps   the features\n" +
                "ps   the code\n" +
                "ps   the infrastructure/deployment\n" +
                "ssMaking: how we create the made\n" +
                "ps   the technique\n" +
                "ps   the tools\n" +
                "ps   the flow of work\n" +
                "ssMakers: our team\n" +
                "ps   minds, bodies, energy\n" +
                "ps   collaborations\n" +
                "ps   all things human\n")
        outlineAppear()
    }

    private fun workingWithStories() {
        scene(130)
        wipe()
        header("Working By Story")
        outline.load("pnWhat is a story?\n" +
                "ss   a story is a change we want in the program\n" +
                "ss   most specify a situation and what will happen when it occurs\n" +
                "ss   we apply stories to the code one at a time\n" +
                "pnThe perfect story has these qualities\n" +
                "ss   size: a day or two of work\n" +
                "ss   visibility: they increase the value of the program detectably\n" +
                "ss   verticality: from pixels to bits and back\n" +
                "ss   shippability: can be turned on at will\n")
        outlineAppear()
        assume(emphaticJumbo)
        letters("we only work today's stories, not tomorrow's").centered(800, 775).appear()
    }

    private fun storiesAndDefinition() {
        scene(150)
        wipe()
        header("Stories and the Three M's")
        outline.load("pnThe Made\n" +
                "sn   under continuous change\n" +
                "sn   steadily increasing value\n" +
                "pnThe Making\n" +
                "sn   ...sufficient unto the day...\n" +
                "sn   rework is normal\n" +
                "sn   we always think about \"turning on\"\n" +
                "pnThe Makers\n" +
                "sn   with stories, we narrow team focus\n" +
                "sn   not working ahead is a big success-factor\n" +
                "sn   just-in-time spec gets the right detail level\n"
        )
        outlineAppear()
    }

    private fun tdd() {
        scene(170)
        wipe()
        header("Test-Driven Development")
        outline.load("pnWe change the code in a specific way\n" +
                "sn   test that it doesn't work\n" +
                "sn   make it work\n" +
                "sn   optimize its changeability\n" +
                "pnMicrotests\n" +
                "sn   run in external app\n" +
                "sn   test very specific bits of our branching logic\n" +
                "sn   are treated as first-class code\n" +
                "pnIMPORTANT: TDD is a productivity tool\n" +
                "sn   easy to be fooled by the word \"test\"\n" +
                "sn   TDD is for shipping more value faster\n")

        outlineAppear()
    }

    private fun tddAndDefinition() {
        scene(190)
        wipe()
        header("TDD and the Three M's")
        val lines =
                """
pnThe Made
ss   biggest impact: speed of delivery
ss   testability correlates well with good design
pnThe Making
ss   integrates well with CI
ss   forces small confirmed success
ss   much faster than running app
pnThe Maker
ss   super-narrow mental bandwidth
ss   rhythm, purpose, and autonomy
ss   continual regression of WIP
ss   in the code, and the coding
""".trimIndent()

        outline.load(lines)
        outlineAppear()
    }

    private fun somePoints() {
        scene(90)
        wipe()
        header("Some Points")
        outline.load("sjTo optimize for collaboration, we must...\n"
                + "pn   seek out difference\n"
                + "pn   provide continuous variation\n"
                + "pn   learn how to find consensus\n"
                + "pn   fight for F2D2\n")
        outlineAppear()

    }

    private fun pointDifference() {
        scene(100)
        wipe()
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
        scene(105)
        assume(emphaticJumbo)
        letters("           the differences between us\n"
                + "   are the most reliable source of ideas\n"
                + "         and we are in the idea business\n"
        ).centered(800, 750).appear()
    }


    private fun pointVariation() {
        scene(110)
        wipe()
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
        scene(105)
        assume(emphaticJumbo)
        letters("   by constantly varying forms\n"
                + "  we constantly make things new: \n"
                + "new forms make different results\n"
        ).centered(800, 750).appear()

    }

    fun pointConsensus() {
        scene(120)
        wipe()
        header("Consensus Is Difficult & Important")
        assume(secondaryJumbo)
        outline.load("snThe keys\n"
                + "ps   consensus is not unanimity\n"
                + "ps   build consensus one on one\n"
                + "ps   'not-horribly-wrong' is the mission\n"
                + "ps   lower stakes means faster consensus\n"
                + "ps   blocks are temporary\n")
        outlineAppear()
        scene(125)
        assume(emphaticJumbo)
        letters("   none of us\n"
                + "is as smart as\n"
                + "   all of us\n"
        ).centered(800, 750).appear()

    }

    fun iterateAndIncrementProcess() {
        scene(130)
        wipe()
        header("Iterate & Increment Process, Too")
        assume(secondaryJumbo)
        outline.load("sjNearest, easiest, cheapest, agreedest\n"
                + "pn   you're building a culture, not a machine\n"
                + "pn   nothing wins like winning\n"
                + "pn   second-tier problems morph to first-tier\n"
                + "sjMake changing process ordinary\n"
                + "ps   all the knobs a little\n"
                + "ps   lather, rinse, repeat\n"
                + "sjAdd metrics late\n"
                + "sjEnjoy!\n"
        )
        outlineAppear()
    }

    fun health() {
        scene(150)
        wipe()
        header("Above All: The Health Of The Team")
        assume(secondaryJumbo)
        outline.load("sjYou can't organize your way in to health\n"
                + "pn   Unhealthy process change can be net negative in impact\n"
                + "pn   Listen listen listen.\n"
                + "sjSeek subjective input with:\n"
                + "pn   retros\n"
                + "pn   outside events\n"
                + "pn   free conversation\n"
                + "sjWorry about psychological safety\n"
                + "pn   \"permission to speak freely\"\n"
                + "pn   be-with instead of do-to\n")
        outlineAppear()
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

    override fun downcast(): ReachingPastProcess {
        return this
    }

    companion object {

        private val INSET = 3
        private val XMARGIN = 20.0
        private val YMARGIN = 20.0
    }
}
