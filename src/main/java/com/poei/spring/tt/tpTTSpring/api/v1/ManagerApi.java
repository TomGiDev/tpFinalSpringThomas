package com.poei.spring.tt.tpTTSpring.api.v1;

import com.poei.spring.tt.tpTTSpring.api.dto.ManagerDto;
import com.poei.spring.tt.tpTTSpring.exception.NotAllowedToDeleteException;
import com.poei.spring.tt.tpTTSpring.exception.UnknownRessourceException;
import com.poei.spring.tt.tpTTSpring.mapper.ManagerMapper;
import com.poei.spring.tt.tpTTSpring.service.ManagerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1/manager")
public class ManagerApi {

    @Autowired
    private final ManagerService managerService;
    private final ManagerMapper managerMapper;

    public ManagerApi(ManagerService managerService, ManagerMapper managerMapper) {
        this.managerService = managerService;
        this.managerMapper = managerMapper;
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(summary = "Return the list of all managers.")
    public ResponseEntity<List<ManagerDto>> getAll() {
        return ResponseEntity.ok(this.managerService.getAll().stream()
                .map(this.managerMapper::mapToDto)
                .toList());
    }

    @GetMapping("/{managerId}")
    @Operation(summary = "Trying to retrieve a manager from the given ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Return the manager found for the given ID"),
            @ApiResponse(responseCode = "404", description = "No manager found for the given ID")
    })
    public ResponseEntity<ManagerDto> getById(@PathVariable final Integer managerId) {
        try {
            return ResponseEntity.ok(this.managerMapper.mapToDto(managerService.getById(managerId)));
        } catch (UnknownRessourceException ure) {
            throw new UnknownRessourceException(ure.getMessage());
        }
    }

    @PostMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE}
    )
    @Operation(summary = "Create a new manager")
    @ApiResponse(responseCode = "201", description = "Manager succesfully created.")
    public ResponseEntity<ManagerDto> createManager(@RequestBody final ManagerDto managerDto) {
        ManagerDto managerDtoResponse = this.managerMapper.mapToDto(
                this.managerService.createManager(
                        this.managerMapper.mapToModel(managerDto)));

        return ResponseEntity.created(URI.create("/v1/managers/" + managerDtoResponse.getId()))
                .body(managerDtoResponse);
    }

    @DeleteMapping(path = "/{id}")
    @Operation(summary = "Delete a manager for the given ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "No content"),
            @ApiResponse(responseCode = "403", description = "Cannot delete the manager for the given ID"),
            @ApiResponse(responseCode = "404", description = "No manager found for the given ID")
    })
    public ResponseEntity<Void> deleteManager(@PathVariable final Integer id) {
        try {
            this.managerService.deleteManager(id);
            return ResponseEntity.noContent().build();
        } catch (UnknownRessourceException ure) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ure.getMessage());
        } catch (NotAllowedToDeleteException ex) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, ex.getMessage());
        }
    }

    @PutMapping(path = "/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
    @Operation(summary = "Update a manager")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "No content")
    })
    public ResponseEntity<Void> updateManager(@PathVariable final Integer id, @RequestBody ManagerDto managerDto) {
        try {
            managerDto.setId(id);
            this.managerService.updateManager(managerMapper.mapToModel(managerDto));

            return ResponseEntity.noContent().build();
        } catch (UnknownRessourceException ure) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ure.getMessage());
        }
    }
}
