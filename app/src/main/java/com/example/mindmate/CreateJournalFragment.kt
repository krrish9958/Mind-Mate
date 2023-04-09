package com.example.mindmate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import kotlinx.coroutines.launch
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.coroutineScope
import com.example.mindmate.database.JournalsDatabase
import com.example.mindmate.entities.Journals
import java.text.SimpleDateFormat
import java.util.*

class CreateJournalFragment : BaseFragment() {

    private lateinit var tvDateTime : TextView
    private lateinit var imgDone : ImageView
    private lateinit var imgBack : ImageView
    private lateinit var etJournalTitle : EditText
    private lateinit var etJournalSubTitle : EditText
    private lateinit var etJournalDesc : EditText
    private var currentDate : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_journal, container, false)
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            CreateJournalFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvDateTime = view.findViewById(R.id.tvDateTime)
        imgDone = view.findViewById(R.id.imgDone)
        imgBack = view.findViewById(R.id.imgBack)
        etJournalTitle = view.findViewById(R.id.etJournalTitle)
        etJournalSubTitle = view.findViewById(R.id.etJournalSubTitle)
        etJournalDesc = view.findViewById(R.id.etJournalDesc)

        val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
        currentDate = sdf.format(Date())
        tvDateTime.text = currentDate

        imgDone.setOnClickListener {
            saveJournal()
        }
        imgBack.setOnClickListener {
            replaceFragment(InnerJournalsFragment())
        }

    }

    private fun saveJournal() {

        if (etJournalTitle.text.isNullOrEmpty()){
            Toast.makeText(context, "Title is required!", Toast.LENGTH_SHORT).show()
        }
        if (etJournalDesc.text.isNullOrEmpty()){
            Toast.makeText(context, "Add something in order to save!", Toast.LENGTH_SHORT).show()
        }

        launch {
            val journals = Journals()
            journals.title = etJournalTitle.text.toString()
            journals.subTitle = etJournalSubTitle.text.toString()
            journals.journalText = etJournalDesc.text.toString()
            journals.dateTime = currentDate
            context.let {
                if (it != null) {
                    JournalsDatabase.getDatabase(it).journalsDao().insertJournals(journals)
                }
                etJournalTitle.setText("")
                etJournalSubTitle.setText("")
                etJournalDesc.setText("")
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager: FragmentManager = (fragment as JournalsFragment).childFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout_journal, fragment)
            .addToBackStack(fragment.javaClass.simpleName)
        fragmentTransaction.commit()
    }
}