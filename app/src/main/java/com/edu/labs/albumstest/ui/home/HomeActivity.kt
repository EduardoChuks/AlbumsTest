package com.edu.labs.albumstest.ui.home

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
import com.edu.labs.albumstest.R
import com.edu.labs.albumstest.databinding.ActivityHomeBinding
import com.edu.labs.albumstest.databinding.DialogAlbumDetailBinding
import com.edu.labs.albumstest.domain.model.Album
import com.edu.labs.albumstest.util.extensions.bind
import com.edu.labs.albumstest.util.extensions.setQuery
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.ext.android.viewModel

@ExperimentalCoroutinesApi
class HomeActivity : AppCompatActivity() {

    private val viewModel: HomeViewModel by viewModel()

    private lateinit var binding: ActivityHomeBinding
    private lateinit var adapter: AlbumsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupView()
        bindViewModel()
    }

    private fun setupView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)

        adapter = AlbumsAdapter(this::showDetail)
        binding.albumsRecyclerView.adapter = adapter
        binding.albumsRecyclerView.focusView = binding.searchView

        val dividerDecor = DividerItemDecoration(this, VERTICAL)
        dividerDecor.setDrawable(resources.getDrawable(R.drawable.layout_line_divider, theme))
        binding.albumsRecyclerView.addItemDecoration(dividerDecor)

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean = false

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.updateArtistQuery(newText.orEmpty())
                viewModel.search() // search while typing
                return false
            }
        })
    }

    private fun bindViewModel() {
        bind(viewModel.artistNameQuery) {
            binding.searchView.setQuery(it)
        }
        bind(viewModel.isLoading) {
            binding.isLoading = it
        }
        bind(viewModel.error) {
            binding.homeMessage = it
        }
        bind(viewModel.data) {
            adapter.updateData(it)
        }
    }

    private fun showDetail(album: Album) {
        val detailDialog = Dialog(this)
        detailDialog.setCancelable(false)

        val detailBinding = DialogAlbumDetailBinding.inflate(LayoutInflater.from(this))

        detailDialog.setContentView(detailBinding.root)
        detailDialog.show()

        // Update Dialog size
        val width = (resources.displayMetrics.widthPixels * 0.85).toInt() // 85% of the screen width
        detailDialog.window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)

        detailBinding.album = album
        detailBinding.onClick = OnClickListener { detailDialog.dismiss() }
    }

}