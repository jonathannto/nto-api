package br.eng.jonathan.ntoerp.dto.mapper;

import br.eng.jonathan.ntoerp.dto.UserDTO;
import br.eng.jonathan.ntoerp.model.User;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {

    @Mapping(target = "password", ignore = true)
    @Mapping(target = "securityAnswer", ignore = true)
    UserDTO toDTO(User user);

    User toEntity(UserDTO userDTO);

    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    void updateEntityFromDto(UserDTO dto, @MappingTarget User entity);
}
