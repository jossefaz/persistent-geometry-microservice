package com.yossefaz.peristentgeometry.controller;

import com.vividsolutions.jts.geom.Geometry;
import com.yossefaz.peristentgeometry.model.DTO.PersistentLineDTO;
import com.yossefaz.peristentgeometry.model.DTO.PersistentPolygonDTO;
import com.yossefaz.peristentgeometry.model.Entities.PersistentLine;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("persistentGeometry")
public class PersistentGeometryController {

    @Autowired
    private PersistentLineDTO persistentLineDTO;

    @Autowired
    private PersistentPolygonDTO persistentPolygonDTO;

    @GetMapping
    public ResponseEntity<List<Geometry>> getAllGeometryObjects() {
        var allGeometry = Stream.concat(persistentLineDTO.getAll().stream(), persistentPolygonDTO.getAll().stream()).collect(Collectors.toList());
        return new ResponseEntity(allGeometry, HttpStatus.OK);
    }

}
