package step

import org.geepawhill.contentment.core.*
import org.geepawhill.contentment.timing.Scheduler
import org.geepawhill.contentment.timing.Timing
import org.geepawhill.contentment.utility.Names
import java.util.*

class Timed(private val ms: Double) : Gesture {
    private val scheduler: Scheduler
    private val atoms: ArrayList<Fragment>
    private val timings: ArrayList<Timing>
    private val name: String
    private var current: Int = 0
    private var context: Context? = null
    private var onFinished: OnFinished? = null

    init {
        this.name = Names.make(Timed::class.java)
        this.atoms = ArrayList()
        this.timings = ArrayList()
        this.scheduler = Scheduler()
    }

    fun add(atom: Fragment): Timed {
        return add(Timing.instant(), atom)
    }

    fun add(ms: Long, atom: Fragment): Timed {
        add(Timing.ms(ms.toDouble()), atom)
        return this
    }

    fun add(timing: Timing, atom: Fragment): Timed {
        atoms.add(atom)
        timings.add(timing)
        return this
    }

    override fun fast(context: Context) {
        for (atom in atoms) {
            atom.prepare(context)
            atom.interpolate(context, 1.0)
        }
    }

    override fun slow(context: Context, onFinished: OnFinished) {
        this.context = context
        this.onFinished = onFinished
        scheduler.schedule(ms, *timings.toTypedArray())
        if (!atoms.isEmpty()) {
            current = 0
            runCurrent()
        } else
            onFinished.run()
    }

    private fun runCurrent() {
        val atom = atoms[current]
        val timing = timings[current]
        FragmentTransition(timing.ms().toLong(), atom, context, object : OnFinished {
            override fun run() {
                next()
            }
        }).play()
    }

    private operator fun next() {
        current += 1
        if (current == atoms.size)
            onFinished!!.run()
        else
            runCurrent()
    }

    override fun toString(): String {
        return name
    }

}
