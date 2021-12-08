package petros.efthymiou.groovy.playlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData

class PlaylistViewModel(
    private val repository: PlayListRepository
) : ViewModel() {

    val playlist = liveData {
        emitSource(repository.getPlayLists().asLiveData())
    }

}
