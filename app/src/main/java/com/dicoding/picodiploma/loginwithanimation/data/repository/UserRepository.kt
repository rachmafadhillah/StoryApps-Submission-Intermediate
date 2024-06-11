package com.dicoding.picodiploma.loginwithanimation.data.repository

import com.dicoding.picodiploma.loginwithanimation.data.api.ApiService
import com.dicoding.picodiploma.loginwithanimation.data.pref.UserModel
import com.dicoding.picodiploma.loginwithanimation.data.pref.UserPreference
import com.dicoding.picodiploma.loginwithanimation.data.response.LoginResponse
import com.dicoding.picodiploma.loginwithanimation.data.response.RegisterResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.HttpException

class UserRepository private constructor(
    private val userPreference: UserPreference,
    private val apiService: ApiService
) {

    suspend fun register(name: String, email: String, password: String): RegisterResponse {
        return try {
            apiService.register(name, email, password)
        } catch (e: HttpException) {
            val errorBody = e.response()?.errorBody()?.string()
            throw Exception(errorBody)
        } catch (e: Throwable) {
            throw Exception(e.message)
        }
    }

    suspend fun login(email: String, password: String): LoginResponse {
        return try {
            val response = apiService.login(email, password)
            val token = response.loginResult?.token ?: ""
            val userModel = UserModel(email = email, token = token, isLogin = true)
            saveSession(userModel)
            response
        } catch (e: HttpException) {
            val errorBody = e.response()?.errorBody()?.string()
            throw Exception(errorBody)
        } catch (e: Throwable) {
            throw Exception(e.message)
        }
    }

    suspend fun saveSession(user: UserModel) {
        userPreference.saveSession(user)
    }

    fun getSession(): Flow<UserModel> {
        return userPreference.getSession()
    }

    suspend fun logout() {
        userPreference.logout()
    }

    companion object {
        fun getInstance(
            apiService: ApiService,
            userPreference: UserPreference
        )= UserRepository(userPreference, apiService)
    }
}