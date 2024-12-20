package com.example.ucp2.ui.navigation

interface AlamatNavigasiDosen {
    val route: String
}
object DestinasiHomeDosen : AlamatNavigasiDosen {
    override val route = "home_dosen"
}
object DestinasiHomeMenu : AlamatNavigasiDosen {
    override val route = "home_menu"
}

object DestinasiHomeMatkul : AlamatNavigasiDosen {
    override val route = "home_mahasiswa"
}
object DestinasiDetailDosen : AlamatNavigasiDosen {
    override val route = "detail_dosen"
    const val NIDN = "nidn"
    val routesWithArg = "$route/{$NIDN}"
}
object DestinasiDetailMatkul : AlamatNavigasiDosen {
    override val route = "detail_matkul"
    const val KODE = "kode"
    val routesWithArg = "$route/{$KODE}"
}
object DestinasiUpdateMatkul : AlamatNavigasiDosen {
    override val route = "update"
    const val KODE = "kode"
    val routesWithArg = "$route/{$KODE}"
}