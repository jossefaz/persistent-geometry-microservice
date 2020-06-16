package com.yossefaz.peristentgeometry.controller;

import com.yossefaz.peristentgeometry.model.DTO.PersistentLineDTO;
import com.yossefaz.peristentgeometry.model.DTO.PersistentPolygonDTO;
import com.yossefaz.peristentgeometry.model.Entities.PersistentLine;
import com.yossefaz.peristentgeometry.model.Entities.PersistentPolygon;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("persistentGeometry/LineString")
public class PersistentLineController {

    @Autowired
    private PersistentLineDTO persistentLineDTO;

    @GetMapping
    public ResponseEntity<List<PersistentLine>> getAllGeometryObjects() {
        return new ResponseEntity(persistentLineDTO.getAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PersistentLine> getPGobject(@PathVariable UUID id) {
        return new ResponseEntity<>(persistentLineDTO.getOne(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Map<String, UUID>> createNewGeom(@RequestBody Map<String, String> payload) throws IOException {
        UUID newCreatedId = persistentLineDTO.create(payload);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newCreatedId).toUri();
        return ResponseEntity.created(location).body(Collections.singletonMap("id", newCreatedId));
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void DeleteGeom(@PathVariable UUID id) {
        persistentLineDTO.delete(id);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void updateGeom(@RequestBody Map<String, String> payload) {
        persistentLineDTO.update(payload);
    }
}
