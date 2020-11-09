package com.mureed.app.data.channels

import androidx.annotation.WorkerThread
import androidx.lifecycle.MutableLiveData
import com.mureed.app.data.db.CategoryDao
import com.mureed.app.data.model.Category
import com.mureed.app.data.model.ChannelViewProgress
import com.mureed.app.data.model.Result
import com.mureed.app.data.model.toErrorResult
import com.mureed.app.utils.Constants.CATEGORIES_NETWORK_ERROR
import com.mureed.app.utils.Constants.LOAD_CATEGORIES_ERROR
import javax.inject.Inject

class CategoryRepository @Inject constructor(
    private val dataSource: ChannelRemoteDataSource,
    private val categoryDao: CategoryDao
) {

    @WorkerThread
    suspend fun fetchCategories(
        categoriesResult: MutableLiveData<Result<List<Category>>>,
        channelViewProgress: MutableLiveData<ChannelViewProgress>
    ) {
        channelViewProgress.value?.categoriesProgress = 0
        channelViewProgress.postValue(channelViewProgress.value)
        try {
            val result = dataSource.getCategories()
            val categories = result.body()?.data?.categories

            if (result.isSuccessful && !categories.isNullOrEmpty()) {
                categoryDao.deleteAll()
                categoryDao.insert(categories)
                categoriesResult.postValue(Result.Success(categories))
            } else {
                categoriesResult.postValue(LOAD_CATEGORIES_ERROR.toErrorResult())
            }
        } catch (exception: Exception) {
            categoriesResult.postValue(CATEGORIES_NETWORK_ERROR.toErrorResult())
        } finally {
            channelViewProgress.value?.categoriesProgress = 100
            channelViewProgress.postValue(channelViewProgress.value)
        }
    }

    fun getCategories() = categoryDao.getCategories()

}