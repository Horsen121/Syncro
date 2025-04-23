package com.example.syncro.domain.usecases

import com.example.syncro.domain.usecases.source_file.AddSourceFile
import com.example.syncro.domain.usecases.source_file.GetSourceFiles

data class SourceFileUseCases(
    val getSourceFiles: GetSourceFiles,
    val addSourceFile: AddSourceFile,
)