package com.example.testing_app

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.testing_app.Storage.Data
import com.example.testing_app.databinding.ActivityResultBinding
import java.text.SimpleDateFormat
import java.time.Duration
import java.util.Locale
import kotlin.math.round

class ResultActivity : AppCompatActivity() {
    private var _binding: ActivityResultBinding? = null
    private val bind: ActivityResultBinding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(bind.root)

        bind.main.visibility = View.GONE
        bind.btnMain.setOnClickListener {
            startActivity(Intent(this@ResultActivity, StartActivity::class.java))
        }

        calculate()
    }

    private fun calculate(){
        Data.checkAnswer()
        val countPositive = Data.questions.filter { it.correctlyAnswer }.size
        val procentPositive = round(countPositive.toDouble()/Data.count.toDouble() * 10000) / 100
        var result = ""
        
        if (countPositive >= Data.appSetting.raiting5) result = "Оценка 5"
        else if (countPositive >= Data.appSetting.raiting4) result = "Оценка 4"
        else if (countPositive >= Data.appSetting.raiting3) result = "Оценка 3"
        else result = "Незачет"

        var hintLine = ""
        if(!Data.appSetting.hint_visibility) hintLine = "Подсказки отключены"
        else  hintLine = "Кол-во используемых подсказок: ${Data.appSetting.user_count_hint} из ${Data.appSetting.count_of_hints}"

        val dateFormatter = SimpleDateFormat("dd MMMM yyyy HH:mm:ss", Locale("ru", "RU"))
        val _date = "Дата начала прохождения теста: " +  dateFormatter.format(Data.dateStart)
        val different = Duration.between(Data.dateStart.toInstant(),Data.dateEnd.toInstant())
        val hours = different.toHours()
        val minutes  = different.toMinutes()
        val seconds = different.seconds
        val _time = "\nВремя прохождения теста: ${hours}.${minutes}.${seconds}"

        bind.tvResult.text = result
        bind.tvResultInfo.text = "$countPositive / ${Data.count}"
        bind.tvResultProcent.text = "Правильных ответов $procentPositive %"
        bind.tvHintInfo.text = hintLine
        bind.tvTimeInfo.text = _date + _time

        bind.main.visibility = View.VISIBLE
    }

}