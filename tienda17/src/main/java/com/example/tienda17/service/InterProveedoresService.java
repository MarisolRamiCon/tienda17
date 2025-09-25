package com.example.tienda17.service;

import com.example.tienda17.dto.ProveedoresRequest;
import com.example.tienda17.dto.ProveedoresResponse;

import java.util.List;
import java.util.Optional;

public interface InterProveedoresService {

    List<ProveedoresResponse> readAll();
    Optional<ProveedoresResponse> readById(Integer id);
    ProveedoresResponse create(ProveedoresRequest request);
    ProveedoresResponse update(Integer id, ProveedoresRequest request);
    String deleteById(Integer id);
}
