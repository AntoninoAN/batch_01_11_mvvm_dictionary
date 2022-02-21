package com.example.urbandictionarynike

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import com.example.urbandictionarynike.di.DictionaryApplication
import com.example.urbandictionarynike.model.DefinitionItem
import com.example.urbandictionarynike.model.Repository
import com.example.urbandictionarynike.model.RepositoryImpl
import com.example.urbandictionarynike.model.UIState
import com.example.urbandictionarynike.model.local.DictionaryRoom
import com.example.urbandictionarynike.viewmodel.DictionaryProvider
import com.example.urbandictionarynike.viewmodel.DictionaryViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {

    /**
     * For Android components (Activity, Services, Broadcast Rec., Content Prov.)
     * we can only use
     * Field Injection.
     */
    @Inject
    lateinit var viewModelProvider: DictionaryProvider

    private val viewModel by lazy {
        viewModelProvider.create(DictionaryViewModel::class.java)
    }

    //    val repo = RepositoryImpl()
//    private val viewmodel = DictionaryViewModel(
//        repo
//    )
    lateinit var job: Deferred<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

// Because we use Field injection
        DictionaryApplication.component.inject(this)


        viewModel.definitions.observe(this) {
            when (it) {
                is UIState.Response -> updateAdapter(it.presentationData)
                is UIState.Error -> showError(it.errorMessage)
                is UIState.Loading -> displayLoading(it.isLoading)
                is UIState.Online -> displayConnectivityBanner(it.isOnline)
            }
        }
    }

    private fun displayConnectivityBanner(online: Boolean) {
        Log.d(TAG, "displayConnectivityBanner: $online")
    }

    private fun displayLoading(loading: Boolean) {
        Log.d(TAG, "displayLoading: $loading")
    }

    private fun showError(errorMessage: String) {
        Log.d(TAG, "showError: $errorMessage")
    }

    private fun updateAdapter(presentationData: List<DefinitionItem>) {
        Log.d(TAG, "updateAdapter: $presentationData")
    }

    override fun onStop() {
        super.onStop()
        job.cancel()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.search_menu, menu)
        val actionMenu = menu?.findItem(R.id.search_term)
        (actionMenu?.actionView as SearchView)
            .setOnQueryTextListener(
                object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        query?.let {
                            viewModel.findNewTerm(it)
                        }
                        return true
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {
                        return false
                    }
                }
            )
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        super.onOptionsItemSelected(item)
        return when (item.itemId) {
            R.id.sort_down_menu -> {
                viewModel.sort(DictionaryViewModel.SortingState.DOWN)
                true
            }
            R.id.sort_up_menu -> {
                viewModel.sort(DictionaryViewModel.SortingState.UP)
                true
            }
            else -> false
        }

    }
}

/**
 * Coroutines
 * Suspend Fun [defines a non-blocking task/execution/operation]
 * coroutine context [how will be executed, Dispatcher{IO,MAIN,DEFAULT}]
 * coroutine Scope [Job to be executed {launch, async}]
 */

suspend fun foo() {
    doo()
}

suspend fun doo(): String {

    return ""
}

// Koin
// Service Locator
// object DictionaryKoin{
//
//    singleton{
//        RepositoryImpl()
//    }
//    provide{
//        DictionaryRoom.getDatabase().getDao()
//    }
//
//    fun provideRoom(): Dao{
//        return
//    }
//    fun provideRepository =
//}





