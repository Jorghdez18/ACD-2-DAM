package com.ejemplo.catalogo.repository.file.csv;

import com.ejemplo.catalogo.model.Producto;
import com.ejemplo.catalogo.model.ProductosXml;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Path;
import java.util.List;

public class XmlProductoRepository extends AbstractProductoRepository {

    private final XmlMapper mapper ;

    public XmlProductoRepository(Path path) {
        super(path);
        productos = load(); //pq primero tiene que cargar todo
        mapper = new XmlMapper(); //clase que es capaz de transoformar lo q lee del dichero en una clase completa con dos condiciones:
        // los parametros del dichero tienen que ser igual que los de la clase record
    }

    @Override
    public void saveAll(List<Producto> items) {
        try {
            ProductosXml productosXml = new ProductosXml();//coge la parte del producto y lo busca dentro del archivo automaticamente
            productosXml.setProductos(productos); //clase etiquetada con todos los productos
            mapper.writerWithDefaultPrettyPrinter()
                    .writeValue(getPath().toFile(), productosXml);
        } catch (IOException e) {
            throw new UncheckedIOException("No se pudo guardar " + getPath(), e);
        } finally{
        }
    }


    @Override
    public List<Producto> load() {

        try {
            //va a la clase productosxml y reconoce las etiquetas
            //productosxml : en esta instancia va a poner todo lo que ha encontrado en productosxml  usando el mapper.readValue(getPath().toFile(), ProductosXml.class);
            //  productos.addAll(productosxml.getProductos()); mete la informacion nueva
            ProductosXml productosxml = mapper.readValue(getPath().toFile(), ProductosXml.class);
            productos.clear(); //limpia la lista antigua
            productos.addAll(productosxml.getProductos());
        } catch (IOException e) {
            throw new UncheckedIOException("No se pudo cargar " + getPath(), e);
        }
        return productos;
    }
}
