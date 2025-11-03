package com.example.Pago.DTO;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.example.Pago.model.Carrito;
import com.example.Pago.model.CarritoItem;
import com.example.Pago.model.Cliente;
import com.example.Pago.model.Paquete;
import com.example.Pago.model.Transaccion;

public class EntityMapper {
    public static ClienteDTO toClienteDTO(Cliente c) {
        if (c == null) return null;
        return new ClienteDTO(
            c.getId(),
            c.getCorreo(),
            c.getNombre(),
            c.getTipo(),
            c.getTarjeta(),
            c.getFechaVencimiento(),
            c.getSaldo()
        );
    }

    public static Cliente toClienteEntity(ClienteDTO dto) {
        if (dto == null) return null;
        Cliente c = new Cliente();
        c.setId(dto.getId());
        c.setCorreo(dto.getCorreo());
        c.setNombre(dto.getNombre());
        c.setTipo(dto.getTipo());
        c.setTarjeta(dto.getTarjeta());
        c.setFechaVencimiento(dto.getFechaVencimiento());
        c.setSaldo(dto.getSaldo());
        return c;
    }

    public static PaqueteDTO toPaqueteDTO(Paquete p) {
        if (p == null) return null;
        return new PaqueteDTO(
            p.getId(),
            p.getNombre(),
            p.getPrecio(),
            p.getDestino()
        );
    }

    public static Paquete toPaqueteEntity(PaqueteDTO dto) {
        if (dto == null) return null;
        Paquete p = new Paquete();
        p.setId(dto.getId());
        p.setNombre(dto.getNombre());
        p.setPrecio(dto.getPrecio());
        p.setDestino(dto.getDestino());
        return p;
    }

    public static CarritoDTO toCarritoDTO(Carrito c) {
        if (c == null) return null;
        Long clienteId = (c.getCliente() != null) ? c.getCliente().getId() : null;
        return new CarritoDTO(c.getId(), clienteId);
    }

    public static Carrito toCarritoEntity(CarritoDTO dto, Cliente cliente) {
        if (dto == null) return null;
        Carrito c = new Carrito();
        c.setId(dto.getId());
        c.setCliente(cliente);
        return c;
    }

    public static CarritoItemDTO toCarritoItemDTO(CarritoItem item) {
        if (item == null) return null;
        Long productoId = (item.getProducto() != null) ? item.getProducto().getId() : null;
        Long carritoId = (item.getCarrito() != null) ? item.getCarrito().getId() : null;

        return new CarritoItemDTO(
            item.getId(),
            item.getCantidad(),
            productoId,
            carritoId
        );
    }

    public static CarritoItem toCarritoItemEntity(CarritoItemDTO dto, Carrito carrito, Paquete paquete) {
        if (dto == null) return null;
        CarritoItem item = new CarritoItem();
        item.setId(dto.getId());
        item.setCantidad(dto.getCantidad());
        item.setCarrito(carrito);
        item.setProducto(paquete);
        return item;
    }

    public static TransaccionDTO toTransaccionDTO(Transaccion t) {
        if (t == null) return null;
        Long clienteId = (t.getCliente() != null) ? t.getCliente().getId() : null;

        return new TransaccionDTO(
            t.getId(),
            t.getMonto(),
            t.getEstado(),
            t.getFecha(),
            clienteId
        );
    }

    public static Transaccion toTransaccionEntity(TransaccionDTO dto, Cliente cliente) {
        if (dto == null) return null;
        Transaccion t = new Transaccion();
        t.setId(dto.getId());
        t.setMonto(dto.getMonto());
        t.setEstado(dto.getEstado());
        t.setFecha(dto.getFecha() != null ? dto.getFecha() : LocalDate.now());
        t.setCliente(cliente);
        return t;
    }
}
