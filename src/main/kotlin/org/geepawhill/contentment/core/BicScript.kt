package org.geepawhill.contentment.core

import javafx.scene.text.Font
import javafx.scene.text.FontPosture
import org.geepawhill.contentment.actor.Appearance
import org.geepawhill.contentment.actors.Art
import org.geepawhill.contentment.actors.Connector
import org.geepawhill.contentment.actors.Letters
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
import org.geepawhill.contentment.position.RightOf
import org.geepawhill.contentment.position.TopLeft
import org.geepawhill.contentment.position.TopRight
import org.geepawhill.contentment.step.ScriptBuilder
import org.geepawhill.contentment.style.Frames
import org.geepawhill.contentment.style.TypeFace
import org.geepawhill.contentment.utility.JfxUtility
import kotlin.random.Random

class BicScript : ScriptBuilder<BicScript>() //MediaRhythm(File("C:\\GeePawHillDotOrgWip\\wip\\ccs talks\\Beauty In Code 2020\\bic2020-raw-tonal.mp4"))) {
{

    private val formats = FormatTable()


    private val primaryJumbo = formats.get(Size.Jumbo, Color.Primary)
    private val primaryNormal = formats.get(Size.Normal, Color.Primary)
    private val primarySmall = formats.get(Size.Small, Color.Primary)

    private val secondaryJumbo = formats.get(Size.Jumbo, Color.Secondary)
    private val secondaryNormal = formats.get(Size.Normal, Color.Secondary)
    private val secondarySmall = formats.get(Size.Small, Color.Secondary)

    private val TertiaryJumbo = formats.get(Size.Jumbo, Color.Tertiary)

    private val emphaticGigantic = formats.get(Size.Gigantic, Color.Emphatic)
    private val emphaticJumbo = formats.get(Size.Jumbo, Color.Emphatic)
    private val emphaticNormal = formats.get(Size.Normal, Color.Emphatic)
    private val emphaticSmall = Format(formats.get(Size.Small, Color.Emphatic), Frames.frame(formats.emphatic, 3.0, .7))

    private val header = Font.font("Chewed Pen BB", FontPosture.ITALIC, 65.0)
    private val headerFormat = Format(primaryJumbo, TypeFace.font(header, 3.0, 1.0),
            TypeFace.color(JfxUtility.color(180, 180, 180), 1.0))

    private val primaryLine = Format(formats.get(Size.Small, Color.Primary), Frames.frame(formats.primary, 3.0, .7))
    private val secondaryLine = Format(formats.get(Size.Normal, Color.Secondary), Frames.frame(formats.secondary, 3.0, .7))
    private val tertiaryLine = Format(formats.get(Size.Normal, Color.Tertiary), Frames.frame(formats.tertiary, 3.0, .7))

    val individualLine = primaryLine
    val teamLine = secondaryLine
    private val master = Grid()
    private val viewport = master.nested(INSET, 15, 100 - INSET, 100 - INSET)
    private val leftView = master.nested(INSET, 15, 47, 100 - INSET)
    private val rightView = master.nested(53, 15, 100 - INSET, 100 - INSET)
    private val rightOutline = Flow(world, rightView.all())
    private val leftOutline = Flow(world, leftView.all())

    private val quarterView = master.nested(20, 25, 100 - INSET, 100 - INSET)
    private val quarterOutline = Flow(world, quarterView.all())
    private val baseOutline = Flow(world, viewport.area(0, 80, 100, 100))

    private var nextHeader = 0

    private val outline: Flow

    init {
        //super()
        // super(new MediaRhythm(new
        // File("D:\\GeePawHillDotOrg\\videos\\lumpOfCoding\\lumpOfCodingFaceover.mp4")));
        // new SimpleRhythm());

        outline = Flow(world, viewport.all())


    }

