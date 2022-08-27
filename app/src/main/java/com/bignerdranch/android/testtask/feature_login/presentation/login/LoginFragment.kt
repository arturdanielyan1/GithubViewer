package com.bignerdranch.android.testtask.feature_login.presentation.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bignerdranch.android.loginpage.ui.theme.ComposeTutorialTheme
import com.bignerdranch.android.testtask.R
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel


class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (requireActivity() as AppCompatActivity).supportActionBar?.hide()
        return ComposeView(requireContext()).apply {
            setContent {
                ComposeTutorialTheme {
                    // A surface container using the 'background' color from the theme
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colors.background
                    ) {
                        LoginLayout()
                    }
                }
            }
        }
    }

    private val viewModel: LoginViewModel by viewModel()

    override fun onViewCreated(v: View, savedInstanceState: Bundle?) {
        super.onViewCreated(v, savedInstanceState)
        observeData()
        viewModel.notifyIfLoggedIn()
    }

    private fun observeData() {
        viewModel.apply {
            isLoginSucceed.observe(viewLifecycleOwner) {
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToMainFlow())
            }
            isFailed.observe(viewLifecycleOwner) {
                Snackbar.make(requireView(), it, Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    @Composable
    fun LoginLayout() {
        val constraints = ConstraintSet {
            val loginText = createRefFor("login_text")
            constrain(loginText) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
            }


            val logoImage = createRefFor("app_logo_image")
            constrain(logoImage) {
                top.linkTo(loginText.top)
                bottom.linkTo(loginText.bottom)
                start.linkTo(loginText.end)

                width = Dimension.value(40.dp)
                height = Dimension.fillToConstraints
            }


            val passwordTf = createRefFor("password_tf")
            constrain(passwordTf) {
                top.linkTo(loginText.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)

                width = Dimension.fillToConstraints
            }

            val loginButtonBox = createRefFor("login_button_box")
            constrain(loginButtonBox) {
                top.linkTo(passwordTf.bottom)
                end.linkTo(parent.end)
            }
        }

        val verticalPadding = Modifier.padding(
            top = 16.dp,
            bottom = 16.dp
        )
        val fullPadding = Modifier.padding(
            top = 0.dp,
            bottom = 16.dp,
            start = 16.dp,
            end = 16.dp,
        )

        ConstraintLayout(
            constraintSet = constraints,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = "Log in",
                modifier = verticalPadding
                    .layoutId("login_text")
                    .padding(start = 16.dp),
                fontSize = 36.sp
            )
            Image(
                painter = painterResource(id = R.drawable.app_logo_fg),
                contentDescription = "App logo",
                contentScale = ContentScale.Crop,
                modifier = verticalPadding.layoutId("app_logo_image")
            )

            var passwordTfState by rememberSaveable {
                mutableStateOf("")
            }

            OutlinedTextField(
                value = passwordTfState,
                onValueChange = {
                    passwordTfState = it
                },
                label = { Text(text = "Personal Access Token") },
                modifier = fullPadding
                    .layoutId("password_tf"),
                shape = RoundedCornerShape(10.dp),
                singleLine = true,
            )


            Box(
                modifier = fullPadding
                    .layoutId("login_button_box")
                    .background(
                        color = Color(0xffFED07A),
                        shape = RoundedCornerShape(15.dp)
                    ),
                contentAlignment = Alignment.Center,
            ) {

                CircularProgressIndicator(
                    color = MaterialTheme.colors.onPrimary,
                )

                Button(
                    onClick = {
                        viewModel.authenticate(
                            passwordTfState
                        )
                    },
                    modifier = Modifier
                        .layoutId("login_button_box")
                        .alpha(
                            if (viewModel.isLoadingS) 0f
                            else 1f
                        ),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(0xffFED07A)
                    ),
                    elevation = null,
                    shape = RoundedCornerShape(15.dp)
                ) {
                    Text(
                        text = "LOG IN",
                        modifier = Modifier
                            .padding(
                                start = 20.dp,
                                end = 20.dp,
                                top = 5.dp,
                                bottom = 5.dp
                            )
                    )
                }
            }
        }
    }
}