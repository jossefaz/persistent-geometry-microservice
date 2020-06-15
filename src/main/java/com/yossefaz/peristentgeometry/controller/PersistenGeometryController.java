package com.yossefaz.peristentgeometry.controller;

import com.yossefaz.peristentgeometry.model.DTO.PersistentGeometryDTO;
import com.yossefaz.peristentgeometry.model.PersistentGeometry;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;
@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("persistentGeometry")
public class PersistenGeometryController {

    @Autowired
    private PersistentGeometryDTO persistentGeometryDTO;

    @GetMapping
    public ResponseEntity<List<PersistentGeometry>> getAllGeometryObjects() {
        return new ResponseEntity(persistentGeometryDTO.getAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PersistentGeometry> getPGobject(@PathVariable UUID id) {
        return new ResponseEntity<>(persistentGeometryDTO.getOne(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UUID> createNewGeom(@RequestBody Map<String, String> payload) throws IOException {
        return new ResponseEntity(persistentGeometryDTO.create(payload), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void DeleteGeom(@PathVariable UUID id) {
        persistentGeometryDTO.delete(id);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void updateGeom(@RequestBody Map<String, String> payload) {
        persistentGeometryDTO.update(payload);
    }
}
