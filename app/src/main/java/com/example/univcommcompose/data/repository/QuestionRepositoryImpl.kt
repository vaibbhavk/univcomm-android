package com.example.univcommcompose.data.repository

import com.example.univcommcompose.data.remote.UnivcommApi
import com.example.univcommcompose.data.remote.dto.QuestionDto
import com.example.univcommcompose.domain.repository.QuestionRepository
import javax.inject.Inject

class QuestionRepositoryImpl @Inject constructor(private val api: UnivcommApi) :
    QuestionRepository {
    override suspend fun getQuestions(token: String): List<QuestionDto> {
        return api.getQuestions(token = token)
    }
}