package com.magaramova.projectfilm

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.magaramova.projectfilm.databinding.FragmentWatchLaterBinding

class WatchLaterFragment : Fragment() {


    private lateinit var binding: FragmentWatchLaterBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWatchLaterBinding.inflate(inflater, container, false)
        val view = binding.root
        inflater.inflate(R.layout.fragment_watch_later, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        AnimationHelper.performFragmentCircularRevealAnimation(
            binding.watchLaterFragmentRoot,
            requireActivity(),
            3
        )
    }
}