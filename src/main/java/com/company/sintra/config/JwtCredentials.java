package com.company.sintra.config;

import com.company.sintra.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;

import java.util.List;

@FieldNameConstants
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JwtCredentials {

    Long iat;
    String username;
    Long userId;
    List<Role> role;
}
