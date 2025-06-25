package NSP_TECH.SOPORTE.model;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="soporte")
@AllArgsConstructor
@NoArgsConstructor
@Schema(description="Todas las solicitudes de soporte")


public class soporte {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID_SOPORTE")
    @Schema(description="identificador del soporte", example="1")
    private Long id_soporte;

    @Column(name="ID_USUARIO",nullable=false ,precision=10)
    @Schema(description="usuario que solicita el soporte", example="2")
    private Long id_usuario;

    @Column(name="ASUNTO",nullable = false,length = 100)
    @Schema(description="asunto del requerimiento", example="problemas con el carrito de compras")
    private String asunto;

    @Column(name="DESCRIPCION",nullable =true ,length = 500)
    @Schema(description="detalle del problema mencionado en asunto",example="no deja agregar productos")
    private String descripcion;

    @Column(name="ESTADO",nullable = true ,length = 20)
    @Schema(description="estado de la solicitud de soporte", example="pendiente / en proceso/ resuelto")
    private String estado;

    @Column(name="FECHA_CREACION",nullable= false)
    @Schema(description="fecha creacion de la solicitud", example="2025-06-24T15:30:00")
    private LocalDateTime fecha_creacion;

    @Column(name="FECHA_RESOLUCION",nullable= true)
    @Schema(description="fecha resolucion de la solicitud", example="2025-06-24T15:30:00")
    private LocalDateTime fecha_resolucion;

}

