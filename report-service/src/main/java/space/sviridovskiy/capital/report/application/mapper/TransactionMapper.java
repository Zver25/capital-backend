package space.sviridovskiy.capital.report.application.mapper;

import org.springframework.stereotype.Component;
import space.sviridovskiy.capital.report.application.dto.TransactionDto;
import space.sviridovskiy.capital.report.domain.model.entity.Transaction;
import space.sviridovskiy.capital.report.domain.model.valueobject.CategoryId;
import space.sviridovskiy.capital.report.domain.model.valueobject.CurrencyCode;
import space.sviridovskiy.capital.report.domain.model.valueobject.MoneyAmount;

@Component
public class TransactionMapper {

  public Transaction toDomain(TransactionDto dto) {
    return Transaction.builder()
        .id(dto.getId())
        .username(dto.getUsername())
        .categoryId(CategoryId.of(dto.getCategoryId()))
        .amount(MoneyAmount.of(dto.getAmount(), CurrencyCode.of(dto.getCurrencyCode())))
        .date(dto.getDate())
        .build();
  }

  public TransactionDto toDto(Transaction domain) {
    return TransactionDto.builder()
        .id(domain.getId())
        .username(domain.getUsername())
        .categoryId(domain.getCategoryId().getValue())
        .amount(domain.getAmount().getValue())
        .currencyCode(domain.getAmount().getCurrencyCode().getValue())
        .date(domain.getDate())
        .build();
  }
}
