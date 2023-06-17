package space.sviridovskiy.capital.income.payload;

import lombok.Data;

import javax.validation.constraints.Positive;

@Data
public class CreateCategoryRequest {
  @Positive(message = "Name should be present")
  private String name;
}
