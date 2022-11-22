package com.example.thelibrary.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.thelibrary.R
import com.example.thelibrary.activities.CreateNewSelfActivity
import com.example.thelibrary.activities.ShelfDetailActivity
import com.example.thelibrary.adapter.ShelvesAdapter
import com.example.thelibrary.mvp.presenter.YourShelvesPresenter
import com.example.thelibrary.mvp.presenter.YourShelvesPresenterImpl
import com.example.thelibrary.mvp.views.YourShelvesView
import com.example.thelibrary.persistance.entities.ShelvesVO
import kotlinx.android.synthetic.main.fragment_your_shelves.*

class YourShelvesFragment : Fragment(), YourShelvesView {
    lateinit var mContext: Context
    lateinit var mAdapter: ShelvesAdapter
    lateinit var mPresenter: YourShelvesPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_your_shelves, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpPresenter()
        setUpRecyclerView()
        setUpOnClickListener()
        mPresenter.onUiReady(this)
    }

    private fun setUpOnClickListener() {
        btnCreateNewLibrary.setOnClickListener {
            mPresenter.onTapCreateNewLibrary()
        }
    }

    private fun setUpRecyclerView() {
        mAdapter = ShelvesAdapter(mPresenter)
        rvShelves.adapter = mAdapter
        rvShelves.layoutManager = LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false)
    }

    private fun setUpPresenter() {
        mPresenter = ViewModelProvider(this)[YourShelvesPresenterImpl::class.java]
        mPresenter.initHomeView(this)
    }

    override fun showShelves(shelfList: List<ShelvesVO>) {
        mAdapter.setNewData(shelfList)
        if (shelfList.isEmpty())
        {
            llBookNoShelf.visibility=View.VISIBLE
        } else {
            llBookNoShelf.visibility=View.GONE
        }
        Log.println(Log.INFO,"shelfList",shelfList.toString())
    }

    override fun navigateToAddNewShelfScreen() {
        startActivity(CreateNewSelfActivity.newIntent(mContext))
    }

    override fun navigateToShelfDetail(shelfName: String) {
        startActivity(ShelfDetailActivity.newIntent(mContext, shelfName))
    }


}