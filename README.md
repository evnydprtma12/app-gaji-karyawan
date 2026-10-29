# Aplikasi Penggajian Karyawan

Aplikasi desktop berbasis **Java Swing** untuk menghitung gaji karyawan dan mengelola data karyawan. Data disimpan di database **MySQL**. Project dibuat dengan **NetBeans** (Apache Ant).

Project ini dibuat sebagai tugas perkuliahan Program Studi Teknologi Informasi, Universitas Bina Sarana Informatika (Depok).

> **Catatan:** Ini aplikasi desktop, bukan aplikasi web. Aplikasi membutuhkan Java dan MySQL di komputer Anda, sehingga **tidak dapat dijalankan lewat GitHub Pages atau browser**. Ikuti langkah instalasi di bawah.

## Fitur

**Hitung Gaji**
- Pilih NIP karyawan, lalu nama, jabatan, dan gaji pokok terisi otomatis dari database.
- Masukkan jumlah ketidakhadiran dan persentase pajak.
- Aplikasi menghitung tunjangan, bonus, potongan, pajak, gaji kotor, dan gaji bersih.
- Tombol **Reset** untuk mengosongkan form dan **Keluar** (dengan konfirmasi).

**Daftar Karyawan**
- Menampilkan seluruh data karyawan dalam tabel.
- Tambah data karyawan (NIP, nama, jabatan, gaji).
- Hapus data karyawan yang dipilih di tabel.
- Cari data berdasarkan NIP, dan tombol **Refresh** untuk menampilkan semua data kembali.

## Aturan Perhitungan Gaji

| Komponen | Aturan |
| --- | --- |
| **Tunjangan** | Gaji Rp2.000.000 - Rp4.999.999 → Rp150.000 |
| | Gaji Rp5.000.000 - Rp7.999.999 → Rp400.000 |
| | Gaji Rp8.000.000 - Rp14.999.999 → Rp1.000.000 |
| | Gaji Rp15.000.000 - Rp30.000.000 → Rp2.000.000 |
| | Di luar rentang tersebut → Rp0 |
| **Bonus** | Ketidakhadiran 0-2 hari → Rp500.000 |
| | Ketidakhadiran 3-4 hari → Rp300.000 |
| | Ketidakhadiran 5 hari atau lebih → Rp0 |
| **Potongan** | Ketidakhadiran lebih dari 5 hari → (hari - 5) × Rp50.000, selain itu Rp0 |
| **Pajak** | Persentase pajak × gaji pokok |
| **Gaji Kotor** | Gaji pokok + bonus - potongan + tunjangan |
| **Gaji Bersih** | Gaji kotor - pajak |

## Teknologi

| Komponen | Keterangan |
| --- | --- |
| Bahasa | Java (target JDK 19) |
| GUI | Java Swing, NetBeans AbsoluteLayout |
| Database | MySQL |
| Koneksi | MySQL JDBC Driver (MySQL Connector/J) |
| IDE | Apache NetBeans (Ant) |

## Kebutuhan Sistem

- **JDK 19** atau lebih baru
- **Apache NetBeans** (versi yang mendukung JDK 19+)
- **MySQL Server**, misalnya lewat [XAMPP](https://www.apachefriends.org/)
- **MySQL Connector/J** (file `.jar`), unduh di [dev.mysql.com/downloads/connector/j](https://dev.mysql.com/downloads/connector/j/)

## Cara Menjalankan

### 1. Clone repository

```bash
git clone https://github.com/evnydprtma12/app-gaji-karyawan
```

### 2. Siapkan database

1. Jalankan **MySQL** (misalnya dari XAMPP Control Panel).
2. Buka **phpMyAdmin** (`http://localhost/phpmyadmin`).
3. Impor file SQL project ini, atau jalankan script berikut di tab **SQL**:

```sql
CREATE DATABASE IF NOT EXISTS penggajian_karyawan;
USE penggajian_karyawan;

CREATE TABLE IF NOT EXISTS tbl_karyawan (
    nip      VARCHAR(20)  NOT NULL PRIMARY KEY,
    nama     VARCHAR(100) NOT NULL,
    jabatan  VARCHAR(50)  NOT NULL,
    gaji     INT          NOT NULL
);

-- Data contoh (opsional)
INSERT INTO tbl_karyawan (nip, nama, jabatan, gaji) VALUES
('1001', 'Contoh Karyawan A', 'Staff',   3500000),
('1002', 'Contoh Karyawan B', 'Supervisor', 6500000),
('1003', 'Contoh Karyawan C', 'Manager', 12000000);
```

> Struktur tabel di atas menyesuaikan kolom yang dipakai di kode (`nip`, `nama`, `jabatan`, `gaji`). Jika Anda memakai file SQL milik project, gunakan file tersebut.

### 3. Sesuaikan koneksi (jika perlu)

Pengaturan koneksi ada di `src/ProjekUAS/DBconfig.java`. Nilai bawaannya:

```java
String url  = "jdbc:mysql://localhost:3306/penggajian_karyawan";
String user = "root";
String pw   = "";
```

Ubah `user` dan `pw` jika MySQL Anda memakai akun lain.

### 4. Buka project di NetBeans

1. Di NetBeans, pilih **File → Open Project**, lalu pilih folder project (yang berisi `src` dan `nbproject`).
2. Jika muncul peringatan **missing library**, klik kanan project → **Properties → Libraries**, lalu:
   - Pada **Classpath**, klik **Add JAR/Folder** dan pilih file `mysql-connector-j-*.jar`.
   - Klik **Add Library** dan pilih **Absolute Layout** (sudah bawaan NetBeans).
3. Pastikan **Main Class** benar: **Properties → Run → Main Class**, isi `ProjekUAS.projek1`.

### 5. Jalankan

Klik kanan file `src/ProjekUAS/projek1.java`, lalu pilih **Run File** (atau `Shift + F6`). Atau jalankan project dengan **F6** setelah Main Class diatur.

## Struktur Folder

```
├── src/
│   ├── ProjekUAS/
│   │   ├── projek1.java          # Form utama: hitung gaji
│   │   ├── TabelKaryawan.java    # Form daftar karyawan (tambah hapus, cari)
│   │   └── DBconfig.java         # Koneksi ke database MySQL
│   └── aset/                     # Gambar (logo, latar belakang, ikon)
├── nbproject/                    # Konfigurasi project NetBeans
├── build.xml                     # Skrip build Ant
└── manifest.mf
```

## Catatan

- Folder `build/` dan `dist/` tidak disertakan di repository. Keduanya dibuat otomatis oleh NetBeans saat project dibangun (**Clean and Build**).
- Aplikasi memakai akun MySQL `root` tanpa password, yang hanya cocok untuk pengembangan di komputer lokal.

## Kontak

- **Nama:** Evan Yudi Pratama
- **Email:** evanyudipratama12@gmail.com