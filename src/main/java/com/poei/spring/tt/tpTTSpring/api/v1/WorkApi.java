package com.poei.spring.tt.tpTTSpring.api.v1;

import com.poei.spring.tt.tpTTSpring.api.dto.WorkDto;
import com.poei.spring.tt.tpTTSpring.exception.NotAllowedToDeleteException;
import com.poei.spring.tt.tpTTSpring.exception.UnknownRessourceException;
import com.poei.spring.tt.tpTTSpring.mapper.WorkMapper;
import com.poei.spring.tt.tpTTSpring.service.WorkService;
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
@RequestMapping("/v1/work")
public class WorkApi {

    @Autowired
    private final WorkService workService;
    private final WorkMapper workMapper;

    public WorkApi(WorkService workService, WorkMapper workMapper) {
        this.workService = workService;
        this.workMapper = workMapper;
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(summary = "Return the list of all works.")
    public ResponseEntity<List<WorkDto>> getAll() {
        return ResponseEntity.ok(this.workService.getAll().stream()
                .map(this.workMapper::mapToDto)
                .toList());
    }

    @GetMapping("/{workId}")
    @Operation(summary = "Trying to retrieve a work from the given ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Return the work found for the given ID"),
            @ApiResponse(responseCode = "404", description = "No work found for the given ID")
    })
    public ResponseEntity<WorkDto> getById(@PathVariable final Integer workId) {
        try {
            return ResponseEntity.ok(this.workMapper.mapToDto(workService.getById(workId)));
        } catch (UnknownRessourceException ure) {
            throw new UnknownRessourceException(ure.getMessage());
        }
    }

    @PostMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE}
    )
    @Operation(summary = "Create a new order")
    @ApiResponse(responseCode = "201", description = "Work succesfully created.")
    public ResponseEntity<WorkDto> createWork(@RequestBody final WorkDto workDto) {
        WorkDto workDtoResponse = this.workMapper.mapToDto(
                this.workService.createWork(
                        this.workMapper.mapToModel(workDto)));

        return ResponseEntity.created(URI.create("/v1/work/" + workDtoResponse.getId()))
                .body(workDtoResponse);
    }

    @DeleteMapping(path = "/{id}")
    @Operation(summary = "Delete a work for the give ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "No content"),
            @ApiResponse(responseCode = "403", description = "Cannot delete the work for the given ID"),
            @ApiResponse(responseCode = "404", description = "No work found for the given ID")
    })
    public ResponseEntity<Void> deleteWork(@PathVariable final Integer id) {
        try {
            this.workService.deleteWork(id);
            return ResponseEntity.noContent().build();
        } catch (UnknownRessourceException ure) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ure.getMessage());
        } catch (NotAllowedToDeleteException ex) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, ex.getMessage());
        }
    }

    @PutMapping(path = "/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
    @Operation(summary = "Update a work")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "No content")
    })
    public ResponseEntity<Void> updateWork(@PathVariable final Integer id, @RequestBody WorkDto workDto) {
        try {
            workDto.setId(id);
            this.workService.updateWork(workMapper.mapToModel(workDto));

            return ResponseEntity.noContent().build();
        } catch (UnknownRessourceException ure) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ure.getMessage());
        }
    }
}
