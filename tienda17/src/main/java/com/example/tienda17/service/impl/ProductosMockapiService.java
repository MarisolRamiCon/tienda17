package com.example.tienda17.service.impl;

import com.example.tienda17.feign.IProductosMockapi;
import com.example.tienda17.model.ProductosMockapi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductosMockapiService implements IProductosMockapi {
    private final IProductosMockapi productosMockapiClient;

    @Autowired
    public ProductosMockapiService(IProductosMockapi productosMockapiClient) {
        this.productosMockapiClient = productosMockapiClient;
    }
    @Override
    public List<ProductosMockapi> ReadAll() {
        return productosMockapiClient.ReadAll();
    }
    @Override
    public ProductosMockapi ReadById(Integer id) {
        return productosMockapiClient.ReadById(id);
    }
    @Override
    public ProductosMockapi create(ProductosMockapi productosMockapi) {
        return productosMockapiClient.create(productosMockapi);
    }

    @Override
    public ProductosMockapi update(int id, ProductosMockapi productosMockapi) {
        return productosMockapiClient.update(id, productosMockapi);
    }

    @Override
    public String deleteLogico(Integer id) {
        Optional<ProductosMockapi> productoBuscado = java.util.Optional.ofNullable(productosMockapiClient.ReadById(id));
        if (productoBuscado.isPresent()) {
            ProductosMockapi producto2 = productoBuscado.get();
            producto2.setProdActivo(false);
            productosMockapiClient.update(id, producto2);
            return "Producto borrado con el ID: " + id;
        } else {
            return "Producto no encontrado con el ID: " + id;
        }
    }
}
