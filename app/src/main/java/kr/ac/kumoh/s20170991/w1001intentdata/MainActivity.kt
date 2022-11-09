package kr.ac.kumoh.s20170991.w1001intentdata

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Toast
import kr.ac.kumoh.s20170991.w1001intentdata.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), OnClickListener {
    companion object{
        const val keyName = "image"
    }
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Toast.makeText(this, intent.getStringExtra("image"),Toast.LENGTH_LONG).show()


        binding.btnDon.setOnClickListener(this)
        binding.btnGobdori.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        val intent = Intent(this, ImageActivity::class.java)
        val value = when(v?.id) {
            binding.btnDon.id -> "don"
            binding.btnGobdori.id -> "gobdori"
            else -> return
        }
        intent.putExtra(keyName,value)
        startActivity(intent)
    }
}