    fun make(): Script {
        pause()
        scene(0)
        cycledHeader(0, "Hello, Beauty In Code 2020!")
        cycledHeader(22, "Thank You!")
        cycledHeader(35, "Thanks, Alastair!")
        cycledHeader(46, "I Appreciate Living.It!")
        cycledHeader(62, "Thank You So Much, Martin Stenlund!")
        cycledHeader(96, "About GeePaw")
        cycledHeader(130, "Programmer, Coach, Consultant")
        cycledHeader(162, "Harvesting Value From Change")
        cycledHeader(185, "Changing Changing Software")
        cycledHeader(200, "The Why")
        cycledHeader(210, "The Trade is Terrific!")
        cycledHeader(237, "Software's Everywhere, And Lucrative")
        cycledHeader(258, "The Trade is Terrible!")
        cycledHeader(301, "Let's Change This")
        cycledHeader(318, "Changing Changing Software")
        cycledHeader(334, "Dynamic Unity")
        cycledHeader(349, "Unity -- One Thing")
        cycledHeader(366, "Dynamic -- Constant Change")
        cycledHeader(380, "Always The Same, Always Different")
        cycledHeader(411, "You Are A Dynamic Unity")
        cycledHeader(436, "Dynamic You")
        cycledHeader(455, "True When We Zoom Out")
        cycledHeader(477, "Cycles & Sequences")
        cycledHeader(504, "Lifelong Continuous Change")
        cycledHeader(525, "And Your Mind, Too")
        cycledHeader(540, "Are You The Same?")
        cycledHeader(555, "What's The Change Doing?")
        cycledHeader(580, "Change, Harvest, Change, Harvest")
        cycledHeader(605, "Your Organization Is A Dynamic Unity")
        cycledHeader(635, "The How")
        cycledHeader(655, "The City On The Hill")
        cycledHeader(680, "South Craptown")
        cycledHeader(695, "Procedural")
        cycledHeader(713, "Global")
        cycledHeader(725, "Precisely Targeted")
        cycledHeader(744, "Given")
        cycledHeader(760, "Final")
        cycledHeader(787, "Whoops!")
        cycledHeader(799, "The Wrong Way")
        cycledHeader(815, "The Change-Harvester's How")
        cycledHeader(825, "Human, Local, Oriented, Taken, Iterative")
        cycledHeader(840, "Human Change")
        cycledHeader(860, "Individual, Group, Culture, Species")
        cycledHeader(880, "Direct Social Interaction")
        cycledHeader(900, "Ordinary Conversation")
        cycledHeader(910, "Don't Discount Yourself")
        cycledHeader(945, "The Human Mind")
        cycledHeader(960, "Rhythm")
        cycledHeader(978, "Why *Human* Change?")
        cycledHeader(990, "Humans Determine Success & Failure")
        cycledHeader(1022, "Humans Are Superpowers")
        cycledHeader(1035, "Local Change")
        cycledHeader(1050, "Neighborhood: Minds, Situation, Ability, Time")
        cycledHeader(1069, "No, Smaller Please")
        cycledHeader(1079, "Why *Local* Change?")
        cycledHeader(1115, "Oriented Change")
        cycledHeader(1130, "How To Choose?")
        cycledHeader(1150, "Not Terribly Wrong")
        cycledHeader(1174, "Act Then Look")
        cycledHeader(1183, "Why *Oriented* Change?")
        cycledHeader(1200, "Optimizing The Wrong Choice")
        cycledHeader(1218, "Multivalence")
        cycledHeader(1235, "Many Kinds of Value")
        cycledHeader(1250, "Better Is Better")
        cycledHeader(1270, "Rhythm Is Better")
        cycledHeader(1290, "Steering Is Better")
        cycledHeader(1304, "Locked In")
        cycledHeader(1318, "Accuracy Over Distance")
        cycledHeader(1327, "A Slope and A Nose-Dive")
        cycledHeader(1350, "Nonsense")
        cycledHeader(1386, "Taken Change")
        cycledHeader(1400, "Work With What's There")
        cycledHeader(1420, "Greenfield: The Blank Page")
        cycledHeader(1455, "Brownfield: The Full Page")
        cycledHeader(1479, "Green To Brown")
        cycledHeader(1502, "What's There Now Dominates")
        cycledHeader(1530, "Why *Taken* Change?")
        cycledHeader(1552, "Code: Already Shipping")
        cycledHeader(1575, "Team: Already Working")
        cycledHeader(1594, "Tacit > Explicit, Erasure Is Expensive")
        cycledHeader(1615, "Taken -> What's Already There")
        cycledHeader(1630, "Iterative")
        cycledHeader(1650, "One Turn Of The Wheel")
        cycledHeader(1680, "Behaviors & Habits Of Change")
        cycledHeader(1708, "Linear Error Epsilon Of Prediction")
        cycledHeader(1747, "Invalid In Complex Adaptive Systems")
        cycledHeader(1767, "Succeeds Under Imperfection")
        cycledHeader(1789, "Mathematics, Not Mysticism")
        cycledHeader(1820, "Embrace Change")
        cycledHeader(1840, "Lowered Stakes")
        cycledHeader(1852, "The Cost Of Prediction")
        cycledHeader(1860, "Human, Local, Oriented, Taken, Iterative")
        cycledHeader(1872, "Extreme Programming (XP)")
        cycledHeader(1890, "Storying, TDD, Pairing")
        cycledHeader(1909, "One Story At A Time: Local")
        cycledHeader(1937, "One Story At A Time: Iterative")
        cycledHeader(1955, "TDD & Refactoring")
        cycledHeader(1981, "Red, Green, Refactor, Push")
        cycledHeader(2000, "TDD: Human Bandwidth")
        cycledHeader(2017, "TDD: Human Rhythm")
        cycledHeader(2040, "Refactoring: Human Understanding")
        cycledHeader(2084, "Refactoring: Taken")
        cycledHeader(2094, "TDD: Micro-Local & Iterative")
        cycledHeader(2091, "TDD: Micro-Local")
        cycledHeader(2112, "Pairing: Human Social Nature")
        cycledHeader(2134, "Beyond Programming")
        cycledHeader(2156, "Human, Local, Oriented, Taken, Iterative")
        cycledHeader(2202, "Cart Before The Horse")
        cycledHeader(2228, "We Have To Change")
        cycledHeader(2252, "The Cart Is The Horse, The Horse Is The Cart")
        cycledHeader(2280, "What To Change: How To Change")
        cycledHeader(2303, "Change Changing Software")
        cycledHeader(2314, "Human, Local, Oriented, Taken, Iterative")

//
//        leadIn()
//        geepaw()
//        theTrade()
//        changingChangingSoftware()
//        dynamic()
//        unity()
//        dynamicUnity()
//        you()
//
//        howToChange()
//
//        human()
//        whyHuman()
//        humanCases()
//
//        local()
//        whyLocal()
//        localCases()
//
//        oriented()
//        whyOriented()
//        orientedCases()
//
//        taken()
//        whyTaken()
//        takenCases()
//
//        iterative()
//        whyIterative()
//        iterativeCases()
//
//        cartBeforeHorse()
//        leadOut()
//
//        welcome()
//        alice()
//        anotherWay()
//
//        collaboration()
//        judgments()
//
//        givenToTaken()
//        experienceNotArgument()
//        iBetters()
//        tBetters()
//
//        sweepingToLocal()
//        enof()
//        tryDifferent()
//
//        finalToIterative()
//        theHabitOfChange()
//        reset()
//
//        change()
//        theQuestion()
//
        end()
        return script
    }


