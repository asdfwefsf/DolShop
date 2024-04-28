package com.company.data_impl.updatesaleinfo


import com.company.data_datasource.productsale.ProductSaleDao
import com.company.domain.model.DomainProductSaleModel
import com.company.domain.repository.UpdateProductSaleRepository
import com.company.saletodomain.mapper.toDomainProductSaleModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UpdateProductSaleRepositoryImpl @Inject constructor(
    private val dao: ProductSaleDao
) : UpdateProductSaleRepository {

    override suspend fun updateSaleMunGu(): Flow<List<DomainProductSaleModel>> {
        return dao.getProductSaleInfo().map {
            it.map {

                it.toDomainProductSaleModel()
            }
        }
    }

}