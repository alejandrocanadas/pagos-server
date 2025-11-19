package com.example.Pago.DTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.example.Pago.model.Carrito;
import com.example.Pago.model.CarritoItem;
import com.example.Pago.model.Cliente;
import com.example.Pago.model.Paquete;
import com.example.Pago.model.Tarjeta;


public class EntityMapper {
    public static ClienteDTO toClienteDTO(Cliente c) {
        if (c == null)
            return null;
        return new ClienteDTO(
                c.getCorreo(),
                c.getNombre(),
                c.getCedula());
    }

    public static Cliente toClienteEntity(ClienteDTO dto) {
        if (dto == null)
            return null;
        Cliente c = new Cliente();
        c.setCorreo(dto.getCorreo());
        c.setNombre(dto.getNombre());
        c.setCedula(dto.getCedula());
        return c;
    }

    public static TarjetaDTO toTarjetaDTO(Tarjeta t) {
        if (t == null)
            return null;

        String cedulaCliente = (t.getCliente() != null) ? t.getCliente().getCedula() : null;

        return new TarjetaDTO(
                t.getId(),
                t.getNumeroTarjeta(),
                t.getTipo(),
                t.getFechaVencimiento().toString(),
                cedulaCliente);
    }

    public static Tarjeta toTarjetaEntity(TarjetaDTO dto, Cliente cliente) {
        if (dto == null)
            return null;

        Tarjeta t = new Tarjeta();
        t.setId(dto.getId());
        t.setNumeroTarjeta(dto.getnumeroTarjeta());
        t.setTipo(dto.getTipo());
        t.setFechaVencimiento(LocalDate.parse(dto.getFechaVencimiento()));
        t.setSaldo(0.0);
        t.setCliente(cliente);
        return t;
    }

    public static PaqueteDTO toPaqueteDTO(Paquete p) {
        if (p == null)
            return null;
        return new PaqueteDTO(
                p.getId(),
                p.getNombre(),
                p.getPrecio(),
                p.getDestino());
    }

    public static Paquete toPaqueteEntity(PaqueteDTO dto) {
        if (dto == null)
            return null;
        Paquete p = new Paquete();
        p.setId(dto.getId());
        p.setNombre(dto.getNombre());
        p.setPrecio(dto.getPrecio());
        p.setDestino(dto.getDestino());
        return p;
    }

    public static CarritoDTO toCarritoDTO(Carrito c) {
        if (c == null)
            return null;
        String clienteCedula = (c.getCliente() != null) ? c.getCliente().getCedula() : null;
        return new CarritoDTO(c.getId(), clienteCedula);
    }

    public static Carrito toCarritoEntity(CarritoDTO dto, Cliente cliente) {
        if (dto == null)
            return null;
        Carrito c = new Carrito();
        c.setId(dto.getId());
        c.setCliente(cliente);
        return c;
    }

    public static CarritoItemDTO toCarritoItemDTO(CarritoItem item) {
        if (item == null)
            return null;

        Long productoId = (item.getProducto() != null)
                ? item.getProducto().getId()
                : null;

        String nombrePaquete = (item.getProducto() != null)
                ? item.getProducto().getNombre()
                : null;

        String cedulaCliente = (item.getCarrito() != null && item.getCarrito().getCliente() != null)
                ? item.getCarrito().getCliente().getCedula()
                : null;

        return new CarritoItemDTO(
                item.getId(),
                item.getCantidad(),
                nombrePaquete,
                cedulaCliente);
    }

    public static CarritoItem toCarritoItemEntity(CarritoItemDTO dto, Carrito carrito, Paquete paquete) {
        if (dto == null)
            return null;
        CarritoItem item = new CarritoItem();
        item.setId(dto.getId());
        item.setCantidad(dto.getCantidad());
        item.setCarrito(carrito);
        item.setProducto(paquete);
        return item;
    }

}
