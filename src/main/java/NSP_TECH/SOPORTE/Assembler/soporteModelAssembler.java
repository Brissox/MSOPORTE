package NSP_TECH.SOPORTE.Assembler;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.stereotype.Component;

import NSP_TECH.SOPORTE.controller.soporteController;
import NSP_TECH.SOPORTE.model.soporte;

@Component
public class soporteModelAssembler implements RepresentationModelAssembler<soporte, EntityModel<soporte>>{

    @Override
    public EntityModel<soporte> toModel(soporte s) {
        return EntityModel.of(
            s,
            linkTo(methodOn(soporteController.class).BuscarSoporte(s.getId_soporte())).withRel("LINKS"),
            linkTo(methodOn(soporteController.class).ListarTodo()).withRel("todas-las-solicitudes-de-soporte")
        );
    }

}
