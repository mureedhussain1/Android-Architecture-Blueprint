package com.mureed.app.view.channels

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.mureed.app.R
import com.mureed.app.data.model.*
import com.mureed.app.databinding.FragmentChannelBinding
import com.mureed.app.app.ViewModelFactory
import com.mureed.app.utils.showErrorSnackBar
import com.mureed.app.view.adapters.BindingRecyclerViewAdapter
import com.mureed.app.view.adapters.categoriesCallback
import com.mureed.app.view.adapters.channelCallback
import com.mureed.app.view.adapters.mediaCallback
import com.mureed.app.view.widget.SpacingDivider
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_channel.*
import javax.inject.Inject

class ChannelFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val categoriesAdapter: BindingRecyclerViewAdapter<Category> by lazy {
        BindingRecyclerViewAdapter(categoriesCallback, R.layout.item_category)
    }

    private val newEpisodesAdapter: BindingRecyclerViewAdapter<Media> by lazy {
        BindingRecyclerViewAdapter(mediaCallback, R.layout.item_media)
    }

    private val channelsAdapter: BindingRecyclerViewAdapter<Channel> by lazy {
        BindingRecyclerViewAdapter(channelCallback, R.layout.item_channels)
    }

    private val viewModel: ChannelViewModel by activityViewModels {
        viewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentChannelBinding.inflate(inflater, container, false).apply {
            this.lifecycleOwner = viewLifecycleOwner
            this.viewModel = this@ChannelFragment.viewModel
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        swipeRefresh.setOnRefreshListener {
            viewModel.loadChannelsData()
        }
        initializeCategoriesAdapter()
        initializeAdapters()
        observeData()
        observeResults()
    }

    private fun observeData() {
        channelsAdapter.observe(viewModel.channels)
        categoriesAdapter.observe(viewModel.categories)
        newEpisodesAdapter.observe(viewModel.episodes)
    }

    private fun observeResults() {
        viewModel.channelProgress.observe(viewLifecycleOwner, Observer {
            if (it.totalProgress == 100) swipeRefresh.isRefreshing = false
        })
        viewModel.categoriesResult.observeErrors(viewModel::loadCategories)
        viewModel.channelsResult.observeErrors(viewModel::loadChannels)
        viewModel.episodesResult.observeErrors(viewModel::loadEpisodes)
    }

    private fun initializeAdapters() {
        // fix scrolling to recyclerview issue
        newEpisodesList.isFocusable = false
        newEpisodesList.isFocusableInTouchMode = false
        channelsList.isFocusable = false
        channelsList.isFocusableInTouchMode = false

        ViewCompat.setNestedScrollingEnabled(newEpisodesList, false)
        newEpisodesList.adapter = newEpisodesAdapter
        ViewCompat.setNestedScrollingEnabled(channelsList, false)
        channelsList.adapter = channelsAdapter
    }

    private fun initializeCategoriesAdapter() {
        categoriesList.isFocusable = false
        categoriesList.isFocusableInTouchMode = false
        ViewCompat.setNestedScrollingEnabled(categoriesList, false)
        categoriesList.addItemDecoration(SpacingDivider(R.dimen.categories_spacing))
        categoriesList.adapter = categoriesAdapter
    }

    // Don't Repeat - DRY
    private fun <T> BindingRecyclerViewAdapter<T>.observe(liveData: LiveData<List<T>>) {
        liveData.observe(viewLifecycleOwner, Observer { submitList(it) })
    }

    private fun <T> LiveData<Result<T>>.observeErrors(retry: () -> Unit) {
        observe(viewLifecycleOwner, Observer {
            if (!value.succeeded) value.error()?.let {
                view?.showErrorSnackBar(it, retry)
            }
        })
    }

}