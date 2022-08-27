package com.bignerdranch.android.testtask.main_flow.feature_all_users.presentation.all_users

import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.testtask.databinding.FragmentAllUsersBinding
import com.bignerdranch.android.testtask.main_flow.MainFlowFragmentDirections
import com.bignerdranch.android.testtask.main_flow.feature_all_users.domain.model.UserData
import com.bignerdranch.android.testtask.main_flow.feature_all_users.presentation.all_users.all_users_adapter_holder.AllUsersAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class AllUsersFragment : Fragment() {

    companion object {
        @JvmStatic fun newInstance() = AllUsersFragment()
    }

    private val viewModel: AllUsersViewModel by viewModel()

    private var _binding: FragmentAllUsersBinding? = null
    private val binding get() = _binding!!

    private val users: MutableList<UserData>
        get() = (binding.allUsersRv.adapter as AllUsersAdapter).users

    private var lastLoadedSince = -1 // sometimes the same data loads twice,
    // before calling load is checked whether the last id is equal to this

    private val onUserItemClick: (UserData) -> Unit = { userData ->
        findNavController().navigate(MainFlowFragmentDirections.actionMainFlowToUserDetailsFragment(userData))
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentAllUsersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(v: View, savedInstanceState: Bundle?) {
        super.onViewCreated(v, savedInstanceState)

        setupViews()
        observeData()
    }

    private fun setupViews() {
        binding.allUsersRv.layoutManager = object : LinearLayoutManager(requireActivity()) {
            override fun supportsPredictiveItemAnimations() = false
        }
        binding.allUsersRv.adapter = AllUsersAdapter(layoutInflater, onUserItemClick)
        binding.allUsersRv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if(!recyclerView.canScrollVertically(1) && lastLoadedSince != users.last().id) {
                    lastLoadedSince = users.last().id
                    viewModel.loadMore(users.last().id)
                }
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {}
        })
    }

    private fun observeData() {
        viewModel.usersList.observe(viewLifecycleOwner) {
            submitList(it)
        }

        viewModel.hasFailed.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }

        viewModel.isLoading.observe(viewLifecycleOwner) {
            if (it) {
                val smoothScroller = object : LinearSmoothScroller(requireContext()) {
                    override fun getVerticalSnapPreference(): Int =
                        SNAP_TO_START

                    override fun calculateSpeedPerPixel(displayMetrics: DisplayMetrics?): Float {
                        return 8f*super.calculateSpeedPerPixel(displayMetrics)
                    }
                }
                smoothScroller.targetPosition = users.size - 1
                if((binding.allUsersRv.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition() == users.size-2)
                    (binding.allUsersRv.layoutManager as LinearLayoutManager).startSmoothScroll(smoothScroller)
            }
        }
    }

    private fun submitList(list: List<UserData>) {
        (binding.allUsersRv.adapter as AllUsersAdapter).apply {
            val oldSize = users.size
            users = list.toMutableList()
            if(users.size > oldSize){
                binding.allUsersRv.adapter?.notifyItemRangeChanged(oldSize, users.size - oldSize)
            } else {
                binding.allUsersRv.adapter?.notifyItemRangeChanged(users.size, oldSize - users.size)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}