package com.apps.stocktrading.profile.web.contract;

import com.apps.stocktrading.base.contract.BaseContract;

public interface ProfileApiContract extends BaseContract {
    String VERSION = API+"/v1";
    String PROFILE = VERSION+"/profile";


    String GET_BY_USERNAME = "getByUsername";

}
