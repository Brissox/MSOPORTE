package NSP_TECH.SOPORTE.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import NSP_TECH.SOPORTE.model.soporte;
import NSP_TECH.SOPORTE.repository.soporteRepository;
import NSP_TECH.SOPORTE.services.soporteServices;

public class soporteServicesTest {

    @Mock
    private soporteRepository soporterepository;
    
    @InjectMocks
    private soporteServices soporteservices;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }



    
    @Test
    public void testBuscarTodo(){
    java.util.List<soporte> lista = new  ArrayList<>();

    soporte sop1 = new soporte();
    soporte sop2 = new soporte();

    
    sop1.setId_soporte(11L);
    sop1.setId_usuario(11L);
    sop1.setAsunto("no puedo ingresar un produccto");
    sop1.setDescripcion("no me deja agregar en el pedido");
    sop1.setEstado("pendiente");
    sop1.setFecha_creacion(LocalDateTime.now() );
    sop1.setFecha_resolucion(null);

    
    sop1.setId_soporte(12L);
    sop2.setId_usuario(12L);
    sop2.setAsunto("no puedo ingresar a mi usuario");
    sop2.setDescripcion("contrasena y/o usuario incorrecto");
    sop2.setEstado("pendiente");
    sop2.setFecha_creacion(LocalDateTime.now().minusDays(1) );
    sop2.setFecha_resolucion(LocalDateTime.now());

    lista.add(sop1);
    lista.add(sop2);

    when(soporterepository.findAll()).thenReturn(lista);

    java.util.List<soporte> resultadoBusqueda = soporteservices.BuscarTodoSoporte();

    assertEquals(2,resultadoBusqueda.size());
    verify(soporterepository, times(1)).findAll();

}

    @Test
    public void testBuscarUnSoporte(){
    soporte sop1 = new soporte();
    
    sop1.setId_soporte(11L);
    sop1.setId_usuario(11L);
    sop1.setAsunto("no puedo ingresar un produccto");
    sop1.setDescripcion("no me deja agregar en el pedido");
    sop1.setEstado("pendiente");
    sop1.setFecha_creacion(LocalDateTime.now() );
    sop1.setFecha_resolucion(null);

    when(soporterepository.findById(11L)).thenReturn(Optional.of(sop1));

    soporte sopBuscado = soporteservices.BuscarUnSoporte(11L);
    assertEquals(11L,sopBuscado.getId_soporte());
    verify(soporterepository, times(1)).findById(11L);

    }



    @Test
    public void testGuardarSoporte(){
        soporte s = new soporte();

        s.setId_soporte(11L);
        s.setId_usuario(11L);
        s.setAsunto("no puedo ingresar un produccto");
        s.setDescripcion("no me deja agregar en el pedido");
        s.setEstado("pendiente");
        s.setFecha_creacion(LocalDateTime.now() );
        s.setFecha_resolucion(null);
        
        when(soporterepository.save(any())).thenReturn(s);

        soporte soporGuardados = soporteservices.GuardarSoporte(s);
        assertEquals("pendiente", soporGuardados.getEstado());
        verify(soporterepository, times(1)).save(s);

    }


    @Test
    public void testEditarSoporte(){

        soporte soporteO = new soporte();
        soporteO.setId_soporte(11L);
        soporteO.setAsunto("problema");
        soporteO.setDescripcion("Inicio");

        soporte soporteE = new soporte();
        soporteE.setId_soporte(11L);
        soporteE.setAsunto("Error");
        soporteE.setDescripcion("No puedo entrar");


        when(soporterepository.save(any(soporte.class))).thenReturn(soporteE);
        when(soporterepository.existsById(11L)).thenReturn(true);
        soporte resultado = soporteservices.GuardarSoporte(soporteE);

        assertNotNull(resultado);
        assertEquals(11L, resultado.getId_soporte());
        assertEquals("Error", resultado.getAsunto());
        assertEquals("No puedo entrar", resultado.getDescripcion());

        verify(soporterepository, times(1)).save(soporteE);
    }

    @Test
    public void testEliminarRuta(){
        Long id = 11L;
        doNothing().when(soporterepository).deleteById(id);

        soporteservices.EliminarSoporte(11L);

        verify(soporterepository, times(1)).deleteById(id);

    }

}

