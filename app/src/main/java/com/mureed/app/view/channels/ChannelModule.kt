package com.mureed.app.view.channels

import androidx.lifecycle.ViewModel
import com.mureed.app.app.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class ChannelModule {

    @ContributesAndroidInjector
    internal abstract fun contributeChannelFragment(): ChannelFragment


    @Binds
    @IntoMap
    @ViewModelKey(ChannelViewModel::class)
    internal abstract fun bindChannelViewModel(viewModel: ChannelViewModel): ViewModel

}