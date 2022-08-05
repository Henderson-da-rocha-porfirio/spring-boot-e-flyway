package com.tuyo.tuyofood.domain.entity;

import com.tuyo.tuyofood.domain.entity.embeddables.Address;
import com.tuyo.tuyofood.domain.enuns.solicitation.SolicitationStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Solicitation {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal subtotal;

    @Column(name = "taxa_frete")
    private BigDecimal taxaFrete;

    @Column(name = "valor_total")
    private BigDecimal valorTotal;

    @Embedded
    private Address addressDelivery;

    private SolicitationStatus status;

    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "timestamp")
    private LocalDateTime dataCriacao;

    @Column(nullable = false, columnDefinition = "timestamp")
    private LocalDateTime dataConfirmacao;

    @Column(nullable = false, columnDefinition = "timestamp")
    private LocalDateTime dataCancelamento;

    @Column(nullable = false, columnDefinition = "timestamp")
    private LocalDateTime dataDelivery;

    @ManyToOne
    @JoinColumn(nullable = false)
    private PaymentForm paymentForm;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Restaurant restaurant;

    @ManyToOne
    @JoinColumn(name = "usuario_cliente_id", nullable = false)
    private UserTuyoFood cliente;

    @OneToMany(mappedBy = "solicitation")
    private List<SolicitationItem> itens = new ArrayList<>();
}
