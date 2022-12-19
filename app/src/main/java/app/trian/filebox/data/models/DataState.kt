package app.trian.filebox.data.models

sealed class DataState<out T>{
    object Loading:DataState<Nothing>()
    object Empty:DataState<Nothing>()
    data class Data <out Result>(val data:Result):DataState<Result>()
    data class Error(val message:String=""):DataState<Nothing>()
}
