package com.developercara.bcanotes.compiler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.TextView
import com.developercara.bcanotes.R
import com.developercara.bcanotes.databinding.ActivityOnlineCompilerBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class OnlineCompiler : AppCompatActivity() {
    private val service = Retrofit.Builder()
        .baseUrl("https://api.jdoodle.com/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(JDoodleService::class.java)
    lateinit var binding: ActivityOnlineCompilerBinding

    private val preBuiltTexts = mapOf(
        "java" to "public class HelloWorld {\n    public static void main(String[] args) {\n        System.out.println(\"Hello, World!\");\n    }\n}",
        "python2" to "print(\"Hello, World!\")",
        "c" to "#include <stdio.h>\n\nint main() {\n    printf(\"Hello, World!\\n\");\n    return 0;\n}",
        "cpp" to "#include <iostream>\nusing namespace std;\n\nint main() {\n    cout << \"Hello, World!\" << endl;\n }"
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnlineCompilerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val textoutput = findViewById<TextView>(R.id.outputTextView)
        binding.codeEditText.setText(preBuiltTexts["java"])

        binding.languageSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val language = binding.languageSpinner.selectedItem.toString().toLowerCase()
                binding.codeEditText.setText(preBuiltTexts[language])
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        binding.compileButton.setOnClickListener {
            val clientId = "8ab37d5db91d3ed13e55da6ad3ab9eee"
            val clientSecret = "e79bf355a6c8f704cee74e1cf7d1c43a73618767816fcadf4ad167cfe095c652"
            val language = binding.languageSpinner.selectedItem.toString().toLowerCase()
            val versionIndex = 3
            val script = binding.codeEditText.text.toString()

            val request = JDoodleCodeRequest(clientId, clientSecret, language, versionIndex, script)

            GlobalScope.launch(Dispatchers.IO) {
                val response = service.executeCode(request)
                withContext(Dispatchers.Main) {
                    textoutput.text = response.output
                }
            }
        }

        binding.runButton.setOnClickListener {
            val clientId = "8ab37d5db91d3ed13e55da6ad3ab9eee"
            val clientSecret = "e79bf355a6c8f704cee74e1cf7d1c43a73618767816fcadf4ad167cfe095c652"
            val language = binding.languageSpinner.selectedItem.toString().toLowerCase()
            val versionIndex = 3
            val script = binding.codeEditText.text.toString()
            val input = binding.inputEditText.text.toString()

            val request = JDoodleCodeRequest(clientId, clientSecret, language, versionIndex, script, stdin = input)


            GlobalScope.launch(Dispatchers.IO) {
                val response = service.executeCode(request)
                withContext(Dispatchers.Main) {
                    textoutput.text = response.output
                }
            }
        }
    }
    }
