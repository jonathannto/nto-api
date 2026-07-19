package br.eng.jonathan.ntoerp.controller;

import br.eng.jonathan.ntoerp.controller.open_api.CashRegisterControllerOpenApi;
import br.eng.jonathan.ntoerp.dto.CashRegisterDTO;
import br.eng.jonathan.ntoerp.dto.assembler.CashRegisterDTOAssembler;
import br.eng.jonathan.ntoerp.exception_handler.exceptions.NotFoundException;
import br.eng.jonathan.ntoerp.service.CashRegisterService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/cashes-registers", produces = "application/json")
@RequiredArgsConstructor
public class CashRegisterController implements CashRegisterControllerOpenApi {

    private final CashRegisterService service;
    private final CashRegisterDTOAssembler assembler;

    @GetMapping
    public ResponseEntity<Page<EntityModel<CashRegisterDTO>>> list(Pageable pageable) {
        var cashesRegisters = service.listAllCashRegister(pageable)
                .map(assembler::toModel);

        return ResponseEntity.ok(cashesRegisters);
    }

    @GetMapping("/{cashRegisterId}")
    public ResponseEntity<EntityModel<CashRegisterDTO>> getCashRegisterById(Long cashRegisterId) {

        var cashRegister = service.findCashRegisterById(cashRegisterId);

        return ResponseEntity.ok()
                .body(assembler.toModel(cashRegister));
    }

    @PostMapping
    public ResponseEntity<EntityModel<CashRegisterDTO>> createNewCashRegister(@Valid @RequestBody CashRegisterDTO cashRegisterDTO, HttpServletResponse response) throws NotFoundException {
        var cashRegister = service.createCashRegister(assembler.mapToEntity(cashRegisterDTO));

        return new ResponseEntity<EntityModel<CashRegisterDTO>>(assembler.toModel(cashRegister), HttpStatus.CREATED);
    }

    @PutMapping("/{idCashRegister}")
    public ResponseEntity<EntityModel<CashRegisterDTO>> updateCashRegister(@PathVariable Long idCashRegister, @Valid @RequestBody CashRegisterDTO cashRegisterDTO) {
        return ResponseEntity.ok(assembler.toModel(
                service.updateCashRegister(idCashRegister, cashRegisterDTO)));
    }

    @DeleteMapping("/{cashRegisterId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteCashRegister(@PathVariable Long cashRegisterId) {
        service.deleteCashRegister(cashRegisterId);
        return ResponseEntity.noContent().build();
    }

}
