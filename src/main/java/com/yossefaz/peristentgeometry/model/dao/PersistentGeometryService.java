package com.yossefaz.peristentgeometry.model.dao;

import com.yossefaz.peristentgeometry.model.PersistentGeometry;

import java.util.List;
import java.util.UUID;

public interface PersistentGeometryService {

    List<PersistentGeometry> findAll();
    PersistentGeometry findById(UUID id);
    PersistentGeometry save(PersistentGeometryService persistentGeometryService);
}
