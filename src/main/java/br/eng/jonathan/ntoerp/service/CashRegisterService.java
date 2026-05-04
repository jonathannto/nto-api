package br.eng.jonathan.ntoerp.service;

import br.eng.jonathan.ntoerp.dto.CashRegisterDTO;
import br.eng.jonathan.ntoerp.dto.mapper.CashRegisterMapper;
import br.eng.jonathan.ntoerp.exception_handler.exceptions.NotFoundException;
import br.eng.jonathan.ntoerp.model.CashRegister;
import br.eng.jonathan.ntoerp.repository.CashRegisterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CashRegisterService {

    private static final String CASH_REGISTER_SEARCH_ERRO = "CASH_REGISTER.SEARCH_ERROR";

    private final MessageSource messageSource;
    private final CashRegisterMapper cashRegisterMapper;
    private final CashRegisterRepository repository;

    public Page<CashRegister> listAllCashRegister(Pageable pagination) {
        return repository.findAll(pagination);
    }

    public CashRegister findCashRegisterById(Long userId) throws NotFoundException {
        return repository.findById(userId).orElseThrow(() -> new NotFoundException(getMessageErro()));
    }

    public CashRegister createCashRegister(CashRegister cashRegister) {
        return repository.save(cashRegister);
    }

    public CashRegister updateCashRegister(Long cashRegisterId, CashRegisterDTO cashRegisterDTO) {
        CashRegister cashRegister = findCashRegisterById(cashRegisterId);

        cashRegisterMapper.updateEntityFromDto(cashRegisterDTO, cashRegister);

        return repository.save(cashRegister);
    }

    public void deleteCashRegister(Long cashRegisterId) {
        if (!repository.existsById(cashRegisterId)) {
            throw new NotFoundException("CashRegister not found");
        }
        repository.deleteById(cashRegisterId);
    }

    private String getMessageErro() {
        return messageSource.getMessage(CashRegisterService.CASH_REGISTER_SEARCH_ERRO, null, LocaleContextHolder.getLocale());
    }

}
