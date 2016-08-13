# Wordpress-Reader-Android
Wordpress Reader with JSON API plugin in Android.

Source code ini sangat sederhana dalam pengambilan data pada CMS Wordpress, dibuat secara klasik (banyak kode yang redundan) yang diharapkan bisa memahami lebih dalam terkait parsing data pada json.


Plugin menggunakan JSON API:

https://id.wordpress.org/plugins/json-api/

sebagai contoh pada aplikasi ini menggunakan 2 layanan:

`/?json=1` mengambil konten pada page pertama.

`/get_category_index/` mengambil kategori-kategori yang tersedia.

`/get_post/?id=` melihat data konten secara keseluruhan.

Layanan-layanan tersebut harus diaktifkan di Settings -> JSON API.

## Screenshot:
![desk](https://s19.postimg.org/ib43aqaoj/photo_2016_08_14_01_59_18.jpg)
![desk](https://s19.postimg.org/yamqua6qb/photo_2016_08_14_01_59_20.jpg)
![desk](https://s19.postimg.org/ho56l7dsj/photo_2016_08_14_01_59_25.jpg)
![desk](https://s19.postimg.org/9wogmn9n7/photo_2016_08_14_01_59_27.jpg)

## Terima kasih untuk:
- Volley
- Gson
- JSON API
