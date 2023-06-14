package space.sviridovskiy.capital.expense.payload;

import lombok.Data;

import javax.validation.constraints.Positive;
import java.util.UUID;

@Data
public class UpdateCategoryRequest {
  @Positive(message = "Id should be present")
  private UUID id;

  @Positive(message = "Name should be present")
  private String name;
}
