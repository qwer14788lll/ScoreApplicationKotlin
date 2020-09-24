package com.example.scoreapplicationkotlin

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var saveA = 0
    private var saveB = 0

    //保存UI上的比分
    private val KEY_A = "key_a"

    //保存UI上的比分
    private val KEY_B = "key_b"

    //保存备份的比分
    private val KEY_A_SAVE = "key_a_save"

    //保存备份的比分
    private val KEY_B_SAVE = "key_b_save"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        readSP()

//         if (savedInstanceState != null) {
//             saveA = savedInstanceState.getInt(KEY_A_SAVE)
//             saveB = savedInstanceState.getInt(KEY_B_SAVE)
//         }

        button_a_add1.setOnClickListener { addA(1) }
        button_a_add2.setOnClickListener { addA(2) }
        button_a_add3.setOnClickListener { addA(3) }

        button_b_add1.setOnClickListener { addB(1) }
        button_b_add2.setOnClickListener { addB(2) }
        button_b_add3.setOnClickListener { addB(3) }

        image_button_revoke.setOnClickListener { revoke() }

        image_button_reset.setOnClickListener { reset() }
    }

    private fun saveSP() {
        val sp = getSharedPreferences("my_sp", MODE_PRIVATE).edit()
        sp.putInt(KEY_A, text_score_a.text.toString().toInt())
        sp.putInt(KEY_B, text_score_b.text.toString().toInt())
        sp.apply()
    }

    private fun readSP() {
        val sp = getSharedPreferences("my_sp", MODE_PRIVATE)
        text_score_a.text = sp.getInt(KEY_A, 0).toString()
        text_score_b.text = sp.getInt(KEY_B, 0).toString()
    }

    /**
     * 保存比分
     */
    private fun save() {
        saveA = text_score_a.text.toString().toInt()
        saveB = text_score_b.text.toString().toInt()
    }

    private fun addA(i: Int) //A队加分
    {
        save()
        text_score_a.text = (saveA + i).toString()
    }

    private fun addB(i: Int) //B队加分
    {
        save()
        text_score_b.text = (saveB + i).toString()
    }

    private fun revoke() //撤销上一次的比分
    {
        text_score_a.text = saveA.toString()
        text_score_b.text = saveB.toString()
    }

    private fun reset() //重置比分
    {
        save()
        text_score_a.text = getString(R.string.zero)
        text_score_b.text = getString(R.string.zero)
    }

    override fun onPause() {
        super.onPause()
        saveSP()
    }
}
