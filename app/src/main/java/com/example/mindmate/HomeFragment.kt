package com.example.mindmate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mindmate.Adapter.DocsData
import com.example.mindmate.Adapter.HomeDocs
import com.example.mindmate.Adapter.HomeSuggestedMusic

class HomeFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter : HomeSuggestedMusic
    private lateinit var recyclerView2 : RecyclerView
    private lateinit var docDataArrayList : ArrayList<DocsData>
    private lateinit var docImg : Array<Int>
    private lateinit var docName : Array<String>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.homeMusicRecyclerView)
        adapter = HomeSuggestedMusic()
        recyclerView.layoutManager = LinearLayoutManager(context,
            LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = adapter
        recyclerView2 = view.findViewById(R.id.docs_recycler_view)
        dataInitialize()
        val layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL, false)
        recyclerView2.layoutManager = layoutManager
        recyclerView2.hasFixedSize()
        recyclerView2.adapter = HomeDocs(docDataArrayList)
    }

    private fun dataInitialize() {
        docDataArrayList = arrayListOf<DocsData>()
        docImg = arrayOf(
            R.drawable.therapist_1,
            R.drawable.therapist_2,
            R.drawable.therapist_3,
            R.drawable.therapist_4,
            R.drawable.therapist_5
        )

        docName = arrayOf(
            getString(R.string.therapist_1),
            getString(R.string.therapist_2),
            getString(R.string.therapist_3),
            getString(R.string.therapist_4),
            getString(R.string.therapist_5)
        )

        for (i in docImg.indices){
            val data = DocsData(docImg[i], docName[i])
            docDataArrayList.add(data)
        }
    }
}