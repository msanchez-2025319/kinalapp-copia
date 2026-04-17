package com.mynorsanchez.kinalapp.service;

import com.mynorsanchez.kinalapp.entity.Cliente;
import com.mynorsanchez.kinalapp.entity.DetalleVenta;
import com.mynorsanchez.kinalapp.entity.Venta;
import com.mynorsanchez.kinalapp.repository.ClienteRepository;
import com.mynorsanchez.kinalapp.repository.DetalleVentaRepository;
import com.mynorsanchez.kinalapp.repository.VentaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ClienteService implements IClientesService {

    private final ClienteRepository clienteRepository;
    private final VentaRepository ventaRepository;
    private final DetalleVentaRepository detalleVentaRepository;

    public ClienteService(ClienteRepository clienteRepository, VentaRepository ventaRepository, DetalleVentaRepository detalleVentaRepository) {
        this.clienteRepository = clienteRepository;
        this.ventaRepository = ventaRepository;
        this.detalleVentaRepository = detalleVentaRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }

    @Override
    public List<Cliente> listarActivos() {
        return clienteRepository.findByEstado(1);
    }

    @Override
    public Cliente guardar(Cliente cliente) {
        validarCliente(cliente);
        if (cliente.getEstado() == null) {
            cliente.setEstado(1);
        }
        return clienteRepository.save(cliente);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Cliente> buscarPorDPI(String dpi) {
        return clienteRepository.findById(dpi);
    }

    @Override
    public Cliente actualizar(String dpi, Cliente cliente) {
        if(!clienteRepository.existsById(dpi)){
            throw new RuntimeException("El cliente no se encontro con el DPI " + dpi);
        }
        cliente.setDPICliente(dpi);
        validarCliente(cliente);
        return clienteRepository.save(cliente);
    }

    @Override
    public void eliminar(String dpi) {
        if (!clienteRepository.existsById(dpi)){
            throw new RuntimeException("El cliente no se encontro con el DPI " + dpi);
        }

        List<Venta> ventasCliente = ventaRepository.findByClienteDPICliente(dpi);
        for (Venta venta : ventasCliente) {
            List<DetalleVenta> detalles = detalleVentaRepository.findByVentaCodigoVenta(venta.getCodigoVenta());
            if (!detalles.isEmpty()) {
                detalleVentaRepository.deleteAll(detalles);
            }
        }

        if (!ventasCliente.isEmpty()) {
            ventaRepository.deleteAll(ventasCliente);
        }

        clienteRepository.deleteById(dpi);
    }

    @Override
    public boolean existePorDPI(String dpi) {
        return clienteRepository.existsById(dpi);
    }

    private void validarCliente(Cliente cliente){
        if(cliente.getDPICliente() == null || cliente.getDPICliente().trim().isEmpty()){
            throw new IllegalArgumentException("El DPI es un dato obligatorio");
        }

        if(cliente.getNombreCliente() == null || cliente.getNombreCliente().trim().isEmpty()){
            throw new IllegalArgumentException("El nombre es un dato obligatorio");
        }

        if(cliente.getApellidoCliente() == null || cliente.getApellidoCliente().trim().isEmpty()){
            throw new IllegalArgumentException("El apellido es un dato obligatorio");
        }
    }
}