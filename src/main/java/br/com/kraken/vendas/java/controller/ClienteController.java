package br.com.kraken.vendas.java.controller;

import br.com.kraken.vendas.java.model.ClienteModel;
import br.com.kraken.vendas.java.modelDTO.ClienteDTO;
import br.com.kraken.vendas.java.service.ClienteService;
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
@RequestMapping ("/cliente")
public class ClienteController {
    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public ResponseEntity<Object> listarCliente () {
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.getClienteRepository().findAll());
    }
    @GetMapping ("/{id}")
    public ResponseEntity<Object> encontrarCliente (@PathVariable (value = "id") UUID id){
        Optional<ClienteModel> optionalClienteModel = clienteService.getClienteRepository().findById(id);
        return optionalClienteModel.<ResponseEntity<Object>>map(
                        clienteModel -> ResponseEntity.status(HttpStatus.OK).body(optionalClienteModel.get()))
                .orElseGet( () -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Found Venda")
        );
    }

    @PostMapping
    public ResponseEntity<Object> efetuarCliente (@RequestBody @Valid ClienteDTO clienteDTO) {
        ClienteModel clienteModel = new ClienteModel();
        BeanUtils.copyProperties(clienteDTO, clienteModel);
        clienteModel.setDataCadastro(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.getClienteRepository().save(clienteModel));
    }

    @PutMapping ("/{id}")
    public ResponseEntity<Object> alterarCliente (@PathVariable (value = "id") UUID id, @RequestBody @Valid ClienteDTO clienteDTO) {
        Optional<ClienteModel> optionalClienteModel = clienteService.getClienteRepository().findById(id);
        if(!optionalClienteModel.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Found Venda");
        optionalClienteModel.get().setNome(clienteDTO.nome());
        optionalClienteModel.get().setSenha(clienteDTO.senha());
        optionalClienteModel.get().setTell(clienteDTO.tell());
        optionalClienteModel.get().setEmail(clienteDTO.email());
        optionalClienteModel.get().setCep(clienteDTO.cep());
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.getClienteRepository().save(optionalClienteModel.get()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletarCliente (@PathVariable (value = "id") UUID id) {
        Optional<ClienteModel> optionalClienteModel = clienteService.getClienteRepository().findById(id);
        if(!optionalClienteModel.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Found Venda");
        clienteService.getClienteRepository().delete(optionalClienteModel.get());
        return ResponseEntity.status(HttpStatus.OK).body("Client delete Success!!");
    }

}
