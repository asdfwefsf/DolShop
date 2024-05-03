package com.company.data.mapper

import com.company.datasource_local_address.AddressInfo
import com.company.domain.model.DomainAddress

fun DomainAddress.toAddressInfo() : AddressInfo {
    return AddressInfo(
        id = this.id,
        addressName = this.addressName,
        addressNumber = this.addressNumber,
        address = this.address,
        addressDetailName = this.addressDetailName,
        phoneNumber = this.phoneNumber
    )
}