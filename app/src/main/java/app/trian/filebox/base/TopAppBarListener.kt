package app.trian.filebox.base

fun interface TopAppBarListener {
    fun onItemClicked(tag:String,data:Map<String,String>)
}

const val TAG_NAV_BACK = "FAB"
const val TAG_ACTION = "ITEM"