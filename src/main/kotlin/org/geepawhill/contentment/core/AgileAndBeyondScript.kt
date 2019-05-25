package org.geepawhill.contentment.core

import javafx.scene.paint.Paint
import javafx.scene.text.Font
import javafx.scene.text.FontPosture
import org.geepawhill.contentment.actor.Appearance
import org.geepawhill.contentment.actors.Art
import org.geepawhill.contentment.actors.Connector
import org.geepawhill.contentment.flow.Color
import org.geepawhill.contentment.flow.Flow
import org.geepawhill.contentment.flow.FormatTable
import org.geepawhill.contentment.flow.Size
import org.geepawhill.contentment.format.Format
import org.geepawhill.contentment.geometry.Point
import org.geepawhill.contentment.geometry.PointPair
import org.geepawhill.contentment.geometry.ViewPort
import org.geepawhill.contentment.grid.Grid
import org.geepawhill.contentment.player.Script
import org.geepawhill.contentment.position.*
import org.geepawhill.contentment.step.ScriptBuilder
import org.geepawhill.contentment.style.Dash
import org.geepawhill.contentment.style.Frames
import org.geepawhill.contentment.style.TypeFace
import org.geepawhill.contentment.utility.JfxUtility
import org.geepawhill.contentment.utility.JfxUtility.color
import kotlin.random.Random

class AgileAndBeyondScript : ScriptBuilder<AgileAndBeyondScript>() {

    val human = "Human"
    val collaboration = "obsess over human collaboration"
    val betters = "braid me-iBetters and team-iBetters"

    val local = "Local"
    val enof = "easiest nearest owwie first"
    val habit = "find the habit of change"

    val iterative = "Iterative"
    val notWrong = "choose definitely not backwards"
    val tryDifferent = "try different, not harder"

    val taken = "Taken"
    val createExperiences = "create experiences, not arguments"
    val gatherResults = "gather reactions not numbers"


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
    private val emphatic: Paint = formats.emphatic
    private val tertiaryNormal: Format
    private val headerFormat: Format

    private val primarySmall = formats.get(Size.Small, Color.Primary)

    private val primaryLine = Format(formats.get(Size.Small, Color.Primary), Frames.frame(formats.primary, 3.0, .7))
    private val secondaryLine = Format(formats.get(Size.Normal, Color.Secondary), Frames.frame(formats.secondary, 3.0, .7))
    private val tertiaryLine = Format(formats.get(Size.Normal, Color.Tertiary), Frames.frame(formats.tertiary, 3.0, .7))

    val individualLine = primaryLine
    val teamLine = secondaryLine
    private val master = Grid()
    private val viewport = master.nested(INSET, 15, 100 - INSET, 100 - INSET)
    private val leftView = master.nested(INSET, 25, 47, 100 - INSET)
    private val rightView = master.nested(53, 25, 100 - INSET, 100 - INSET)
    private val rightOutline = Flow(world, rightView.all())

    private val quarterView = master.nested(20, 25, 100 - INSET, 100 - INSET)
    private val quarterOutline = Flow(world, quarterView.all())


    private val outline: Flow

    init {
        // super(new MediaRhythm(new
        // File("D:\\GeePawHillDotOrg\\videos\\lumpOfCoding\\lumpOfCodingFaceover.mp4")));
        // new SimpleRhythm());

        outline = Flow(world, viewport.all())

        color(119, 187, 65)
        color(177, 140, 254)

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
        scene(0)
        leadIn()
        welcome()
        alice()
        anotherWay()
        humans()
        collaboration()
        iBetters()
        tBetters()
//        workflowsAndDefinition()
//        theMeetings()
//        theMeetingsAndDefinition()
//        ciAndCd()
//        ciCdAndDefinition()
//        themes()
//        change()
//        theQuestion()
//        geepaw()
        end()
        return script
    }


    private fun leadIn() {
        buildChord()
        letters("GeePaw's Notebook:").format(primaryJumbo).at(TopLeft(XMARGIN, YMARGIN)).called("header").appear()
        assume(secondaryJumbo)
        letters("Change Changing").at(TopLeft(100.0, 300.0)).appear()
        assume(secondaryNormal)
        letters("How To Make Effective Transformations").at(TopLeft(100.0, 400.0)).appear()
        assume(emphaticSmall)
        letters("Copyright (C) 2019, GeePawHill. All rights reserved.").at(TopLeft(20.0, 825.0)).appear()
        endChord()
        pause()
        wipe()
    }

