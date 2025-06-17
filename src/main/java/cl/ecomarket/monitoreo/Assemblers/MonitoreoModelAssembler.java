package cl.ecomarket.monitoreo.Assemblers;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import cl.ecomarket.monitoreo.Controller.MonitoreoController;
import cl.ecomarket.monitoreo.Model.Monitoreo;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class MonitoreoModelAssembler implements RepresentationModelAssembler<Monitoreo, EntityModel<Monitoreo>> {

    @Override
    @NonNull
    public EntityModel<Monitoreo> toModel(@NonNull Monitoreo monitoreo) {
        return EntityModel.of(
            monitoreo,
            linkTo(methodOn(MonitoreoController.class).buscarPorId(monitoreo.getId())).withSelfRel(),
            linkTo(methodOn(MonitoreoController.class).listar()).withRel("monitoreos")
        );
    }
}

