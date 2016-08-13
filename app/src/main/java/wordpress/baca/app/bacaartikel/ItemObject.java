package wordpress.baca.app.bacaartikel;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by isfaaghyth on 24/06/16.
 */
public class ItemObject {
    public class WordpressJson {
        @SerializedName("posts")
        public List<Hasil> posts;

        public class Hasil {
            @SerializedName("categories")
            public List<Kategori> categories;

            @SerializedName("url")
            public String url;

            @SerializedName("id")
            public String id;

            @SerializedName("title")
            public String title;

            @SerializedName("excerpt")
            public String excerpt;

            @SerializedName("thumbnail")
            public String thumbnail;

            @SerializedName("content")
            public String content;

            @SerializedName("date")
            public String date;
        }

        public class Kategori {
            @SerializedName("title")
            public String title;

            @SerializedName("post_count")
            public String post_count;
        }
    }

    public class KategoriPost {
        @SerializedName("categories")
        public List<Hasil> categories;

        public class Hasil {
            @SerializedName("slug")
            public String slug;

            @SerializedName("title")
            public String title;

            @SerializedName("post_count")
            public String post_count;
        }
    }

    public class KomentarPost {

        @SerializedName("comments")
        public List<Komentar> comments;

        public class Komentar {
            @SerializedName("name")
            public String name;

            @SerializedName("content")
            public String content;
        }
    }
}
