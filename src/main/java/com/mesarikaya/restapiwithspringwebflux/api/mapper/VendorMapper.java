package com.mesarikaya.restapiwithspringwebflux.api.mapper;

import com.mesarikaya.restapiwithspringwebflux.api.model.VendorDTO;
import com.mesarikaya.restapiwithspringwebflux.models.Vendor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface VendorMapper {

    VendorMapper INSTANCE = Mappers.getMapper(VendorMapper.class);


    // @Mapping(source="getId", target="id")
    VendorDTO vendorToVendorDTO(Vendor vendor);
    Vendor vendorDTOtoVendor(VendorDTO vendorDTO);
}
