package com.developercara.bcanotes.compiler

import retrofit2.http.Body
import retrofit2.http.POST


interface JDoodleService {
        @POST("execute")
        suspend fun executeCode(
            @Body codeRequest: JDoodleCodeRequest
        ): JDoodleCodeResponse
    }

