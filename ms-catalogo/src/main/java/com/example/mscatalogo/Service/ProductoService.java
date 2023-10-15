package com.example.mscatalogo.Service;

import com.example.mscatalogo.Entity.Producto;

import java.util.List;
import java.util.Optional;
public interface ProductoService {

    public List<Producto> listar();

    public Producto guardar(Producto producto);

    public Producto actualizar(Producto producto);

    public Optional<Producto> listarPorId(Integer id);

    public void eliminarPorId(Integer id);

}
