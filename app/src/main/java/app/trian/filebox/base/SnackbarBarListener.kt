package app.trian.filebox.base

fun interface SnackbarBarListener {
    fun onItemClicked(tag:String,data:Map<String,String>)
}

const val TAG_ACTION_SEND = "TAG_ACTION_SEND"
const val TAG_ACTION_MORE = "TAG_ACTION_MORE"