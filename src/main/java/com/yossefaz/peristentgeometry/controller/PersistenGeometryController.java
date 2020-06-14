package com.yossefaz.peristentgeometry.controller;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;
import com.yossefaz.peristentgeometry.model.PersistentGeometry;
import com.yossefaz.peristentgeometry.model.dao.PersistentGeometryImpl;
import com.yossefaz.peristentgeometry.model.dao.PersistentGeometryService;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.yossefaz.peristentgeometry.utils.wktParser;

import java.util.List;
import java.util.UUID;

@RestController
public class PersistenGeometryController {

@Autowired
private PersistentGeometryService persistentGeometryService;

    @GetMapping(value = "persistentGeometry")
    public ResponseEntity<List<PersistentGeometry>> listeGeom() {
        return new ResponseEntity<>(persistentGeometryService.findAll(), HttpStatus.OK);
    }

    //persistentGeometry/id
    @GetMapping(value = "persistentGeometry/{id}")
    public ResponseEntity<PersistentGeometry> getPGobject(@PathVariable UUID id) {

        PersistentGeometry persistentGeometry = PersistentGeometry.builder()
                .id(UUID.randomUUID())
                .geometry(wktParser.wktToGeometry("POINT(-105 40)"))
                .build();

        return new ResponseEntity<>(persistentGeometry, HttpStatus.OK);

    }

}
