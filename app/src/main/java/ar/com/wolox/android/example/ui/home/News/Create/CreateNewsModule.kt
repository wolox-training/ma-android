package ar.com.wolox.android.example.ui.home.News.Create

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class CreateNewsModule {

    @ContributesAndroidInjector
    internal abstract fun createNewsActivity(): CreateNewsActivity

    @ContributesAndroidInjector
    internal abstract fun createNewsFragment(): CreateNewsFragment
}
