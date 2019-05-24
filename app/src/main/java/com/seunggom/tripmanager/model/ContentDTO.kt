package com.seunggom.tripmanager.model

import java.util.*

data class ContentDTO (var explain: String? = null,
                       var title : String? = null,
                       var startDate: Date? = null,
                       var endDate: Date? = null) {

    data class Region (var name: String? = null,
                       var imageUrl: Set<String>? = null)
}