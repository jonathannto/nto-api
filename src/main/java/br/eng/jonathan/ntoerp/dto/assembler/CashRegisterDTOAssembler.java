package br.eng.jonathan.ntoerp.dto.assembler;

import br.eng.jonathan.ntoerp.controller.CashRegisterController;
import br.eng.jonathan.ntoerp.dto.CashRegisterDTO;
import br.eng.jonathan.ntoerp.dto.mapper.CashRegisterMapper;
import br.eng.jonathan.ntoerp.model.CashRegister;
import br.eng.jonathan.ntoerp.service.CashRegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * Assembler responsável por converter a entidade CashRegister em recursos DTO com links HATEOAS.
 *
 * @author Jonathan Nascimento
 * @since 1.0
 */
@Component
@RequiredArgsConstructor
public class CashRegisterDTOAssembler implements RepresentationModelAssembler<CashRegister, EntityModel<CashRegisterDTO>> {

    private final CashRegisterMapper cashRegisterMapper;
    private final CashRegisterService cashRegisterService;

    public CashRegister mapToEntity(CashRegisterDTO cashRegisterDTO) {
        if (cashRegisterDTO.getCashRegisterId() == null) {
            return cashRegisterMapper.toEntity(cashRegisterDTO);
        }

        CashRegister existingRegister = cashRegisterService.findCashRegisterById(cashRegisterDTO.getCashRegisterId());
        cashRegisterMapper.updateEntityFromDto(cashRegisterDTO, existingRegister);
        return existingRegister;
    }

    @Override
    public EntityModel<CashRegisterDTO> toModel(CashRegister cashRegister) {
        CashRegisterDTO dto = cashRegisterMapper.toDTO(cashRegister);

        return EntityModel.of(dto,
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