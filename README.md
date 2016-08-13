# Wordpress-Reader-Android
Wordpress Reader with JSON API plugin in Android.

Source code ini sangat sederhana dalam pengambilan data pada CMS Wordpress, dibuat secara klasik (banyak kode yang redundan) yang diharapkan bisa memahami lebih dalam terkait parsing data json menggunakan volley dan gson.


Plugin menggunakan JSON API:

https://wordpress.org/plugins/json-api/other_notes/

sebagai contoh pada aplikasi ini menggunakan 3 layanan:

`/?json=1` mengambil konten pada page pertama.

`/get_category_index/` mengambil kategori-kategori yang tersedia.

`/get_post/?id=` melihat data konten secara keseluruhan.

Layanan-layanan tersebut harus diaktifkan di Settings -> JSON API.


## Screenshot:
<p align="center">
  <img src="https://s19.postimg.org/ib43aqaoj/photo_2016_08_14_01_59_18.jpg" width="350"/>
  <img src="https://s19.postimg.org/yamqua6qb/photo_2016_08_14_01_59_20.jpg" width="350"/>
  <img src="https://s19.postimg.org/ho56l7dsj/photo_2016_08_14_01_59_25.jpg" width="350"/>
  <img src="https://s19.postimg.org/9wogmn9n7/photo_2016_08_14_01_59_27.jpg" width="350"/>
</p>

## Terima kasih untuk:
- Volley
- Gson
- JSON API


## License

```
Copyright (c) 2016 isfaaghyth.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

Happy coding \m/

isfaaghyth 2016
