package fr.airweb.airwebtest.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.airweb.airwebtest.MainActivity
import fr.airweb.airwebtest.utils.Constants
import fr.airweb.airwebtest.adapters.NewsItemRecyclerViewAdapter
import fr.airweb.airwebtest.R
import fr.airweb.airwebtest.domain.models.PsgModel
import fr.airweb.airwebtest.ui.NewsViewModel
import fr.airweb.airwebtest.ui.UiNewsEvent
import fr.airweb.airwebtest.utils.CellClickListener
import org.koin.android.ext.android.inject

class ListNewsFragment : Fragment(), CellClickListener, SearchView.OnQueryTextListener {

    private val viewModel: NewsViewModel by inject()
    private var progressLoading: ProgressBar? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list_news, container, false)
        val rcyListNews = view.findViewById<RecyclerView>(R.id.RcyVlistNews)
        progressLoading = view.findViewById(R.id.Progressoading)
        displayLoading(true)
        rcyListNews!!.layoutManager = LinearLayoutManager(requireActivity())
        val editSearch = view.findViewById<SearchView>(R.id.search)
        editSearch.setOnQueryTextListener(this)

        //init the Custom adataper
        val adapter = NewsItemRecyclerViewAdapter(
            requireContext(),
            this
        )
        //set the CustomAdapter
        rcyListNews.adapter = adapter
        viewModel.uiNewsEvent.observe(
            requireActivity(),
            Observer { uiNewEvent ->
                when (uiNewEvent) {
                    is UiNewsEvent.DisplayAllNews -> {
                        adapter.setNewsList(uiNewEvent.news)
                        displayLoading(false)
                    }
                    is UiNewsEvent.DisplayNewsByType -> adapter.setNewsList(uiNewEvent.newsByType)
                    is UiNewsEvent.DisplayNewsSortedByTitle -> adapter.setNewsList(uiNewEvent.newsSortedByTitle)
                    is UiNewsEvent.DisplayNewsSortedByDate -> adapter.setNewsList(uiNewEvent.newsSortedByDate)
                    is UiNewsEvent.DisplayNewsSearchedByTitle -> adapter.setNewsList(uiNewEvent.newsSearchedByTitle)
                }
            })
        navigateToContactFragment()
        return view
    }

    private fun displayLoading(loading: Boolean) {
        progressLoading?.visibility = if (loading) View.VISIBLE else View.GONE
    }

    override fun onCellClickListener(psgModel: PsgModel) {
        val bundle = bundleOf(Constants.PSG_NEW_DETAIL to psgModel)
        view?.findNavController()
            ?.navigate(R.id.action_ListNewsFragment_to_DetailsNewsFragment, bundle)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        query?.let { viewModel.searchNewsByTitle(it) }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        newText?.let { viewModel.searchNewsByTitle(it) }
        return true
    }

    private fun navigateToContactFragment() {
        (requireActivity()as MainActivity).contactBtn?.setOnClickListener {
            view?.findNavController()
                ?.navigate(R.id.action_ListNewsFragment_to_ContactFragment)
        }
    }
}