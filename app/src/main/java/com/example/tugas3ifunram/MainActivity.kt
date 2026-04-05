package com.example.tugas3ifunram

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etNama = findViewById<EditText>(R.id.etNama)
        val etNim = findViewById<EditText>(R.id.etNim)
        val spinnerProdi = findViewById<Spinner>(R.id.spinnerProdi)
        val rgJenisKelamin = findViewById<RadioGroup>(R.id.rgJenisKelamin)
        val cbCoding = findViewById<CheckBox>(R.id.cbCoding)
        val cbGaming = findViewById<CheckBox>(R.id.cbGaming)
        val cbMembaca = findViewById<CheckBox>(R.id.cbMembaca)
        val btnTampilkan = findViewById<Button>(R.id.btnTampilkan)

        btnTampilkan.setOnClickListener {
            val nama = etNama.text.toString().trim()
            val nim = etNim.text.toString().trim()
            val prodi = spinnerProdi.selectedItem.toString()

            // 1. Validasi Input Dasar (Nama dan NIM tidak boleh kosong)
            if (nama.isEmpty()) {
                etNama.error = "Nama tidak boleh kosong"
                return@setOnClickListener
            }
            if (nim.isEmpty()) {
                etNim.error = "NIM tidak boleh kosong"
                return@setOnClickListener
            }

            // Get Jenis Kelamin
            val selectedJkId = rgJenisKelamin.checkedRadioButtonId
            if (selectedJkId == -1) {
                Toast.makeText(this, "Pilih Jenis Kelamin", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val rbJenisKelamin = findViewById<RadioButton>(selectedJkId)
            val jenisKelamin = rbJenisKelamin.text.toString()

            // Get Hobi (Minimal 1 dipilih)
            val listHobi = mutableListOf<String>()
            if (cbCoding.isChecked) listHobi.add("Coding")
            if (cbGaming.isChecked) listHobi.add("Gaming")
            if (cbMembaca.isChecked) listHobi.add("Membaca")

            if (listHobi.isEmpty()) {
                Toast.makeText(this, "Pilih minimal satu hobi", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val hobiString = listHobi.joinToString(", ")

            // 2. Buat Object Parcelable
            val mhs = Mahasiswa(nama, nim, prodi, jenisKelamin, hobiString)

            // 3. Navigasi menggunakan Explicit Intent
            val intent = Intent(this, ProfileActivity::class.java)
            intent.putExtra("EXTRA_MAHASISWA", mhs) // Kirim data parcelable
            startActivity(intent)
        }
    }
}