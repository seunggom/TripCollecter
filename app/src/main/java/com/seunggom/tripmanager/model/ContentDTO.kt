package com.seunggom.tripmanager.model

import com.seunggom.tripmanager.addRegionData
import java.util.*

data class ContentDTO (var title : String? = null,
                       var startDate: String? = null,
                       var endDate: String? = null,
                       var regionlist: ArrayList<addRegionData>? = null,
                       //var imageUrl: Set<String>? = null,
                       var imageUrl : String? = null,
                       var userId: String? = null,
                       var rating: Int? = null,
                       var explain: String? = null)