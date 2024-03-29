package com.example.ktask.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.example.ktask.R
import com.example.ktask.model.ModelPlaces
import com.example.ktask.viewmodel.ViewModel1
import kotlinx.android.synthetic.main.fragment_save_places.view.*
import java.lang.Exception
import kotlin.concurrent.thread

class SavedPlaces : Fragment() {

    lateinit var vm: ViewModel1
    var list = arrayListOf<ModelPlaces>()
    private var recycler1: RecyclerView? = null
    lateinit var adapterSave: SaveAdapter
    var modelPlaces = ModelPlaces()
    var pos1 = 0
    lateinit var progressBar: ProgressBar

    companion object {
        fun newInstance(): SavedPlaces {
            return SavedPlaces()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm = ViewModelProviders.of(this).get(ViewModel1::class.java) ?: throw Exception("invalid model")

    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)

    }

    override fun onDetach() {
        super.onDetach()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var view = inflater.inflate(R.layout.fragment_save_places, container, false)
        var layoutManager: LinearLayoutManager? = null

        recycler1 = view.findViewById(R.id.recycler1)
        progressBar = view.findViewById<ProgressBar>(R.id.progressBar)

        getAllPlaces()
        recycler1?.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            recycler1.layoutManager = layoutManager
            // recycler1.addItemDecoration(DividerItemDecoration(context,LinearLayoutManager.VERTICAL))
            adapterSave = SaveAdapter(requireContext(), list, object : SaveAdapter.TaskHandler2 {
                override fun itemClick(pos: Int, list1:ArrayList<ModelPlaces>) {
                    //pos1=pos

                        vm.delete(list1.get(pos))

                                list1.clear()
                                adapterSave.notifyDataSetChanged()

                }
            })
            recycler1.adapter = adapterSave

        }
        return view
    }

    fun getAllPlaces() {
        vm.select1().observe(this, Observer { mps ->

            if (mps!!.isNotEmpty()) {
                list.addAll(mps)
                adapterSave.notifyDataSetChanged()
            }
        })
    }

    override fun onResume() {
        super.onResume()

        adapterSave.notifyDataSetChanged()

    }
}

//////////////////////////////////////////////////////////////////////
//        var vm1 = PlaceAppDatabase.getDatabase(requireContext())
//        var placedao: PlaceDao = vm1!!.placedao()
//        placedao.getList1().observe(this,  Observer { mps ->
//            if (mps!!.size > 0) {
//                list.addAll(mps)
//
//                adapterSave.notifyDataSetChanged()
//
//            }
//        })
//////////        /////////////////////////////////////////////////






//        vm.allData.observe(this,object:Observer<ModelPlaces>{
//            override fun onChanged(t: ModelPlaces?) {
//
//                if(t!=null) {
//                    list.add(t!!)
//                    recycler1!!.adapter = adapterSave
//                }
//
//
//                Log.e("","list =="+list)
////
////
//            }

//                      allData->
//            if (allData != null) {
//
////                list.add(allData)
////                list.iterator().hasNext()
////                list.iterator().next().placeId
////                list.iterator().next().placeName
//
//
//                map.put(allData.placeId,allData)
//                map.iterator().next()
//                Log.e("","map=="+map)
//
//            }


    //    })

 //   }

//    fun updateView(modelPlaces: ModelPlaces){
//        var newList = ArrayList<ModelPlaces>()
//        newList.add(modelPlaces)
//        adapterSave = SaveAdapter(activity!!,newList, object : SaveAdapter.TaskHandler2{
//            override fun itemClick(pos: Int, modelPlaces: ModelPlaces,Status:Boolean) {
//
//
//            }
//        })
//        recycler1?.apply {
//          layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
//            recycler1.layoutManager = layoutManager
//            recycler1?.adapter = adapterSave
//        }
//    }
//}

