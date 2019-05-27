package com.winualltask.myapplication.networking

import com.winualltask.myapplication.R
import retrofit2.Call


// takes the retrofit request and makes an API call and send back the result through sealed class
fun <T> process(call:Call<T>):ProcessRequestResponse?{
    try {
        val response = call.execute()
        return if (response.isSuccessful && response.body()!=null){
            ProcessRequestResponse.Success(response.body())
        }else {
            ProcessRequestResponse.Failure(R.string.something_went_wrong)
        }
    }catch (e:Exception){
        e.printStackTrace()
        return ProcessRequestResponse.Failure(R.string.something_went_wrong)
    }
}
sealed class ProcessRequestResponse{
    data class Success<T>(var success: T):ProcessRequestResponse()
    data class Failure(var msgId:Int):ProcessRequestResponse()
}