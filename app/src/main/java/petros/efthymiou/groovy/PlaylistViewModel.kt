package petros.efthymiou.groovy

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class PlaylistViewModel(
    private val repository: PlayListRepository
) : ViewModel() {

    val playlist = MutableLiveData<Result<List<Playlist>>>()

    init {
        viewModelScope.launch {
            repository.getPlayLists()
                .collect {
                    playlist.value = it
                }
        }
    }

}
