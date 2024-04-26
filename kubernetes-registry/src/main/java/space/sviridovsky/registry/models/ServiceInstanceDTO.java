package space.sviridovsky.registry.models;

import lombok.Data;
import org.springframework.cloud.client.ServiceInstance;

@Data
public class ServiceInstanceDTO {
  private String instanceId;
  private String hostName;
  private String ipAddr;
  private int port;
  private String homePageUrl;
  private String statusPageUrl;
  private String healthCheckUrl;
  private String metadata;

  public static ServiceInstanceDTO from(ServiceInstance serviceInstance) {
    final ServiceInstanceDTO dto = new ServiceInstanceDTO();
    dto.setInstanceId(serviceInstance.getInstanceId());
    dto.setHostName(serviceInstance.getHost());
    dto.setIpAddr(serviceInstance.getHost());
    dto.setPort(serviceInstance.getPort());
    dto.setHomePageUrl("http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort());
    dto.setStatusPageUrl(dto.getHomePageUrl() + "/actuator/info");
    dto.setHealthCheckUrl(dto.getHomePageUrl() + "/actuator/health");

    return dto;
  }
}
