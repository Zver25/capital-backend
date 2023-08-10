package space.sviridovskiy.capital.income.payload;

import lombok.Data;

import javax.validation.constraints.Positive;
import java.util.UUID;

@Data
public class UpdateCategoryRequest {
  private UUID id;

  @Positive(message = "Name should be present")
  private String name;

  private boolean isDisabled;
}
