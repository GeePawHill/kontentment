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
import org.geepawhill.contentment.style.Dash
import org.geepawhill.contentment.style.Frames
import org.geepawhill.contentment.style.TypeFace
import org.geepawhill.contentment.utility.JfxUtility
import org.geepawhill.contentment.utility.JfxUtility.color
import step.ScriptBuilder

class ReachingPastProcess : ScriptBuilder<ReachingPastProcess>() {

    private val formats = FormatTable()
    private val secondarySmall = formats.get(Size.Small, Color.Secondary)

    private val communityFormat = Format(Frames.frame(formats.primary, 2.0, 1.0, Dash.dash(4.0, 4.0)))
    private val balanceFormat = Format(Frames.frame(formats.tertiary, 1.0, 1.0, Dash.dash(15.0, 20.0)))

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
    private val mFormat = Format(formats.get(Size.Normal, Color.Tertiary),
            Frames.frame(formats.tertiary, 3.0, .7, Dash.solid()))

    private val primarySmall = formats.get(Size.Small, Color.Primary)

    private val master = Grid()
    private val viewport = master.nested(INSET, 15, 100 - INSET, 100 - INSET)
    private val leftView = master.nested(INSET, 25, 47, 100 - INSET)
    private val rightView = master.nested(53, 25, 100 - INSET, 100 - INSET)
    private val rightOutline = Flow(world, rightView.all())

    private val outline: Flow

    init {
        // super(new MediaRhythm(new
        // File("D:\\GeePawHillDotOrg\\videos\\lumpOfCoding\\lumpOfCodingFaceover.mp4")));
        // new SimpleRhythm());

        outline = Flow(world, viewport.all())

        color(119, 187, 65)
        color(177, 140, 254)
        emphatic = formats.emphatic

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
        techniques()

        tdd()
        tddAndDefinition()
        sprints()
        sprintsAndTheDefinition()
//        workingWithStories()
//        storiesAndDefinition()
//        theMeetings()
//        ciAndCd()
//        theMeetingsAndDefinition()
//        ciCdAndDefinition()
//
//        collaborationIsCritical()
//        iterateAndIncrementProcess()
//        health()

        end()
        return script
    }

    private fun leadIn() {
        scene(0)
        wipe()
        buildChord()
        letters("GeePaw's Notebook:").format(primaryJumbo).at(TopLeft(XMARGIN, YMARGIN)).called("header").appear()
        assume(secondaryJumbo)
        letters("Reaching Past Process\nFinding Your Way In Agility").centered(450, 450).appear()
        assume(emphaticSmall)
        letters("Copyright (C) 2018, GeePawHill. All rights reserved.").at(TopLeft(20.0, 825.0)).appear()
        endChord()
        pause()
    }

    private fun geepaw() {
        wipe()
        buildChord()
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
        endChord()
        pause()
    }

    private fun goingAgileIsEasy() {
        wipe()
        header("Going Agile Is Easy")
        pause()
    }

    private fun structure() {
        wipe()
        buildChord()
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
        endChord()
        pause()
        assume(emphaticJumbo)
        letters("agility is not process").centered(1200, 400).sketch()
        assume(emphaticNormal)
        letters("to get there we have to reach past\n" +
                "      structure and method"
        ).centered(1200, 550).sketch()
        pause()
    }

