package com.yossefaz.peristentgeometry.controller;

import com.vividsolutions.jts.geom.Polygon;
import com.yossefaz.peristentgeometry.model.PersistentGeometry;
import com.yossefaz.peristentgeometry.model.dao.PersistentGeometryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.yossefaz.peristentgeometry.utils.wktParser;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("persistentGeometry")
public class PersistenGeometryController {

    @Autowired
    private PersistentGeometryService persistentGeometryService;

    @GetMapping
    public ResponseEntity<List<PersistentGeometry>> listeGeom() {
        return new ResponseEntity(persistentGeometryService.findAll(), HttpStatus.OK);
    }

    //persistentGeometry/id
    @GetMapping(value = "/{id}")
    public ResponseEntity<PersistentGeometry> getPGobject(@PathVariable UUID id) {
        PersistentGeometry persistentGeometry = persistentGeometryService.findById(id).orElse(null);
        return new ResponseEntity<>(persistentGeometry, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UUID> createNewGeom(@RequestBody Map<String, String> payload) throws IOException {
        String wkt = payload.get("geometry");
        Polygon newpoly = wktParser.wktToGeometry(wkt);
        PersistentGeometry newPersistantGeom = PersistentGeometry.builder().geometry(newpoly).build();
        PersistentGeometry newGeom = persistentGeometryService.save(newPersistantGeom);
        return new ResponseEntity(newGeom.getId(), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void DeleteGeom(@PathVariable UUID id) {
        persistentGeometryService.deleteById(id);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void updateGeom(@RequestBody Map<String, String> payload) {
        Polygon wkt = wktParser.wktToGeometry(payload.get("geometry"));
        UUID id = UUID.fromString(payload.get("id"));
        PersistentGeometry newPersistantGeom = PersistentGeometry.builder().id(id).geometry(wkt).build();
        persistentGeometryService.save(newPersistantGeom);

    }
}
