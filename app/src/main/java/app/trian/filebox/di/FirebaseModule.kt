/***
 * Copyright trian.app
 * Author Trian Damai-triandamai@gmail.com
 * Created at 24/12/22 23.45
 */
package app.trian.filebox.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule{
    @Provides
    fun provideFirestore():FirebaseFirestore=FirebaseFirestore.getInstance()

    @Provides
    fun provideFirebaseAuth():FirebaseAuth=FirebaseAuth.getInstance()

    @Provides
    fun provideFirebaseStorage():FirebaseStorage=FirebaseStorage.getInstance()

}