    private fun definition() {
        wipe()

        assume(secondarySmall)
        letters(
                "agility is an autopoeitic community embracing change\n" +
                        "               by a triple-balance between \n" +
                        "          the made, the making, and the makers"
        ).centered(800, 100).sketch()
        pause(1)

        box(leftView.all()).format(communityFormat).sketch()
        letters("autopoeitic community").centered(leftView.point(50, 5)).format(primarySmall).sketch()
        pause()
        rightOutline.load(
                """
pnCommunity
ss   a collection of *humans*
ss   agility is always about collaboration
pnAutopoeisis
ss   literally, 'self-making'
ss   coined in '79, derives from theory of biology
ss   no fixed parts
ss   no fixed structure
ss   no fixed mechanisms
""".trimIndent()
        )
        rightOutline.blurt()
        pause(1)

        rightOutline.fade()
        val communityOneLiner = letters("A continually re-organizing and \n" +
                "re-creating gathering of humans").centered(rightView.point(50, 50)).format(emphaticNormal).sketch()
        pause(1)

        communityOneLiner.fadeOut()
        letters("Embrace\nChange!").centered(leftView.point(50, 45)).format(emphaticNormal).sketch()
        pause()
        rightOutline.load(
                """
pnOld-school controls change
ss   change is seen as avoidable/controllable risk
pnNew-school sees change as the value
ss   change is the entire job
pnEmbrace change at every level
ss   in the made thing
ss   in the market and the domain
ss   in the personnel, structure, and roles
ss   in the code, and the coding
""".trimIndent())
        rightOutline.blurt()

        pause(1)
        rightOutline.fade()
        val ecOneLiner = letters("Agilists surf change!").centered(rightView.point(50, 50)).format(emphaticNormal).sketch()

        pause(1)
        ecOneLiner.fadeOut()
        assume(balanceFormat)
        outline(polygon(3, 250.0, leftView.point(50, 45)))
        assume(tertiaryNormal)
        letters("The Made").at(TopLeft(leftView.all().northwest().add(20.0, 70.0))).sketch()
        letters("The Making").at(TopRight(leftView.all().northeast().add(-20.0, 70.0))).sketch()
        letters("The Makers").centered(leftView.all().south().add(0.0, -50.0)).sketch()
        pause(1)

        rightOutline.load(
                """
ssMade: the product
ps   the features
ps   the code
ps   the infrastructure/deployment
ssMaking: how we create the made
ps   the technique
ps   the tools
ps   the flow of work
ssMakers: our team
ps   minds, bodies, +energy
ps   collaborations
ps   all things human
""".trimIndent())
        rightOutline.blurt()
        pause(1)
        rightOutline.fade()
        val balanceOneLiner = letters("we seek to keep all three\nin the sweet spot").centered(rightView.point(50, 50)).sketch()
        pause()
    }

    private fun techniques() {
        wipe()
        header("Parts of the Process")
        outlineAppear()
        pause()
    }

    private fun workingWithStories() {
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
        pause()
    }

    private fun storiesAndDefinition() {
        wipe()
        header("Working With Stories")
        madeMakingMaker()
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
        pause()
    }

    private fun madeMakingMaker() {
        val column = viewport.nested(85, 10, 100, 90)
        ovalLetters("Made").format(mFormat).centered(column.point(50, 0)).appear()
        ovalLetters("Making").format(mFormat).centered(column.point(50, 50)).appear()
        ovalLetters("Makers").format(mFormat).centered(column.point(50, 100)).appear()
        pause()
    }

    private fun tdd() {
        wipe()
        header("Test-Driven Development")
        outline.load(
                """
psWe change the code in a specific way
ss   red - test that it doesn't work
ss   green - make it work
ss   refactor - optimize its changeability
psMicrotests
ss   very small, one function or logic branch
ss   very fast, mostly 1 or 2 ms
ss   easy to write, easy to read
ss   run many times per developer per day
ss   first-class code

""".trimIndent())
        outline.blurt()
        pause()
    }

