package com.rongyi.myapplication

import com.rongyi.webviewdemo.bean.MovieEntity
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Demo class
 *
 * @author yikwing
 * @date 2017/10/21
 */
interface MovieService {
    @GET("top250")
    fun getTopMovie(@Query("start") start: Int, @Query("count") count: Int): Observable<MovieEntity>
}