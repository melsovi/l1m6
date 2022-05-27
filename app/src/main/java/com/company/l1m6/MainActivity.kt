package com.company.l1m6

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.company.l1m6.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>

    companion object{
        const val INTENT_KEY = "text"
    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sendText()
        getText()
    }

    private fun getText() {
        resultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()){
            result : ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK && result.data != null){
                val intent = result.data?.getStringExtra(INTENT_KEY).toString()
                binding.textTv.setText(intent)
            }
        }
    }

    private fun sendText() {
        binding.clickBtn.setOnClickListener{
            if (binding.textTv.length()>0){
                val intent = Intent(this,SecondActivity::class.java)
                intent.putExtra(INTENT_KEY,binding.textTv.text.toString())
                resultLauncher.launch(intent)
            }else{
                Toast.makeText(this,this.getString(R.string.toast),Toast.LENGTH_LONG).show()
            }
        }
    }
}