package com.example.tienda17.service.impl;

import com.example.tienda17.entity.Productos;
import com.example.tienda17.model.ProductosDto;
import com.example.tienda17.repository.IProductosRepository;
import com.example.tienda17.service.IProductosService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductosService implements IProductosService {
    private final IProductosRepository productosrepository;

    public ProductosService(IProductosRepository productosrepository) {
        this.productosrepository = productosrepository;
    }

    @Override
    public List<Productos> ReadAll() {
        return productosrepository.findAll().stream().filter(tie->tie.getActivo()).toList();
    }

    @Override
   public Optional<Productos> ReadById(Integer idproducto) {
        return productosrepository.findById(idproducto);
    }

    @Override
    public Productos create(Productos productos) {
        return productosrepository.save(productos);
    }

    @Override
    public Productos update(Productos productos) {
        return productosrepository.save(productos);
    }

    @Override
    public String deleteById(Integer idproducto) {
        Optional<Productos> produtoBuscado = productosrepository.findById(idproducto);
        if(produtoBuscado.isPresent()){
            Productos producto2 = produtoBuscado.get();
            producto2.setActivo(false);
            productosrepository.save(producto2);
            return "Producto eliminado: " + idproducto;
        }else{
        return "Registro eliminado no encontrado";
        }
    }
    @Override
    public List<Productos> precioMayor(double prodprecio) {
        return productosrepository.findByProdprecioGreaterThan(prodprecio);
    }

    @Override
    public List<Productos> precioMayorquery(double prodprecio) {
        return productosrepository.precioMayorquery(prodprecio);
    }

    @Override
    public List<ProductosDto> readAll() {
        return productosrepository.findAll().stream().map(
                productos -> {
                    ProductosDto productosDto= new ProductosDto(
                            productos.getIdproducto(), productos.getProdnombre(), productos.getProdprecio(),productos.getActivo());
                    return productosDto;
                }
        ).toList();
    }

}
