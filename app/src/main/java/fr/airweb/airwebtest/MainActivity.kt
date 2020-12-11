package fr.airweb.airwebtest

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import fr.airweb.airwebtest.domain.models.PsgModelTypeEnum
import fr.airweb.airwebtest.ui.NewsViewModel
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {
    private val viewModel: NewsViewModel by inject()
    var contactBtn: FloatingActionButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        contactBtn = findViewById<FloatingActionButton>(R.id.contact)
        setSupportActionBar(toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_news -> {
                viewModel.fetchByType(PsgModelTypeEnum.NEWS)
                true
            }
            R.id.action_hot -> {
                viewModel.fetchByType(PsgModelTypeEnum.HOT)
                true
            }
            R.id.action_actualite -> {
                viewModel.fetchByType(PsgModelTypeEnum.ACTUALITE)
                true
            }
            R.id.action_sort_by_title -> {
                viewModel.sortListByTitle()
                true
            }
            R.id.action_sort_by_date -> {
                viewModel.sortListByDate()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}