package ru.spectr.core_network.models

import com.google.gson.annotations.SerializedName

enum class LocationType {
    @SerializedName("city")
    CITY,

    @SerializedName("region")
    REGION,

    @SerializedName("state")
    STATE,

    @SerializedName("province")
    PROVINCE,

    @SerializedName("country")
    COUNTRY,

    @SerializedName("continent")
    CONTINENT
}