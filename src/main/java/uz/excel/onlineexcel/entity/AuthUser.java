package uz.excel.onlineexcel.entity;

import lombok.Getter;
import lombok.Setter;
import uz.excel.onlineexcel.entity.base.Auditable;
import uz.excel.onlineexcel.enums.AuthRole;
import uz.excel.onlineexcel.enums.Status;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(schema = "auth", name = "auth_user")
public class AuthUser extends Auditable {

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

    @Column(nullable = false)
    private Long organizationId;
}
