package com.example.thelibrary.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.thelibrary.R
import com.example.thelibrary.adapter.AddToShelvesAdapter
import com.example.thelibrary.data.model.LibraryModel
import com.example.thelibrary.data.model.LibraryModelImpl
import com.example.thelibrary.delegate.AddToShelfDelegate
import com.example.thelibrary.mvp.presenter.AddToShelvesPresenter
import com.example.thelibrary.mvp.presenter.AddToShelvesPresenterImpl
import com.example.thelibrary.mvp.views.AddToShelvesView
import com.example.thelibrary.persistance.entities.LibraryBooksVO
import com.example.thelibrary.persistance.entities.ShelvesVO
import kotlinx.android.synthetic.main.activity_add_to_shelves.*

class AddToShelvesActivity : AppCompatActivity(), AddToShelvesView {
    lateinit var mAdapter: AddToShelvesAdapter
    lateinit var mPresenter: AddToShelvesPresenter

    companion object {
        const val IE_ADD_TO_SHELVES = "IE_ADD_TO_SHELVES"
        fun newIntent(context: Context, data: String): Intent {
            val intent = Intent(context, AddToShelvesActivity::class.java)
            intent.putExtra(IE_ADD_TO_SHELVES, data)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_to_shelves)
        setUpPresenter()
        val mData = intent.getStringExtra(IE_ADD_TO_SHELVES)
        mData?.let {
            mPresenter.onUiReady(this, it)
        }

        setUpRecyclerView()
        setUpOnClickListener()
    }

    private fun setUpPresenter() {
        mPresenter = ViewModelProvider(this)[AddToShelvesPresenterImpl::class.java]
        mPresenter.iniView(this)
    }

    private fun setUpOnClickListener() {
        btnConfirmAddToShelves.setOnClickListener {
            mPresenter.onSelectShelves()
            finish()
        }
    }

    private fun setUpRecyclerView() {
        mAdapter = AddToShelvesAdapter(mPresenter)
        rvAddToShelves.adapter = mAdapter
        rvAddToShelves.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }


    override fun showShelvesList(shelvesList: List<ShelvesVO>) {
        Log.println(Log.INFO, "shelveList", shelvesList.toString())
        mAdapter.setNewData(shelvesList)
    }

    override fun navigateToPreviousScreen() {
        super.onBackPressed()
    }
}