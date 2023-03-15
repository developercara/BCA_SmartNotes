package com.developercara.bcanotes.compiler

data class JDoodleCodeResponse(
    val output: String,
    val statusCode: Int,
    val memory: String,
    val cpuTime: String,
    val error: String
)
