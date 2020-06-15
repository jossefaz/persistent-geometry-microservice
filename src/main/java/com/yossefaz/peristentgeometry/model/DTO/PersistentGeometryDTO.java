package com.yossefaz.peristentgeometry.model.DTO;

import com.vividsolutions.jts.geom.Polygon;
import com.yossefaz.peristentgeometry.model.PersistentGeometry;
import com.yossefaz.peristentgeometry.model.service.PersistentGeometryService;
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
public class PersistentGeometryDTO implements DTO<PersistentGeometry, UUID> {

    @Autowired
    private PersistentGeometryService persistentGeometryService;


    @Override
    public UUID create(PersistentGeometry type) {
        return null;
    }

    @Override
    public UUID create(Map<String, String> payload) {
        String wkt = payload.get("geometry");
        var newpoly = wktParser.wktToGeometry(wkt);
        var newPersistantGeomObject = PersistentGeometry.builder().geometry(newpoly).build();
        var newPersistantGeom = persistentGeometryService.save(newPersistantGeomObject);
        return newPersistantGeom.getId();
    }

    @Override
    public void update(PersistentGeometry type) {

    }
   @Override
    public void update(Map<String, String> payload) {
       Polygon wkt = wktParser.wktToGeometry(payload.get("geometry"));
       UUID id = UUID.fromString(payload.get("id"));
       PersistentGeometry newPersistantGeom = PersistentGeometry.builder().id(id).geometry(wkt).build();
       persistentGeometryService.save(newPersistantGeom);
    }

    @Override
    public List<PersistentGeometry> getAll() {
        List<PersistentGeometry> result = new ArrayList<>();
        persistentGeometryService.findAll().forEach(result::add);
        return result;
    }

    @Override
    public PersistentGeometry getOne(UUID uuid) {
        return persistentGeometryService.findById(uuid).orElse(null);
    }

    @Override
    public void delete(UUID uuid) {
        persistentGeometryService.deleteById(uuid);
    }
}
