package com.example.mindmate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mindmate.Adapter.CommentAdapter
import com.example.mindmate.Models.CommentModel
import com.google.firebase.database.*
import org.w3c.dom.Text
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class DetailedPostActivity : AppCompatActivity() {

    private lateinit var detailPostTv : TextView
    private lateinit var postComment : TextView
    private lateinit var recyclerView: RecyclerView
    private lateinit var dateTv : TextView
    private lateinit var commentList: ArrayList<CommentModel>
    private lateinit var adapter: CommentAdapter
    private lateinit var addComment : EditText
    private lateinit var imgBack : ImageView
    private lateinit var dbReferencePost: DatabaseReference
    private lateinit var dbReferenceComment: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed_post)

        commentList = arrayListOf<CommentModel>()
        detailPostTv = findViewById(R.id.postDetailTv)
        postComment = findViewById(R.id.postComment)
        recyclerView = findViewById(R.id.detailedPostRv)
        addComment = findViewById(R.id.addCmntEt)
        dateTv = findViewById(R.id.datePostTv)
        imgBack = findViewById(R.id.backBtnPost)

        detailPostTv.text = intent.getStringExtra("postText")
        val postId = intent.getStringExtra("postId")

        dateTv.text = intent.getStringExtra("postDate")

        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.setHasFixedSize(true)

        postComment.setOnClickListener {
            commentDataSave()
        }
        imgBack.setOnClickListener {
            onBackPressed()
        }

        dbReferencePost = FirebaseDatabase.getInstance().getReference("Posts/$postId")
        dbReferenceComment = dbReferencePost.child("Comments")
        getCommentsData()
    }

    private fun commentDataSave() {
        val commentText = addComment.text.toString()
        if (commentText.isEmpty()){
            addComment.error = "Enter a comment to post"
        }
        val commentId = dbReferenceComment.push().key!!
        val comment = CommentModel(commentId,commentText)
        dbReferenceComment.child(commentId).setValue(comment)
            .addOnSuccessListener {
                addComment.text.clear()
                Toast.makeText(this, "Comment posted successfully", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { error ->
                Toast.makeText(this, "Error occured : ${error.message}", Toast.LENGTH_SHORT).show()
            }
    }

    private fun getCommentsData() {
        dbReferenceComment.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                commentList.clear()
                if (snapshot.exists()){
                    for (commentSnap in snapshot.children){
                        val commentData = commentSnap.getValue(CommentModel::class.java)
                        commentList.add(commentData!!)
                    }
                    adapter = CommentAdapter(commentList)
                    recyclerView.adapter = adapter
                    recyclerView.isNestedScrollingEnabled = true

                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}