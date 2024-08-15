package com.apps.stocktrading.profile.service;

import com.apps.stocktrading.profile.dto.ProfileDTO;
import com.apps.stocktrading.profile.web.request.ProfileUpdateRequest;

public interface ProfileService {
    ProfileDTO createProfile(ProfileDTO user);
    ProfileDTO getProfileByUsername(String username);
    ProfileDTO updateProfile(ProfileUpdateRequest user);

    void deleteProfile(String username);
}
