package com.example.rssfeed

import android.graphics.Movie
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserException
import org.xmlpull.v1.XmlPullParserFactory
import java.io.IOException
import java.io.InputStream

class XmlParser{

private val Movie = ArrayList<Movies>()
private var text: String? = null

private var MovieTitle = ""
private var MovienDescription = ""
private var MovieLink = ""

fun parse(inputStream: InputStream): List<Movies> {
    try {
        val factory = XmlPullParserFactory.newInstance()
        factory.isNamespaceAware = true
        val parser = factory.newPullParser()
        parser.setInput(inputStream, null)
        var eventType = parser.eventType
        while (eventType != XmlPullParser.END_DOCUMENT) {

            val tagName = parser.name

            when (eventType) {
                XmlPullParser.TEXT -> text = parser.text
                XmlPullParser.END_TAG -> when {
                    tagName.equals("title", ignoreCase = true) -> {
                        MovieTitle = text.toString()
                    }
                    tagName.equals("description", ignoreCase = true) -> {
                        MovienDescription = text.toString()
                    }
                    tagName.equals("link", ignoreCase = true) -> {
                        MovieLink = parser.getAttributeValue(1).toString()
                    }
                    tagName.equals("entry", ignoreCase = true) -> {
                        Movie.add(Movies(MovieTitle,MovieLink, MovienDescription))
                    }
                    else -> {}
                }

                else -> {
                }
            }
            eventType = parser.next()
        }

    } catch (e: XmlPullParserException) {
        e.printStackTrace()
    } catch (e: IOException) {
        e.printStackTrace()
    }
    return Movie
}

}