package com.mesarikaya.restapiwithspringwebflux.api.controllers;


import com.mesarikaya.restapiwithspringwebflux.Services.VendorService;
import com.mesarikaya.restapiwithspringwebflux.api.mapper.VendorMapper;
import com.mesarikaya.restapiwithspringwebflux.api.model.VendorDTO;
import com.mesarikaya.restapiwithspringwebflux.models.Vendor;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@Api("This is my Vendor Controller")
@RestController
@RequestMapping("/api/v1")
public class VendorController {

    private final VendorService vendorService;
    private final VendorMapper vendorMapper;

    public VendorController(VendorService vendorService, VendorMapper vendorMapper) {
        this.vendorService = vendorService;
        this.vendorMapper = vendorMapper;
    }

    @ApiOperation(value = "This will give the list of all vendors.", notes = "These are some notes about the API.")
    @GetMapping("/vendors")
    @ResponseStatus(HttpStatus.OK)
    public Mono<List<VendorDTO>> getVendors(){

        return vendorService.getVendors();
    }

    @ApiOperation(value = "This will give the list of all vendors with the provided lastname.", notes = "These are some notes about the API.")
    @GetMapping("/vendors/get/{lastName}")
    public Mono<List<VendorDTO>> getVendorsByLastName(@PathVariable String lastName){

        return vendorService.getByLastName(lastName);
    }

    @ApiOperation(value = "This enables search of vendors based on id.", notes = "These are some notes about the API.")
    @GetMapping("/vendors/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<VendorDTO> getVendorById(@PathVariable String id){

        return vendorService.getByID(id);
    }

    @ApiOperation(value = "This will allow adding new vendors to the list.", notes = "These are some notes about the API.")
    @PostMapping("/vendors/new")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Void> addVendor(@RequestBody VendorDTO theVendorDTO){

        Mono<Vendor> savedVendor = vendorService.saveVendor(theVendorDTO);
        return savedVendor.map(vendorMapper::vendorToVendorDTO).then();
    }

    @ApiOperation(value = "This will enable updating the vendor with all properties.", notes = "These are some notes about the API.")
    @PutMapping("/vendors/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<VendorDTO> updateVendor(@PathVariable String id, @RequestBody VendorDTO theVendorDTO){

        return vendorService.saveorUpdateVendor(id, theVendorDTO);
    }

    @ApiOperation(value = "This will enable to modify the vendor partially or fully.", notes = "These are some notes about the API.")
    @PatchMapping("/vendors/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<VendorDTO> patchVendor(@PathVariable String id, @RequestBody VendorDTO theVendorDTO){

        return vendorService.patch(id, theVendorDTO);
    }

    @ApiOperation(value = "This will delete the vendor.", notes = "These are some notes about the API.")
    @DeleteMapping("/vendors/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Mono<Void> deleteVendorById(@PathVariable String id){
        return vendorService.deleteVendorByID(id);
    }
}
