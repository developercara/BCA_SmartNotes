package com.developercara.bcanotes.compiler

data class JDoodleCodeRequest(
    val clientId: String,
    val clientSecret: String,
    val language: String,
    val versionIndex: Int,
    val script: String,
    val stdin: String = "",
    val stdout: String = "",
    val memoryLimit: Int = 262144,
    val cpuTimeLimit: Int = 2,
    val callbackUrl: String = "",
    val compileArgs: String = "",
    val runArgs: String = ""
)

