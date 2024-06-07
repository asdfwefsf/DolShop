package com.company.data.mapper

import com.company.data.datasource.address.AddressInfo
import com.company.domain.model.DomainAddress

fun AddressInfo.toDomainAddress() : DomainAddress {
    return DomainAddress(
        id = this.id,
        addressName = this.addressName,
        addressNumber = this.addressNumber,
        address = this.address,
        addressDetailName = this.addressDetailName,
        phoneNumber = this.phoneNumber
    )
}