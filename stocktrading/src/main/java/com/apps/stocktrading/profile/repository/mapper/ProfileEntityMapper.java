package com.apps.stocktrading.profile.repository.mapper;

import com.apps.stocktrading.profile.dto.BankInfoDTO;
import com.apps.stocktrading.profile.dto.ProfileDTO;
import com.apps.stocktrading.profile.entity.ProfileEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.io.IOException;
import java.util.Objects;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ProfileEntityMapper {
    ObjectMapper objectMapper = new ObjectMapper();

    @Mapping(target = "bankInfo", source = "bankInfo", qualifiedByName = "bankInfoDtoToJsonString")
    ProfileDTO toDto(ProfileEntity ProfileEntity);
    @Mapping(target = "bankInfo", source = "bankInfo", qualifiedByName = "jsonStringToBankInfoDto")
    ProfileEntity toEntity(ProfileDTO ProfileDTO);

    @Named("bankInfoDtoToJsonString")
    default String bankInfoDtoToJsonString(BankInfoDTO bankDto) throws IOException {
        if(Objects.nonNull(bankDto)){
            return new String(objectMapper.writeValueAsBytes(bankDto));
        }
        return null;
    }

    @Named("jsonStringToBankInfoDto")
    default BankInfoDTO jsonStringToBankInfoDto(String bankJson) throws IOException {
        if(Objects.nonNull(bankJson)){
            BankInfoDTO dto = objectMapper.readValue(bankJson, BankInfoDTO.class);
            return dto;
        }
        return null;
    }
}
