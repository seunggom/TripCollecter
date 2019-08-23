package com.seunggom.tripmanager.model

import java.io.Serializable

class RegionDTO : Serializable {
    var si_do_1 = ArrayList<Int>()
    var si_do_2 = ArrayList<Int>()
    var si_do_3 = ArrayList<Int>()
    var si_do_4 = ArrayList<Int>()
    var si_do_5 = ArrayList<Int>()
    var si_do_6 = ArrayList<Int>()
    var si_do_7 = ArrayList<Int>()
    var si_do_8 = ArrayList<Int>()
    var si_do_9 = ArrayList<Int>()
    var si_do_10 = ArrayList<Int>()

    constructor() {
        for(i in 0..7) si_do_1.add(0)
        for(i in 0..30) si_do_2.add(0)
        for(i in 0..17) si_do_3.add(0)
        for(i in 0..10) si_do_4.add(0)
        for(i in 0..14) si_do_5.add(0)
        for(i in 0..22) si_do_6.add(0)
        for(i in 0..17) si_do_7.add(0)
        for(i in 0..13) si_do_8.add(0)
        for(i in 0..21) si_do_9.add(0)
        for(i in 0..1) si_do_10.add(0)
    }
}
