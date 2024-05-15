# Chaiden---0806022310023---Java-Persistence

Proyek ini adalah aplikasi manajemen data mahasiswa sederhana menggunakan Hibernate dan MySQL. Aplikasi ini memungkinkan pengguna untuk melakukan operasi CRUD (Create, Read, Update, Delete) pada data mahasiswa.

## Fitur

- **Menambahkan data mahasiswa**: Tambahkan informasi baru tentang mahasiswa.
- **Mengubah data mahasiswa**: Perbarui informasi mahasiswa yang sudah ada.
- **Menghapus data mahasiswa**: Hapus informasi mahasiswa dari database.
- **Melihat daftar mahasiswa**: Tampilkan daftar semua mahasiswa yang tersimpan di database.

## Prasyarat

Pastikan Anda memiliki perangkat lunak berikut yang sudah terpasang di sistem Anda:

- [Java Development Kit (JDK) 17](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html)
- [Apache Maven](https://maven.apache.org/)
- [MySQL Server](https://www.mysql.com/)

## Konfigurasi Database

Pastikan MySQL Server berjalan dan buat database baru dengan nama `sekolahnyachaiden`. Anda dapat melakukannya dengan menjalankan perintah berikut di MySQL:

```sql
CREATE DATABASE sekolahnyachaiden;

CREATE TABLE `students` (
  `id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `age` int(11) NOT NULL,
  `major` varchar(50) NOT NULL
)
```

## StrukturProyek

Berikut adalah struktur proyek:
```
sekolahnyachaiden/
│
├── src/main/java/com/example/
│   ├── HibernateTest.java
│   ├── HibernateUtil.java
│   ├── Students.java
│
├── src/main/resources/
│   ├── hibernate.cfg.xml
│   ├── Students.hbm.xml
│
├── pom.xml
└── README.md
```

## Teknologi yang digunakan:
Java: Bahasa pemrograman utama yang digunakan untuk mengembangkan aplikasi ini.
Hibernate: Framework ORM (Object Relational Mapping) yang digunakan untuk berinteraksi dengan database.
MySQL: Database yang digunakan untuk menyimpan data mahasiswa.
Maven: Alat manajemen proyek dan sistem build yang digunakan untuk mengelola dependensi.


## Cara kerja aplikasi

Aplikasi ini berinteraksi dengan database MySQL melalui Hibernate. Berikut adalah deskripsi singkat tentang bagaimana berbagai file berkontribusi dalam aplikasi ini:

- HibernateTest.java: File utama yang mengandung logika aplikasi, termasuk operasi CRUD dan antarmuka pengguna berbasis konsol.
- HibernateUtil.java: Mengelola konfigurasi Hibernate dan menyediakan session factory.
- Students.java: Entitas JPA yang mewakili tabel Students dalam database.
- Students.hbm.xml: File mapping Hibernate yang menghubungkan entitas Students dengan tabel Students di database.
- hibernate.cfg.xml: File konfigurasi Hibernate yang menyimpan pengaturan koneksi database dan properti Hibernate lainnya.
- pom.xml: File konfigurasi Maven yang mendefinisikan dependensi proyek.

  
