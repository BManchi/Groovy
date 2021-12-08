package petros.efthymiou.groovy

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test

import org.junit.Rule
import petros.efthymiou.groovy.utils.MainCoroutineScopeRule
import petros.efthymiou.groovy.utils.getValueForTest

class PlaylistViewModelShould {

    @get:Rule
    var coroutinesTestRules = MainCoroutineScopeRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    val viewModel: PlaylistViewModel
    val repository: PlayListRepository = mock()

    init {
        viewModel = PlaylistViewModel(repository)
    }

    @Test
    fun getPlayListFromRepository() {
        viewModel.playlist.getValueForTest()

        verify(repository, times(1)).getPlayLists()
    }
}