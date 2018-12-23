package co.winds.myapplication

data class LanguageModel(
    var langNameEng:String,
    var langNameOwn:String,
    var langCode:String,
    var langNameInDefaultLocale: String,
    var isSelected: Boolean,
    var pos:Int
)
