package app.trian.filebox.composables

import android.content.Context
import javax.inject.Inject

class GetStringResources @Inject constructor(
    val appContext:Context
) {
    internal operator fun invoke(id:Int):String = appContext.getString(id)

}