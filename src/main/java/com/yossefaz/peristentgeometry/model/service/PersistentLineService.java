package com.yossefaz.peristentgeometry.model.service;
import com.yossefaz.peristentgeometry.model.Entities.PersistentLine;
import com.yossefaz.peristentgeometry.model.Entities.PersistentPolygon;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface PersistentLineService extends CrudRepository<PersistentLine, UUID> {}
