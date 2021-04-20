package com.alexlianardo.github2

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.alexlianardo.github2.adapter.SectionsPagerAdapter
import com.alexlianardo.github2.databinding.ActivityUsersDetailBinding
import com.alexlianardo.github2.model.Users
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_users_detail.*
import kotlinx.android.synthetic.main.fragment_followers.*
import kotlinx.android.synthetic.main.fragment_following.*
import kotlinx.android.synthetic.main.users_item.*
import org.json.JSONObject
import java.util.*

class UsersDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUsersDetailBinding

    companion object {
        private val TAG = MainActivity::class.java.simpleName

        const val EXTRA_USERS = "extra_users"
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.followers,
            R.string.following
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users_detail)

        binding = ActivityUsersDetailBinding.inflate(layoutInflater)

        setContentView(binding.root)

        //mengambil data username user yang diklik di constant value EXTRA_USERS
        val users = intent.getParcelableExtra(EXTRA_USERS) as Users?

        //mengirim data EXTRA_USERS ke function getData
        getData(users?.username)

        //bagian Tab Layout
        val sectionAdapter = SectionsPagerAdapter(this)
        val viewPager: ViewPager2 = findViewById(R.id.view_pager)
        viewPager.adapter = sectionAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        TabLayoutMediator(tabs, viewPager) {tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()

        supportActionBar?.elevation = 0f

    }

    //function untuk mengambil data yang diperlukan untuk digunakan di User Detail Activity lalu di tampilkan di activity_users_detail
    private fun getData(id: String?) {
        if(progressBar_detail != null) {
            progressBar_detail.visibility = View.GONE
        }
        progressBar_detail.visibility = View.VISIBLE
        val client = AsyncHttpClient()
        val url = "https://api.github.com/users/$id"
        client.addHeader("Authorization", "token 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")
        client.addHeader("User-Agent", "request")
        client.get(url, object : AsyncHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Array<Header>, responseBody: ByteArray) {
                val result = String(responseBody)
                Log.d(TAG, result)
                progressBar_detail.visibility = View.INVISIBLE
                try {
                    val jsonObject = JSONObject(result)
                    val uname = jsonObject.getString("login").toLowerCase(Locale.ROOT)
                    val name = jsonObject.getString("name")
                    val photo = jsonObject.getString("avatar_url")
                    val following = jsonObject.getString("following")
                    val followers = jsonObject.getString("followers")
                    val repository = jsonObject.getString("public_repos")
                    val company = jsonObject.getString("company")
                    val location = jsonObject.getString("location")
                    Glide.with(this@UsersDetailActivity)
                        .load(photo)
                        .apply(RequestOptions().override(250, 250))
                        .into(detail_img_user)
                    detail_username.text = uname
                    detail_name.text = name
                    detail_following.text = following
                    detail_followers.text = followers
                    detail_company.text = company
                    detail_location.text = location
                    detail_repository.text = repository
                } catch (e: Exception) {
                    Toast.makeText(this@UsersDetailActivity, e.message, Toast.LENGTH_SHORT).show()
                    e.printStackTrace()
                }
            }
            override fun onFailure(statusCode: Int, headers: Array<Header>, responseBody: ByteArray, error: Throwable) {
                progressBar_detail.visibility = View.INVISIBLE
                val errorMessage = when (statusCode) {
                    401 -> "$statusCode : Bad Request"
                    403 -> "$statusCode : Forbidden"
                    404 -> "$statusCode : Not Found"
                    else -> "$statusCode : ${error.message}"
                }
                Toast.makeText(this@UsersDetailActivity, errorMessage, Toast.LENGTH_SHORT).show()
            }
        })
    }
}