    private fun welcome() {
        wipe()
        assume(emphaticJumbo)
        letters("Welcome").centered(800, 250).appear()
        letters("Professional").centered(800, 350).called("#pro").appear()
        letters("Software Development").centered(800, 450).called("#sw").appear()
        letters("Coaches!").centered(800, 550).appear()
        pause()
        cross("#pro", 300.0, 40.0, 0.0, 0.0).sketch()
        pause()
        cross("#sw", 600.0, 40.0, 0.0, 0.0).sketch()
        pause()
        oval(PointPair(660.0, 500.0, 940.0, 610.0)).format(primaryLine).sketch()
        pause()

        assume(primaryJumbo)
        letters("Coaches Change Teams!").centered(800, 700).sketch()
        pause()
        wipe()
    }

    private fun alice() {

        header("Alice, A Vision, and Change")
        assume(secondaryNormal)
        pause()
        val changeY = 250
        letters("A City On\n   A Hill").centered(1400, changeY).sketch()
        pause()
        letters("  South\nCraptown").centered(200, changeY).sketch()
        pause()
        val change = connector()
        change.actor.from(280, changeY).to(1290, changeY, true)
        change.format(primaryLine).sketch(3000.0)
        pause()

        val fours = viewport.nested(5, 40, 95, 95)
        val col1 = fours.nested(2, 2, 24, 100)
        assume(primaryNormal)
        letters("Procedural").at(TopLeft(col1.nw())).sketch()
        val proc = connector()
        proc.actor.from(col1.nw().add(0.0, 60.0)).to(col1.ne().add(-80.0, 60.0))
        proc.sketch()

        val col2 = fours.nested(26, 2, 48, 100)
        assume(primaryNormal)
        letters("Given").at(TopLeft(col2.nw())).sketch()
        val given = connector()
        given.actor.from(col2.nw().add(0.0, 60.0)).to(col2.ne().add(-80.0, 60.0))
        given.sketch()

        val col3 = fours.nested(52, 2, 72, 100)
        assume(primaryNormal)
        letters("Sweeping").at(TopLeft(col3.nw())).sketch()
        val sweeping = connector()
        sweeping.actor.from(col3.nw().add(0.0, 60.0)).to(col3.ne().add(-80.0, 60.0))
        sweeping.sketch()

        val col4 = fours.nested(76, 2, 98, 100)
        assume(primaryNormal)
        letters("Final").at(TopLeft(col4.nw())).sketch()
        val final = connector()
        final.actor.from(col4.nw().add(0.0, 60.0)).to(col4.ne().add(-80.0, 60.0))
        final.sketch()

        pause()
        val procList = Flow(world, col1.nested(0, 20, 100, 100).all())
        procList.load(
                """
                    ssmeetings
                    ssartifacts
                    ssrules
                    ssmechanics
                    ssalgorithmic
                """.trimIndent()
        )
        procList.blurt()
        pause()

        val givenList = Flow(world, col2.nested(0, 20, 100, 100).all())
        givenList.load(
                """
                    ssexternal
                    ssmeasured
                    ssreported
                """.trimIndent()
        )
        givenList.blurt()
        pause()

        val sweepingList = Flow(world, col3.nested(0, 20, 100, 100).all())
        sweepingList.load(
                """
                    ssorg-wide
                    ssmulti-aspect
                    ssfar reaching
                    ssall now
                """.trimIndent()
        )
        sweepingList.blurt()
        pause()

        val finalList = Flow(world, col4.nested(0, 20, 100, 100).all())
        finalList.load(
                """
                    ssendpointed
                    ssperfect
                    ssfinished
                """.trimIndent()
        )
        finalList.blurt()
        pause()


        buildChord()
        fade(.2, 3000.0)
        letters("Thereby very likely\n     creating a\n   collosal failure\n          on an\nintergalactic scale!").format(formats[Size.Gigantic, Color.Emphatic]).centered(viewport.all().center()).fadeIn(3000.0)
        endChord()
        pause()
        wipe()
    }

    private fun anotherWay() {
        header("$human, $taken, $local, $iterative")
        outline.load(
                "pn$human\n" +
                        "ss    $collaboration\n" +
                        "ss    $betters\n" +
                        "pn$taken\n" +
                        "ss    $createExperiences\n" +
                        "ss    $gatherResults\n" +
                        "pn$local\n" +
                        "ss    $enof\n" +
                        "ss    $habit\n" +
                        "pn$iterative\n" +
                        "ss    $notWrong\n" +
                        "ss    $tryDifferent\n")
        outline.blurt()
        pause()
        wipe()
    }

