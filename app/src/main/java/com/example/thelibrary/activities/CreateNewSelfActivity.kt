package com.example.thelibrary.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.thelibrary.R
import com.example.thelibrary.data.model.LibraryModel
import com.example.thelibrary.data.model.LibraryModelImpl
import com.example.thelibrary.mvp.presenter.CreateNewShelfPresenter
import com.example.thelibrary.mvp.presenter.CreateNewShelfPresenterImpl
import com.example.thelibrary.mvp.views.CreateNewShelfView
import kotlinx.android.synthetic.main.activity_create_new_self.*

class CreateNewSelfActivity : AppCompatActivity(), CreateNewShelfView {
    lateinit var mPresenter:CreateNewShelfPresenter

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, CreateNewSelfActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_new_self)
        setUpPresenter()
        etShelfName.setOnEditorActionListener(object : TextView.OnEditorActionListener {
            override fun onEditorAction(p0: TextView?, p1: Int, p2: KeyEvent?): Boolean {
                if (p1 == EditorInfo.IME_ACTION_DONE) {
                    Log.println(Log.INFO, "done", etShelfName.text.toString())
                    mPresenter.onTapDoneToCreateShelf(etShelfName.text.toString())
                    finish()
                }
                return false
            }
        }

        )

        ivBackCreateNewShelf.setOnClickListener {
            finish()
        }


    }

    private fun setUpPresenter() {
        mPresenter=ViewModelProvider(this)[CreateNewShelfPresenterImpl::class.java]
        mPresenter.iniView(this)
    }
}