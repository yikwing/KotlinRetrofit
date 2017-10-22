package com.rongyi.myapplication


import android.util.Log
import com.rongyi.webviewdemo.bean.MovieEntity
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit


/**
 * Demo class
 *
 * @author yikwing
 * @date 2017/10/21
 */
class HttpMethods private constructor() {

    val DEFAULT_TIMEOUT = 5L

    val BASE_URL = "https://api.douban.com/v2/movie/"

    var movieService: MovieService

    val loggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message ->
        Log.d("RetrofitLog", "retrofitBack = " + message)
    })


    init {
        //手动创建一个OkHttpClient并设置超时时间
        val httpClientBuilder = OkHttpClient.Builder()
        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)

        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        httpClientBuilder.addInterceptor(loggingInterceptor)


        var retrofit = Retrofit.Builder()
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build()

        movieService = retrofit.create(MovieService::class.java)
    }

    companion object {
        fun getInstance(): HttpMethods {
            return SingletonHolder.INSTANCE
        }
    }

    private object SingletonHolder {
        val INSTANCE = HttpMethods()
    }

    fun getBook(observer: Observer<MovieEntity>, start: Int, count: Int) {

        movieService.getTopMovie(start, count)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
