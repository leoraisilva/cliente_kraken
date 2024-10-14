package br.com.kraken.vendas.java.controller;

import br.com.kraken.vendas.java.model.VendasModel;
import br.com.kraken.vendas.java.modelDTO.VendasDTO;
import br.com.kraken.vendas.java.service.VendasService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin (origins = "*")
@RequestMapping ("/vendas")
public class VendasController {
    private final VendasService vendasService;

    public VendasController (VendasService vendasService) {
        this.vendasService = vendasService;
    }

    @GetMapping
    public ResponseEntity<Object> listarVendas () {
        return ResponseEntity.status(HttpStatus.OK).body(vendasService.getVendasRepository().findAll());
    }
    @GetMapping ("/{id}")
    public ResponseEntity<Object> encontrarVenda (@PathVariable (value = "id") UUID id){
        Optional<VendasModel> optionalVendasModel = vendasService.getVendasRepository().findById(id);
        return optionalVendasModel.<ResponseEntity<Object>>map(
                vendasModel -> ResponseEntity.status(HttpStatus.OK).body(optionalVendasModel.get()))
                .orElseGet( () -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Found Venda")
        );
    }

    @PostMapping
    public ResponseEntity<Object> efetuarVenda (@RequestBody @Valid VendasDTO vendasDTO) {
        VendasModel vendasModel = new VendasModel();
        BeanUtils.copyProperties(vendasDTO, vendasModel);
        vendasModel.setDataVenda(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));
        return ResponseEntity.status(HttpStatus.CREATED).body(vendasService.getVendasRepository().save(vendasModel));
    }

    @PutMapping ("/{id}")
    public ResponseEntity<Object> alterarVenda (@PathVariable (value = "id") UUID id, @RequestBody @Valid VendasDTO vendasDTO) {
        Optional<VendasModel> optionalVendasModel = vendasService.getVendasRepository().findById(id);
        if(!optionalVendasModel.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Found Venda");
        optionalVendasModel.get().setIdUsuario(vendasDTO.idUsuario());
        optionalVendasModel.get().setProdutos(vendasDTO.produtos());
        optionalVendasModel.get().setPosicao_id(vendasDTO.posicao_id());
        optionalVendasModel.get().setQuantidade(vendasDTO.quantidade());
        optionalVendasModel.get().setValorTotal(vendasDTO.valorTotal());
        optionalVendasModel.get().setDataVenda(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));
        optionalVendasModel.get().setStatusVendas(vendasDTO.statusVendas());
        return ResponseEntity.status(HttpStatus.CREATED).body(optionalVendasModel.get());
    }

}
