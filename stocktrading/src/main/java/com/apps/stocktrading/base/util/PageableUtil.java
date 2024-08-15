package com.apps.stocktrading.base.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class PageableUtil {

    public static Pageable createPageable(int page, int pageSize){
        return PageRequest.of(page,pageSize);
    }
}
