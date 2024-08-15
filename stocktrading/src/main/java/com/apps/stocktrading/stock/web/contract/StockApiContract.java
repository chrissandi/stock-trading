package com.apps.stocktrading.stock.web.contract;

import com.apps.stocktrading.base.contract.BaseContract;

public interface StockApiContract extends BaseContract {
    String VERSION = API+"/v1";
    String STOCK = VERSION+"/stock";


    String GET_BY_STOCKCODE = "getByStockCode";
}
