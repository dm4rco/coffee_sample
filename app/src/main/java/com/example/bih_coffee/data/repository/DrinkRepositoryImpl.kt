package com.example.bih_coffee.data.repository

import com.example.bih_coffee.data.local.DrinkDao
import com.example.bih_coffee.data.remote.SamplesApi
import com.example.bih_coffee.domain.model.Drink
import com.example.bih_coffee.common.Status
import com.example.bih_coffee.domain.model.Review
import com.example.bih_coffee.domain.repository.DrinkRepository
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class DrinkRepositoryImpl @Inject constructor(
    private val api: SamplesApi,
    private val dao: DrinkDao
) : DrinkRepository {

    override fun getDrinks(onlyDatabase: Boolean): Flow<Status<List<Drink>>> = flow {
        emit(Status.Loading())


        if (onlyDatabase.not()) {
            val drinks = dao.getDrinks().map { it.toDrink() }
            emit(Status.Loading(data = drinks))

            try {
                val remoteDrinks = api.getDrinks()

                if (drinks.isEmpty()) {
                    dao.insertDrinks(remoteDrinks.map {
                        it.toDrinkEntity()
                    })
                } else {
                    dao.insertDrinks(remoteDrinks.map {
                        it.toDrinkEntity(drinks.getOrNull(it.id - 1)?.isLiked)
                    })
                }
            } catch (e: HttpException) {
                emit(
                    Status.Error(
                        exception = e,
                        data = drinks
                    )
                )
            } catch (e: IOException) {
                emit(
                    Status.Error(
                        exception = e,
                        data = drinks
                    )
                )
            }
        }

        val newDrinks = dao.getDrinks().map { it.toDrink() }
        emit(Status.Success(newDrinks))
    }

    override fun getDrinkById(id: Int): Flow<Status<Drink>> = flow {
        val drink = dao.getDrinkById(id).toDrink()
        emit(Status.Success(drink))
    }

    override suspend fun updateLikeStatusById(isLiked: Boolean, id: Int) = coroutineScope {
        dao.updateLikeStatusById(isLiked, id)
    }

    override suspend fun sendReview(review: Review): Flow<Status<Any>> = flow {
        try {
            api.sendReview(review)
        } catch (e: HttpException) {
            emit(
                Status.Error(exception = e)
            )
        } catch (e: IOException) {
            emit(
                Status.Error(exception = e)
            )
        }

    }
}