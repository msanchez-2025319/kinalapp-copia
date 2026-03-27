package com.mynorsanchez.kinalapp.service;

import com.mynorsanchez.kinalapp.entity.Producto;
import com.mynorsanchez.kinalapp.repository.ProductoRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductoService implements IProductoService{

    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Producto> listarTodo() {
        return productoRepository.findAll();
    }

    @Override
    public List<Producto> listarActivos() {
        return productoRepository.findByEstado(1L);
    }

    @Override
    public Producto guardar(Producto producto) {
        validarProducto(producto);
        return productoRepository.save(producto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Producto> buscarPorCodigoProducto(Long codigoProducto) {
        return productoRepository.findById(codigoProducto);
    }

    @Override
    public Producto actualizar(Long codigoProducto, Producto producto) {
        if (!productoRepository.existsById((codigoProducto))){
            throw new RuntimeException("El Producto no se encontro con el codigoProducto"+codigoProducto);
        }
        producto.setCodigoProducto(codigoProducto);
        validarProducto(producto);
        return productoRepository.save(producto);
    }



    @Override
    public void eliminar(Long codigoProducto) {
        if (!productoRepository.existsById(codigoProducto)){
            throw new RuntimeException("El Productno no se encontro con el codigoProducto"+codigoProducto);
        }
        productoRepository.deleteById(codigoProducto);
    }

    @Override
    public boolean existePorCodigoProducto(Long codigoProducto) {
        return productoRepository.existsById(codigoProducto);
    }
    private void validarProducto(Producto producto) {
    if (producto.getCodigoProducto()== null){
        throw new IllegalArgumentException("El Codigo Producto es un dato obligatorio");
    }
    if (producto.getNombre_producto()== null || producto.getNombre_producto().trim().isEmpty()){
        throw new IllegalArgumentException("El nombre es un dato obligatorio");

    }
    if (producto.getPrecio()== null){
        throw  new IllegalArgumentException("El Precion es un dato obligatorio ");
    }
    if (producto.getStock()==null){
        throw new IllegalArgumentException("El Stock es un dato obligatorio");
    }
    if (producto.getEstado()== null){
        throw new IllegalArgumentException("El Estado es un dato obligatorio");
        }
    }
}

