package app.trian.filebox.base

interface TopAppBarListener {
    fun onItemClicked(tag:String ="",data:Map<String,String> = mapOf())
}

const val TAG_NAV_BACK = "FAB"
const val TAG_ACTION = "ITEM"