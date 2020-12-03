package com.xeross.xerstats.controller.fragments

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions.bitmapTransform
import com.xeross.xerstats.R
import com.xeross.xerstats.controller.viewmodel.LevelViewModel
import com.xeross.xerstats.models.LevelModel
import com.xeross.xerstats.utils.Utils
import com.xeross.xerstats.utils.Utils.showToast
import jp.wasabeef.glide.transformations.BlurTransformation
import kotlinx.android.synthetic.main.level_alert_dialog.*
import kotlinx.android.synthetic.main.level_alert_dialog.view.*
import kotlinx.android.synthetic.main.level_alert_dialog.view.button_cancel
import kotlinx.android.synthetic.main.level_alert_dialog.view.button_save_time
import kotlinx.android.synthetic.main.level_details_content.*
import kotlinx.android.synthetic.main.level_details_content.view.*
import kotlinx.android.synthetic.main.level_pb_alert_dialog.*
import kotlinx.android.synthetic.main.level_pb_alert_dialog.view.*


abstract class FragmentManager : Fragment() {

    private var viewModel: LevelViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        context?.let { viewModel = Utils.configureViewModel(this, it) }
    }

    private fun getLevel(id: Int, view: View) {
        viewModel?.let {
            it.getLevel(id).observe(this, Observer { level ->
                level?.let {
                    view.textView_timer_best.text = level.bestLevel
                    view.textView_timer_pb.text = level.pbLevel
                    view.textView_timer_time.text = level.timerLevel
                }
            })
        }
        view.textView_timer_best.text = "00:00:00"
        view.textView_timer_pb.text = "00:00:00"
        view.textView_timer_time.text = "00:00:00"
    }

    private fun initializeResults(view: View) {
        var secondBest: Long = 0
        viewModel?.let {
            it.getLevels().observe(this, Observer { levels ->
                levels.forEach { level ->
                    val string =
                        if(level.bestLevel.length < 8) "00:" + level.bestLevel else level.bestLevel
                    val second = if (string.length >= 8) string.substring(
                        6,
                        8
                    ).toInt() else 0
                    val minute = if (string.length >= 5) string.substring(
                        3,
                        5
                    ).toInt() else 0
                    val hour = if (string.length >= 2) string.substring(
                        0,
                        2
                    ).toInt() else 0
                    if (hour != 0) secondBest += hour * 60 * 60
                    if (minute != 0) secondBest += minute * 60
                    if (second != 0) secondBest += second
                }
                val secondF = secondBest % 60
                var hourF = secondBest / 60
                val minuteF = hourF % 60
                hourF /= 60
                val bestTime = "${if (hourF < 10) "0" else ""}$hourF:" +
                        "${if (minuteF < 10) "0" else ""}$minuteF:" +
                        "${if (secondF < 10) "0" else ""}$secondF"
                view.textView_timer_best.text = bestTime
            })
        }
        view.textView_timer_best.text = "00:00:00"
    }

    private fun initializeResultsTime(view: View) {
        var secondBest: Long = 0
        viewModel?.let {
            it.getLevels().observe(this, Observer { levels ->
                levels.forEach { level ->
                    val string =
                        if(level.timerLevel.length < 8) "00:" + level.timerLevel else level.timerLevel
                    val second = if (string.length >= 8) string.substring(
                        6,
                        8
                    ).toInt() else 0
                    val minute = if (string.length >= 5) string.substring(
                        3,
                        5
                    ).toInt() else 0
                    val hour = if (string.length >= 2) string.substring(
                        0,
                        2
                    ).toInt() else 0
                    if (hour != 0) secondBest += hour * 60 * 60
                    if (minute != 0) secondBest += minute * 60
                    if (second != 0) secondBest += second
                }
                val secondF = secondBest % 60
                var hourF = secondBest / 60
                val minuteF = hourF % 60
                hourF /= 60
                val bestTime = "${if (hourF < 10) "0" else ""}$hourF:" +
                        "${if (minuteF < 10) "0" else ""}$minuteF:" +
                        "${if (secondF < 10) "0" else ""}$secondF"
                view.textView_timer_time.text = bestTime
            })
        }
        view.textView_timer_time.text = "00:00:00"
    }

    private fun initializeResultsPB(view: View) {
        var secondBest: Long = 0
        viewModel?.let {
            it.getLevels().observe(this, Observer { levels ->
                levels.forEach { level ->
                    val string =
                        if(level.pbLevel.length < 8) "00:" + level.pbLevel else level.pbLevel
                    val second = if (string.length >= 8) string.substring(
                        6,
                        8
                    ).toInt() else 0
                    val minute = if (string.length >= 5) string.substring(
                        3,
                        5
                    ).toInt() else 0
                    val hour = if (string.length >= 2) string.substring(
                        0,
                        2
                    ).toInt() else 0
                    if (hour != 0) secondBest += hour * 60 * 60
                    if (minute != 0) secondBest += minute * 60
                    if (second != 0) secondBest += second
                }
                val secondF = secondBest % 60
                var hourF = secondBest / 60
                val minuteF = hourF % 60
                hourF /= 60
                val bestTime = "${if (hourF < 10) "0" else ""}$hourF:" +
                        "${if (minuteF < 10) "0" else ""}$minuteF:" +
                        "${if (secondF < 10) "0" else ""}$secondF"
                view.textView_timer_pb.text = bestTime
            })
        }
        view.textView_timer_pb.text = "00:00:00"
    }


    protected fun initializeLevel(view: View, drawable: Int, name: String, id: Int) {
        Glide.with(context!!).load(drawable)
            .apply(bitmapTransform(BlurTransformation(20)))
            .into(view.imageView_background_level)
        view.textView_name_level.text = name
        if (id == 8) {
            initializeResults(view)
            initializeResultsPB(view)
            initializeResultsTime(view)
        } else getLevel(id, view)
        view.button_alert_dialog.setOnClickListener {
            if (id == 8) {
                showAlertDialogTimer()
            } else showAlertDialog(id)
        }
    }

    private fun String.toEditable(): Editable = Editable.Factory.getInstance().newEditable(this)

    private fun showAlertDialog(id: Int) {
        LayoutInflater.from(activity).inflate(R.layout.level_alert_dialog, null)
            .let {
                val alertDialog = AlertDialog.Builder(context!!).setView(it).show()
                it.editText_best.text = textView_timer_best.text.toString().toEditable()
                it.editText_split.text = textView_timer_time.text.toString().toEditable()
                it.button_cancel.setOnClickListener { alertDialog.dismiss() }
                it.button_save_time.setOnClickListener { _ ->
                    if (it.editText_best.text!!.trim().isEmpty() || it.editText_split.text!!.trim().isEmpty()) {
                        Toast.makeText(context!!, "Information manquante", Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        viewModel?.let { viewModel ->
                            viewModel.insertLevel(
                                LevelModel(
                                    it.editText_best.text!!.trim().toString(),
                                    textView_timer_pb.text!!.trim().toString(),
                                    it.editText_split.text!!.trim().toString(), id
                                )
                            )
                            alertDialog.dismiss()

                        }
                    }
                }
            }
    }

    private fun showAlertDialogTimer() {
        LayoutInflater.from(activity).inflate(R.layout.level_pb_alert_dialog, null)
            .let {
                val alertDialog = AlertDialog.Builder(context!!).setView(it).show()
                viewModel?.let { vm ->
                    vm.getLevels().observe(this, Observer { levels ->
                        levels.forEach { level ->
                            when (level.idLevel) {
                                1 -> it.editText_1.text = level.timerLevel.toEditable()
                                2 -> it.editText_2.text = level.timerLevel.toEditable()
                                3 -> it.editText_3.text = level.timerLevel.toEditable()
                                4 -> it.editText_4.text = level.timerLevel.toEditable()
                                5 -> it.editText_5.text = level.timerLevel.toEditable()
                                6 -> it.editText_6.text = level.timerLevel.toEditable()
                                7 -> it.editText_7.text = level.timerLevel.toEditable()
                            }
                        }
                    })
                }
                it.button_cancel.setOnClickListener { alertDialog.dismiss() }
                it.button_save_time.setOnClickListener { _ ->
                    if (it.editText_1.text!!.trim().isEmpty() || it.editText_2.text!!.trim().isEmpty() ||
                        it.editText_3.text!!.trim().isEmpty() || it.editText_4.text!!.trim().isEmpty() ||
                        it.editText_7.text!!.trim().isEmpty() ||
                        it.editText_5.text!!.trim().isEmpty() || it.editText_6.text!!.trim().isEmpty()
                    ) {
                        Toast.makeText(context!!, "Information manquante", Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        viewModel?.let { viewModel ->
                            for (i in 1..7) {
                                when (i) {
                                    1 -> viewModel.updatePB(1, it.editText_1.text.toString())
                                    2 -> viewModel.updatePB(2, it.editText_2.text.toString())
                                    3 -> viewModel.updatePB(3, it.editText_3.text.toString())
                                    4 -> viewModel.updatePB(4, it.editText_4.text.toString())
                                    5 -> viewModel.updatePB(5, it.editText_5.text.toString())
                                    6 -> viewModel.updatePB(6, it.editText_6.text.toString())
                                    7 -> viewModel.updatePB(7, it.editText_7.text.toString())
                                }
                            }
                            alertDialog.dismiss()

                        }
                    }
                }
            }
    }
}