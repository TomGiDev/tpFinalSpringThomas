package com.poei.spring.tt.tpTTSpring.api.v1;

import com.poei.spring.tt.tpTTSpring.api.dto.AddressDto;
import com.poei.spring.tt.tpTTSpring.exception.UnknownResourceException;
import com.poei.spring.tt.tpTTSpring.mapper.AddressMapper;
import com.poei.spring.tt.tpTTSpring.service.AddressService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1/address")
public class AddressApi {

    private final AddressService addressService;
    private final AddressMapper addressMapper;

    public AddressApi(AddressService addressService, AddressMapper addressMapper) {
        this.addressService = addressService;
        this.addressMapper = addressMapper;
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(summary = "Return the list of all addresses")
    public ResponseEntity<List<AddressDto>> getAll() {
        return ResponseEntity.ok(
                addressService.getAll().stream()
                        .map(addressMapper::mapToDto)
                        .toList()
        );
    }

    @GetMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(summary = "Return a address by its id")
    public ResponseEntity<AddressDto> getUserById(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(addressMapper.mapToDto(addressService.getById(id)));
        } catch (UnknownResourceException ure) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ure.getMessage());
        }
    }

    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(summary = "Create an address")
    public ResponseEntity<AddressDto> createAddress(@RequestBody AddressDto addressDto) {
        AddressDto addressCreated = addressMapper.mapToDto(addressService.createAddress(addressMapper.mapToModel(addressDto)));
        return ResponseEntity.created(URI.create("/v1/addresses/" + addressCreated.getId()))
                .body(addressCreated);
    }

    @DeleteMapping(path = "/{id}")
    @Operation(summary = "Delete an address")
    public ResponseEntity<Void> deleteAddress(@PathVariable final Integer id) {
        try {
            addressService.deleteAddress(id);
            return ResponseEntity.noContent().build();
        } catch (UnknownResourceException ure) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ure.getMessage());
        }
    }

    @PutMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(summary = "Update address")
    public ResponseEntity<AddressDto> updateAddress(@RequestBody AddressDto addressDto, @PathVariable final Integer id) {
        try {
            addressDto.setId(id);
            AddressDto updatedAddress = addressMapper.mapToDto(addressService.updateAddress(addressMapper.mapToModel(addressDto)));
            return ResponseEntity.ok(updatedAddress);
        } catch (UnknownResourceException ure) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ure.getMessage());
        }
    }
}
