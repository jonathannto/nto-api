package br.eng.jonathan.ntoerp.dto.assembler;

import br.eng.jonathan.ntoerp.controller.UserController;
import br.eng.jonathan.ntoerp.dto.UserDTO;
import br.eng.jonathan.ntoerp.dto.mapper.UserMapper;
import br.eng.jonathan.ntoerp.model.User;
import br.eng.jonathan.ntoerp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
@RequiredArgsConstructor
public class UserDTOAssembler implements RepresentationModelAssembler<User, EntityModel<UserDTO>> {

    private final UserMapper userMapper;
    private final UserService userService;
    
    public User mapToEntity(UserDTO userDTO) {
        if (userDTO.getUserId() == null) {
            return userMapper.toEntity(userDTO);
        }

        User existingUser = userService.findUserById(userDTO.getUserId());
        userMapper.updateEntityFromDto(userDTO, existingUser);
        return existingUser;
    }

    /**
     * Converte uma entidade User em UserDTO envolto em um EntityModel com links HATEOAS.
     * Implementa RepresentationModelAssembler para seguir o padrão oficial do Spring HATEOAS.
     *
     * @author Jonathan Nascimento
     * @since 1.0
     * @param user Entidade de domínio
     * @return EntityModel com recurso e links
     */
    @Override
    public EntityModel<UserDTO> toModel(User user) {

        UserDTO dto = userMapper.toDTO(user);

        return EntityModel.of(dto,
                linkTo(methodOn(UserController.class)
                        .getUserById(user.getUserId()))
                        .withSelfRel()
                        .withType("GET"),
                linkTo(methodOn(UserController.class)
                        .list(null))
                        .withRel("list")
                        .withType("GET"),
                linkTo(methodOn(UserController.class)
                        .createNewUser(null, null))
                        .withRel("create")
                        .withType("POST"),
                linkTo(methodOn(UserController.class)
                        .updateUser(user.getUserId(), null))
                        .withRel("update")
                        .withType("PUT"),
                linkTo(methodOn(UserController.class)
                        .deleteUser(user.getUserId()))
                        .withRel("delete")
                        .withType("DELETE")
        );
    }

}
