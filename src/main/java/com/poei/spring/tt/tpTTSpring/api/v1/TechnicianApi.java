package com.poei.spring.tt.tpTTSpring.api.v1;

import com.poei.spring.tt.tpTTSpring.api.dto.TechnicianDto;
import com.poei.spring.tt.tpTTSpring.exception.UnknownRessourceException;
import com.poei.spring.tt.tpTTSpring.mapper.TechnicianMapper;
import com.poei.spring.tt.tpTTSpring.service.TechnicianService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1/technician")
public class TechnicianApi {

    private final TechnicianService technicianService;
    private final TechnicianMapper technicianMapper;

    public TechnicianApi(TechnicianService technicianService, TechnicianMapper technicianMapper) {
        this.technicianService = technicianService;
        this.technicianMapper = technicianMapper;
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(summary = "Return the list of all technicians")
    public ResponseEntity<List<TechnicianDto>> getAll() {
        return ResponseEntity.ok(
                technicianService.getAll().stream()
                        .map(technicianMapper::mapToDto)
                        .toList()
        );
    }

    @GetMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(summary = "Return a technician by its id")
    public ResponseEntity<TechnicianDto> getUserById(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(technicianMapper.mapToDto(technicianService.getById(id)));
        } catch (UnknownRessourceException ure) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ure.getMessage());
        }
    }

    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(summary = "Create a technician")
    public ResponseEntity<TechnicianDto> createTechnician(@RequestBody TechnicianDto technicianDto) {
        TechnicianDto technicianCreated = technicianMapper.mapToDto(technicianService.createTechnician(technicianMapper.mapToModel(technicianDto)));
        return ResponseEntity.created(URI.create("/v1/technician/" + technicianCreated.getId()))
                .body(technicianCreated);
    }

    @DeleteMapping(path = "/{id}")
    @Operation(summary = "Delete a technician")
    public ResponseEntity<Void> deleteTechnician(@PathVariable final Integer id) {
        try {
            technicianService.deleteTechnician(id);
            return ResponseEntity.noContent().build();
        } catch (UnknownRessourceException ure) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ure.getMessage());
        }
    }

    @PutMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(summary = "Update technician")
    public ResponseEntity<TechnicianDto> updateTechnician(@RequestBody TechnicianDto technicianDto, @PathVariable final Integer id) {
        try {
            technicianDto.setId(id);
            TechnicianDto updatedTechnician = technicianMapper.mapToDto(technicianService.updateTechnician(technicianMapper.mapToModel(technicianDto)));
            return ResponseEntity.ok(updatedTechnician);
        } catch (UnknownRessourceException ure) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ure.getMessage());
        }
    }

}
