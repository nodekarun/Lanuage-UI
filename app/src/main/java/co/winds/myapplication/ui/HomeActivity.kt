package co.winds.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import co.winds.myapplication.common.BaseActivity

class HomeActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }
}
