package com.seunggom.tripmanager.model

import java.util.*

data class ContentDTO (var title : String? = null,
                       var startDate: Date? = null,
                       var endDate: Date? = null,
                       var region: Set<String>? = null,
                       var imageUrl: Set<String>? = null,
                       var userId: String? = null,
                       var rating: Int? = null,
                       var explain: String? = null)