package com.twotwenty.rostovarchitecture

import com.google.firebase.firestore.GeoPoint
import java.io.Serializable

data class PlaceModel(
    var Name: String ?= null,
    var Description: String ?= null,
    var img: String ?= null,
    var Adress: String ?= null,
    var pointr: GeoPoint?= null
) : Serializable {
}