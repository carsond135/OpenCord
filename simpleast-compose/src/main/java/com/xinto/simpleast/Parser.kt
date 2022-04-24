package com.xinto.simpleast

import java.util.*

public class Parser<RC, T : Node<RC>, S> {
    private val rules = ArrayList<Rule<RC, out T, S>>()
    public inline fun rules(block: RuleScope.() -> Unit) {
        RuleScope().apply(block)
    }

    public fun parse(
        source: CharSequence,
        initialState: S,
        rules: List<Rule<RC, out T, S>> = this.rules,
    ): MutableList<T> {
        val remainingParses = Stack<ParseSpec<RC, S>>()
        val topLevelRootNode = Node<RC>()

        var lastCapture: String? = null

        if (source.isNotEmpty()) {
            remainingParses.add(
                ParseS
            )
        }
    }
}