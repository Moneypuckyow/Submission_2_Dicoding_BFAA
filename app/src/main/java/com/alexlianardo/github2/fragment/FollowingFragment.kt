package com.alexlianardo.github2.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.alexlianardo.github2.R
import com.alexlianardo.github2.UsersDetailActivity
import com.alexlianardo.github2.adapter.ListUsersAdapter
import com.alexlianardo.github2.model.Users
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import kotlinx.android.synthetic.main.fragment_following.*
import org.json.JSONArray
import org.json.JSONObject
import java.util.*
import kotlin.collections.ArrayList

class FollowingFragment : Fragment() {

    val username = ArrayList<String>()
    private val list = ArrayList<Users>()
    private lateinit var adapter: ListUsersAdapter

    companion object {
        private val TAG = FollowingFragment::class.java.simpleName
        const val EXTRA_USERS = "extra_users"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = ListUsersAdapter(list)
        list.clear()
        val users = activity!!.intent.getParcelableExtra(EXTRA_USERS) as Users?
        getUsersFollowingData(users?.username.toString())
        recycleViewFollowing.isNestedScrollingEnabled = false
    }


    private fun showRecyclerList() {
        recycleViewFollowing.layoutManager = LinearLayoutManager (context!!)
        val listUsersAdapter = ListUsersAdapter(list)
        recycleViewFollowing.adapter = listUsersAdapter
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_following, container, false)
    }

    private fun getUsersFollowingData(id: String) {
        if(progressBarFollowing != null) {
            progressBarFollowing.visibility = View.GONE
        }
        progressBarFollowing.visibility = View.VISIBLE
        val client = AsyncHttpClient()
        val url = "https://api.github.com/users/$id/following"
        client.addHeader("Authorization", "token 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")
        client.addHeader("User-Agent", "request")
        client.get(url, object : AsyncHttpResponseHandler(){
            override fun onSuccess(statusCode: Int, headers: Array<Header>? ,responseBody: ByteArray){
                progressBarFollowing.visibility = View.INVISIBLE
                val result = String(responseBody)
                Log.d(TAG, result)
                try {
                    val jsonArray = JSONArray(result)
                    for (i in 0 until jsonArray.length()) {
                        val jsonObject = jsonArray.getJSONObject(i)
                        val username = jsonObject.getString("login")
                        getDetailUsers(username.toString())
                    }
                } catch (e: Exception) {
                    Toast.makeText(activity, e.message, Toast.LENGTH_SHORT)
                        .show()
                    e.printStackTrace()
                }

            }
            override fun onFailure(
                statusCode: Int,
                headers: Array<Header>,
                responseBody: ByteArray,
                error: Throwable
            ) {
                progressBarFollowing.visibility = View.VISIBLE
                val errorMessage = when (statusCode) {
                    401 -> "$statusCode : Bad Request"
                    403 -> "$statusCode : Forbidden"
                    404 -> "$statusCode : Not Found"
                    else -> "$statusCode : ${error.message}"
                }
                Toast.makeText(activity, errorMessage, Toast.LENGTH_LONG)
                    .show()
            }
        })
    }

    private fun getDetailUsers(id: String) {
        if(progressBarFollowing != null) {
            progressBarFollowing.visibility = View.GONE
        }
        progressBarFollowing.visibility = View.VISIBLE
        val client = AsyncHttpClient()
        client.addHeader("User-Agent", "request")
        client.addHeader("Authorization", "token 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")
        val url = "https://api.github.com/users/$id"
        client.get(url, object : AsyncHttpResponseHandler() {
            override fun onSuccess(statusCode: Int,headers: Array<Header>,responseBody: ByteArray) {
                progressBarFollowing.visibility = View.INVISIBLE
                val result = String(responseBody)
                Log.d(TAG, result)
                try {
                    val jsonObject = JSONObject(result)
                    val username: String = jsonObject.getString("login").toString().toLowerCase(Locale.ROOT)
                    val name: String = jsonObject.getString("name")
                    val photo: String = jsonObject.getString("avatar_url")
                    val company: String = jsonObject.getString("company").toString()
                    val location: String = jsonObject.getString("location").toString()
                    val repository: String = jsonObject.getString("public_repos")
                    val followers: String = jsonObject.getString("followers")
                    val following: String = jsonObject.getString("following")

                    list.add(
                        Users(
                                username,
                                name,
                                company,
                                location,
                                photo,
                                repository,
                                followers,
                                following
                        )
                    )
                    showRecyclerList()
                } catch (e: Exception) {
                    Toast.makeText(activity, e.message, Toast.LENGTH_SHORT)
                        .show()
                    e.printStackTrace()
                }
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<Header>,
                responseBody: ByteArray,
                error: Throwable
            ) {
                progressBarFollowing.visibility = View.VISIBLE
                val errorMessage = when (statusCode) {
                    401 -> "$statusCode : Bad Request"
                    403 -> "$statusCode : Forbidden"
                    404 -> "$statusCode : Not Found"
                    else -> "$statusCode : ${error.message}"
                }
                Toast.makeText(activity, errorMessage, Toast.LENGTH_LONG)
                    .show()
            }
        })
    }



}
