package kr.ac.kumoh.s20170991.w1001intentdata

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import kr.ac.kumoh.s20170991.w1001intentdata.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), OnClickListener {
    companion object{
        const val keyName = "image"
    }
    private lateinit var binding: ActivityMainBinding
    private lateinit var launcher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnDon.setOnClickListener(this)
        binding.btnGobdori.setOnClickListener(this)
        //Toast.makeText(this, intent.getStringExtra("image"),Toast.LENGTH_LONG).show()

        launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode != RESULT_OK)
                return@registerForActivityResult
            val result = it.data?.getIntExtra(ImageActivity.resultName, ImageActivity.NON)
            val str = when (result) {
                ImageActivity.LIKE -> "좋아요"
                ImageActivity.DISLIKE -> "싫어요"
                else -> ""
            }
            val image = it.data?.getStringExtra(ImageActivity.imageName)
            when (image) {
                "don" -> binding.btnDon.text = "돈까스 ($str)"
                "gobdori" -> binding.btnGobdori.text = "곱도리 ($str)"
            }
        }
    }

    override fun onClick(v: View?) {
        val intent = Intent(this, ImageActivity::class.java)
        val value = when(v?.id) {
            binding.btnDon.id -> "don"
            binding.btnGobdori.id -> "gobdori"
            else -> return
        }
        intent.putExtra(keyName, value)
        startActivity(intent)
    }
}