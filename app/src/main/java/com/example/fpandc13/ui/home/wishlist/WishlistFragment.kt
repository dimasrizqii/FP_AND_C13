package com.example.fpandc13.ui.home.wishlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fpandc13.R
import com.example.fpandc13.adapter.WishlistAdapter
import com.example.fpandc13.databinding.FragmentWishlistBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WishlistFragment : Fragment() {

    private var _binding: FragmentWishlistBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: WishlistViewModel
    private lateinit var adapter: WishlistAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWishlistBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this.requireActivity()).get(WishlistViewModel::class.java)

        binding.rvWishlist.layoutManager = LinearLayoutManager(this.requireActivity())
        viewModel.getWishlistTicket()?.observe(this.requireActivity()) {
            if (it != null) {
                adapter = WishlistAdapter(it)
                adapter.notifyDataSetChanged()
                binding.rvWishlist.adapter = WishlistAdapter(it)
            }
        }
    }
}