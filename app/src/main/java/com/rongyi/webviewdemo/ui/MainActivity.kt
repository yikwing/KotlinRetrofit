package com.rongyi.webviewdemo.ui

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.rongyi.myapplication.HttpMethods
import com.rongyi.webviewdemo.adapter.MovieAdapter
import com.rongyi.webviewdemo.bean.MovieEntity
import com.rongyi.webviewdemo.R
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var observer: Observer<MovieEntity>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        initView()
        initData()


    }

    private fun initData() {
        observer = object : Observer<MovieEntity> {
            override fun onComplete() {

            }

            override fun onSubscribe(d: Disposable) {
            }

            override fun onError(e: Throwable) {
            }

            override fun onNext(t: MovieEntity) {
                val subjects = t.subjects
                recycler.adapter = MovieAdapter(subjects, {
                    val intent = Intent(this@MainActivity, WebViewActivity::class.java)
                    intent.putExtra("url", it.alt)
                    startActivity(intent)
                }, this@MainActivity)
            }
        }

        HttpMethods.getInstance().getBook(observer, 0, 20)
    }

    private fun initView() {
        recycler.layoutManager = LinearLayoutManager(this)
    }
}
