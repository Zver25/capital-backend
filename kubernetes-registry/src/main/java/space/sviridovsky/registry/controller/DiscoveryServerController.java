package space.sviridovsky.registry.controller;

import lombok.AllArgsConstructor;
import org.springframework.cloud.client.discovery.ReactiveDiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import space.sviridovsky.registry.models.ApplicationDTO;
import space.sviridovsky.registry.models.ApplicationsDTO;
import space.sviridovsky.registry.models.ServiceInstanceDTO;

import java.util.List;
import java.util.NoSuchElementException;

@RestController("/applications")
@AllArgsConstructor
public class DiscoveryServerController {
  private final ReactiveDiscoveryClient reactiveDiscoveryClient;

  @GetMapping
  public ResponseEntity<ApplicationsDTO> getApplications() {
    return reactiveDiscoveryClient.getServices()
      .flatMap(applicationName ->
        reactiveDiscoveryClient.getInstances(applicationName)
          .collectList()
          .map(serviceInstances -> ApplicationDTO.from(applicationName, serviceInstances)))
      .collectList()
      .map(applications -> ResponseEntity.ok(new ApplicationsDTO(applications)))
      .block();
  }

  @GetMapping("{applicationName}")
  public ResponseEntity<List<ServiceInstanceDTO>> getApplicationInstances(@PathVariable String applicationName) {
    return reactiveDiscoveryClient.getInstances(applicationName)
      .map(ServiceInstanceDTO::from)
      .collectList()
      .map(ResponseEntity::ok)
      .block();
  }

  @GetMapping("{applicationName}/{instanceId}")
  public ResponseEntity<ServiceInstanceDTO> getInstance(@PathVariable String applicationName, @PathVariable String instanceId) {
    return ResponseEntity.ok(
      reactiveDiscoveryClient.getInstances(applicationName)
        .filter(serviceInstance -> serviceInstance.getInstanceId().equals(instanceId)).singleOrEmpty()
        .map(ServiceInstanceDTO::from)
        .blockOptional()
        .orElseThrow(NoSuchElementException::new)
    );
  }
}
