package dev.mathitos.dndsheet.main

import android.os.Bundle
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf

import com.google.android.material.floatingactionbutton.FloatingActionButton
import dev.mathitos.dndsheet.R
import dev.mathitos.dndsheet.entities.Sheet

class MainActivity : AppCompatActivity(), MainContract.View {
    private lateinit var presenter: MainContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = MainPresenter()
        presenter.bindView(this)

        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        navView.setupWithNavController(navController)

        setupMenuButton()
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    private fun setupMenuButton() {
        val menuButton = findViewById<FloatingActionButton>(R.id.menu_button)
        menuButton.setOnClickListener {
            presenter.onMenuButtonClick()
        }
    }

    override fun openDrawer() {
        val drawerLayout: DrawerLayout = this.findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)

        drawerLayout.openDrawer(navView)
    }

    fun showText(text: String) {
        Toast.makeText(this@MainActivity, text, Toast.LENGTH_SHORT).show()
    }

    fun showSheetPage(sheet: Sheet) {
        val navController = findNavController(R.id.nav_host_fragment)
        val bundle = Bundle()
        bundle.putSerializable("sheet", sheet)
        navController.navigate(R.id.nav_sheet, bundle)
    }
}
