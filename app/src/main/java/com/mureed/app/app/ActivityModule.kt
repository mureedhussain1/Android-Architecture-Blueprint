package com.mureed.app.app

import com.mureed.app.view.MainActivity
import com.mureed.app.view.channels.ChannelModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector(
        modules = [
            ChannelModule::class
        ]
    )
    internal abstract fun mainActivity(): MainActivity

}