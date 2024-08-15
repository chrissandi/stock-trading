package com.apps.stocktrading.base.contract;

public interface BaseContract {
    String SERVICE_NAME = "/stocktrading";
    String API = SERVICE_NAME+"/api";
    String GET_ALL = "/getAll";
    String GET_PAGED = "/getPaged";
    String GET_BY_ID = "/getById";
    String CREATE = "/create";
    String UPDATE = "/update";
    String DELETE = "/delete";
    String FILTER = "/filter";
    String GET_ALL_OPERATION = "/getAll/";
    String GET_PAGED_OPERATION = "/getPaged/";
    String GET_BY_ID_OPERATION = "/getById/";
    String CREATE_OPERATION = "/create/";
    String UPDATE_OPERATION = "/update/";
    String DELETE_OPERATION = "/delete/";
    String FILTER_OPERATION = "/filter/";
}
