package com.example.univcommcompose.domain.repository

import com.example.univcommcompose.data.remote.dto.QuestionDto

interface QuestionRepository {
    suspend fun getQuestions(token: String): List<QuestionDto>
}