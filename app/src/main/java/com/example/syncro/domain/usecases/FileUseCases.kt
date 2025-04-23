package com.example.syncro.domain.usecases

import com.example.syncro.domain.usecases.file.AddFile
import com.example.syncro.domain.usecases.file.GetFiles

data class FileUseCases(
    val getFiles: GetFiles,
    val addFile: AddFile,
)