    private fun humans() {
        wipe()
        header("Procedural -> Human")
        outline.load(
                """
pnSoftware development is always a mixed system
ss    humans
ss    not-humans: procedures, methods, artifacts, forms
pnProcedures are powerful, but humans are super-powerful
pn    For good...
ss        old-school accident investigation (via sydney dekker)
ss        "operator error" vs "system error"
ss        humans are the primary source of safety in mixed systems
pn    For ill...
ss        did you ever hear of a work-to-rule strike?
ss        (or a teenager?)
                """.trimIndent()
        )
        outline.blurt()

        pause()
        assume(emphaticJumbo)
        letters("\"Lean In\" To The Humans!").centered(800, 650).sketch()
        pause()
        wipe()
    }

    private fun collaboration() {
        wipe()
        header("Obsess Over Collaboration")
        outline.load(
                """
pn"Collaboration"
ss   frequent focused direct dialog (f2d2)
ss   dialog, simple conversation, beats all others
pnVariety is key
ss   mode: solo, paired, mobbed, meeting
ss   space: small, large, bustling, quiet
ss   structure: planned, haykumeer,
                """.trimIndent())
        outlineAppear()
        assume(emphaticJumbo)
        letters("*humans* make procedures work").centered(800, 775).appear()
        pause()
        wipe()
    }

    private fun iBetters() {
        assume(secondarySmall)

        val individual = art("agileandbeyond/blueBall.png", 50.0).centered(leftView.point(50, 80)).called("#individual")
        val connectors = mutableListOf<Appearance<Connector>>()
        val betters = mutableListOf<Appearance<Art>>()

        for (i in 1..5) {
            val c = connector()
            connectors += c
            c.format(individualLine)
            val target = leftView.point(i * 17, 24)
            c.actor.from("#individual").to(target, true)
            betters += art("agileandbeyond/blueBall.png", 50.0).centered(leftView.point(i * 17, 22))
        }

        rightOutline.load("""
            pn"better"
            ss    stronger, faster, kinder
            ss    smarter, more articulate
            ss    as geek, teacher, manager, parent, person
            pn"i-better"
            ss    this is a "better" for an individual
            ss    whatever that might mean to her
            pnremarkable facts
            ss    there are lots of them!
            ss    we have different sets: no two exactly alike
            ss    we love feeling we're moving along an i-better

        """.trimIndent())

        letters(
                "i create or exploit openings through which \n" +
                        "            individuals can take\n" +
                        "           small steps closer to\n" +
                        "      who or how they wish they were\n"

        ).centered(800, 130).fadeIn()
        pause()

        buildChord()
        individual.sketch()
        letters("individual").centered(leftView.point(50, 90)).sketch()
        endChord()
        pause()

        buildChord()
        betters[2].appear()
        connectors[2].sketch(1000.0)
        endChord()
        pause()

        for (i in 0..3) rightOutline.letters(i).sketch()
        pause()

        letters("i-better").centered(leftView.point(50, 10)).sketch()
        for (i in 4..6) rightOutline.letters(i).sketch()
        pause()

        buildChord()
        for (i in 0..4) {
            if (i == 2) continue
            betters[i].fadeIn(1000.0)
            connectors[i].fadeIn((1000.0))
        }
        endChord()
        for (i in 7..10) rightOutline.letters(i).sketch()
        pause()
        wipe()
    }

    private fun tBetters() {
        assume(secondarySmall)
        header("Teams Have T-Betters")

        val team = art("agileandbeyond/redBall.png", 150.0).centered(viewport.point(50, 80)).called("#team")
        val connectors = mutableListOf<Appearance<Connector>>()
        val betters = mutableListOf<Appearance<Art>>()
        for (i in 1..5) {
            val c = connector()
            connectors += c
            c.format(teamLine)
            val target = viewport.point((i * 17) + 2, 29)
            c.actor.from("#team").to(target, true)
            betters += art("agileandbeyond/redBall.png", 150.0).centered(viewport.point(i * 17, 22)).called("#b$i")
        }


        buildChord()
        team.appear()
        for (i in 0..4) {
            betters[i].fadeIn()
            connectors[i].fadeIn()
        }
        letters("    team").centered(viewport.point(50, 90)).sketch()
        endChord()
        pause()



        buildChord()
        actor("header").disappear()
        header("Teams Have T-Betters and Individuals")
        art("agileandbeyond/blueBall.png", 50.0).centered(viewport.point(21, 81)).called("#i1").fadeIn(1000.0)
        art("agileandbeyond/blueBall.png", 50.0).centered(viewport.point(71, 65)).called("#i2").fadeIn(1000.0)
        for (i in 1..7) {
            val randomX = Random.nextInt(20, 80)
            val randomY = Random.nextInt(50, 90)
            art("agileandbeyond/blueBall.png", 50.0).centered(viewport.point(randomX, randomY)).called("#ix$i").fadeIn(1000.0)

        }
        endChord()
        pause()

        buildChord()
        for (i in 1..7) actor("#ix$i").fadeOut()

        endChord()

        art("agileandbeyond/blueBall.png", 50.0).centered(viewport.point(36, 24)).called("#ib1").fadeIn()
        val ibetter1 = connector()
        ibetter1.actor.from("#i1").to("#ib1", true)
        ibetter1.format(individualLine).sketch(1000.0)

        val ibetter2 = connector()
        ibetter2.actor.from("#i2").to("#ib1", true)
        ibetter2.format(individualLine).sketch(1000.0)

        buildChord()
        for (i in 0..4) {
            if (i == 1) continue
            betters[i].fadeOut()
            connectors[i].fadeOut()
        }
        endChord()
        pause()

        wipe()
    }

