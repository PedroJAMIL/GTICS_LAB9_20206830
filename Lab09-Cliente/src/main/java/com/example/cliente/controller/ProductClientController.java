package com.example.cliente.controller;

import com.example.cliente.dto.ProductDTO;
import com.example.cliente.service.ProductApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ProductClientController {

    @Autowired
    private ProductApiService productApiService;
    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/productos")
    public String listarProductos(Model model) {
        try {
            List<ProductDTO> productos = productApiService.getAllProducts();
            model.addAttribute("productos", productos);
            model.addAttribute("mensaje", "Productos cargados exitosamente");
            model.addAttribute("tipoMensaje", "success");
        } catch (Exception e) {
            model.addAttribute("productos", List.of());
            model.addAttribute("mensaje", "Error al cargar productos: " + e.getMessage());
            model.addAttribute("tipoMensaje", "danger");
        }
        return "productos";
    }
    
    @GetMapping("/buscar")
    public String buscarProducto(
            @RequestParam(required = false) Integer id,
            Model model) {
        
        if (id == null) {
            // Mostrar formulario de búsqueda vacío
            return "buscar";
        }

        try {
            ProductDTO producto = productApiService.getProductById(id);
            
            if (producto != null) {
                model.addAttribute("producto", producto);
                model.addAttribute("mensaje", "Producto encontrado exitosamente");
                model.addAttribute("tipoMensaje", "success");
            } else {
                model.addAttribute("mensaje", "Producto no encontrado con ID: " + id);
                model.addAttribute("tipoMensaje", "warning");
            }
        } catch (Exception e) {
            model.addAttribute("mensaje", "Error al buscar producto: " + e.getMessage());
            model.addAttribute("tipoMensaje", "danger");
        }

        return "buscar";
    }
}
