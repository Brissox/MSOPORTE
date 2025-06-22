package NSP_TECH.SOPORTE.model;

import java.util.Date;

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
    @Schema(description="aa")
    private Long id_soporte;

    @Column(name="ID_USUARIO",nullable=false ,precision=10)
    @Schema(description="aa")
    private Long id_usuario;

    @Column(name="ASUNTO",nullable = false,length = 100)
    @Schema(description="aa")
    private String asunto;

    @Column(name="DESCRIPCION",nullable =true ,length = 500)
    @Schema(description="aa")
    private String descripcion;

    @Column(name="ESTADO",nullable = true ,length = 20)
    @Schema(description="aa")
    private String estado;

    @Column(name="FECHA_CREACION",nullable= false)
    @Schema(description="aa")
    private Date fecha_creacion;

    @Column(name="FECHA_RESOLUCION",nullable= false)
    @Schema(description="aa")
    private Date fecha_resolucion;

}

