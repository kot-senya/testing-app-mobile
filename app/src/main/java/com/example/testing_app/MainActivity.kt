package com.example.testing_app

import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testing_app.Adapter.NavigationViewHolder
import com.example.testing_app.Adapter.RecyclerHintsAdapter
import com.example.testing_app.Adapter.RecyclerImageAdapter
import com.example.testing_app.Adapter.RecyclerNavigationAdapter
import com.example.testing_app.Fragments.ArrangamentFragment
import com.example.testing_app.Fragments.ChoosingManyFragment
import com.example.testing_app.Fragments.ChoosingMultiplyAnswerFragment
import com.example.testing_app.Fragments.ChoosingOneFragment
import com.example.testing_app.Fragments.MatchTheElementFragment
import com.example.testing_app.Fragments.MatchTheValueFragment
import com.example.testing_app.Helper.ActivityComponentLoad.loadFragment
import com.example.testing_app.Helper.ActivityComponentLoad.openDialogCustom
import com.example.testing_app.Storage.Data
import com.example.testing_app.Storage.Data.convertImageLine
import com.example.testing_app.databinding.ActivityMainBinding
import com.example.testing_app.databinding.DialogOptionHintBinding
import com.example.testing_app.databinding.DialogOptionNavigationBinding
import com.example.testing_app.databinding.DialogOptionUserOkBinding
import java.time.Instant
import java.util.Date


class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val bind: ActivityMainBinding get() = _binding!!

    private val timer:CountDownTimer = object :CountDownTimer(Data.time, 1000){
        override fun onTick(millisUntilFinished: Long) {
        }

        override fun onFinish() {
            Data.dateEnd = Date.from(Instant.now())

            if(Data.dialogMain != null) {Data.dialogMain!!.dismiss(); Data.dialogMain = null}
            if(Data.dialogUser != null) {Data.dialogUser!!.dismiss(); Data.dialogUser = null}
            bind.main.visibility = View.INVISIBLE

            val _bind = DialogOptionUserOkBinding.inflate(layoutInflater)
            _bind.header.visibility = View.GONE
            _bind.text.text = "Время на прохождение теста закончилось. Нажмите \"${_bind.buttonOk.text}\" чтобы закончить тест"

            val dialog = openDialogCustom(_bind, Data.context!!)

            _bind.buttonOk.setOnClickListener{
                dialog.dismiss()
                startActivity(Intent(this@MainActivity, ResultActivity::class.java))
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)

        Data.context = this
        Data.currentQuestion = 1

        bind.progressBar.max = Data.count

        if(!Data.appSetting.hint_visibility)
            bind.btnOpenHint.visibility = View.GONE

        initClickListener()
        initOhterThings()

        nextPage()

        Data.dateStart = Date.from(Instant.now())
        timer.start()
    }

    private fun initOhterThings() {
        val metrics = Resources.getSystem().getDisplayMetrics()
        val densityDpi = (metrics.density * 160f).toInt()

        Data.width = metrics.widthPixels
        Data.dp = (densityDpi / 160).toInt()

        Data.layoutInflater = layoutInflater
    }

    private fun initClickListener() {
        bind.btnOpenNav.setOnClickListener {
            openNavigationDialog()
        }
        bind.btnOpenHint.setOnClickListener {
            openHintDialog()
        }
        bind.btnNext.setOnClickListener {
            if (Data.currentQuestion + 1 <= Data.count) {
                Data.currentQuestion++
                if (Data.currentQuestion == Data.count) bind.btnNext.text = "Завершить тест"
                nextPage()
            } else {
                timer.cancel()
                Data.dateEnd = Date.from(Instant.now())
                startActivity(Intent(this@MainActivity, ResultActivity::class.java))
            }
        }
    }

    private fun nextPage() {
        bind.tvQuestionText.text = Data.questions[Data.currentQuestion - 1].name
        bind.tvQuestionCount.text = "Вопрос " + Data.currentQuestion.toString()
        bind.progressBar.progress = Data.currentQuestion

        when (Data.questions[Data.currentQuestion - 1].id_category) {
            1 -> loadFragment(ChoosingOneFragment(), supportFragmentManager)
            2 -> loadFragment(ChoosingManyFragment(), supportFragmentManager)
            3, 4 -> loadFragment(ArrangamentFragment(), supportFragmentManager)
            5 -> loadFragment(MatchTheValueFragment(), supportFragmentManager)
            6 -> loadFragment(MatchTheElementFragment(), supportFragmentManager)
            7 -> loadFragment(ChoosingMultiplyAnswerFragment(), supportFragmentManager)
        }

        loadImagesQuestion()

        bind.btnOpenHint.isEnabled = Data.questions[Data.currentQuestion - 1].hints != null
    }

    private fun loadImagesQuestion() {
        if (Data.questions[Data.currentQuestion - 1].images != null) {
            bind.rvImages.layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            bind.rvImages.adapter =
                RecyclerImageAdapter(convertImageLine(Data.questions[Data.currentQuestion - 1].images!!))
            bind.rvImages.visibility = View.VISIBLE
        } else {
            bind.rvImages.adapter = null
            bind.rvImages.visibility = View.GONE
        }
    }

    private fun openHintDialog() {
        val _bind = DialogOptionHintBinding.inflate(layoutInflater)
        val dialog = openDialogCustom(_bind, this)
        Data.dialogMain = dialog

        _bind.btnClose.setOnClickListener {
            Data.dialogMain = null
            dialog.dismiss()
        }

        _bind.rvCards.layoutManager = GridLayoutManager(this, 1)
        _bind.rvCards.setHasFixedSize(true)
        _bind.rvCards.adapter = RecyclerHintsAdapter(Data.questions[Data.currentQuestion - 1].hints!!, _bind.countHint)
    }

    private fun openNavigationDialog() {
        val _bind = DialogOptionNavigationBinding.inflate(layoutInflater)
        val dialog = openDialogCustom(_bind, this)
        Data.dialogMain = dialog

        //слушатели
        _bind.btnClose.setOnClickListener {
            Data.dialogMain = null
            dialog.dismiss()
        }
        _bind.btnEndTest.setOnClickListener {
            dialog.dismiss()
            timer.cancel()
            Data.dateEnd = Date.from(Instant.now())
            startActivity(Intent(this@MainActivity, ResultActivity::class.java))
        }

        //кнопки навигации
        var columnCount: Int = (Data.width - 40 * Data.dp) / (90 * Data.dp)
        _bind.rvNav.layoutManager = GridLayoutManager(this, columnCount)
        val adapter = RecyclerNavigationAdapter(Data.numerics)
        adapter.setListener(object : RecyclerNavigationAdapter.ClickListener {
            override fun onClick(holder: NavigationViewHolder, position: Int) {
                if (adapter.elements[position] == Data.count) bind.btnNext.text = "Завершить тест"
                else bind.btnNext.text = "Следующий вопрос"
                Data.currentQuestion = adapter.elements[position]
                nextPage()
                dialog.dismiss()
            }
        })
        _bind.rvNav.adapter = adapter
    }
}

