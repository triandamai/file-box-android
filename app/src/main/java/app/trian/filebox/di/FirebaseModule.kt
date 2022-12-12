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
    fun provideFirestore()=FirebaseFirestore.getInstance()

    @Provides
    fun provideFirebaseAuth()=FirebaseAuth.getInstance()

    @Provides
    fun provideFirebaseStorage()=FirebaseStorage.getInstance()

}