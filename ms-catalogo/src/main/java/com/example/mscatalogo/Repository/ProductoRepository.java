package com.example.mscatalogo.Repository;

import com.example.mscatalogo.Entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {
}
