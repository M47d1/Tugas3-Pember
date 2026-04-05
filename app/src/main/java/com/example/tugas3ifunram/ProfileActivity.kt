package com.example.tugas3ifunram

import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        // 1. Inisialisasi View (ID harus sesuai dengan XML TableLayout)
        val tvNama = findViewById<TextView>(R.id.tvResultNama)
        val tvNim = findViewById<TextView>(R.id.tvResultNim)
        val tvProdi = findViewById<TextView>(R.id.tvResultProdi)
        val tvJk = findViewById<TextView>(R.id.tvResultJk)
        val tvHobi = findViewById<TextView>(R.id.tvResultHobi)
        val btnBack = findViewById<Button>(R.id.btnBack)

        // 2. Memunculkan tombol kembali di ActionBar (Batang Biru Atas)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Detail Profil Mahasiswa"

        // 3. Menerima data Parcelable dari MainActivity
        val mahasiswa = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("EXTRA_MAHASISWA", Mahasiswa::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra("EXTRA_MAHASISWA")
        }

        // 4. Menampilkan data ke UI
        // Karena di XML sudah ada label "Nama :", di sini kita cukup isi datanya saja
        mahasiswa?.let {
            tvNama.text = it.nama
            tvNim.text = it.nim
            tvProdi.text = it.prodi
            tvJk.text = it.jenisKelamin
            tvHobi.text = it.hobi
        }

        // 5. Logika tombol kembali manual (Button di bawah CardView)
        btnBack.setOnClickListener {
            finish() // Menutup halaman ini dan kembali ke form
        }
    }

    // Fungsi agar tombol panah "<-" di ActionBar berfungsi
    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}