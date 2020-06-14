package com.yossefaz.peristentgeometry.model.dao;
import com.yossefaz.peristentgeometry.model.PersistentGeometry;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface PersistentGeometryService extends CrudRepository<PersistentGeometry, UUID> {}
