package com.bignerdranch.android.testtask.main_flow

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.bignerdranch.android.testtask.R
import com.bignerdranch.android.testtask.databinding.FragmentMainFlowBinding
import com.bignerdranch.android.testtask.main_flow.feature_all_users.presentation.all_users.AllUsersFragment
import com.bignerdranch.android.testtask.main_flow.feature_my_repos.presentation.my_repos.MyReposFragment
import com.bignerdranch.android.testtask.main_flow.feature_profile.presentation.profile.ProfileFragment
import com.google.android.material.navigation.NavigationBarView

class MainFlowFragment : Fragment(), NavigationBarView.OnItemSelectedListener {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        (requireActivity() as AppCompatActivity).supportActionBar?.show()
        _binding = FragmentMainFlowBinding.inflate(inflater, container, false)
        return binding.root
    }

    private var _binding: FragmentMainFlowBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(v: View, savedInstanceState: Bundle?) {
        super.onViewCreated(v, savedInstanceState)

        binding.mainFlowVp.isUserInputEnabled = false
        binding.mainFlowVp.adapter = object : FragmentStateAdapter(this){

            override fun createFragment(position: Int): Fragment {
                return when(position) {
                    0 -> MyReposFragment.newInstance()
                    1 -> AllUsersFragment.newInstance()
                    2 -> ProfileFragment.newInstance()
                    else -> throw IllegalArgumentException("Fragment count is 3, gotten position is 3")
                }
            }

            override fun getItemCount() = 3
        }
        binding.mainFlowVp.registerOnPageChangeCallback(viewPagerChangeCallback)

        binding.bottomNavigation.setOnItemSelectedListener(this as NavigationBarView.OnItemSelectedListener)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        Log.d("myLogs", item.title.toString())
        binding.mainFlowVp.setCurrentItem(item.order, true)
        return true
    }

    private val menuProvider = object : MenuProvider {
        override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
            menu.clear()
            if(binding.mainFlowVp.currentItem == 2) {
                menuInflater.inflate(R.menu.menu_profile, menu)
            }else {
                menuInflater.inflate(R.menu.search_menu, menu)
                val searchView = menu.findItem(R.id.search_user).actionView as SearchView
                searchView.isSubmitButtonEnabled = true
                searchView.setOnQueryTextListener(searchListener)
            }
        }

        override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
            if(menuItem.itemId == R.id.item_settings) {
                requireActivity().removeMenuProvider(this)
                findNavController().navigate(MainFlowFragmentDirections.actionMainFlowToSettingsFragment())
            }
            return true
        }

    }

    var searchListener: SearchView.OnQueryTextListener? = null

    private val viewPagerChangeCallback: ViewPager2.OnPageChangeCallback
        get() = object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.bottomNavigation.menu.getItem(position).isChecked = true
                (requireActivity() as AppCompatActivity).supportActionBar?.apply {
                    title = binding.bottomNavigation.menu.getItem(position).title
                }

                when (position) {
                    2 -> {
                        requireActivity().removeMenuProvider(menuProvider)
                        requireActivity().addMenuProvider(menuProvider)
                    }
                    1 -> {
                        requireActivity().removeMenuProvider(menuProvider)
                        requireActivity().addMenuProvider(menuProvider)
                    }
                    else -> {
                        requireActivity().removeMenuProvider(menuProvider)
                    }
                }
            }
        }

    override fun onDestroy() {
        super.onDestroy()
        requireActivity().removeMenuProvider(menuProvider)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.mainFlowVp.adapter = null
        _binding = null
    }
}