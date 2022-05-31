package com.example.articlespetapp.model

import com.google.gson.*
import java.lang.reflect.Type

class ArticleDeserializer: JsonDeserializer<Article> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Article {
        val author = context?.deserialize(json?.asJsonObject?.get("author"), String::class.java) ?: "author unknown"
        val urlToImage = context?.deserialize(json?.asJsonObject?.get("urlToImage"), String::class.java) ?: ""
        val title = context?.deserialize(json?.asJsonObject?.get("title"), String::class.java) ?: ""
        return Article(0, author, title, urlToImage, 0)
    }
}