package com.mixed.testcases.native

class NativeLib {

    external fun stringFromJNI(): String

    companion object {
        init {
            System.loadLibrary("mixedTestCases")
        }
    }
}
