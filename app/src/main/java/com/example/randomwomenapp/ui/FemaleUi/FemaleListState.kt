package com.example.randomwomenapp.ui.FemaleUi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.randomwomenapp.data.remote.dto.FemaleDto
import com.example.randomwomenapp.data.repository.FemaleRepository
import com.example.randomwomenapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class FemaleListState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val females: List<FemaleDto> = emptyList()
)

@HiltViewModel
class FemaleViewModel @Inject constructor(
    private val femaleRepository: FemaleRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(FemaleListState())
    val uiState: StateFlow<FemaleListState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            femaleRepository.getRandomUsers().collect() { result ->
                when (result) {
                    is Resource.Loading -> {
                        _uiState.update { it.copy(isLoading = true) }
                    }
                    is Resource.Success -> {
                        _uiState.update {
                            it.copy(
                                females = result.data ?: emptyList(),
                                isLoading = false,
                                error = null
                            )
                        }
                    }
                    is Resource.Error -> {
                        _uiState.update {
                            it.copy(
                                error = result.message ?: "Error desconocido",
                                isLoading = false
                            )
                        }
                    }
                }
            }
        }
    }
}