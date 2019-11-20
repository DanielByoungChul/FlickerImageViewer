package com.deloitte.flickerimageviewer.utils

import com.deloitte.flickerimageviewer.models.Photo
import org.junit.Test
import org.junit.Assert.*


/**
 *  PhotoInfoToHttpsUrl Test
 *
 *   https://farm{farm}.static.flickr.com/{server}/{id}_{secret}.jpg
 *   https://farm5.static.flickr.com/4740/39593986652_0ec416669f.jpg
 *
 * */

class PhotoInfoToHttpsUrlTest {

    val correct_https_url1 = "https://farm5.static.flickr.com/4740/39593986652_0ec416669f.jpg"
    val correct_https_url2 = "https://farm9.static.flickr.com/8080/19593986652_11c416669f.jpg"
    val wrong_http_url = "http://farm5.static.flickr.com/4740/39593986652_0ec416669f.jpg"

    @Test
    fun photoInfoToHttpsUrl__convert_is_correct_case1() {
        val photo = Photo("39593986652","0ec416669f","4740",5)
        PhotoInfoToHttpsUrl.convert(photo)
        assertEquals(correct_https_url1, PhotoInfoToHttpsUrl.convert(photo))
    }

    @Test
    fun photoInfoToHttpsUrl__convert_is_correct_case2() {
        val photo = Photo("19593986652","11c416669f","8080",9)
        PhotoInfoToHttpsUrl.convert(photo)
        assertEquals(correct_https_url2, PhotoInfoToHttpsUrl.convert(photo))
    }

    @Test
    fun photoInfoToHttpsUrl__convert_is_incorrect_with_http() {
        val photo = Photo("39593986652","0ec416669f","4740",5)
        PhotoInfoToHttpsUrl.convert(photo)
        assertNotEquals(wrong_http_url, PhotoInfoToHttpsUrl.convert(photo))
    }

    @Test
    fun photoInfoToHttpsUrl__convert_is_incorrect_with_id() {
        val photo = Photo("41293986652","0ec416669f","4740",5)
        PhotoInfoToHttpsUrl.convert(photo)
        assertNotEquals(correct_https_url1, PhotoInfoToHttpsUrl.convert(photo))
    }

    @Test
    fun photoInfoToHttpsUrl__convert_is_incorrect_with_secret() {
        val photo = Photo("39593986652","1ec416669f","4740",5)
        PhotoInfoToHttpsUrl.convert(photo)
        assertNotEquals(correct_https_url1, PhotoInfoToHttpsUrl.convert(photo))
    }

    @Test
    fun photoInfoToHttpsUrl__convert_is_incorrect_with_server() {
        val photo = Photo("39593986652","0ec416669f","5740",5)
        PhotoInfoToHttpsUrl.convert(photo)
        assertNotEquals(correct_https_url1, PhotoInfoToHttpsUrl.convert(photo))
    }

    @Test
    fun photoInfoToHttpsUrl__convert_is_incorrect_with_farm() {
        val photo = Photo("39593986652","0ec416669f","4740",6)
        PhotoInfoToHttpsUrl.convert(photo)
        assertNotEquals(correct_https_url1, PhotoInfoToHttpsUrl.convert(photo))
    }
}
