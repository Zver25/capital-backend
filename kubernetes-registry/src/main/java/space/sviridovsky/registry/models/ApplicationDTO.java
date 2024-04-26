package space.sviridovsky.registry.models;

import lombok.Data;
import org.springframework.cloud.client.ServiceInstance;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class ApplicationDTO {
  private String name;
  private List<ServiceInstanceDTO> instance;

  public static ApplicationDTO from(String name, List<ServiceInstance> instances) {
    final ApplicationDTO application = new ApplicationDTO();

    application.setName(name);
    application.setInstance(
      instances.stream()
        .map(ServiceInstanceDTO::from)
        .collect(Collectors.toList()));

    return application;
  }
}
