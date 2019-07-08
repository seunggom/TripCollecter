package com.seunggom.tripmanager.model

import com.seunggom.tripmanager.RegionNameAndPhoto
import com.seunggom.tripmanager.addRegionData
import kotlin.collections.ArrayList

data class ContentDTO (var title : String? = null,
                       var startDate: String? = null,
                       var endDate: String? = null,
                       var regionName : ArrayList<RegionNameAndPhoto>? = null,
                       var userId: String? = null,
                       var rating: Int? = null,
                       var explain: String? = null,
                       var timestamp: Long? = null)