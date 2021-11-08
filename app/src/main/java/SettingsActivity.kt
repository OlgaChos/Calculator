package com.example.calculator



import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import java.lang.Double.parseDouble


class SettingsActivity : AppCompatActivity() {

    //private val viewModel by viewModels<SettingsViewModel>()
    //private val viewBinding by viewBinding(SettingsActivityBinding::bind)
    companion object {
        var asap: String = "##"
        var spid: Int = 1
        var vibr: Long = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)

        var result_panel_description: TextView = findViewById(R.id.result_panel_description)
        result_panel_description.text = asap

        var vibrate_panel_description: TextView = findViewById(R.id.vibrate_panel_description)
        vibrate_panel_description.text = "$vibr ms"

        val settingsBack: View = findViewById(R.id.settingsBack)
        settingsBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val result_panel_container: View = findViewById(R.id.result_panel_container)
        result_panel_container.setOnClickListener {
            showDialog()
        }

        val vibrate_panel_container: View = findViewById(R.id.vibrate_panel_container)
        vibrate_panel_container.setOnClickListener {
            showVibrate()
        }


       // viewModel.resultPanelState.observe(this) {state ->
      //      resources.getStringArray(R.array.okr_types)[state.ordinal]
     //   }

      //  viewModel.openResultPanelAction.observe(this) {type ->
      //      showDialog()
      //  }


    }


    private fun showDialog() {
        AlertDialog.Builder(this)
            .setTitle("Точность округления")

            .setPositiveButton("OK"){dialog, id ->

            }
            .setNegativeButton("Отмена"){dialog, id ->

            }
            .setSingleChoiceItems(R.array.okr_types,SettingsActivity.spid) {dialog, id ->
                Toast.makeText(this,id.toString(),Toast.LENGTH_SHORT).show()
               //viewModel.onResultPanelTypeChanged(ResultPanelType.values()[id])
                val list =  resources.getStringArray(R.array.okr_types)
                SettingsActivity.spid = id
                SettingsActivity.asap = list[id]
                val result_panel_description: TextView = findViewById(R.id.result_panel_description)
                result_panel_description.text = list[id]
                //val intent = Intent(this,MainActivity::class.java)
                //intent.putExtra("round","$okrs")
                dialog.dismiss()
                //startActivity(intent)
            }
            .create()
            .show()
    }

    private fun showVibrate() {
        val mDialogView = LayoutInflater.from(this).inflate(R.layout.vibrate_activity, null)
        val mBuilder = AlertDialog.Builder(this)
            .setView(mDialogView)
            .setTitle("Режим вибрации")
            //val mAlertDialog = mBuilder.show()
        setContentView(R.layout.vibrate_activity)
        val dialog_ok_btn:  View = findViewById(R.id.dialog_ok_btn)
        val dialog_cancel_btn:  View = findViewById(R.id.dialog_cancel_btn)
        val vibrateBack:  View = findViewById(R.id.vibrateBack)
        val vibrate_input:  TextView = findViewById(R.id.vibrate_input)
        dialog_ok_btn.setOnClickListener{
           // mAlertDialog.dismiss()
            val vibr_i = vibrate_input.text.toString()
            var numeric = true
            try { val num = parseDouble(vibr_i)
                vibr = vibr_i.toLong()
                val intent = Intent(this, SettingsActivity::class.java)
                startActivity(intent)
            }
                catch (e: NumberFormatException) {
                    numeric = false
                    Toast.makeText(this,"Введите числовое значение",Toast.LENGTH_SHORT).show()
                }
        }
        dialog_cancel_btn.setOnClickListener{
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }

        vibrateBack.setOnClickListener{
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }

    }
}
