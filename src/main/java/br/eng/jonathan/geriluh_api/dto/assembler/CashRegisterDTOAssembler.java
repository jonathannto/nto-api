package br.eng.jonathan.geriluh_api.dto.assembler;

import br.eng.jonathan.geriluh_api.controller.CashRegisterController;
import br.eng.jonathan.geriluh_api.dto.CashRegisterDTO;
import br.eng.jonathan.geriluh_api.dto.UserDTO;
import br.eng.jonathan.geriluh_api.dto.mapper.CashRegisterMapper;
import br.eng.jonathan.geriluh_api.exception_handler.exceptions.NotFoundException;
import br.eng.jonathan.geriluh_api.model.CashRegister;
import br.eng.jonathan.geriluh_api.model.User;
import br.eng.jonathan.geriluh_api.service.CashRegisterService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
@RequiredArgsConstructor
public class CashRegisterDTOAssembler implements RepresentationModelAssembler<CashRegister, EntityModel<CashRegisterDTO>> {

    private final CashRegisterMapper cashRegisterMapper;
    private final CashRegisterService cashRegisterService;

    public CashRegister mapToEntity(CashRegisterDTO cashRegisterDTO) throws NotFoundException {
        //Refatorando...
    }

    /**
     * Uses {@link ModelMapper} to convert an entity into a DTO
     * while also transforming the class into an {@link EntityModel} and adding links for HATEOAS
     * @author Jonathan
     * @since 1.0
     * @serialData 2024-12-03
     * @param cashRegister {@link br.eng.jonathan.geriluh_api.model.CashRegister}
     * @return {@link EntityModel} <{@link br.eng.jonathan.geriluh_api.dto.CashRegisterDTO}>
     */
    public EntityModel<CashRegisterDTO> mapToEntityModelDTO(CashRegister cashRegister) {
        return EntityModel.of(mapToDTO(cashRegister),
                linkTo(methodOn(CashRegisterController.class)
                        .getCashRegisterById(cashRegister.getCashRegisterId()))
                        .withSelfRel()
                        .withType("GET"),
                linkTo(methodOn(CashRegisterController.class)
                        .list(null))
                        .withRel("list")
                        .withType("GET"),
                linkTo(methodOn(CashRegisterController.class)
                        .createNewCashRegister(null, null))
                        .withRel("create")
                        .withType("POST"),
                linkTo(methodOn(CashRegisterController.class)
                        .updateCashRegister(cashRegister.getCashRegisterId(), null))
                        .withRel("update")
                        .withType("PUT"),
                linkTo(methodOn(CashRegisterController.class)
                        .deleteCashRegister(cashRegister.getCashRegisterId()))
                        .withRel("delete")
                        .withType("DELETE")
        );
    }


}
