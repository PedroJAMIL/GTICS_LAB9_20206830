package com.example.cliente.service;

import com.example.cliente.dto.ProductDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.client5.http.auth.AuthScope;
import org.apache.hc.client5.http.auth.UsernamePasswordCredentials;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.auth.BasicCredentialsProvider;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Ejercicio 4.1: Configuración del Cliente (2 puntos)
 * - Implementa la lógica necesaria para consumir servicios web protegidos
 * - Configura la autenticación requerida para acceder al API
 */
@Service
public class ProductApiService {

    @Value("${api.base.url}")
    private String apiBaseUrl;

    @Value("${api.username}")
    private String username;

    @Value("${api.password}")
    private String password;

    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Crea un cliente HTTP con autenticación básica configurada
     */
    private CloseableHttpClient createAuthenticatedClient() {
        BasicCredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(
            new AuthScope(null, -1),
            new UsernamePasswordCredentials(username, password.toCharArray())
        );

        return HttpClients.custom()
            .setDefaultCredentialsProvider(credentialsProvider)
            .build();
    }

    /**
     * Ejercicio 4.2: Listar Productos Autenticado (2 puntos)
     * Obtiene todos los productos del API protegido
     */
    public List<ProductDTO> getAllProducts() {
        try (CloseableHttpClient httpClient = createAuthenticatedClient()) {
            HttpGet request = new HttpGet(apiBaseUrl);
            
            try (CloseableHttpResponse response = httpClient.execute(request)) {
                int statusCode = response.getCode();
                
                if (statusCode == 200) {
                    String json = EntityUtils.toString(response.getEntity());
                    return objectMapper.readValue(json, new TypeReference<List<ProductDTO>>() {});
                } else if (statusCode == 401) {
                    throw new RuntimeException("Error de autenticación: credenciales inválidas");
                } else {
                    throw new RuntimeException("Error al obtener productos: HTTP " + statusCode);
                }
            }
        } catch (Exception e) {
            System.err.println("Error al conectar con el API: " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    /**
     * Ejercicio 4.3: Buscar un Producto Autenticado (2 puntos)
     * Obtiene un producto específico por ID del API protegido
     */
    public ProductDTO getProductById(Integer id) {
        try (CloseableHttpClient httpClient = createAuthenticatedClient()) {
            HttpGet request = new HttpGet(apiBaseUrl + "/" + id);
            
            try (CloseableHttpResponse response = httpClient.execute(request)) {
                int statusCode = response.getCode();
                
                if (statusCode == 200) {
                    String json = EntityUtils.toString(response.getEntity());
                    return objectMapper.readValue(json, ProductDTO.class);
                } else if (statusCode == 404) {
                    return null; // Producto no encontrado
                } else if (statusCode == 401) {
                    throw new RuntimeException("Error de autenticación: credenciales inválidas");
                } else {
                    throw new RuntimeException("Error al obtener producto: HTTP " + statusCode);
                }
            }
        } catch (Exception e) {
            System.err.println("Error al conectar con el API: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Error al buscar producto: " + e.getMessage());
        }
    }
}
