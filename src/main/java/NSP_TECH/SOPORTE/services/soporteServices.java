package NSP_TECH.SOPORTE.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import NSP_TECH.SOPORTE.model.soporte;
import NSP_TECH.SOPORTE.repository.soporteRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional

public class soporteServices {

    @Autowired
    private soporteRepository soporterepository;
    
    public List<soporte> BuscarTodoSoporte(){
        return soporterepository.findAll();
    }
        public soporte BuscarUnSoporte(Long id_soporte){
        return soporterepository.findById(id_soporte).get();
    }

    public soporte GuardarSoporte(soporte soporte){
        return soporterepository.save(soporte);

    }
    public void EliminarSoporte(Long id_soporte){
        soporterepository.deleteById(id_soporte);
    }

}
