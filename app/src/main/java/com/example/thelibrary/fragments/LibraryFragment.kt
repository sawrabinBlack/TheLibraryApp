package com.example.thelibrary.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.thelibrary.R
import com.example.thelibrary.delegate.LibraryViewPodDelegate
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import kotlinx.android.synthetic.main.fragment_library.*

class LibraryFragment : Fragment() {
lateinit var mDelegate:LibraryViewPodDelegate
    var tabList = listOf("Your Library", "Your Shelves")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_library, container, false)

    }

    fun switchFragment(fragment: Fragment) {
        childFragmentManager.beginTransaction().replace(R.id.flLibraryContainer, fragment).commit()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        switchFragment(YourBooksFragment())
        tabList.forEach {
            tabLibrary.newTab().apply {
                text = it
                tabLibrary.addTab(this)
            }
        }

        tabLibrary.addOnTabSelectedListener(object :OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.let {
                    when(it.position){
                        0-> switchFragment(YourBooksFragment())
                        1->switchFragment(YourShelvesFragment())
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })

    }




}