package app.trian.filebox.base

interface BottomBarListener {
    fun onItemClicked(tag:String ="",data:Map<String,String> = mapOf())
}

const val TAG_FAB = "FAB"
const val TAG_ITEM = "ITEM"