    private fun leadIn() {
        buildChord()
        letters("GeePaw's Notebook:").format(primaryJumbo).at(TopLeft(XMARGIN, YMARGIN)).called("header").appear()
        assume(secondaryJumbo)
        letters("Changing Changing Software").at(TopLeft(100.0, 300.0)).appear()
        assume(secondaryNormal)
        letters("An Intro to the Change-Harvester's Approach").at(TopLeft(100.0, 400.0)).appear()
        assume(emphaticSmall)
        letters("Copyright (C) 2020, GeePawHill. All rights reserved.").at(TopLeft(20.0, 825.0)).appear()
        endChord()
        pause()
        wipe()
    }

    private fun geepaw() {
        header("What's GeePaw's Story?")
        outline.load(
                "pnGeePaw Hill\n" +
                        "ss    yes, it's really GeePaw\n" +
                        "ss    a grandfather from an early age\n" +
                        "pnFour Decades in the Geek Trades\n" +
                        "ss    programmer, teacher, coach, consultant, writer, speaker\n" +
                        "ss    a lot of roles, but just one job\n"
        )
        outline.blurt()
        assume(emphaticJumbo)
        letters("helping people harvest the value of change").centered(800, 575).appear()
        assume(secondaryNormal)
        letters("in code, in individuals, in teams").centered(800, 675).appear()
        letters("in process, in s/w departments, in organizations").centered(800, 725).appear()
        pause()
        wipe()
    }

