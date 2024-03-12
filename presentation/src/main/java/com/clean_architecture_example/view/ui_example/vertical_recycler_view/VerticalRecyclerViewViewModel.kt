package com.clean_architecture_example.view.ui_example.vertical_recycler_view

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

data class UserEntity(
    val id: Int,
    val name: String,
    val age: Int,
)

@HiltViewModel
class VerticalRecyclerViewViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    val users = mutableStateOf<List<UserEntity>>(generateDummyUsers())

    private fun generateDummyUsers(): List<UserEntity> {
        val names = listOf(
            "Olivia", "Emma", "Ava", "Charlotte", "Sophia",
            "Amelia", "Isabella", "Mia", "Evelyn", "Harper",
            "Liam", "Noah", "Oliver", "Elijah", "William",
            "James", "Benjamin", "Lucas", "Henry", "Alexander"
        )

        return List(5000) { index ->
            UserEntity(
                id = index,
                name = names.random(),
                age = (18..60).random()
            )
        }
    }
}