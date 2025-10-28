package com.example.tienda17.feign;
import com.example.tienda17.model.Aspirantes;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@FeignClient(value = "Asprante", url = "https://68d647ddc2a1754b426a2234.mockapi.io/api/v4/")

public interface IAspirante {



        @GetMapping("/aspirante")
        List<Aspirantes> readAll();

        @GetMapping("/aspirante/{id}")
        Aspirantes readById(@PathVariable String id);

        @PostMapping("/aspirante")
        Aspirantes create(@RequestBody Aspirantes pedido);

        @PutMapping("/aspirante/{id}")
        Aspirantes update(@PathVariable String id, @RequestBody Aspirantes pedido);

        @DeleteMapping("/aspirante/{id}")
        void delete(@PathVariable String id);
    }

