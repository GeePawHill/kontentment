package org.geepawhill.contentment.core

import org.geepawhill.contentment.actor.Appearance
import org.geepawhill.contentment.actors.Letters
import org.geepawhill.contentment.flow.Color
import org.geepawhill.contentment.flow.Flow
import org.geepawhill.contentment.flow.FormatTable
import org.geepawhill.contentment.flow.Size
import org.geepawhill.contentment.grid.Grid
import org.geepawhill.contentment.player.Script
import org.geepawhill.contentment.position.TopLeft
import org.geepawhill.contentment.position.TopRight
import org.geepawhill.contentment.rhythm.MediaRhythm
import org.geepawhill.contentment.step.ScriptBuilder
import java.io.File

@Suppress("unused")
class BicScript : ScriptBuilder<BicScript>(MediaRhythm(File("C:\\GeePawHillDotOrgWip\\wip\\ccs talks\\Beauty In Code 2020\\bic2020-raw-tonal.mp4"))) {

    private val formats = FormatTable()


    private val primaryJumbo = formats[Size.Jumbo, Color.Primary]

    private val secondaryJumbo = formats[Size.Jumbo, Color.Secondary]

    private val tertiaryJumbo = formats[Size.Jumbo, Color.Tertiary]

    private val emphaticJumbo = formats[Size.Jumbo, Color.Emphatic]

    private val master = Grid()
    private val viewport = master.nested(INSET, 15, 100 - INSET, 100 - INSET)

    private var nextHeader = 0

    private val outline: Flow

    init {
        //super()
        // super(new MediaRhythm(new
        // File("D:\\GeePawHillDotOrg\\videos\\lumpOfCoding\\lumpOfCodingFaceover.mp4")));
        // new SimpleRhythm());
        script.length = 2359000L
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


    private fun cycledHeader(sync: Long, text: String) {
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
            else -> letters(text).format(tertiaryJumbo).at(TopRight(master.point(100 - INSET, 100 - INSET - 10))).appear()
        }
    }

    override fun downcast(): BicScript {
        return this
    }

    companion object {
        private const val INSET = 3
    }
}
