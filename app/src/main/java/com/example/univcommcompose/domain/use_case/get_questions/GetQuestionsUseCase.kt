package com.example.univcommcompose.domain.use_case.get_questions

import com.example.univcommcompose.common.Resource
import com.example.univcommcompose.data.remote.dto.toQuestion
import com.example.univcommcompose.domain.model.Question
import com.example.univcommcompose.domain.repository.QuestionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Named


class GetQuestionsUseCase @Inject constructor(
    private val questionRepository: QuestionRepository,
    private @Named("auth_token") val token: String
) {
    operator fun invoke(): Flow<Resource<List<Question>>> = flow {
        try {
            emit(Resource.Loading())
            val questions = questionRepository.getQuestions(token = token).map {
                it.toQuestion()
            }
            emit(Resource.Success(questions))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred."))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach the server. Check your internet connection."))
        }
    }
}