    private fun theTrade() {
        header("The Software Trade")
        leftOutline.load(
                "pjTerrific!\n" +
                        "ps    crazy growth\n" +
                        "ss        25 million developers\n" +
                        "ss        doubling every 5 years\n" +
                        "ps    money money money\n" +
                        "ss        1 trillion $/year\n" +
                        "ss        fortune 50: amazon, google, microsoft\n" +
                        "ps    present everywhere\n" +
                        "ss        computers & phones\n" +
                        "ss        cars & refrigerators\n"
        )
        leftOutline.blurt()
        pause()
        rightOutline.load(
                "pjTerrible!\n" +
                        "ss    executives: no value\n" +
                        "ss    makers: no joy\n" +
                        "ss    managers: no control\n" +
                        "ss    operations: no peace\n" +
                        "ss    security: no safety\n" +
                        "ss    designers: no usability\n" +
                        "ss    customers: no stability\n" +
                        "ss    analysts: no predictability\n" +
                        "ss    everyone: NO HAPPINESS"
        )
        rightOutline.blurt()
        pause()
        assume(emphaticJumbo)
        letters("So Which Is It?").centered(800, 800).appear()
        pause()
        wipe()
    }

    private fun changingChangingSoftware() {
        header("Changing Changing Software")
        pause()
        wipe()
    }

    private fun dynamic() {
        header("\"Dynamic\"")
        rightOutline.load(
                "pnDynamic means changing...\n" +
                        "pn...in varied modes...\n" +
                        "ss    components\n" +
                        "ss    arrangement\n" +
                        "ss    ordering\n" +
                        "pn...with varied flows...\n" +
                        "ss    sequence\n" +
                        "ss    cycle\n" +
                        "pn...from varied causes...\n" +
                        "pn...to varied effects."
        )
        rightOutline.blurt()
        pause()
        val dynamic = art("bic/dynamic.png", leftView.all().width()).at(TopLeft(leftView.nw()))
        dynamic.fadeIn()
        pause()

        wipe()
    }

    private fun unity() {
        header("\"Unity\"")
        leftOutline.load(
                "pnUnity means one thing...\n" +
                        "ss    an inside\n" +
                        "ss    an outside\n" +
                        "ss    a boundary\n" +
                        "pn...the same thing...\n" +
                        "ss    across time\n" +
                        "ss    acrross space\n" +
                        "pn...as seen by another"
        )
        leftOutline.blurt()
        pause()
        val unity = art("bic/unity.png", rightView.all().width()).at(TopLeft(rightView.nw()))
        unity.fadeIn()
        pause()
        wipe()
    }

    private fun dynamicUnity() {
        header("\"Dynamic Unity\"")
        rightOutline.load(
                "enA thing that is...\n" +
                        "ss    self-sustaining\n" +
                        "ss    self-building\n" +
                        "ss    self-extending\n" +
                        "ss    self-bounding\n" +
                        "ss    self-repairing\n" +
                        "ss    self-organizing\n" +
                        "enchange after change,\n" +
                        "enworld without end,\n"
        )
        rightOutline.blurt()
        pause()
        val dynamicUnity = art("bic/dynamicUnity.png", leftView.all().width()).at(TopLeft(leftView.nw()))
        dynamicUnity.fadeIn()
        pause()
        wipe()
    }

    private fun you() {
        header("The Nearest Dynamic Unity: You")
        pause()
        wipe()
    }

    private fun howToChange() {
        header("How To Change Things")
        outline.load(
                """
pnHuman -> relentlessly focused on the humans in our systems
ss    "leaning in" to human scale, long & weak suits, energy
pnLocal -> narrow in scope, complexity, and time
ss    the easiest change that creates value
pnOriented -> turned broadly towards a horizon of "better" 
ss    opportunistic, improvised, adaptive
pnTaken -> drawn from inside the context, not outside
ss    using, transforming, what is already there
pnIterative -> continuous, ongoing, and repetitive
ss    in full confidence we'll change it once, then change it again
                """.trimIndent()
        )
        outline.blurt()
        pause()
        wipe()
    }