    private fun tddAndDefinition() {
        wipe()
        header("TDD and the Three M's")

        val madeGrid = viewport.area(0, 8, 33, 50)
        letters("The Made").at(TopLeft(madeGrid.northwest().add(0.0, -60.0))).format(primaryNormal).appear()
        val madeFlow = Flow(world, madeGrid)

        val makingGrid = viewport.area(33, 8, 66, 50)
        letters("The Making").at(TopLeft(makingGrid.northwest().add(0.0, -60.0))).format(primaryNormal).appear()
        val makingFlow = Flow(world, makingGrid)

        val makersGrid = viewport.area(66, 8, 100, 50)
        letters("The Makers").at(TopLeft(makersGrid.northwest().add(0.0, -60.0))).format(primaryNormal).appear()
        val makersFlow = Flow(world, makersGrid)

        madeFlow.load(
                """
ss  production speed
ss  SOLID design
""".trimIndent()
        )

        makingFlow.load(
                """
ss  tight cycles
ss  no regression ?'s
ss  executable documents
""".trimIndent()
        )
        makersFlow.load(
                """
ss  narrow bandwidth
ss  food pellets
ss  fearlessness
""".trimIndent()
        )

        madeFlow.blurt()
        makingFlow.blurt()
        makersFlow.blurt()

        val commentFlow = Flow(world, viewport.area(0, 40, 0, 100))
        commentFlow.load(
                """
enTDD is a productivity technique
pn   don't let "test" fool you
pn   if the value involves changing branching logic in code, TDD works
enTDD is not easy
pn   it is very different: find people to help you learn it
                """.trimIndent()
        )
        commentFlow.blurt()
        pause()
    }

    private fun theMeetings() {
        wipe()
        header("The Meetings")
        val lines =
                """
pnStandup
ss   daily, same time, same place, n*45 seconds
ss   mission: what's changed, and how do we help with it?
ss   not a status report, a sharing
ss   one-sentence answer or schedule a focus
pnFocus
ss   voluntary session about a topic: law of two feet
ss   problem-solving, free association, ask-and-answer
pnRetrospective
ss   weekly, half-hour
ss   mission: what's the nearest smallest owwie we can fix?
ss   wildly varying structure & leadership
ss   "permission to speak freely"
""".trimIndent()

        outline.load(lines)
        outlineAppear()
        pause()
    }

    private fun theMeetingsAndDefinition() {
        wipe()
        header("Meetings and the Three M's")
        val lines =
                """
pnThe Made
ss   mostly focus:
ss       blue-skying
ss       standard-setting
ss       architecture-level problem-solving
pnThe Making
ss   standups help us organize the day
ss   focus sessions are all about the making
ss   retrospectives help us know how the making goes
pnThe Maker
ss   retrospectives are maker-centric
ss   they seek to find and fix failures in our way of working
""".trimIndent()

        outline.load(lines)
        outlineAppear()
        pause()
    }

    private fun sprints() {
        wipe()
        header("Workflows: Sprints/Pull & Swarm")
        val lines =
                """
pnSprints
ss   short (1-2 week) cycles
ss   sizing & choosing stories in advance
ss   flexible WIP loads
ss   per-cycle grooming, planning, and review meetings
ss   work-load shape often one-person, sometimes assigned
pnPull & Swarm
ss   originated in experiments with one-day then half-day sprints
ss   no cycle: we finish one story and pull another
ss   no formal estimation: all stories are same-sized (using focus sessions)
ss   maximize the number of people on one story at a time
ss   frequent (not mandatory) pairing & mobbing
ss   minimize "meetings"
""".trimIndent()

        outline.load(lines)
        outlineAppear()
        pause()
    }

    private fun sprintsAndTheDefinition() {
        wipe()
        header("Workflows & The Three M's")
        val madeGrid = viewport.area(0, 8, 33, 50)
        letters("The Made").at(TopLeft(madeGrid.northwest().add(0.0, -60.0))).format(primaryNormal).appear()
        val madeFlow = Flow(world, madeGrid)

        val makingGrid = viewport.area(33, 8, 66, 50)
        letters("The Making").at(TopLeft(makingGrid.northwest().add(0.0, -60.0))).format(primaryNormal).appear()
        val makingFlow = Flow(world, makingGrid)

        val makersGrid = viewport.area(66, 8, 100, 50)
        letters("The Makers").at(TopLeft(makersGrid.northwest().add(0.0, -60.0))).format(primaryNormal).appear()
        val makersFlow = Flow(world, makersGrid)

        madeFlow.load(
                """
ss  distributed risk
ss  steady improvement
ss  higher confidence
""".trimIndent()
        )
        makingFlow.load(
                """
ss  just-in-time value-creation
ss  responsive steering
ss  real reportable progress
ss  (p&s) minimal WIP
""".trimIndent()
        )
        makersFlow.load(
                """
ss  narrow bandwidth
ss  sufficient unto the day
ss  large-scale rhythm
ss  (p&s) high knowledge-diffusion
""".trimIndent()
        )

        madeFlow.blurt()
        makingFlow.blurt()
        makersFlow.blurt()

        val commentFlow = Flow(world, viewport.area(0, 40, 0, 100))
        commentFlow.load(
                """
enThe large scale is about our desire to:
pn   steadily add value
pn   track that it's being added
pn   react to changing markets
pn   avoid pre-committing to uncertainty
                """.trimIndent()
        )
        commentFlow.blurt()
        pause()
    }

