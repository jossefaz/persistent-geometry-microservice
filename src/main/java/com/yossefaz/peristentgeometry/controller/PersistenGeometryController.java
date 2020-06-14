package com.yossefaz.peristentgeometry.controller;

import com.vividsolutions.jts.geom.Polygon;
import com.yossefaz.peristentgeometry.model.PersistentGeometry;
import com.yossefaz.peristentgeometry.model.dao.PersistentGeometryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.yossefaz.peristentgeometry.utils.wktParser;
import java.io.IOException;
import java.util.Map;

@RestController
public class PersistenGeometryController {

    @Autowired
    private PersistentGeometryService persistentGeometryService;

    @CrossOrigin(origins = "*")
    @PostMapping(value="/persistentGeometry")
    public ResponseEntity createNewGeom(@RequestBody Map<String, String> payload) throws IOException {
        String wkt = payload.get("geometry");
        Polygon newpoly = wktParser.wktToGeometry(wkt);
        PersistentGeometry newPersistantGeom = PersistentGeometry.builder().geometry(newpoly).build();
        PersistentGeometry newGeom = persistentGeometryService.save(newPersistantGeom);
        var headers = new HttpHeaders();
        headers.add("Location", "/persistentGeometry/" + newGeom.getId().toString() );
        return new ResponseEntity(headers, HttpStatus.CREATED);
    }
}
