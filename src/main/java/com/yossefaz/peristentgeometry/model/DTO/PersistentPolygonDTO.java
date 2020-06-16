package com.yossefaz.peristentgeometry.model.DTO;

import com.vividsolutions.jts.geom.Polygon;
import com.yossefaz.peristentgeometry.model.Entities.PersistentPolygon;
import com.yossefaz.peristentgeometry.model.service.PersistentPolygonService;
import com.yossefaz.peristentgeometry.utils.wktParser;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Service
public class PersistentPolygonDTO implements DTO<PersistentPolygon, UUID> {

    @Autowired
    private PersistentPolygonService persistentPolygonService;


    @Override
    public UUID create(PersistentPolygon type) {
        return null;
    }

    @Override
    public UUID create(Map<String, String> payload) {
        String wkt = payload.get("geometry");
        var newpoly = (Polygon) wktParser.wktToGeometry(wkt);
        var newPersistantGeomObject = PersistentPolygon.builder().geometry(newpoly).build();
        var newPersistantGeom = persistentPolygonService.save(newPersistantGeomObject);
        return newPersistantGeom.getId();
    }

    @Override
    public void update(PersistentPolygon type) {

    }
   @Override
    public void update(Map<String, String> payload) {
       var wkt = (Polygon) wktParser.wktToGeometry(payload.get("geometry"));
       UUID id = UUID.fromString(payload.get("id"));
       PersistentPolygon newPersistantGeom = PersistentPolygon.builder().id(id).geometry(wkt).build();
       persistentPolygonService.save(newPersistantGeom);
    }

    @Override
    public List<PersistentPolygon> getAll() {
        List<PersistentPolygon> result = new ArrayList<>();
        persistentPolygonService.findAll().forEach(result::add);
        return result;
    }

    @Override
    public PersistentPolygon getOne(UUID uuid) {
        return persistentPolygonService.findById(uuid).orElse(null);
    }

    @Override
    public void delete(UUID uuid) {
        persistentPolygonService.deleteById(uuid);
    }
}
