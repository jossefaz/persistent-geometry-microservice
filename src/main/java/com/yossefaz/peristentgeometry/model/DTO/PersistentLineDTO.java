package com.yossefaz.peristentgeometry.model.DTO;

import com.vividsolutions.jts.geom.LineString;
import com.yossefaz.peristentgeometry.model.Entities.PersistentLine;
import com.yossefaz.peristentgeometry.model.service.PersistentLineService;
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
public class PersistentLineDTO implements DTO<PersistentLine, UUID> {

    @Autowired
    private PersistentLineService PersistentLineService;


    @Override
    public UUID create(PersistentLine type) {
        return null;
    }

    @Override
    public UUID create(Map<String, String> payload) {
        String wkt = payload.get("geometry");
        var newpoly = wktParser.wktToGeometry(wkt);
        var newPersistantGeomObject = PersistentLine.builder().geometry((LineString) newpoly).build();
        var newPersistantGeom = PersistentLineService.save(newPersistantGeomObject);
        return newPersistantGeom.getId();
    }

    @Override
    public void update(PersistentLine type) {

    }
   @Override
    public void update(Map<String, String> payload) {
       LineString wkt = (LineString) wktParser.wktToGeometry(payload.get("geometry"));
       UUID id = UUID.fromString(payload.get("id"));
       PersistentLine newPersistantGeom = PersistentLine.builder().id(id).geometry(wkt).build();
       PersistentLineService.save(newPersistantGeom);
    }

    @Override
    public List<PersistentLine> getAll() {
        List<PersistentLine> result = new ArrayList<>();
        PersistentLineService.findAll().forEach(result::add);
        return result;
    }

    @Override
    public PersistentLine getOne(UUID uuid) {
        return PersistentLineService.findById(uuid).orElse(null);
    }

    @Override
    public void delete(UUID uuid) {
        PersistentLineService.deleteById(uuid);
    }
}
