package com.seunggom.tripmanager.model

import com.seunggom.tripmanager.RegionNameAndPhoto
import java.io.Serializable


class ContentDTO : Serializable {
    var title: String? = null
    var startDate: String? = null
    var endDate: String? = null
    var regionName: ArrayList<RegionNameAndPhoto>? = null
    var userId: String? = null
    var rating: Float = 0f
    var explain: String? = null
    var timestamp: Long? = null
    var isOpen: Boolean = true
}