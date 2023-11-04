package com.example.randomwomenapp.data.repository

import com.example.randomwomenapp.data.remote.dto.FemaleDto
import com.example.randomwomenapp.data.remote.dto.RandomUserApi
import com.example.randomwomenapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.util.UUID
import javax.inject.Inject

class FemaleRepository @Inject constructor(
    private val api: RandomUserApi
) {
    fun getRandomUsers(): Flow<Resource<List<FemaleDto>>> = flow {
        try {
            emit(Resource.Loading())
            val randomSeed = UUID.randomUUID().toString()
            val random = api.getRandomUsers("female", 1, 10, randomSeed)
            emit(Resource.Success(random))
        } catch (e: HttpException) {
            emit(Resource.Error(e.message ?: "Error HTTP GENERAL"))
        } catch (e: IOException) {
            emit(Resource.Error(e.message ?: "verificar tu conexion a internet"))
        }
    }
}
