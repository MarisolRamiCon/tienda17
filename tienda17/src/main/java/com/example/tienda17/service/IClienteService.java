package com.example.tienda17.service;

import com.example.tienda17.dto.ClienteResponse;

import java.util.List;


public interface IClienteService {
    public List<ClienteResponse> ReadAll();
}
