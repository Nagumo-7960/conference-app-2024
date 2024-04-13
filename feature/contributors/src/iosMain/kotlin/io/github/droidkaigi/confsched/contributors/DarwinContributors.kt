package io.github.droidkaigi.confsched.contributors

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.interop.LocalUIViewController
import androidx.compose.ui.window.ComposeUIViewController
import app.cash.molecule.DisplayLinkClock
import io.github.droidkaigi.confsched.designsystem.theme.KaigiTheme
import io.github.droidkaigi.confsched.model.ContributorsRepository
import io.github.droidkaigi.confsched.ui.KmpViewModelLifecycle
import io.github.droidkaigi.confsched.ui.UserMessageStateHolderImpl
import platform.UIKit.UIViewController

@Suppress("UNUSED")
fun contributorViewController(
    contributorsRepository: ContributorsRepository,
    onContributorItemClick: (url: String) -> Unit,
): UIViewController = ComposeUIViewController {
    val viewModel = ContributorsScreenViewModel(
        contributorsRepository = contributorsRepository,
        userMessageStateHolder = UserMessageStateHolderImpl(),
        composeCoroutineContext = DisplayLinkClock,
        viewModelLifecycle = KmpViewModelLifecycle(),
    )
    val uiViewController = LocalUIViewController.current
    LaunchedEffect(uiViewController) {
//        uiViewController
        // TODO: How to know the destroy event of the ViewController?
//        viewModel.viewModelScope.cancel()
    }

    KaigiTheme {
        ContributorsScreen(
            viewModel = viewModel,
            isTopAppBarHidden = true,
            onNavigationIconClick = { /** no action for iOS side **/ },
            onContributorItemClick = onContributorItemClick,
        )
    }
}