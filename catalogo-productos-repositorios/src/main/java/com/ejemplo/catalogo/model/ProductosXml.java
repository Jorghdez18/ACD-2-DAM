package com.ejemplo.catalogo.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.ArrayList;
import java.util.List;

@JacksonXmlRootElement(localName = "productos")//busca etiqueta la raiz del xml en este caso productos
public class ProductosXml {
    @JacksonXmlElementWrapper(useWrapping = false)//
    @JacksonXmlProperty(localName = "producto")//busca todas las propiedades de la raiz con el nombre "producto"y hace una lista con ellos
    public List<Producto> productos;

    public ProductosXml(){
        productos = new ArrayList<>();
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }
}
