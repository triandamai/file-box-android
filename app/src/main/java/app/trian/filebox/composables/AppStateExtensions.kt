package app.trian.filebox.composables

import app.trian.filebox.BottomBarType
import app.trian.filebox.FileBoxState

fun FileBoxState.incrementSelectedFileCount(){
    with(this){
        changeBottomBar(BottomBarType.PICK_FILE)
        selectedFileCount +=1
    }
}