package com.dogeway.dw.usuario;

import com.dogeway.dw.mascota.Mascota;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Date;
import java.util.Collection;
import java.util.List;

@Table(name = "usuarios", uniqueConstraints = @UniqueConstraint(columnNames = "correo"))
@Entity(name = "Usuario")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombres;
    private String apellidos;
    private String correo;
    private Intereses intereses;
    private Genero genero;
    private Date fecha_nacimiento;
    private String pais;
    private String estado;
    private String ciudad;
    private String telefono;
    private String foto;
    @JsonIgnore
    private String password;

    @OneToMany(mappedBy = "propietario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Mascota> mascotas;

    public Usuario(RegisterDTO registroUsuario, String passwordEncoded) {
        this.nombres = registroUsuario.nombres();
        this.apellidos = registroUsuario.apellidos();
        this.correo = registroUsuario.correo();
        this.intereses = registroUsuario.intereses();
        this.genero = registroUsuario.genero();
        this.fecha_nacimiento = registroUsuario.fechaNacimiento();
        this.pais = registroUsuario.pais();
        this.estado = registroUsuario.estado();
        this.ciudad = registroUsuario.estado();
        this.telefono = registroUsuario.telefono();
        this.foto = registroUsuario.foto();
        this.password = passwordEncoded;
    }

    public Usuario(String nombre, String apellidos, String correo, Intereses intereses, Genero genero, Date fechaNacimiento, String pais, String estado, String ciudad, String telefono, String passwordEncoded) {
        this.nombres=nombre;
        this.apellidos=apellidos;
        this.correo=correo;
        this.intereses=intereses;
        this.genero=genero;
        this.fecha_nacimiento=fechaNacimiento;
        this.pais=pais;
        this.estado=estado;
        this.ciudad=ciudad;
        this.telefono=telefono;
        this.password=passwordEncoded;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return correo;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void actualizarDatos(UserUpdateDto registroUsuario, String passwordEncoded) {
        this.nombres = registroUsuario.nombres();
        this.apellidos = registroUsuario.apellidos();
        this.correo = registroUsuario.correo();
        this.intereses = registroUsuario.intereses();
        this.genero = registroUsuario.genero();
        this.fecha_nacimiento = registroUsuario.fechaNacimiento();
        this.pais = registroUsuario.pais();
        this.estado = registroUsuario.estado();
        this.ciudad = registroUsuario.estado();
        this.telefono = registroUsuario.telefono();
        this.password = passwordEncoded;
    }
}
