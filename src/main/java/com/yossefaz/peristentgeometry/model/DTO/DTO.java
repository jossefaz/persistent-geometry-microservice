package com.yossefaz.peristentgeometry.model.DTO;

import java.util.List;
import java.util.Map;

public interface DTO<T, ID> {
    ID create(T type);
    ID create(Map<String, String> payload);
    void update(T type);
    void update(Map<String, String> payload);
    List<T> getAll();
    T getOne(ID id);
    void delete(ID id);

}
