package edu.e_commerce.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode(of = "id")
@Table(name = "tb_refreshToken")
public class RefreshToken {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "refreshTokenId")
    private Long id;
    @Column(name = "refreshToken",nullable = false)
    private String refreshToken;
    @OneToOne
    @JoinColumn(name = "userId")
    private User user;
}