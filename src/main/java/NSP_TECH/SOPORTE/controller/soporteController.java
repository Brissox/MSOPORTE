package NSP_TECH.SOPORTE.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import NSP_TECH.SOPORTE.Assembler.soporteModelAssembler;
import NSP_TECH.SOPORTE.model.soporte;
import NSP_TECH.SOPORTE.services.soporteServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/v1/Soporte")
public class soporteController {

    @Autowired
    private soporteServices soporteservices;
    
    @Autowired
    private soporteModelAssembler assembler;

      // ENDPOINT PARA BUSCAR TODOS LOS REGISTROS DE SOPORTE
    @GetMapping

    @Operation(summary = "SOPORTES", description = "Operacion que listar todo los registros de soporte")
    @ApiResponses (value = {
        @ApiResponse(responseCode = "200", description = "Se listaron correctamente los registros de soporte", content = @Content(mediaType = "application/json", schema = @Schema(implementation = soporte.class))),
        @ApiResponse(responseCode = "404", description = "No se encontro ningun registro de soporte", content = @Content(mediaType = "application/json", schema = @Schema(type = "string", example = "No se encuentran Datos")))


    })

    public ResponseEntity<?> ListarTodo(){
        List<soporte> soporte = soporteservices.BuscarTodoSoporte();
        if (soporte.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encuentran dato");
        } else {
            return ResponseEntity.ok(assembler.toCollectionModel(soporte));
        }
    }

    // ENDPOINT PARA BUSCAR UN REGISTRO DE SOPORTE
    @GetMapping("/{ID_SOPORTE}")

    @Operation(summary = "SOPORTE", description = "Operacion que lista un registro de soporte")
    @Parameters (value = {
        @Parameter (name="ID_SOPORTE", description= "ID del registro de soporte que se buscara", in = ParameterIn.PATH, required= true)

    })

    @ApiResponses (value = {
        @ApiResponse(responseCode = "200", description = "Se lista correctamente el registro de soporte ", content = @Content(mediaType = "application/json", schema = @Schema(implementation = soporte.class))),
        @ApiResponse(responseCode = "404", description = "No se encontro ningun registro de soporte", content = @Content(mediaType = "application/json", schema = @Schema(type = "string", example = "No se encuentran Datos")))
    })

    public ResponseEntity<?> BuscarSoporte(@PathVariable Long ID_SOPORTE){

        try {
            soporte soporteBuscada = soporteservices.BuscarUnSoporte(ID_SOPORTE);
            return ResponseEntity.ok(assembler.toModel(soporteBuscada));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encuentra el registro");
        }
    }

// ENDPOINT PARA REGISTRAR UNA SOLICITUD DE SOPORTE
    @PostMapping
    @Operation(summary = "ENDPOINT QUE REGISTRA UNA SOLICITUD DE SOPORTE", description = "ENDPOINT QUE REGISTRA UNA SOLICITUD DE SOPORTE",requestBody= @io.swagger.v3.oas.annotations.parameters.RequestBody(description="REPORTE QUE SE VA A REGISTRAR", required = true, content = @Content(schema = @Schema(implementation = soporte.class))))
    @ApiResponses (value = {
        @ApiResponse(responseCode = "200", description = "Se registro correctamente la solicitud de soporte", content = @Content(mediaType = "application/json", schema = @Schema(implementation = soporte.class))),
        @ApiResponse(responseCode = "500", description = "Indica que no se logro registrar la solicitud", content = @Content(mediaType = "application/json", schema = @Schema(type = "string", example = "No se puede registrar la solicitud ")))
    })

    public ResponseEntity<?> GuardarSoporte(@RequestBody soporte soporteGuardar){
    try {
            soporte soporteRegistrar = soporteservices.GuardarSoporte(soporteGuardar);
            return ResponseEntity.ok(assembler.toModel(soporteGuardar));
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("No se puede registrar la solicitud");
    }
    }


// ENDPOINT PARA EDITAR UNA SOLICITUD DE SOPORTE
    @PutMapping("/{ID_SOPORTE}")

    @Operation(summary = "ENDPOINT QUE EDITA UNA SOLICITUD DE SOPORTE", description = "ENDPOINT QUE EDITA UNA SOLICITUD DE SOPORTE", requestBody=@io.swagger.v3.oas.annotations.parameters.RequestBody(description="SOLICITUD DE SOPORTE QUE SE VA A REGISTRAR", required = true, content = @Content(schema = @Schema(implementation = soporte.class))))
    @Parameters (value = {
        @Parameter (name="ID_SOPORTE", description= "ID de la solicitud que se editara", in = ParameterIn.PATH, required= true)})

    @ApiResponses (value = {
        @ApiResponse(responseCode = "200", description = "Se edito correctamente la solicitud de soporte", content = @Content(mediaType = "application/json", schema = @Schema(implementation = soporte.class))),
        @ApiResponse(responseCode = "500", description = "Solicitud no registrada", content = @Content(mediaType = "application/json", schema = @Schema(type = "string", example = "No se puede registrar la solicitud")))   
    })
    public ResponseEntity<?> ActualizarSoporte(@PathVariable Long ID_SOPORTE, @RequestBody soporte soporteActualizar){
        try {
            soporte soporteActualizado = soporteservices.BuscarUnSoporte(ID_SOPORTE);
            soporteActualizado.setId_soporte(soporteActualizar.getId_soporte());
            soporteActualizado.setId_usuario(soporteActualizar.getId_usuario());
            soporteActualizado.setAsunto(soporteActualizar.getAsunto());
            soporteActualizado.setDescripcion(soporteActualizar.getDescripcion());
            soporteActualizado.setEstado(soporteActualizar.getEstado());
            soporteActualizado.setFecha_creacion(soporteActualizar.getFecha_creacion());
            soporteActualizado.setFecha_resolucion(soporteActualizar.getFecha_resolucion());
            soporteservices.GuardarSoporte(soporteActualizar);
            return ResponseEntity.ok(assembler.toModel(soporteActualizar));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pedido no esta registrado");
        }
    }
    


}
