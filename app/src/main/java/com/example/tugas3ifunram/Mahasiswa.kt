package com.example.tugas3ifunram

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Mahasiswa(
    val nama: String,
    val nim: String,
    val prodi: String,
    val jenisKelamin: String,
    val hobi: String
) : Parcelable