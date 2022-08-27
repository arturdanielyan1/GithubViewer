package com.bignerdranch.android.testtask.main_flow.feature_my_repos.presentation.my_repos

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bignerdranch.android.testtask.core.model.UserRepo
import com.bignerdranch.android.testtask.databinding.FragmentMyReposBinding
import com.bignerdranch.android.testtask.main_flow.repos_adapter_holder.UserRepoAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class MyReposFragment : Fragment() {

    companion object {
        @JvmStatic fun newInstance() = MyReposFragment()
    }

    private val viewModel: MyReposViewModel by viewModel()

    private var _binding: FragmentMyReposBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentMyReposBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(v: View, savedInstanceState: Bundle?) {
        super.onViewCreated(v, savedInstanceState)

        setUpViews()
        observeData()
    }

    private fun observeData() {
        viewModel.apply {
            repos.observe(viewLifecycleOwner) {
                submitList(it)
            }
            hasFailed.observe(viewLifecycleOwner) {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            }
            removeLastLoadingItem.observe(viewLifecycleOwner) {
                binding.repoListRv.adapter?.notifyItemRemoved(
                    (binding.repoListRv.adapter as UserRepoAdapter).reposList.size
                )
            }
        }
    }

    private fun setUpViews() {
        binding.repoListRv.layoutManager = LinearLayoutManager(requireActivity())
        binding.repoListRv.adapter = UserRepoAdapter(layoutInflater, requireContext())
    }

    private fun submitList(newList: List<UserRepo>) {
        (binding.repoListRv.adapter as UserRepoAdapter).apply {
            val oldSize = reposList.size
            reposList = newList.toMutableList()
            binding.repoListRv.adapter?.notifyItemRangeInserted(oldSize, newList.size-oldSize)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}