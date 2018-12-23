package co.winds.myapplication

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*


class LanguageActivity : AppCompatActivity(), LangAdapter.SingleClickListener {


    companion object {
        var langagePos=-1
        var isCheck=false

    }

    private fun getLanData(): ArrayList<LanguageModel> {
        val listData = ArrayList<LanguageModel>()
        listData.add(LanguageModel("English", "English", "en", "en", false, 0))
        listData.add(LanguageModel("Hindi", "हिंदी", "hi", "en", false, 1))
        listData.add(LanguageModel("Telugu", "తెలుగు", "te", "en", false, 2))
        listData.add(LanguageModel("Tamil", "தமிழ்", "ta", "en", false, 3))
        listData.add(LanguageModel("Malayalam", "മലയാളം", "ml", "en", false, 4))
        listData.add(LanguageModel("Gujarati", "ગુજરાતી", "gu", "en", false, 5))
        listData.add(LanguageModel("Assamese ", "অসমীয়া", "as", "en", false, 6))
        listData.add(LanguageModel("Bengali", "বাঙালি", "bn", "en", false, 7))
        listData.add(LanguageModel("Kannada", "ಕನ್ನಡ", "kn", "en", false, 8))
        listData.add(LanguageModel("Marathi", "मराठी", "mr", "en", false, 9))
        listData.add(LanguageModel("Oriya", "ଓଡ଼ିଆ", "or", "en", false, 10))

        return listData
    }
    val str="hi"

    private lateinit var mLangAdapter: LangAdapter
    private var langCode = ""
    private var list=getLanData()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


         when(str) {
            "en" -> {
                langagePos =0
                isCheck =true
            }
            "hi" ->  langagePos = 1
            "te" -> langagePos = 2
            "ta" -> langagePos = 3
            "ml" -> langagePos = 4
            "gu" -> langagePos = 5
            "as" -> langagePos = 6
            "bn" -> langagePos = 7
            "kn" -> langagePos = 8
            "mr" -> langagePos = 9
            "or" -> langagePos = 10
            else -> langagePos = -1
        }

        setupSearchView()
        initRecyview()

    }

    private fun initRecyview() {
        lng_recy.layoutManager =LinearLayoutManager(this)
        val dividerItemDecoration = DividerItemDecoration(lng_recy.context, 1)
        lng_recy.addItemDecoration(dividerItemDecoration)
        mLangAdapter = LangAdapter(list)
        lng_recy.adapter = mLangAdapter
        mLangAdapter.setOnItemClickListener(this)
    }

    override fun onItemClickListener(position: Int, view: View, langCode: String) {
        langagePos =position
        mLangAdapter.selectedItem()
        this.langCode = langCode
    }


    fun btn_continue(v: View) {
        Toast.makeText(this, langCode, Toast.LENGTH_SHORT).show()
    }


    /*Search For Listing  Start*/
    private fun setupSearchView() {

        if (currentFocus != null) {
            val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        }

        search_view.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
            }

            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
            }

            override fun afterTextChanged(editable: Editable) {
                filter(editable.toString())
            }
        })
    }

    private fun filter(text: String) {
        val filteredNames = ArrayList<LanguageModel>()
        list.filterTo(filteredNames) {
            it.langNameEng.toLowerCase().contains(text.toLowerCase())
        }
        try {
            mLangAdapter.filterList(filteredNames)
        } catch (e: Exception) {
        }
        if (filteredNames.isEmpty()) noDataShow() else noDataHide()
    }
    /*Search For listing END*/


    private fun noDataShow() {
        tv_nodata.visibility = View.VISIBLE
    }

    private fun noDataHide() {
        tv_nodata.visibility = View.GONE
    }





}
