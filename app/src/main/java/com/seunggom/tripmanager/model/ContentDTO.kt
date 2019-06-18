package com.seunggom.tripmanager.model

import android.net.Uri
import com.seunggom.tripmanager.RegionName
import com.seunggom.tripmanager.addRegionData
import java.util.*
import kotlin.collections.ArrayList

data class ContentDTO (var title : String? = null,
                       var startDate: String? = null,
                       var endDate: String? = null,
                       var regionName : ArrayList<RegionName>? = null,
                       //var regionlist: ArrayList<addRegionData>? = null,
                       var userId: String? = null,
                       var rating: Int? = null,
                       var explain: String? = null)