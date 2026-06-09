package com.worldcup.worldcup.shared.service;

import java.util.List;

public interface BaseService<Request, Response, ID> {
    List<Response> findAll();
    Response findById(ID id);
    Response save(Request request);
    Response update(ID id, Request request);
//    void logicalDeleteById(ID id);
    void deleteById(ID id);
}
