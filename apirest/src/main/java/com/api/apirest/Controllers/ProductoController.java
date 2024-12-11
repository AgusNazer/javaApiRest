package com.api.apirest.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.apirest.Repositories.ProductoRepositorie;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.api.apirest.Entities.Producto;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoRepositorie productoRepositorie;

    @GetMapping
    public List<Producto> getAllProductos(){
        return productoRepositorie.findAll();
    }
    @PostMapping 
    public Producto createProducto(@RequestBody Producto producto){
        return productoRepositorie.save(producto);
    }

        
    
    @GetMapping("/{id}")
    public Producto getProductById(@PathVariable Long id){
        return productoRepositorie.findById(id)
        .orElseThrow(() -> new RuntimeException("No se encontro el ID: " + id));
    }

    @PutMapping("/{id}")
    public Producto actualizarProducto(@PathVariable Long id, @RequestBody Producto detallesProducto){
        Producto producto = productoRepositorie.findById(id)
        .orElseThrow(() -> new RuntimeException("No se encontro el ID: " + id));

        producto.setNombre(detallesProducto.getNombre());
        producto.setPrecio(detallesProducto.getPrecio());

        return productoRepositorie.save(producto);
    }
    @DeleteMapping("/{id}")
    public String borrarProducto(@PathVariable Long id){
        Producto producto = productoRepositorie.findById(id)
        .orElseThrow(() -> new RuntimeException("No se encontro el ID: " + id)); 

         productoRepositorie.delete(producto);
         return "El producto con el id: " + id + "fue eliminado correctamente";
    }
}

