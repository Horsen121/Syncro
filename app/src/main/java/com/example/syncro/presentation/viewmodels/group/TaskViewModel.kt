package com.example.syncro.presentation.viewmodels.group

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.syncro.utils.TaskDifficult
import javax.inject.Inject

class TaskViewModel @Inject constructor(
//    private val repository: MyRepository
) : ViewModel() {

    private var _name = mutableStateOf("")
    val name: State<String> = _name

    private var _desc = mutableStateOf("")
    val desc: State<String> = _desc

    private var _diff = mutableStateOf(TaskDifficult.Medium)
    val diff: State<TaskDifficult> = _diff
    private var _diffOpen = mutableStateOf(false)
    val diffOpen: State<Boolean> = _diffOpen

    private var _files = mutableStateOf<List<String>>(emptyList())
    val files: State<List<String>> = _files


    fun onNameChange(text: String) {
        _name.value = text
    }

    fun onDescChange(text: String) {
        _desc.value = text
    }

    fun onDiffChange(newDiff: TaskDifficult) {
        _diff.value = newDiff
    }
    fun onDiffOpenChange(open: Boolean) {
        _diffOpen.value = open
    }

    fun onFileAdd(file: String) {
        _files.value += file
    }

    fun onFileRemove(file: String) {
        _files.value -= file
    }

    fun Clear() {
        _name.value = ""
        _desc.value = ""
        _diff.value = TaskDifficult.Medium
        _diffOpen.value = false
        _files.value = emptyList()
    }

}