package com.developercara.bcanotes.quizgame

class Modelclass{
    var Question: String? = null
    var oA: String? = null
    var oB: String? = null
    var oC: String? = null
    var oD: String? = null
    var ans: String? = null

    constructor() {}

    constructor(Question: String?, oA: String?, oB: String?, oC: String?, oD: String?, ans: String?) {
        this.Question = Question
        this.oA = oA
        this.oB = oB
        this.oC = oC
        this.oD = oD
        this.ans = ans
    }
}
