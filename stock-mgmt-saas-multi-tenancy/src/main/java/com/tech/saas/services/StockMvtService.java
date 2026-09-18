package com.tech.saas.services;

import com.tech.saas.common.PageResponse;
import com.tech.saas.requests.StockMvtRequest;
import com.tech.saas.responses.StockMvtResponse;

public interface StockMvtService extends BasicService<StockMvtRequest, StockMvtResponse> {

    PageResponse<StockMvtResponse> findAllByProductId(final String productId, final int page, final int size);
}
