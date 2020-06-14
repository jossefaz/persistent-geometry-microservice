package com.yossefaz.peristentgeometry.model.dao;

import java.util.List;
import java.util.UUID;

public interface PersistentGeometry {

    public List<PersistentGeometry> findAll();
    public PersistentGeometry findById(UUID id);
    public PersistentGeometry save(PersistentGeometry persistentGeometry);
}
