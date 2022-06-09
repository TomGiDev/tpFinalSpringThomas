package com.poei.spring.tt.tpTTSpring.api.v1;

import com.poei.spring.tt.tpTTSpring.api.dto.VehiculeDto;
import com.poei.spring.tt.tpTTSpring.exception.NotAllowedToDeleteException;
import com.poei.spring.tt.tpTTSpring.exception.UnknownRessourceException;
import com.poei.spring.tt.tpTTSpring.mapper.VehiculeMapper;
import com.poei.spring.tt.tpTTSpring.service.VehiculeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1/vehicule")
public class VehiculeApi {

    private final VehiculeService vehiculeService;
    private final VehiculeMapper vehiculeMapper;

    public VehiculeApi(VehiculeService vehiculeService, VehiculeMapper vehiculeMapper) {
        this.vehiculeService = vehiculeService;
        this.vehiculeMapper = vehiculeMapper;
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(summary = "Return the list of all vehicules.")
    public ResponseEntity<List<VehiculeDto>> getAll() {
        return ResponseEntity.ok(this.vehiculeService.getAll().stream()
                .map(this.vehiculeMapper::mapToDto)
                .toList());
    }

    @GetMapping("/{vehiculeId}")
    @Operation(summary = "Trying to retrieve a vehicule from the given ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Return the vehicule found for the given ID"),
            @ApiResponse(responseCode = "404", description = "No order vehicule for the given ID")
    })
    public ResponseEntity<VehiculeDto> getById(@PathVariable final Integer vehiculeId) {
        try {
            return ResponseEntity.ok(this.vehiculeMapper.mapToDto(vehiculeService.getById(vehiculeId)));
        } catch (UnknownRessourceException ure) {
            throw new UnknownRessourceException(ure.getMessage());
        }
    }

    @PostMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE}
    )
    @Operation(summary = "Create a new vehicule")
    @ApiResponse(responseCode = "201", description = "Vehicule succesfully created.")
    public ResponseEntity<VehiculeDto> createVehicule(@RequestBody final VehiculeDto vehiculeDto) {
        VehiculeDto vehiculeDtoResponse = this.vehiculeMapper.mapToDto(
                this.vehiculeService.createVehicule(
                        this.vehiculeMapper.mapToModel(vehiculeDto)));

        return ResponseEntity.created(URI.create("/v1/vehicules/" + vehiculeDtoResponse.getId()))
                .body(vehiculeDtoResponse);
    }

    @DeleteMapping(path = "/{id}")
    @Operation(summary = "Delete a vehicule for the give ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "No content"),
            @ApiResponse(responseCode = "403", description = "Cannot delete the vehicule for the given ID"),
            @ApiResponse(responseCode = "404", description = "No vehicule found for the given ID")
    })
    public ResponseEntity<Void> deleteVehicule(@PathVariable final Integer id) {
        try {
            this.vehiculeService.deleteVehicule(id);
            return ResponseEntity.noContent().build();
        } catch (UnknownRessourceException ure) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ure.getMessage());
        } catch (NotAllowedToDeleteException ex) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, ex.getMessage());
        }
    }

    @PutMapping(path = "/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
    @Operation(summary = "Update a vehicule")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "No content")
    })
    public ResponseEntity<Void> updateVehicule(@PathVariable final Integer id, @RequestBody VehiculeDto vehiculeDto) {
        try {
            vehiculeDto.setId(id);
            this.vehiculeService.updateVehicule(vehiculeMapper.mapToModel(vehiculeDto));

            return ResponseEntity.noContent().build();
        } catch (UnknownRessourceException ure) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ure.getMessage());
        }
    }
}
