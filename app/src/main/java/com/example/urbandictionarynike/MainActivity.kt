package com.example.urbandictionarynike

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.urbandictionarynike.model.DefinitionItem
import com.example.urbandictionarynike.model.Repository
import com.example.urbandictionarynike.model.RepositoryImpl
import com.example.urbandictionarynike.model.UIState
import com.example.urbandictionarynike.viewmodel.DictionaryProvider
import com.example.urbandictionarynike.viewmodel.DictionaryViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    /**
     * For Android components (Activity, Services, Broadcast Rec., Content Prov.)
     * we can only use
     * Field Injection.
     */
    @Inject
    lateinit var viewModelProvider: DictionaryProvider

    private val viewModel by lazy{
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

        viewModel.definitions.observe(this){
            when (it){
                is UIState.Response -> updateAdapter(it.presentationData)
                is UIState.Error -> showError(it.errorMessage)
                is UIState.Loading -> displayLoading(it.isLoading)
                is UIState.Online -> displayConnectivityBanner(it.isOnline)
            }
        }

    }

    private fun displayConnectivityBanner(online: Boolean) {

    }

    private fun displayLoading(loading: Boolean) {

    }

    private fun showError(errorMessage: String) {

    }

    private fun updateAdapter(presentationData: List<DefinitionItem>) {

    }

    override fun onStop() {
        super.onStop()
        job.cancel()
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