    private fun change() {
        header("Change, Old White Guys, and Socrates")
        pause()
        val needleman = art("agileandbeyond/jacobNeedleman.png", 250.0).at(TopLeft(viewport.nw())).fadeIn(3000.0)
        val caption = letters("Jacob Needleman").at(BelowCenter(needleman)).appear()
        art("agileandbeyond/heartOfPhilosophy.jpg", 250.0).at(BelowLeft(caption)).fadeIn(3000.0)
        pause()
        letters("In his book, Needleman describes losing his sense of joy, ").at(TopLeft(quarterView.nw())).sketch(2000.0)
        pause()
        wipe()
    }

    private fun theQuestion() {
        letters("What do we change next?").centered(ViewPort.CENTER).fadeIn(5000.0)
        pause()
        pause()
    }


    private fun workflowsAndDefinition() {
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
ss  good tracking & reporting
ss  large-scale cycle
ss  *minimum WIP
ss  *maximum flow
""".trimIndent()
        )
        makersFlow.load(
                """
ss  narrow bandwidth
ss  sufficient unto the day
ss  *knowledge-diffusion
""".trimIndent()
        )

        madeFlow.blurt()
        makingFlow.blurt()
        makersFlow.blurt()

        val commentFlow = Flow(world, viewport.area(0, 40, 0, 100))
        commentFlow.load(
                """
enSprints are largely aimed "upward"
pn   track what value being added and how fast
pn   provide convenient checkpoints
pn   many practitioners have switched to p&s
enThe key: keep it as light as will possibly work
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
        header("The Meetings and the Three M's")

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
""".trimIndent()
        )

        makingFlow.load(
                """
ss  just-in-time help
ss  non-intrusive status
""".trimIndent()
        )
        makersFlow.load(
                """
ss  narrow bandwidth
ss  knowledge diffusion
ss  power-building
""".trimIndent()
        )

        madeFlow.blurt()
        makingFlow.blurt()
        makersFlow.blurt()

        val commentFlow = Flow(world, viewport.area(0, 35, 0, 100))
        commentFlow.load(
                """
enThe standup is for us, not others
pn   we use it to stay in touch
pn   we use it to target force
pn   we use it to avoid blackouts
enThe retro is for us, not others
pn   power & trust come from small consecutive wins
pn   always fix the easiest universally agreed owwie first
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
ss  ship at will
ss  in-use confidence
""".trimIndent()
        )

        makingFlow.load(
                """
ss  toggles
ss  factory/strategy
ss  side-by-side
""".trimIndent()
        )
        makersFlow.load(
                """
ss  narrow bandwidth
ss  merges disappear
ss  *always-at-head
""".trimIndent()
        )

        madeFlow.blurt()
        makingFlow.blurt()
        makersFlow.blurt()

        val commentFlow = Flow(world, viewport.area(0, 35, 0, 100))
        commentFlow.load(
                """
enCI/CD is complex technique
pn   jenkins first, test brushfires real, confident toggles, branchless
pn   developers don't know how to toggle well: we will have to learn
enThe power of working at head is enormous
pn   we find problems much sooner
pn   we distribute merge/integration pain
pn   we ship when it's convenient for the market
                """.trimIndent()
        )
        commentFlow.blurt()
        pause()
    }

    private fun themes() {
        wipe()
        buildChord()
        header("Recurring themes")
        assume(secondaryJumbo)
        outline.load(
                """
pnNarrowing mental scope is almost always a win
ss   juggle less instead of breaking biological barriers with will
ss   WIP and scope wins are universal
pnWe run towards change instead of away from it
ss   at every level, change is about value
ss   agility itself has changed and will again
pnNone of this is about intellectual purity
ss   we are solving *this* problem, not all problems
ss   we are creating sustainable software organizations
""".trimIndent()
        )
        outlineAppear()
        endChord()
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

    override fun downcast(): AgileAndBeyondScript {
        return this
    }

    companion object {

        private val INSET = 3
        private val XMARGIN = 20.0
        private val YMARGIN = 20.0
    }
}
