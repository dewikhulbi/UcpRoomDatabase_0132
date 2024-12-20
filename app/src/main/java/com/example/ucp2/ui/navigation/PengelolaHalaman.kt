package com.example.ucp2.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.meet10.ui.view.mahasiswa.DetailMatkulView
import com.example.ucp2.ui.view.dosen.DestinasiInsertDosen
import com.example.ucp2.ui.view.dosen.DetailDosenView
import com.example.ucp2.ui.view.dosen.HomeDosenView
import com.example.ucp2.ui.view.matkul.HomeMatkulView
import com.example.ucp2.ui.view.dosen.HomeMenuView
import com.example.ucp2.ui.view.dosen.InsertDosenView
import com.example.ucp2.ui.view.matkul.DestinasiInsertMatkul
import com.example.ucp2.ui.view.matkul.InsertMatkulView
import com.example.ucp2.ui.view.matkul.UpdateMatkulView

@Composable
fun PengelolaHalaman(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = DestinasiHomeMenu.route
    ) {
        composable(
            route = DestinasiHomeMenu.route
        ) {
            HomeMenuView(
                onDosenClick = {
                    navController.navigate(DestinasiHomeDosen.route)
                },
                onMatkulClick = {
                    navController.navigate(DestinasiHomeMatkul.route)
                }
            )
        }
        // Layar Home Dosen
        composable(
            route = DestinasiHomeDosen.route
        ) {
            HomeDosenView(
                onDetailClick = { nidn ->
                    navController.navigate("${DestinasiDetailDosen.route}/$nidn")
                },
                onAddDosen = {
                    navController.navigate(DestinasiInsertDosen.route)
                },
                modifier = modifier
            )
        }

        composable(
            route = DestinasiHomeMatkul.route
        ) {
            HomeMatkulView(
                onDetailClick = { kode ->
                    navController.navigate("${DestinasiDetailMatkul.route}/$kode")
                },
                onAddMatkul = {
                    navController.navigate(DestinasiInsertMatkul.route)
                },
                modifier = modifier
            )
        }
        composable (
            route = DestinasiInsertDosen.route
        ) {
            InsertDosenView(
                onBack = {
                    navController.popBackStack()
                },
                onNavigate = {
                    navController.popBackStack()
                },
                modifier = modifier
            )
        }

        composable (
            DestinasiDetailDosen.routesWithArg,
            arguments = listOf(
                navArgument(DestinasiDetailDosen.NIDN) {
                    type = NavType.StringType
                }
            )
        ){
            val nidn = it.arguments?.getString(DestinasiDetailDosen.NIDN)

            nidn?.let { nidn ->
                DetailDosenView(
                    onBack = {
                        navController.popBackStack()
                    },
                )
            }
        }

        composable (
            route =DestinasiInsertMatkul.route
        ) {
            InsertMatkulView(
                onBack = {
                    navController.popBackStack()
                },
                onNavigate = {
                    navController.popBackStack()
                },
                modifier = modifier
            )
        }

        composable (
            DestinasiDetailMatkul.routesWithArg,
            arguments = listOf(
                navArgument(DestinasiDetailMatkul.KODE) {
                    type = NavType.StringType
                }
            )
        ){
            val kode = it.arguments?.getString(DestinasiDetailMatkul.KODE)

            kode?.let { kode ->
                DetailMatkulView(
                    onBack = {
                        navController.popBackStack()
                    },
                    onEditClick = {
                        navController.navigate("${DestinasiUpdateMatkul.route}/$kode")
                    },
                    modifier = modifier,
                    onDeleteClick = {
                        navController.popBackStack()
                    }
                )
            }
        }

        composable(
            DestinasiUpdateMatkul.routesWithArg,
            arguments = listOf(
                navArgument (DestinasiUpdateMatkul.KODE) {
                    type = NavType.StringType
                }
            )
        ) {
            UpdateMatkulView(
                onBack = {
                    navController.popBackStack()
                },
                onNavigate = {
                    navController.popBackStack()
                },
                modifier = modifier,
            )
        }
    }
}
