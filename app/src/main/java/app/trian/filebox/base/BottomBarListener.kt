package app.trian.filebox.base

fun interface BottomBarListener {
    fun onItemClicked(tag:String,data:Map<String,String>)
}

const val TAG_FAB = "FAB"
const val TAG_ITEM = "ITEM"
const val TAG_DELETE_ALL_SELECTED_FILE = "TAG_DELETE_ALL_SELECTED_FILE"
const val TAG_DETAIL_ALL_SELECTED_FILE = "TAG_DETAIL_ALL_SELECTED_FILE"