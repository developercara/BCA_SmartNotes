package com.developercara.bcanotes.quizgame.categories

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.developercara.bcanotes.MainActivity
import com.developercara.bcanotes.R
import com.developercara.bcanotes.quizgame.MainQuiz
import com.developercara.bcanotes.quizgame.Modelclass
import com.developercara.bcanotes.quizgame.WonActivity
import com.google.android.material.progressindicator.LinearProgressIndicator
import com.google.firebase.database.*

class cprogramActivity : AppCompatActivity() {
    private var countDownTimer: CountDownTimer? = null
    private var timerValue = 20
    private var progressDialog: Dialog? = null

    private lateinit var progressBar: LinearProgressIndicator

    private lateinit var listofQ: ArrayList<Modelclass>
    private lateinit var modelclass: Modelclass
    private var index = 0
    private lateinit var card_question: TextView
    private lateinit var optiona: TextView
    private lateinit var optionb: TextView
    private lateinit var optionc: TextView
    private lateinit var optiond: TextView
    private lateinit var cardOA: CardView
    private lateinit var cardOB: CardView
    private lateinit var cardOC: CardView
    private lateinit var cardOD: CardView
    private lateinit var questionTimer: TextView
    private lateinit var questiono: TextView
    private lateinit var nextbtn: LinearLayout

    private var correctCount = 0
    private var wrongCount = 0

