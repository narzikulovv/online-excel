package uz.excel.onlineexcel.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import uz.excel.onlineexcel.entity.base.Auditable;
import uz.excel.onlineexcel.service.base.enums.AuthRole;
import uz.excel.onlineexcel.service.base.enums.Status;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(schema = "auth", name = "auth_user")
public class AuthUser extends Auditable implements GrantedAuthority {

    @Column
    private String fullName;

    @Column
    private String phone;

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private byte[] picture;

    @Column
    @Enumerated(value = EnumType.STRING)
    private AuthRole role;

    @Column
    @Enumerated(value = EnumType.STRING)
    private Status status;

//    @Column(nullable = false)
    @Column(name = "organization_id")
    private Long organizationId;

    @Override
    public String getAuthority() {
        return this.role.name();
    }
}
