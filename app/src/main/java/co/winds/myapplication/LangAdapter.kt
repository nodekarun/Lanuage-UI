package co.winds.myapplication

import android.annotation.SuppressLint
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import co.winds.myapplication.LanguageActivity.Companion.isCheck
import co.winds.myapplication.LanguageActivity.Companion.langagePos
import kotlinx.android.synthetic.main.row_layout.view.*

class LangAdapter(var list: ArrayList<LanguageModel>) : RecyclerView.Adapter<LangAdapter.ViewHolder>() {


    internal interface SingleClickListener {
        fun onItemClickListener(position: Int, view: View,langCode:String)
    }

    companion object {
        private var sClickListener: SingleClickListener? = null
        private var sSelected = -1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.row_layout, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bindItems(list[position], list,position)
    }


    fun selectedItem() {
        notifyDataSetChanged()
    }

    internal fun setOnItemClickListener(clickListener: SingleClickListener) {
        sClickListener = clickListener
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),View.OnClickListener {


        @SuppressLint("SetTextI18n")
        val context = itemView.context
        var lang1 = itemView.lng_name1
        var lang2 = itemView.lng_name2
        val lang_select = itemView.lang_select
        var langCode:String = ""
        var po=0

        fun bindItems(model: LanguageModel, li: ArrayList<LanguageModel>, position: Int) {
            itemView.setOnClickListener(this)
            this.setIsRecyclable(false)

            if(LanguageActivity.isCheck) {
                    if(model.pos==0)
                    lang_select.isChecked =true
            }else{
                lang_select.isChecked = sSelected == model.pos
            }
            po=model.pos
            lang1.text = model.langNameEng
            lang2.text = model.langNameOwn
            langCode=model.langCode

            sSelected =langagePos

            if(lang_select.isChecked){
                select(model)
            }else {
                unselect(model)
            }

        }

        override fun onClick(v: View?) {
            isCheck=false
            sSelected = po
            sClickListener!!.onItemClickListener(po,v!!,langCode)
        }

        private fun select(m: LanguageModel) {
            lang1.text = m.langNameEng
            lang2.text = m.langNameOwn
            lang1.setTextColor(Color.parseColor("#071F30"))
            lang2.setTextColor(Color.parseColor("#071F30"))
        }

        private fun unselect(m: LanguageModel) {
            lang1.text = m.langNameEng
            lang2.text = m.langNameOwn
            lang1.setTextColor(Color.parseColor("#b4bbc0"))
            lang2.setTextColor(Color.parseColor("#b4bbc0"))
        }

    }

    fun filterList(filteredNames: ArrayList<LanguageModel>) {
        this.list = filteredNames
        notifyDataSetChanged()
    }
}
