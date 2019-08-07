package com.ybznek.processHelper

class JobTask(private val processes: List<Process>) {
    val lastProces: Process
        get() {
            return processes.last()
        }

    val exitCode: Int?
        get() {
            return if (lastProces.isAlive)
                null
            else
                lastProces.exitValue()
        }

    fun exitCode(): Int {
        return lastProces.let { last ->
            last.waitFor()
            last.exitValue()
        }
    }

    fun success(): Boolean {
        return exitCode() == 0
    }

    fun waitFor() {
        lastProces.waitFor()
    }

    fun getOutputString(): String {
        return lastProces.inputStream.bufferedReader().readText()
    }

    fun lines(): Sequence<String> {
        return lastProces.inputStream.bufferedReader().buffered().lineSequence()
    }
}