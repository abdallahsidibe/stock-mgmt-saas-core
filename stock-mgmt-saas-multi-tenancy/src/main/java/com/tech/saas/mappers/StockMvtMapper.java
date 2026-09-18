package com.tech.saas.mappers;

import com.tech.saas.entities.Product;
import com.tech.saas.entities.StockMvt;
import com.tech.saas.requests.StockMvtRequest;
import com.tech.saas.responses.StockMvtResponse;
import org.springframework.stereotype.Component;

@Component
public class StockMvtMapper {

    public StockMvt toEntity(final StockMvtRequest request) {
        return StockMvt.builder()
                       .dateMvt(request.getDateMvt())
                       .comment(request.getComment())
                       .typeMvt(request.getTypeMvt())
                       .quantity(request.getQuantity())
                       .product(Product.builder()
                                       .id(request.getProductId())
                                       .build())
                .deleted(false)
                       .build();
    }

    public StockMvtResponse toResponse(final StockMvt entity) {
        return StockMvtResponse.builder()
                .id(entity.getId())
                               .dateMvt(entity.getDateMvt())
                               .comment(entity.getComment())
                               .typeMvt(entity.getTypeMvt())
                               .quantity(entity.getQuantity())
                               .build();
    }
}
