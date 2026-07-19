package br.eng.jonathan.ntoerp.service;

import br.eng.jonathan.ntoerp.dto.PaymentTypeDTO;
import br.eng.jonathan.ntoerp.dto.mapper.PaymentTypeMapper;
import br.eng.jonathan.ntoerp.exception_handler.exceptions.NotFoundException;
import br.eng.jonathan.ntoerp.model.PaymentType;
import br.eng.jonathan.ntoerp.repository.PaymentTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
@RequiredArgsConstructor
public class PaymentTypeService {

    private static final String PAYMENT_TYPE_SEARCH_ERROR = "PAYMENT_TYPE.SEARCH_ERROR";

    private final PaymentTypeMapper paymentTypeMapper;
    private final PaymentTypeRepository repository;
    private final MessageSource messageSource;

    public Page<PaymentType> listAllPaymentType(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public PaymentType findPaymentTypeById(Long paymentId) {
        return repository.findById(paymentId).orElseThrow(() -> new NotFoundException(getErrorMessage()));
    }

    public PaymentType createPaymentType(PaymentType paymentType) {
        return repository.save(paymentType);
    }

    public PaymentType updatePaymentType(Long paymentId, PaymentTypeDTO paymentTypeDTO) {

        var paymentType = findPaymentTypeById(paymentId);
        paymentTypeMapper.updateEntityFromDto(paymentTypeDTO, paymentType);
        return repository.save(paymentType);
    }

    public void deletePaymentType(Long paymentId) {
        if (!repository.existsById(paymentId)) {
            throw new NotFoundException(getErrorMessage());
        }
        repository.deleteById(paymentId);
    }

    private String getErrorMessage() {
        return messageSource.getMessage(PAYMENT_TYPE_SEARCH_ERROR, null, Locale.getDefault());
    }
}
