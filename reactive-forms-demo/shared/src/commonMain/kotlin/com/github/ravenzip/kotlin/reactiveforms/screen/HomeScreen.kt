package com.github.ravenzip.kotlin.reactiveforms.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.ravenzip.kotlin.reactiveforms.RootNavigationViewModel
import com.github.ravenzip.kotlin.reactiveforms.SimpleButton

// TODO переработать меню так, чтобы не нужен был скролл
@Composable
fun HomeScreen(navigationViewModel: RootNavigationViewModel) {
    Column(
        modifier = Modifier.padding(15.dp).fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(30.dp),
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Text(
                text = "Добро пожаловать в Kotlin Reactive Forms",
                fontWeight = FontWeight.W500,
                fontSize = 23.sp,
            )

            Text(
                text =
                    "Библиотека компонентов для Compose Multiplatform, " +
                        "расширяющая возможности стандартной Material библиотеки"
            )

            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                HomeScreenShortImportantInfo("Версия", "0.4.0")
                HomeScreenShortImportantInfo("Модули", "4")
                HomeScreenShortImportantInfo("Компоненты", "6")
                HomeScreenShortImportantInfo("Формы", "2")
            }
        }

        Column(verticalArrangement = Arrangement.spacedBy(20.dp)) {
            HomeScreenGridGroup(
                "Компоненты",
                "Демонстрация компонентов, использующих реактивные формы",
            ) {
                items(navigationViewModel.componentScreens) { screen ->
                    SimpleButton(
                        { navigationViewModel.navigateTo(screen) },
                        screen.toString(),
                    )
                }
            }

            HomeScreenRowGroup("Ссылки") {
                item {
                    SimpleButton({}, "GitHub")
                }

                item {
                    SimpleButton({}, "Telegram")
                }
            }
        }
    }
}

@Composable
fun HomeScreenGroup(
    title: String,
    description: String = "",
    content: @Composable () -> Unit,
) {
    Card {
        Column(
            modifier = Modifier.padding(15.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            Column {
                Text(text = title, fontSize = 18.sp, fontWeight = FontWeight.W500)
                if (description.isNotBlank()) {
                    Text(text = description)
                }
            }

            content()
        }
    }
}

@Composable
fun HomeScreenRowGroup(
    title: String,
    description: String = "",
    content: LazyListScope.() -> Unit,
) {
    HomeScreenGroup(title, description) {
        LazyRow(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            content()
        }
    }
}

@Composable
fun HomeScreenGridGroup(
    title: String,
    description: String = "",
    content: LazyGridScope.() -> Unit,
) {
    HomeScreenGroup(title, description) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            content()
        }
    }
}

@Composable
fun HomeScreenShortImportantInfo(name: String, value: String) {
    Card {
        Column(modifier = Modifier.padding(10.dp)) {
            Text(text = name, fontWeight = FontWeight.W500)
            Text(text = value)
        }
    }
}
