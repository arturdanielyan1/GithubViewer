package com.bignerdranch.android.testtask.main_flow.feature_profile.presentation.profile

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bignerdranch.android.testtask.core.USERNAME
import com.bignerdranch.android.testtask.databinding.FragmentProfileBinding
import com.bignerdranch.android.testtask.main_flow.MainFlowFragmentDirections
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment : Fragment() {

    companion object {
        @JvmStatic fun newInstance() = ProfileFragment()
    }

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ProfileViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(v: View, savedInstanceState: Bundle?) {
        super.onViewCreated(v, savedInstanceState)

        viewModel.getProfilePhoto()

        viewModel.profilePhoto.observe(viewLifecycleOwner) {
            binding.userAvatarIv.setImageBitmap(it)
        }

        binding.userAvatarIv.setOnLongClickListener {
            val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
                .setType("image/*")
            imagePickerLauncher.launch(intent)
            true
        }

        binding.userUsernameTv.text = USERNAME

        binding.logOutButton.setOnClickListener {
            viewModel.logout()
            viewModel.removeProfilePhoto()
            findNavController().navigate(MainFlowFragmentDirections.actionMainFlowToLoginFragment())
        }
    }

    private val imagePickerLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult())
    { result: ActivityResult? ->
        val data = result?.data
        if(result?.resultCode == RESULT_OK && data != null) {
            val selectedImage = data.data
            binding.userAvatarIv.setImageURI(selectedImage)
            viewModel.setProfilePhoto(requireContext().contentResolver, selectedImage!!)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}