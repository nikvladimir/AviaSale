package com.alfadroid.subscribers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.alfadroid.subscribers.databinding.FragmentSubscribersBinding


class SubscribersFragment : Fragment() {
    private val binding: FragmentSubscribersBinding by viewBinding(FragmentSubscribersBinding::bind)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentSubscribersBinding.inflate(layoutInflater).root
    }

    companion object {
        fun newInstance(): SubscribersFragment = SubscribersFragment()
    }
}
