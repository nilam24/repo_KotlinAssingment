package com.example.ktask.view


import android.arch.lifecycle.*
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.ktask.R
import com.example.ktask.model.*
import com.example.ktask.viewmodel.ViewModel1
import kotlinx.android.synthetic.main.fragment_all_places.view.*
import org.json.JSONArray
import org.json.JSONObject
import java.lang.Exception
import java.util.HashMap
import kotlin.concurrent.thread

class AllPlaces : Fragment() {

    lateinit var viewmodel1 : ViewModel1
    lateinit var adapterPlace: ApllPlaceAdapter
    private var recycler: RecyclerView? = null
    var listPlace = ArrayList<ModelPlaces>()
    var list = ArrayList<ModelPlaces>()
    var map = HashMap<String, ModelPlaces>()
    var success: Long = 0
    var pos1: Int = 0
    var rtrlist : ArrayList<ModelPlaces> = arrayListOf<ModelPlaces>()
    var ll :String = "40.7484,-73.9857"
    var oauth_token :String = "NPKYZ3WZ1VYMNAZ2FLX1WLECAWSMUVOQZOIDBN53F3LVZBPQ"
    var v : String = "20180616"

    companion object {
        fun newInstance(): AllPlaces {
            return AllPlaces()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
          viewmodel1 = ViewModelProviders.of(this!!).get(ViewModel1::class.java)   ?: throw Exception("invalid activity")


        Toast.makeText(context, "hi kotlin  ", Toast.LENGTH_LONG).show()

    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
    }

    override fun onDetach() {
        super.onDetach()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var view = inflater.inflate(R.layout.fragment_all_places, container, false)

        var qu = Volley.newRequestQueue(context)
        var modelPlaces: ModelPlaces = ModelPlaces()


        var url: String =
            "https://api.foursquare.com/v2/venues/search?ll=40.7484,-73.9857&oauth_token=NPKYZ3WZ1VYMNAZ2FLX1WLECAWSMUVOQZOIDBN53F3LVZBPQ&v=20180616"

        val strReq = StringRequest(Request.Method.GET, url, Response.Listener<String> { response ->
            var resp = response.toString()
            val jsonObj: JSONObject = JSONObject(resp)
            val jsonobj2: JSONObject = jsonObj.getJSONObject("response")
            val jsonArray: JSONArray = jsonobj2.getJSONArray("venues")
            var result1: String = ""
            var result2: String = ""
            for (i in 0 until jsonArray.length()) {
                var jsonInner: JSONObject = jsonArray.getJSONObject(i)
                result1 = jsonInner.getString("id")
                result2 = jsonInner.getString("name")

                modelPlaces = ModelPlaces(result1, result2)
                listPlace.add(modelPlaces)
                Log.e("id=" + result1, "name =" + listPlace)

            }
            //---------------------------------------------

//            viewmodel1.apiCall(requireContext(),ll,oauth_token,v).observe(this, Observer { mps->
//
//                if(mps!=null)
//                rtrlist.add(mps)
//                if(rtrlist.size>0)
//                    rtrlist.addAll(rtrlist)
//            })

            //---------------------------------------------------------


            var dbList = arrayListOf<ModelPlaces>()

            //TODO : here
            //  var vm1 = PlaceAppDatabase.getDatabase(requireContext())
            // var placedao: PlaceDao = vm1!!.placedao()
//            placedao.getList1().observe(this, Observer { mps ->
//                if (mps!!.size > 0) {
//                    dbList.addAll(mps)
//                    if (!dbList.isEmpty()) {
//                        for (i in 0 until listPlace.size) {
//                            for (j in 0 until dbList.size) {
//                                if (dbList.get(j).placeId.equals(listPlace.get(i).placeId)) {
//                                    listPlace.get(i).isSaved = true
//                                }
//                            }
//                        }
//                    }

            var dbList1: ArrayList<ModelPlaces> = arrayListOf<ModelPlaces>()
            viewmodel1.select1().observe(this, Observer { mps ->
                if (mps!!.size >= 0) {
                    dbList1.addAll(mps)
                    if (!dbList1.isEmpty()) {
                        for (i in 0 until listPlace.size)
                            for (j in 0 until dbList1.size)
                                if (dbList1.get(j).placeId.equals(listPlace.get(i).placeId)) {
                                    listPlace.get(i).isSaved = true
                                }
                    }
                }

                var layoutManager: LinearLayoutManager? = null

                recycler = view?.findViewById<RecyclerView>(R.id.recycler)

                recycler?.apply {
                    layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
                    recycler.layoutManager = layoutManager
                    adapterPlace =
                        ApllPlaceAdapter(context, listPlace, object : ApllPlaceAdapter.TaskHandler1 {

                            override fun itemClick(pos: Int, modelPlaces: ModelPlaces, status: Int) {
                                try {
                                    listPlace.get(pos).isSaved = true
                                    adapterPlace.notifyDataSetChanged()
                                    thread(true) {
                                        viewmodel1.insert(modelPlaces)
                                    }

                                    Toast.makeText(activity, "" + pos + modelPlaces, Toast.LENGTH_LONG).show()

                                } catch (e: Exception) {
                                    e.printStackTrace()
                                }
                            }
                        })
                    recycler.adapter = adapterPlace
                    adapterPlace.notifyDataSetChanged()
                }
            })

        }, Response.ErrorListener { Log.e("error volley", "") })
        qu.add(strReq)

        return view

    }

//    fun dataCheck(modelPlaces: ModelPlaces){
//
//        var modelPlacesFromDb = ModelPlaces()
//
//        vm.allData.observe(this,Observer<ModelPlaces>(){
//            it->modelPlacesFromDb.placeId
//
//        })

 //   }

}

