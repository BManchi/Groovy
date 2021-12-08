package petros.efthymiou.groovy

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

import org.junit.Rule
import petros.efthymiou.groovy.utils.MainCoroutineScopeRule
import petros.efthymiou.groovy.utils.getValueForTest

class PlaylistViewModelShould {

    @get:Rule
    var coroutinesTestRules = MainCoroutineScopeRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val repository: PlayListRepository = mock()
    private val playlists = mock<List<Playlist>>()
    private val expected = Result.success(playlists)


    @Test
    fun getPlayListFromRepository() = runBlockingTest {
        runBlocking {
            whenever(repository.getPlayLists()).thenReturn(
                flow {
                    emit(expected)
                }
            )
        }
        val viewModel = PlaylistViewModel(repository)
        viewModel.playlist.getValueForTest()

        verify(repository, times(1)).getPlayLists()
    }

    @Test
    fun emitsPlaylistFromRepository() {
        runBlocking {
            whenever(repository.getPlayLists()).thenReturn(
                flow {
                    emit(expected)
                }
            )
        }
        val viewModel = PlaylistViewModel(repository)
        assertEquals(expected, viewModel.playlist.getValueForTest())
    }
}