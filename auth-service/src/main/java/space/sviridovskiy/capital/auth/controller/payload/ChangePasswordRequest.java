package space.sviridovskiy.capital.auth.controller.payload;

import lombok.Data;

@Data
public class ChangePasswordRequest {
  private String oldPassword;
  private String newPassword;
}
