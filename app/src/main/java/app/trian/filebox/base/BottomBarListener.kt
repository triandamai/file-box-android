package app.trian.filebox.base

fun interface BottomBarListener {
    fun onItemClicked(tag:String,data:Map<String,String>)
}

const val TAG_FAB = "FAB"
const val TAG_ITEM = "ITEM"