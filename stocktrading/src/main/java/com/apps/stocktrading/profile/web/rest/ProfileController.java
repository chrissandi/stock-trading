package com.apps.stocktrading.profile.web.rest;

import com.apps.stocktrading.profile.dto.ProfileDTO;
import com.apps.stocktrading.profile.service.ProfileService;
import com.apps.stocktrading.profile.web.contract.ProfileApiContract;
import com.apps.stocktrading.profile.web.request.ProfileUpdateRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ProfileApiContract.PROFILE)
public class ProfileController {
    private final ProfileService userService;

    public ProfileController(ProfileService userService) {
        this.userService = userService;
    }

    @PostMapping(ProfileApiContract.CREATE)
    public ProfileDTO createProfile(@RequestBody ProfileDTO user) {
        return userService.createProfile(user);
    }

    @GetMapping("/{username}")
    public ProfileDTO getProfile(@PathVariable String username) {
        return userService.getProfileByUsername(username);
    }

    @PostMapping(ProfileApiContract.UPDATE)
    public ProfileDTO updateProfile(@RequestBody ProfileUpdateRequest request) {
        return userService.updateProfile(request);
    }

    @DeleteMapping("/{username}")
    public void deleteProfile(@RequestParam String username) {
        userService.deleteProfile(username);
    }
}
