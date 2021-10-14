package com.example.rssfeed

import android.app.ProgressDialog
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {

    lateinit var rv: RecyclerView
    lateinit var movies: MutableList<Movies>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rv = findViewById(R.id.rvMain)

        FetchMovies().execute()
    }

    private inner class FetchMovies :AsyncTask<Void, Void, MutableList<Movies>>(){
        val parser = XmlParser()
        val progressD = ProgressDialog(this@MainActivity)

        override fun onPreExecute() {
            super.onPreExecute()
            progressD.setMessage("Please Wait")
            progressD.show()
        }

        override fun doInBackground(vararg p0: Void?): MutableList<Movies> {

            val url = URL("http://www.rediff.com/rss/moviesreviewsrss.xml")
            val urlConnection = url.openConnection() as HttpURLConnection
            movies =
                urlConnection.inputStream?.let { parser.parse(it) } as MutableList<Movies>
            return movies
        }

        override fun onPostExecute(result: MutableList<Movies>?) {
            super.onPostExecute(result)
            progressD.dismiss()
            rv.adapter = RVAdapter(result)
            rv.layoutManager = LinearLayoutManager(applicationContext)
        }

    }
}