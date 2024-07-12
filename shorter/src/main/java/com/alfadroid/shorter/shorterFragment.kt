package com.alfadroid.shorter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.alfadroid.shorter.databinding.FragmentShorterBinding


class ShorterFragment : Fragment() {
    private val binding: FragmentShorterBinding by viewBinding(FragmentShorterBinding::bind)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentShorterBinding.inflate(layoutInflater).root
    }

    companion object {
        fun newInstance(): ShorterFragment = ShorterFragment()
    }
}