package com.company.data.mapper.advertisement

import com.company.data.datasource.advertisement.AdvertisementInfo
import com.company.domain.entity.Advertisement

fun AdvertisementInfo.toAdvertisement() : Advertisement {
    return Advertisement(
        AdvertisementMunGu = this.AdvertisementMunGu,
        AdvertisementImage = this.AdvertisementImage
    )
}