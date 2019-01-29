package com.mesarikaya.restapiwithspringwebflux.Services;


import com.mesarikaya.restapiwithspringwebflux.Repositories.VendorRepository;
import com.mesarikaya.restapiwithspringwebflux.api.mapper.VendorMapper;
import com.mesarikaya.restapiwithspringwebflux.api.model.VendorDTO;
import com.mesarikaya.restapiwithspringwebflux.models.Vendor;
import org.bson.codecs.ObjectIdGenerator;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class VendorServiceImpl implements VendorService{


    private VendorRepository vendorRepository;
    private VendorMapper vendorMapper;

    public VendorServiceImpl(VendorRepository vendorRepository, VendorMapper vendorMapper) {
        this.vendorRepository = vendorRepository;
        this.vendorMapper = vendorMapper;
    }


    @Override
    public Mono<List<VendorDTO>> getByLastName(String lastName) {
        Flux<Vendor> vendors = vendorRepository.findByLastName(lastName);
        Mono<List<VendorDTO>> vendortoVendorDTO = vendors.map(vendorMapper::vendorToVendorDTO).collectList();

        return vendortoVendorDTO;
    }

    @Override
    public Mono<List<VendorDTO>> getVendors() {
        Flux<Vendor> vendors = vendorRepository.findAll();
        Mono<List<VendorDTO>> vendorToVendorDTOList = vendors.map(vendorMapper::vendorToVendorDTO).collectList();

        return vendorToVendorDTOList;
    }

    @Override
    public Mono<Long> count() {
        return vendorRepository.count();
    }

    @Override
    public Mono<Vendor> saveVendor(VendorDTO vendorDTO) {
        Vendor vendor = vendorMapper.vendorDTOtoVendor(vendorDTO);
        vendor.setId(new ObjectIdGenerator().generate().toString());
        return vendorRepository.save(vendor);
    }

    @Override
    public Mono<VendorDTO> getByID(String id) {
        Mono<Vendor> vendor = vendorRepository.findById(id);

        if(vendor != null){
            Mono<VendorDTO> vendorDTO = vendor.map(vendorMapper::vendorToVendorDTO);
            return vendorDTO;
        }else{
            throw new RuntimeException("No such id exists!");
        }
    }

    @Override
    public Mono<VendorDTO> createNewVendor(VendorDTO vendorDTO) {

        Vendor vendor = vendorMapper.vendorDTOtoVendor(vendorDTO);
        vendor.setId(new ObjectIdGenerator().generate().toString());
        Mono<Vendor> savedVendor = vendorRepository.save(vendor);

        Mono<VendorDTO> returnToDTO = savedVendor.map(vendorMapper::vendorToVendorDTO);

        return returnToDTO;
    }

    @Override
    public Mono<VendorDTO> saveorUpdateVendor(String id, VendorDTO vendorDTO) {
        if(this.getByID(id) != null){
            Vendor vendor = vendorMapper.vendorDTOtoVendor(vendorDTO);
            vendor.setId(new ObjectIdGenerator().generate().toString());
            Mono<Vendor> savedVendor = vendorRepository.save(vendor);
            return savedVendor.map(vendorMapper::vendorToVendorDTO);
        }else{
            Mono<VendorDTO> savedVendorDTO = this.createNewVendor(vendorDTO);
            return savedVendorDTO;
        }
    }


    @Override
    public Mono<VendorDTO> patch(String id, VendorDTO vendorDTO) {

        Vendor vendor = vendorRepository.findById(id).block();

        if (vendor != null){

            if(vendorDTO.getLastName() != null){
                vendor.setLastName(vendorDTO.getLastName());
            }

            if(vendorDTO.getFirstName() != null){
                vendor.setFirstName(vendorDTO.getFirstName());
            }

            // Save customer
            Mono<Vendor> savedVendor = vendorRepository.save(vendor);

            // Convert Customer to CustomerDTO object
            VendorDTO returnDTO = savedVendor.map(vendorMapper::vendorToVendorDTO).block();

            return Mono.just(returnDTO);
        }
        else{
            Mono<VendorDTO> savedVendorDTO = this.createNewVendor(vendorDTO);
            return savedVendorDTO;
        }
    }

    @Override
    public Mono<Void> deleteVendorByID(String id) {
        return vendorRepository.deleteById(id);
    }

}
