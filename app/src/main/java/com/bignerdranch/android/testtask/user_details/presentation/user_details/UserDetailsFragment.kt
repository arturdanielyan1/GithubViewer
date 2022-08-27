package com.bignerdranch.android.testtask.user_details.presentation.user_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bignerdranch.android.testtask.core.model.UserRepo
import com.bignerdranch.android.testtask.databinding.FragmentUserDetailsBinding
import com.bignerdranch.android.testtask.main_flow.MainFlowFragmentArgs
import com.bignerdranch.android.testtask.main_flow.feature_all_users.domain.model.UserData
import com.bignerdranch.android.testtask.main_flow.repos_adapter_holder.UserRepoAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel


class UserDetailsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentUserDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    private val viewModel: UserDetailsViewModel by viewModel()

    private var _binding: FragmentUserDetailsBinding? = null
    private val binding get() = _binding!!

    private val args: MainFlowFragmentArgs by navArgs()


    override fun onViewCreated(v: View, savedInstanceState: Bundle?) {
        super.onViewCreated(v, savedInstanceState)

        setupViews()
        observeData()
    }

    private fun setupViews() {
        (requireActivity() as AppCompatActivity).supportActionBar?.title = "User Details"

        val userData = args.userData as UserData

        binding.userDtAvatarIv.setImageBitmap(userData.avatarBitmap)
        binding.userDtUsernameTv.text = userData.login

        binding.userDtReposRv.layoutManager = LinearLayoutManager(requireActivity())
        binding.userDtReposRv.adapter = UserRepoAdapter(layoutInflater, requireContext())

        viewModel.loadRepos(userData.login)
    }

    private fun observeData() {
        viewModel.apply {

            repos.observe(viewLifecycleOwner) {
                submitList(it.toMutableList())
            }

            removeLastLoading.observe(viewLifecycleOwner) {
                binding.userDtReposRv.adapter?.notifyItemRemoved(
                    (binding.userDtReposRv.adapter as UserRepoAdapter).reposList.size
                )
            }

        }
    }

    private fun submitList(list: MutableList<UserRepo>) {
        val reposList = (binding.userDtReposRv.adapter as UserRepoAdapter).reposList
        val oldSize = reposList.size
        (binding.userDtReposRv.adapter as UserRepoAdapter).reposList = list
        binding.userDtReposRv.adapter?.notifyItemRangeInserted(oldSize, list.size-oldSize)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.repos.value = mutableListOf()
        _binding = null
    }
}