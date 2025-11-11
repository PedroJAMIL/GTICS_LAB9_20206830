package com.example.lab09;

import java.sql.*;

public class TestConnection {
    public static void main(String[] args) {
        String url = "jdbc:mysql://lewisrp.dev:3306/Northwind";
        String user = "root";
        String password = "4s@wo19.+[xTrUnk@}9";
        
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            System.out.println("✓ Conexión exitosa a la base de datos!");
            
            // Obtener metadatos de las tablas
            DatabaseMetaData metaData = conn.getMetaData();
            
            System.out.println("\n=== Tablas que contienen 'product' (cualquier mayúscula/minúscula) ===");
            ResultSet tables = metaData.getTables("Northwind", null, "%", new String[]{"TABLE"});
            
            while (tables.next()) {
                String tableName = tables.getString("TABLE_NAME");
                if (tableName.toLowerCase().contains("product") || 
                    tableName.toLowerCase().contains("categor") || 
                    tableName.toLowerCase().contains("supplier")) {
                    System.out.println("  → " + tableName);
                }
            }
            
            System.out.println("\n=== Intentando consultar 'Products' (con P mayúscula) ===");
            try (Statement stmt = conn.createStatement()) {
                ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM Products");
                if (rs.next()) {
                    System.out.println("✓ Tabla 'Products' existe y tiene " + rs.getInt(1) + " registros");
                }
            } catch (SQLException e) {
                System.out.println("✗ Error al consultar 'Products': " + e.getMessage());
            }
            
            System.out.println("\n=== Intentando consultar 'products' (con p minúscula) ===");
            try (Statement stmt = conn.createStatement()) {
                ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM products");
                if (rs.next()) {
                    System.out.println("✓ Tabla 'products' existe y tiene " + rs.getInt(1) + " registros");
                }
            } catch (SQLException e) {
                System.out.println("✗ Error al consultar 'products': " + e.getMessage());
            }
            
            System.out.println("\n=== Columnas de la tabla Products ===");
            try {
                ResultSet columns = metaData.getColumns("Northwind", null, "Products", null);
                while (columns.next()) {
                    String columnName = columns.getString("COLUMN_NAME");
                    String columnType = columns.getString("TYPE_NAME");
                    System.out.println("  → " + columnName + " (" + columnType + ")");
                }
            } catch (SQLException e) {
                System.out.println("✗ Error al obtener columnas: " + e.getMessage());
            }
            
            System.out.println("\n=== Columnas de la tabla Suppliers ===");
            try {
                ResultSet columns = metaData.getColumns("Northwind", null, "Suppliers", null);
                while (columns.next()) {
                    String columnName = columns.getString("COLUMN_NAME");
                    String columnType = columns.getString("TYPE_NAME");
                    System.out.println("  → " + columnName + " (" + columnType + ")");
                }
            } catch (SQLException e) {
                System.out.println("✗ Error al obtener columnas: " + e.getMessage());
            }
            
            System.out.println("\n=== Columnas de la tabla Categories ===");
            try {
                ResultSet columns = metaData.getColumns("Northwind", null, "Categories", null);
                while (columns.next()) {
                    String columnName = columns.getString("COLUMN_NAME");
                    String columnType = columns.getString("TYPE_NAME");
                    System.out.println("  → " + columnName + " (" + columnType + ")");
                }
            } catch (SQLException e) {
                System.out.println("✗ Error al obtener columnas: " + e.getMessage());
            }
            
        } catch (SQLException e) {
            System.out.println("✗ Error de conexión: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
