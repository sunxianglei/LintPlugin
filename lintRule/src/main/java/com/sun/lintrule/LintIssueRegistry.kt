package com.sun.lintrule

import com.android.tools.lint.client.api.IssueRegistry
import com.android.tools.lint.detector.api.Issue
import com.sun.lintrule.detector.uast.LogDetector

/**
 * @author sunxianglei
 * @date 2021/3/10
 * @desc
 */
class LintIssueRegistry : IssueRegistry() {
    override val issues: List<Issue>
        get() = listOf(LogDetector.ISSUE)
}