    private fun human() {
        header("Human Change!")
        outline.load(
                """
pnWe want our changes to optimize the system for the humans.
ss    humans have limited mental bandwidth
es        smaller tasks, fewer balls in the air, narrower scope
es        reduced multi-tasking, rapid response to blockage
ss    humans are highly skilled at social interaction
es        emphasize direct directed dialog
es        fewer meetings, more haykumeer protocol 
ss    humans need a steady flow of tension & release (rhythm)
es        work distributed in small steps and cycles
es        orient towards "relaxed with pep"
ss    humans are alike in a few ways and different in many
es        so we'll need a culture of safety
es        beyond tolerance: actively seek difference
                """.trimIndent()
        )
        outline.blurt()
        pause()
        wipe()
    }

    private fun whyHuman() {
        header("Why Human?")
        outline.load(
                """
pnSoftware development is always a mixed system
ss    humans
ss    not-humans
ss    procedures, methods, artifacts, forms
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
        wipe()
    }

    private fun humanCases() {
        header("Human Cases")
        outline.load(
                """
pnEveryday
ss    Memorizing the menu number that will get you quickest to a human
ss    Making a short to-do list, then adding an item you've already done
                """.trimIndent()
        )
        outline.blurt()

        pause()
        wipe()
    }

    private fun local() {
        header("Local Change!")
        outline.load(
                """
pnWe want our changes to be close, small, and fast
ss   locality seeks to minimize the answers to *all* of these
es       what next?
es       when can we start?
es       how hard will it be?
es       how long will it take?
es       how soon can we tell?
ss   "No, smaller!" is the mantra
es       what is the smallest change whose effect we can detect?
                """.trimIndent()
        )
        outline.blurt()
        pause()
        wipe()
    }

    private fun whyLocal() {
        header("Why Local?")
        outline.load(
                """
pnSimply put, it's more effective all around
pnEvery small change...
ss     addresses a small issue
ss     proves facts reasoning & argumentation can't prove
ss     provides humans with narrowed scope and with rhythm
ss     is an opportunity-point, a place we can steer
ss     clears brush so larger issues can be approached
ss     builds the *habit* of change
pnUnderlying theory -> non-linear effects
ss     curves with sharp knees
ss     curves that look like an 's'
""".trimIndent()
        )
        outline.blurt()

        pause()
        wipe()
    }

    private fun localCases() {
        header("Local Cases")
        outline.load(
                """
pnSoftware development is always a mixed system
ss    humans
ss    not-humans
ss    procedures, methods, artifacts, forms
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
        wipe()
    }

    private fun oriented() {
        header("Oriented Change!")
        outline.load(
                """
pnWe want our changes turned towards the horizon of "better"
pnEvery small change...
ss     addresses a small issue
ss     proves facts reasoning & argumentation can't prove
ss     provides humans with narrowed scope and with rhythm
ss     is an opportunity-point, a place we can steer
ss     clears brush so larger issues can be approached
ss     builds the *habit* of change
pnUnderlying theory -> non-linear effects
ss     curves with sharp knees
ss     curves that look like an 's'
""".trimIndent()
        )
        outline.blurt()

        pause()
        wipe()
    }

    private fun whyOriented() {
        header("Why Oriented?")
        pause()
        wipe()
    }

    private fun orientedCases() {
        header("Oriented Cases")
        pause()
        wipe()
    }

    private fun taken() {
        header("Taken Change")
        pause()
        wipe()
    }

    private fun whyTaken() {
        header("Why Taken?")
        pause()
        wipe()
    }

    private fun takenCases() {
        header("Taken Cases")
        pause()
        wipe()
    }

    private fun iterative() {
        header("Iterative Change")
        pause()
        wipe()
    }

    private fun whyIterative() {
        header("Why Iterative")
        pause()
        wipe()
    }

    private fun iterativeCases() {
        header("Iterative Cases")
        pause()
        wipe()
    }

    private fun cartBeforeHorse() {
        header("The Cart Before The Horse?")
        pause()
        wipe()
    }

    private fun leadOut() {
        header("How To Change")
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
        header("Human, Taken, Local, Iterative")
        outline.load(
                "pnProcedural -> Human\n" +
                        "ss    obsess over human collaboration\n" +
                        "ss    seek judgments not numbers\n" +
                        "pnGiven -> Taken\n" +
                        "ss    create experiences, not arguments\n" +
                        "ss    braid i-Betters and t-Betters\n" +
                        "pnSweeping -> Local\n" +
                        "ss    easiest nearest owwie first\n" +
                        "ss    try different, not harder\n" +
                        "pnFinal -> Iterative\n" +
                        "ss    find the habit of change\n" +
                        "ss    open the team\n"
        )
        outline.blurt()
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
ss   structure: planned, impromptu, short, haykumeer
ss   roles: change leaders at random
                """.trimIndent())
        outlineAppear()
        assume(emphaticJumbo)
        letters("human collaboration makes procedures work").centered(800, 775).appear()
        pause()
        wipe()
    }

    private fun judgments() {
        wipe()
        header("Seek Judgments Not Numbers")
        val lines =
                """
pnAvoid the cult of the number
ss   dramatic reduction of information
ss   false precision
ss   target slippage
ss   automated tests running independently, monitored always
pnHumans know things they don't know
ss   ask. no, really: *ask*
ss   everyone is an expert in how they feel
pnVary your technique
ss   mini-retrospectives
ss   three-question surveys
ss   one-on-one time
""".trimIndent()
        outline.load(lines)
        outlineAppear()
        pause()
        wipe()
    }

    private fun givenToTaken() {
        header("Given -> Taken")
        pause()
        assume(primaryJumbo)
        letters("How do I get them to do\n       what I want?").centered(800, 300).sketch()
        pause()
        assume(secondaryJumbo)
        buildChord()
        letters("(I don't)").centered(800, 450).fadeIn()
        assume(emphaticGigantic)
        letters("        Get them to do\nwhat *they* want to do!").centered(800, 700).fadeIn()
        endChord()
        pause()
        wipe()
    }

    private fun experienceNotArgument() {
        header("Create Experiences, Not Arguments")
        val lines =
                """
pnNothing works to persuade like having a winning experience
ss   humans don't mostly decide what to do based on reasons
ss   they decide what to do and use reasons to justify it
pnAn experience...
ss   is a short period of a particular activity
ss   is intended to put the problem and its solution "under the fingers"
pnWays to have an experience
ss   a toy
ss   a small portion of the day-job problem & solution
ss   the full day-job problem & solution
""".trimIndent()

        outline.load(lines)
        pause()
        for (i in 0..2) outline.letters(i).appear()
        pause()
        for (i in 3..9) outline.letters(i).appear()
        pause()
        wipe()
    }

    private fun iBetters() {

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
            pni-better
            ss    this is a "better" for an individual
            ss    whatever that might mean to her
            pnremarkable facts
            ss    there are lots of them!
            ss    we have different sets: no two exactly alike
            ss    we love feeling we're moving along an i-better

        """.trimIndent())

        header("Braid I-Betters With T-Betters")
        pause()
        assume(emphaticSmall)
        letters(
                "i create or exploit openings through which\n" +
                        "individuals can take small steps closer to\n" +
                        "     who or how they wish they were"
        ).at(TopLeft(50.0, 100.0)).fadeIn()
        pause()
        assume(secondarySmall)
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

        letters("i-better").centered(leftView.point(50, 15)).sketch()
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

    private fun sweepingToLocal() {
        header("Sweeping -> Local")
        art("agileandbeyond/satir.png", 750.0).at(TopLeft(viewport.nw())).sketch()
        rightOutline.load("""pnSatir curve
ss    foreign element
ss    chaos
ss    trough
ss    transforming idea
ss    new status quo
pnHow bad is the trough?
ss    the bigger the change...
ss    ...the bigger-er the trough
ss    (wider and deeper, both!)
        """.trimIndent())
        rightOutline.blurt()
        pause()
        wipe()
    }

    private fun enof() {
        header("Easiest Nearest Owwie First (ENOF)")
        val lines =
                """
pnWhy not tackle the biggest thing holding us back?
ss   bigger problem means bigger change
ss   bigger change means bigger-er trough!
pnThe biggest thing holding most teams back?
ss   it's partly all the little things
ss   it's mostly letting the little things become that big thing
pnEasiest Nearest Owwie First
ss   what's agreed, something we all see as a pain?
ss   what's small, an hour to a week to fix?
ss   lather, rinse, repeat
""".trimIndent()

        outline.load(lines)
        outlineAppear()
        pause()
        wipe()
    }

    private fun tryDifferent() {
        header("Try Different, Not Harder")
        pause()
        val lines =
                """
tj                  "We just need to try harder!"
ss
pnAbandon 19th century motivational models
ss   forget moral pressure
ss   forget good citizenship
ss   forget extrinsic carrot and sticks
ss   forget no-pain-no-gain
pnWhat works is "better"
ss   humans swim where the krill are
ss   trust the people who will be changed
ss   try anything they think won't be worse
""".trimIndent()

        outline.load(lines)
        outline.letters(0).sketch()
        pause()
        for (i in 1..6) outline.letters(i).appear()
        pause()
        for (i in 7..10) outline.letters(i).appear()
        pause()
        wipe()
    }

    private fun finalToIterative() {
        header("Final -> Iterative")
        assume(secondaryNormal)
        val lines =
                """
pnBad news about the city on the hill
ss   you don't actually know where it is...
ss   ...it is a permanently moving target
ss   there's always another problem, another change
pnYou *will* change the same thing twice
ss   if this one doesn't work, try another one
ss   sounds inefficient, but it's not
ss      we underestimate the cost of "becoming right"
ss      we underestimate the cost of inaction
""".trimIndent()
        outline.load(lines)
        outline.blurt()
        pause()


        wipe()
    }

    private fun theHabitOfChange() {
        header("The Habit Of Change")
        val lines =
                """
tj                "Change Makes People Nervous"
ss
pnToo *much* at once makes people nervous
ss   humans absorb small amounts of change routinely
ss   teams that acclimate to steady change thrive
pnChange something every week
ss   if we get pushback, make the changes smaller
pnAnd remember the basics:
ss   collaborate to find ENOF each week
ss   get reactions in words, not numbers
ss   don't be afraid to re-visit

""".trimIndent()

        outline.load(lines)
        outlineAppear()
        pause()
        wipe()
    }

    private fun reset() {
        header("Open The Team")
        val lines =
                """
pnWe can't change what we can't sense
ss   retrospectives and the IBM[tm] are your friends
ss   but quiet reflection, sensitive observation, these are the real keys
pnWe can't change what we can't say
ss   be honest, but remember...
ss       honesty is believing everything you say
ss       it's not saying everything you believe
ss   we need to create spaces for permission to speak freely
ss   we need to find ways to get quiet people's ideas, too
pnWe can't change what we assume we can't change
ss   assume permission
ss   hammer on provisionality & experiment
""".trimIndent()
        outline.load(lines)
        for (i in 0..2) outline.letters(i).appear()
        pause()
        for (i in 3..8) outline.letters(i).appear()
        pause()
        for (i in 9..11) outline.letters(i).appear()
        pause()
        wipe()
    }

    private fun change() {
        header("Final Thoughts: Changing the World")
        pause()
        wipe()
    }


    private fun theQuestion() {
        assume(emphaticJumbo)
        letters("What do we change next?").centered(ViewPort.CENTER).fadeIn(5000.0)
        pause()
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
        letters(text).format(headerFormat).at(TopLeft(master.point(INSET, INSET))).called("header").appear()
    }

    fun cycledHeader(sync: Long, text: String) {
        scene(sync)
        val appearance = appearanceFor(text)
        sync(5)
        appearance.fadeOut()
        nextHeader = (nextHeader + 1) % 4
    }

    private fun appearanceFor(text: String): Appearance<Letters> {
        return when (nextHeader % 4) {
            0 -> letters(text).format(emphaticJumbo).at(TopLeft(master.point(INSET, INSET))).appear()
            1 -> letters(text).format(primaryJumbo).at(TopRight(master.point(100 - INSET, INSET))).appear()
            2 -> letters(text).format(secondaryJumbo).at(TopLeft(master.point(INSET, 100 - INSET - 10))).appear()
            else -> letters(text).format(TertiaryJumbo).at(TopRight(master.point(100 - INSET, 100 - INSET - 10))).appear()
        }
    }

    private fun headerEnd(end: String) {
        letters(end).format(secondaryJumbo).at(RightOf(actor("header"))).sketch()
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

    override fun downcast(): BicScript {
        return this
    }

    companion object {

        private val INSET = 3
        private val XMARGIN = 20.0
        private val YMARGIN = 20.0
    }
}
