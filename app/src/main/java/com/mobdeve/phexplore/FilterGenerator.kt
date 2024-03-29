package com.mobdeve.phexplore

class FilterGenerator {

    companion object{
        fun loadData(): ArrayList<FilterModel>{
            val data = ArrayList<FilterModel>()

            data.add(FilterModel("LandEats", R.drawable.filter_landeats_icon, "#D2FDC6"))
            data.add(FilterModel("CulTour", R.drawable.filter_cultour_icon, "#F6A0A3"))
            data.add(FilterModel("PastQuests", R.drawable.filter_pastquests_icon, "#C892F8"))
            data.add(FilterModel("ThrillScapes", R.drawable.filter_thrillscapes_icon, "#F3CE84"))

            return data
        }
    }
}