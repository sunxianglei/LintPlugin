package com.sun.lintrule.detector.uast

import com.android.tools.lint.client.api.UElementHandler
import com.android.tools.lint.detector.api.*
import org.jetbrains.uast.UCallExpression
import org.jetbrains.uast.UElement
import java.util.*

/**
 * @author sunxianglei
 * @date 2021/3/10
 * @desc
 */
class LogDetector: Detector(), Detector.UastScanner {

    companion object {
        val ISSUE = Issue.create(
            id = "LogUsage",
            briefDescription = "避免使用系统自带的Log工具",
            explanation = "推荐使用项目中封装的LsLog, 已帮助您处理好不同环境是否答应的逻辑",
            category = Category.CORRECTNESS,
            priority = 5,
            severity = Severity.ERROR,
            implementation = Implementation(
                LogDetector::class.java,
                Scope.JAVA_FILE_SCOPE
            )
        )
    }

    override fun getApplicableUastTypes(): List<Class<out UElement>>? {
        return Collections.singletonList(UCallExpression::class.java)
    }

    override fun createUastHandler(context: JavaContext): UElementHandler? {
        return object : UElementHandler() {
            override fun visitCallExpression(node: UCallExpression) {
                if(node.receiver != null && node.methodName != null) {
                    val methodName = node.methodName
                    if("v" == methodName
                        || "d" == methodName
                        || "i" == methodName
                        || "e" == methodName
                        || "w" == methodName
                        || "wtf" == methodName) {
                        val method = node.resolve()
                        context.uastFile?.classes?.get(0)?.name.let {
                            if("LsLog" == it) {
                                return
                            }
                        }
                        if(context.evaluator.isMemberInClass(method, "android.util.Log")) {
                            report(context, node)
                        }
                    }
                }
            }

            fun report(context: JavaContext, node: UCallExpression) {
                val fix = LintFix.create()
                    .replace()
                    .with("LsLog." + node.methodName
                            + "(" + node.valueArguments[0].asSourceString()
                            + ", " + node.valueArguments[1].asSourceString() + ");")
                    .build()
                context.report(
                    ISSUE,
                    node,
                    context.getLocation(node),
                    "避免使用系统自带的Log工具",
                    fix
                )
            }
        }
    }

}