package com.example.mindmate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.mindmate.adapter.DocsData
import com.example.mindmate.adapter.HomeDocs
import com.example.mindmate.adapter.HomeSuggestedMusic


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter : HomeSuggestedMusic
    private lateinit var recyclerView2 : RecyclerView
    private lateinit var docDataArrayList : ArrayList<DocsData>
    private lateinit var docImg : Array<Int>
    private lateinit var docName : Array<String>
//    private late init var musicData : ArrayList<Music data>
//    private lateinit var youTubePlayerView: YouTubePlayerView
//    private lateinit var videoId : String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        // Inflate the layout for this fragment
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

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}