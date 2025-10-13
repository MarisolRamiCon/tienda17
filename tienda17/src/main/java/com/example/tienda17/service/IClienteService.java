package com.ejemploTienda.Service;


import java.util.List;

import com.ejemploTienda.Dto.ClienteDto;
import com.ejemploTienda.Dto.ClienteResponse;


public interface IClienteService {

	public List<ClienteResponse> ReadAll();
	
}
