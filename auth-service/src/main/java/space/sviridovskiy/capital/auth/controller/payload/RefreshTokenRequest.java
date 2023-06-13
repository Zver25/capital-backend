package space.sviridovskiy.capital.auth.controller.payload;

import lombok.Data;

@Data
public class RefreshTokenRequest {
  String refreshToken;
}
