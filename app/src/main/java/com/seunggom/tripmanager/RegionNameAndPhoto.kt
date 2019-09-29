package com.seunggom.tripmanager

import java.io.Serializable

data class RegionNameAndPhoto (var name1: String? = null,
                               var name2: String? = null,
                               var photo: ArrayList<String>? = null,
                               var explains: ArrayList<String>? = null) : Serializable
