package space.sviridovsky.registry.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ApplicationsDTO {
  private List<ApplicationDTO> applications;
}
