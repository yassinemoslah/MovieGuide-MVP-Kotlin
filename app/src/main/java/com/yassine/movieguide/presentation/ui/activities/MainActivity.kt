package com.yassine.movieguide.presentation.ui.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.yassine.movieguide.R
import com.yassine.movieguide.core.models.Category
import com.yassine.movieguide.presentation.presenters.implementations.MainPresenterImplementation
import com.yassine.movieguide.presentation.presenters.interfaces.MainPresenter
import com.yassine.movieguide.presentation.ui.adapters.CategoriesAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainPresenter.View {

    private val mainPresenter = MainPresenterImplementation()
    private lateinit var categoriesAdapter: CategoriesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        categoriesAdapter = CategoriesAdapter()
        rvCategories.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvCategories.adapter = categoriesAdapter

        mainPresenter.setView(this)
    }


    override fun showCategories(categories: ArrayList<Category>) {
        hideProgress()
        categoriesAdapter.setCategories(categories)
    }

    override fun updateCategory(categorie: Category) {
        categoriesAdapter.updateCategory(categorie)
    }

    override fun hideProgress() {
        progressBar.visibility = View.GONE
    }

    override fun onDestroy() {
        super.onDestroy()
        mainPresenter.destroy()
    }

    override fun showError() {
        Toast.makeText(this, "Please verify your internet connexion", Toast.LENGTH_SHORT).show()
    }
}