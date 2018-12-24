package co.winds.myapplication.ui

import android.os.Bundle
import co.winds.myapplication.R
import co.winds.myapplication.common.BaseActivity
import co.winds.myapplication.utils.LocaleHelper
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        textView2.setOnClickListener {
            LocaleHelper.setLocale(this, "hi");
            recreate()
        }

    }
}
