package com.ybznek.processHelper

class JobTaskBuilder(b: ProcessBuilder) {
    private val processes = ArrayList<ProcessBuilder>()

    init {
        processes.add(b)
    }

    fun to(vararg cmd: String): JobTaskBuilder {
        val b = ProcessBuilder(*cmd)
        processes.add(b)
        return this
    }

    fun to(builder: ProcessBuilder): JobTaskBuilder {
        processes.add(builder)
        return this
    }

    fun start(): JobTask {
        return JobTask(ProcessBuilder.startPipeline(processes))
    }
}