package com.example.univcommcompose.domain.use_case.get_posts

import com.example.univcommcompose.common.Resource
import com.example.univcommcompose.data.remote.dto.toPost
import com.example.univcommcompose.domain.model.Post
import com.example.univcommcompose.domain.repository.PostRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Named

class GetPostsUseCase @Inject constructor(
    private val postRepository: PostRepository,
    private @Named("auth_token") val token: String
) {
    operator fun invoke(): Flow<Resource<List<Post>>> = flow {
        try {
            emit(Resource.Loading())
            val posts = postRepository.getPosts(token = token).map {
                it.toPost()
            }
            emit(Resource.Success(posts))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred."))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach the server. Check your internet connection."))
        }
    }
}