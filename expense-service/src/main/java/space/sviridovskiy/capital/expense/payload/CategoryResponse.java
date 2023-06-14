package space.sviridovskiy.capital.expense.payload;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class CategoryResponse {
  private UUID id;

  private String name;
}
