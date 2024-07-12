package com.alfadroid.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.alfadroid.profile.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {
    private val binding: FragmentProfileBinding by viewBinding(FragmentProfileBinding::bind)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentProfileBinding.inflate(layoutInflater).root
    }

    companion object {
        fun newInstance(): ProfileFragment = ProfileFragment()
    }

}