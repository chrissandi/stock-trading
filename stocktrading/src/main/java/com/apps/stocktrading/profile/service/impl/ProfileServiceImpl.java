package com.apps.stocktrading.profile.service.impl;

import com.apps.stocktrading.profile.dto.ProfileDTO;
import com.apps.stocktrading.profile.entity.ProfileEntity;
import com.apps.stocktrading.profile.repository.ProfileRepository;
import com.apps.stocktrading.profile.repository.mapper.ProfileEntityMapper;
import com.apps.stocktrading.profile.service.ProfileService;
import com.apps.stocktrading.profile.web.request.ProfileUpdateRequest;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class ProfileServiceImpl implements ProfileService {
    private static final Logger logger = Logger.getLogger(ProfileServiceImpl.class.getName());
    private final ProfileRepository profileRepository;
    private final ProfileEntityMapper mapper;

    public ProfileServiceImpl(ProfileRepository profileRepository, ProfileEntityMapper mapper) {
        this.profileRepository = profileRepository;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public ProfileDTO createProfile(ProfileDTO profile) {
        try{
            validateProfileCreation(profile);
        }
        catch (IllegalArgumentException err){
            logger.info(err.getMessage());
        }
        String usernameLower = profile.getUsername().toLowerCase();
        profile.setUsername(usernameLower);
        return mapper.toDto(profileRepository.saveAndFlush(mapper.toEntity(profile)));
    }

    @Override
    public ProfileDTO getProfileByUsername(String username) {
        Optional<ProfileEntity> profileOptional = profileRepository.findByUsername(username);
        return mapper.toDto(profileOptional.get());
    }

    @Override
    @Transactional
    public ProfileDTO updateProfile(ProfileUpdateRequest request) throws IllegalArgumentException{
        ProfileDTO result = new ProfileDTO();
        if(validateProfileUpdate(request)){
            Optional<ProfileEntity> optionalProfileEntity = profileRepository.findByUsername(request.getCurrentUsername());
            ProfileEntity profileEntity = optionalProfileEntity.get();
            if(!request.getNewUsername().isEmpty()){
                profileEntity.setUsername(request.getNewUsername());
            }
            if(!request.getEmail().isEmpty()){
                profileEntity.setEmail(request.getEmail());
            }
            if(!request.getPhoneNumber().isEmpty()){
                profileEntity.setPhoneNumber(request.getPhoneNumber());
            }
            if(!request.getFirstName().isEmpty()){
                profileEntity.setFirstName(request.getFirstName());
            }
            if(!request.getLastName().isEmpty()){
                profileEntity.setLastName(request.getLastName());
            }
            if(request.getDateOfBirth()!=null){
                profileEntity.setDateOfBirth(request.getDateOfBirth());
            }
            if(Objects.nonNull(request.getBankInfo())){
                try {
                    profileEntity.setBankInfo(mapper.fromBodyJsonToString(request.getBankInfo()));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            result = mapper.toDto(profileRepository.saveAndFlush(profileEntity));

        }

        return result;
    }

    @Override
    public void deleteProfile(String username) {
        Optional<ProfileEntity> profileOptional = profileRepository.findByUsername(username);
        if(profileOptional.isPresent()){
            ProfileEntity profileEntity = profileOptional.get();
            profileEntity.setDeletedDate(ZonedDateTime.now());
            profileEntity.setDeletedFlag(true);
            profileRepository.saveAndFlush(profileEntity);
        }
        else{
            throw new IllegalArgumentException("No matching profile.");
        }
    }


    private boolean validateProfileCreation(ProfileDTO profile) throws IllegalArgumentException{
        Optional<ProfileEntity> profileNameOptional = profileRepository.findByUsername(profile.getUsername());
        Optional<ProfileEntity> profileEmailOptional = profileRepository.findByEmail(profile.getEmail());
        if(profileNameOptional.isPresent()){
            throw new IllegalArgumentException("Username was taken!");
        }
        if(profileEmailOptional.isPresent()){
            throw new IllegalArgumentException("Email already exist!");
        }
        return true;
    }
    private boolean validateProfileUpdate(ProfileUpdateRequest request) throws IllegalArgumentException{
        Optional<ProfileEntity> profileUsernameNameOptional = profileRepository.findByUsername(request.getCurrentUsername());
        Optional<ProfileEntity> profileNewUsernameOptional = profileRepository.findByUsername(request.getCurrentUsername());
        Optional<ProfileEntity> profileEmailOptional = profileRepository.findByEmail(request.getEmail());
        if(!profileUsernameNameOptional.isPresent()){
            throw new IllegalArgumentException("Profile not found!");
        }
        if(profileNewUsernameOptional.isPresent()){
            throw new IllegalArgumentException("Username was taken!");
        }
        if(profileEmailOptional.isPresent()){
            throw new IllegalArgumentException("Email already exist!");
        }
        return true;
    }
}
