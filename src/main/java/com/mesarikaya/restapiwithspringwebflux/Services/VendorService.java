package com.mesarikaya.restapiwithspringwebflux.Services;

import com.mesarikaya.restapiwithspringwebflux.api.model.VendorDTO;
import com.mesarikaya.restapiwithspringwebflux.models.Vendor;
import reactor.core.publisher.Mono;

import java.util.List;

public interface VendorService {

    Mono<List<VendorDTO>> getByLastName(String lastName);
    Mono<List<VendorDTO>> getVendors();
    Mono<Long> count();
    Mono<Vendor> saveVendor(VendorDTO vendorDTO);
    Mono<VendorDTO> getByID(String id);
    Mono<VendorDTO> createNewVendor(VendorDTO vendorDTO);
    Mono<VendorDTO> saveorUpdateVendor(String id, VendorDTO vendorDTO);
    Mono<VendorDTO> patch(String id, VendorDTO vendorDTO);
    Mono<Void> deleteVendorByID(String id);
}
