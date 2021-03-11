package com.sun.lintplugin.util

import android.util.Log
import com.sun.lintplugin.BuildConfig

/**
 * @author sunxianglei
 * @date 2021/3/10
 * @desc
 */

class LsLog {
    companion object {
        fun v(tag: String, msg: String) {
            if(BuildConfig.DEBUG) {
                Log.v(tag, msg)
            }
        }
        fun d(tag: String, msg: String) {
            if(BuildConfig.DEBUG) {
                Log.d(tag, msg)
            }
        }
    }
}