    private lateinit var myRef: DatabaseReference
    var questionCount = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_game)

        questionTimer = findViewById(R.id.question_number)
        questiono = findViewById(R.id.questionno)
        questionTimer.text = "$timerValue s"

        showProgressDialog()
        listofQ = ArrayList()
        val database = FirebaseDatabase.getInstance()
        myRef = database.getReference("Cprogramming")

        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                if (snapshot.exists()) {
                    for (questionSnapshot in snapshot.children) {
                        val question = questionSnapshot.getValue(Modelclass::class.java)
                        listofQ.add(question!!)
                    }
                    listofQ.shuffle()
                    modelclass = listofQ[index]
                    hooks()
                    setAllData()
                    nextbtn.isClickable = false
                    hideProgressDialog()
                    // Start timer and progress bar only after progress dialog is hidden
                    if (progressDialog == null || !progressDialog!!.isShowing) {
                        startTimer()
                    }

                }

            }

            override fun onCancelled(error: DatabaseError) {
//                    Log.e("QuizGame", "Failed to read value.", error.toException())
            }
        })

    }
    private fun startTimer() {
        countDownTimer = object : CountDownTimer(20000, 1000) {
            @SuppressLint("SetTextI18n")
            override fun onTick(millisUntilFinished: Long) {
                timerValue -= 1
                progressBar = findViewById(R.id.quiz_timer)
                progressBar.progress = timerValue
                // Update the question timer TextView
                questionTimer.text = "$timerValue s"
            }

            override fun onFinish() {
                val dialog = Dialog(this@cprogramActivity)
                dialog.setContentView(R.layout.timeout_dialog)
                dialog.show()
                dialog.findViewById<View>(R.id.tryagain).setOnClickListener {
                    val intent = Intent(this@cprogramActivity, MainQuiz::class.java)
                    startActivity(intent)
                }
            }
        }.start()
    }
    private fun startTimerinstance(){
        countDownTimer = object : CountDownTimer(20000, 1000) {
            @SuppressLint("SetTextI18n")
            override fun onTick(millisUntilFinished: Long) {
                timerValue = (millisUntilFinished / 1000).toInt()
                progressBar = findViewById(R.id.quiz_timer)
                progressBar.progress = timerValue
                questionTimer.text = "$timerValue s"
            }

            override fun onFinish() {
                val dialog = Dialog(this@cprogramActivity)
                dialog.setContentView(R.layout.timeout_dialog)
                dialog.show()
                dialog.findViewById<View>(R.id.tryagain).setOnClickListener {
                    val intent = Intent(this@cprogramActivity, MainActivity::class.java)
                    startActivity(intent)
                }
            }
        }.start()
    }




    private fun setAllData() {
        card_question.text = modelclass.Question
        optiona.text = modelclass.oA
        optionb.text = modelclass.oB
        optionc.text = modelclass.oC
        optiond.text = modelclass.oD
    }



    @SuppressLint("SetTextI18n")
    private fun Correct(cardView: CardView) {
        cardView.setCardBackgroundColor(ContextCompat.getColor(this, R.color.teal_700))
        nextbtn.isClickable = true
        countDownTimer!!.cancel()
        correctCount++
        questionCount++



        nextbtn.setOnClickListener {
            questiono.text="Question $questionCount of 20"
            if (questionCount >= 20) {

                gameWon()
            }else if (index < listofQ.size - 1) {
                index++
                modelclass = listofQ[index]
                setAllData()
                resetColor()
                enableButton()
                nextbtn.isClickable = false
                // Create a new CountDownTimer instance and start it
                startTimerinstance()
            } else {
                gameWon()
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun Wrong(cardOA: CardView) {

        cardOA.setCardBackgroundColor(ContextCompat.getColor(this, R.color.red))
        nextbtn.isClickable = true
        countDownTimer!!.cancel()
        questionCount++
        wrongCount++

        nextbtn.setOnClickListener {
            questiono.text="Question $questionCount of 20"
            if (questionCount >= 20) {
                gameWon()
            }else if (index < listofQ.size - 1) {
                index++
                modelclass = listofQ[index]
                setAllData()
                resetColor()
                enableButton()
                nextbtn.isClickable = false
                // Create a new CountDownTimer instance and start it
                startTimerinstance()
            } else {
                gameWon()
            }
        }
    }
    private fun gameWon() {
        val intent = Intent(this@cprogramActivity, WonActivity::class.java)
        intent.putExtra("score", correctCount)
        intent.putExtra("wrong", wrongCount)
        startActivity(intent)
    }



    fun OptionAClick(view: View?) {
        disableButton()
        nextbtn.isClickable = true

        if (modelclass.oA == modelclass.ans) {
            cardOA.setCardBackgroundColor(ContextCompat.getColor(this, R.color.teal_700))
            if (index < listofQ.size - 1) {
                Correct(cardOA)
            } else {
                gameWon()
            }
        } else {
            Wrong(cardOA)
        }
    }

    fun OptionBClick(view: View?) {
        disableButton()
        nextbtn.isClickable = true

        if (modelclass.oB == modelclass.ans) {
            cardOB.setCardBackgroundColor(ContextCompat.getColor(this, R.color.teal_700))
            if (index < listofQ.size - 1) {
                Correct(cardOB)
            } else {
                gameWon()
            }
        } else {
            Wrong(cardOB)
        }
    }

    fun OptionCClick(view: View?) {
        disableButton()
        nextbtn.isClickable = true

        if (modelclass.oC == modelclass.ans) {
            cardOC.setCardBackgroundColor(ContextCompat.getColor(this, R.color.teal_700))
            if (index < listofQ.size - 1) {
                Correct(cardOC)
            } else {
                gameWon()
            }
        } else {
            Wrong(cardOC)
        }
    }

    fun OptionDClick(view: View?) {
        disableButton()
        nextbtn.isClickable = true

        if (modelclass.oD == modelclass.ans) {
            cardOD.setCardBackgroundColor(ContextCompat.getColor(this, R.color.teal_700))
            if (index < listofQ.size - 1) {
                Correct(cardOD)
            } else {
                gameWon()
            }
        } else {
            Wrong(cardOD)
        }
    }
    private fun hooks() {
        card_question = findViewById(R.id.card_question)
        optiona = findViewById(R.id.card_optiona)
        optionb = findViewById(R.id.card_optionb)
        optionc = findViewById(R.id.card_optionc)
        optiond = findViewById(R.id.card_optiond)

        cardOA = findViewById(R.id.cardA)
        cardOB = findViewById(R.id.cardB)
        cardOC = findViewById(R.id.cardC)
        cardOD = findViewById(R.id.cardD)

        nextbtn = findViewById(R.id.next_btn1)

    }
    private fun enableButton() {
        cardOA.isClickable = true
        cardOB.isClickable = true
        cardOC.isClickable = true
        cardOD.isClickable = true
    }

    private fun disableButton() {
        cardOA.isClickable = false
        cardOB.isClickable = false
        cardOC.isClickable = false
        cardOD.isClickable = false
    }

    private fun resetColor() {
        cardOA.setCardBackgroundColor(ContextCompat.getColor(this, R.color.lightblue2))
        cardOB.setCardBackgroundColor(ContextCompat.getColor(this, R.color.lightblue2))
        cardOC.setCardBackgroundColor(ContextCompat.getColor(this, R.color.lightblue2))
        cardOD.setCardBackgroundColor(ContextCompat.getColor(this, R.color.lightblue2))
    }
    private fun isNetworkAvailable(): Boolean {
        val connectivityManager = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }
    private fun showProgressDialog() {
        progressDialog = Dialog(this)
        progressDialog?.setContentView(R.layout.dialog_progress)
//        progressDialog?.findViewById<TextView>(R.id.message_text_view)?.text = message
        progressDialog?.setCancelable(false)
        progressDialog?.show()
    }

    private fun hideProgressDialog() {
        progressDialog?.dismiss()
    }
    override fun onBackPressed() {
        // Create an alert dialog to confirm if the user wants to exit the quiz
        AlertDialog.Builder(this)
            .setTitle("Exit quiz?")
            .setMessage("Are you sure you want to exit the quiz?")
            .setPositiveButton("Yes") { _, _ ->
                // Stop the countdown timer
                countDownTimer?.cancel()
                // Call the superclass method to exit the activity
                super.onBackPressed()
            }
            .setNegativeButton("No", null)
            .show()
    }


}
