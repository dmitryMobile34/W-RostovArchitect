package com.twotwenty.rostovarchitecture

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.*


class MainActivity : AppCompatActivity() {

    private lateinit var firebaseFirestore: FirebaseFirestore
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: PlacesRVAdapter
    private lateinit var list: ArrayList<PlaceModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecyclerView()
        initDatabase()

    }

    private fun initDatabase() {

        firebaseFirestore = FirebaseFirestore.getInstance()

        firebaseFirestore.collection("Kazan").
        addSnapshotListener(object: EventListener<QuerySnapshot> {
            override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?) {
                for (dc: DocumentChange in value?.documentChanges!!){
                    if(dc.type == DocumentChange.Type.ADDED){
                        Log.d("devx", dc.document.toString())
                        list.add(dc.document.toObject(PlaceModel::class.java))
                        Log.d("devx", dc.document.toObject(PlaceModel::class.java).pointr.toString())

                    }
                }
                adapter.notifyDataSetChanged()
            }


        })
    }


    private fun initRecyclerView() {

        recyclerView = findViewById(R.id.rvPlaces)
        recyclerView.setHasFixedSize(true)
        list = arrayListOf()
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = PlacesRVAdapter(list, applicationContext)
        Log.d("devx", list.size.toString())

        recyclerView.adapter = adapter

    }

    companion object{
        fun openActivity(model: SerializeAdapter, context: Context){
            val bundle = Bundle()
            Log.d("devx", model.pointr_x.toString())

            bundle.putSerializable("model", model)
            val intent = Intent(context, PoiActivity::class.java)
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.putExtra("bundle", bundle)
            context.startActivity(intent)
        }
    }



}