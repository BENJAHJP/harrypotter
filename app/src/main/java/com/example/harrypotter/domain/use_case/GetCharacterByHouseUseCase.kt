package com.example.harrypotter.domain.use_case

import com.example.harrypotter.common.Resource
import com.example.harrypotter.data.remote.api.HarryPotterApi
import com.example.harrypotter.data.remote.dto.toSingleCharacterModel
import com.example.harrypotter.domain.model.SingleCharacterModel
import com.example.harrypotter.domain.repository.HarryPotterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class GetCharacterByHouseUseCase @Inject constructor(
    private val harryPotterRepository: HarryPotterRepository
) {
    operator fun invoke(houseName: String): Flow<Resource<Response<List<SingleCharacterModel>>>> = flow {
        try {
            emit(Resource.Loading())
            val characters = harryPotterRepository.getCharacterByHouse(houseName).body()?.map { it.toSingleCharacterModel() }
            emit(Resource.Success(Response.success(characters)))
        } catch (e: HttpException){
            //emit(Resource.Error(e.localizedMessage ?:" An unexpected error occurred"))
        } catch (e: IOException){
            //emit(Resource.Error("Can't reach server, check your internet connection"))
        }
    }
}