    private fun ciAndCd() {
        wipe()
        header("Continuous Integration & Continuous Deployment")
        val lines =
                """
pnContinuous Integration (CI)
ss   very frequent (<hourly normally) integrations
ss   branchless variant: pull, push, test, ship at head
ss   implies continuous code review, e.g. pairing/mobbing
ss   automated tests running independently, monitored always
pnContinuous Deployment
ss   actually go-live at will from head
ss   web-sites literally deploy, apps auto- or user-update
ss   all barriers-to-shipping are automated and tested
""".trimIndent()
        outline.load(lines)
        outlineAppear()
        pause()
    }

    private fun ciCdAndDefinition() {
        wipe()
        header("CI/CD and the Three M's")
        val lines =
                """
pnThe Made
ss   biggest impact: time-to-market
ss   maximally "lived-in" by deployment time
ss   high configurability -- at-will change
ss   risk is minimized & distributed
pnThe Making
ss   step-wise approach emphasized for value-definers
ss   every line could go-live: very different code
ss   every line could go-live: tests become urgent
pnThe Maker
ss   raises discipline & skill by direct feedback
ss   increases motivation by rhythm and purpose
""".trimIndent()

        outline.load(lines)
        outlineAppear()
        pause()
    }

    private fun threeMsRedux() {
        wipe()
        header("CI/CD and the Three M's")
        val lines =
                """
pnThe Made
ss   biggest impact: time-to-market
ss   maximally "lived-in" by deployment time
ss   high configurability -- at-will change
ss   risk is minimized & distributed
pnThe Making
ss   step-wise approach emphasized for value-definers
ss   every line could go-live: very different code
ss   every line could go-live: tests become urgent
pnThe Maker
ss   raises discipline & skill by direct feedback
ss   increases motivation by rhythm and purpose
""".trimIndent()

        outline.load(lines)
        outlineAppear()
        pause()
    }

    private fun collaborationIsCritical() {
        wipe()
        header("Optimize For Collaboration")
        val lines =
                """
pn*Everything* agile depends on the quality of our human interaction
ss   emphasize actual conversation
ss   build consensus outside of meetings
ss   encourage haykumeer protocols
ss   vary everything about meetings -- time, timing, leadership, format
pnWe collaborate because we are different
ss   different paths, strengths, weaknesses, & above all: ideas
ss   our success depends on the steady flow of ideas
ss   when we all have the same idea, what do we have? one idea
pnMakers includes lots of different skillsets
ss   be sure to include your value-definers: they're makers, too
ss   cross-function pairing & mobbing is normal for agility
""".trimIndent()

        outline.load(lines)
        outlineAppear()
        pause()
    }


    fun iterateAndIncrementProcess() {
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
        pause()
    }

    fun health() {
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
        pause()
    }

    private fun outlineAppear() {
        buildChord()
        for (i in 0 until outline.size()) {
            outline.letters(i).appear()
        }
        endChord()
    }

    fun header(text: String) {
        letters(text).format(headerFormat).at(TopRight(master.point(100 - INSET, INSET))).called("header").appear()
    }

    private fun headerEnd(end: String) {
        letters(end).format(secondaryJumbo).at(RightOf(actor("header").entrance())).sketch()
    }

    internal fun polygon(sides: Int, radius: Double, at: Point): List<Point> {
        val result = mutableListOf<Point>()
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
