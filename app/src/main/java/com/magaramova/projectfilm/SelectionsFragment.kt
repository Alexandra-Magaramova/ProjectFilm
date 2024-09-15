package com.magaramova.projectfilm

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.magaramova.projectfilm.databinding.FragmentSelectionsBinding


class SelectionsFragment : Fragment() {
    private lateinit var binding: FragmentSelectionsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSelectionsBinding.inflate(inflater, container, false)
        val view = binding.root
        inflater.inflate(R.layout.fragment_selections, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        AnimationHelper.performFragmentCircularRevealAnimation(binding.selectionsFragmentRoot, requireActivity(), 1)
    }
}