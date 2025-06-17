package br.com.kraken.vendas.java.controller;

import br.com.kraken.vendas.java.configs.securities.ClientSecurity;
import br.com.kraken.vendas.java.configs.securities.TokenServices;
import br.com.kraken.vendas.java.model.AcessoModel;
import br.com.kraken.vendas.java.model.ClienteModel;
import br.com.kraken.vendas.java.modelDTO.AcessoDTO;
import br.com.kraken.vendas.java.modelDTO.GenerationToken;
import br.com.kraken.vendas.java.modelDTO.ClienteDTO;
import br.com.kraken.vendas.java.modelDTO.LoginRequest;
import br.com.kraken.vendas.java.service.AcessoService;
import br.com.kraken.vendas.java.service.interfaces.iClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin (origins = "*")
@RequestMapping ("/cliente")
public class ClienteController {
    private final TokenServices token;
    private final iClienteService service;
    private final ClientSecurity security;
    private final AcessoService acessoService;
    private final AuthenticationManager authenticationManager;

    public ClienteController(AcessoService acessoService, iClienteService iclienteService, AuthenticationManager authenticationManager, ClientSecurity security, TokenServices token) {
        this.service = iclienteService;
        this.authenticationManager = authenticationManager;
        this.security = security;
        this.token = token;
        this.acessoService = acessoService;
    }

    @PostMapping("/auth/registry")
    public ResponseEntity<Object> cadastroCliente (@RequestBody @Valid AcessoDTO acessoDTO) {
        var acessoModel = new AcessoModel();
        BeanUtils.copyProperties(acessoDTO, acessoModel);
        acessoModel.setDataCadastro(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));
        return ResponseEntity.status(HttpStatus.CREATED).body(acessoService.getRepository().save(acessoModel));
    }

    @PostMapping("/auth/login")
    public ResponseEntity<GenerationToken> login (@RequestBody @Valid LoginRequest login){
        var user = new UsernamePasswordAuthenticationToken(login.usuario(), login.senha());
        var auth = this.authenticationManager.authenticate(user);
        var token = this.token.GenerationToken((ClienteModel) auth.getPrincipal());
        return ResponseEntity.status(HttpStatus.OK).body(new GenerationToken(token));
    }

    @GetMapping
    public ResponseEntity<Object> listarCliente () {
        return ResponseEntity.status(HttpStatus.OK).body(service.getClienteRepository().findAll());
    }
    @GetMapping ("/{id}")
    public ResponseEntity<Object> encontrarCliente (@PathVariable (value = "id") UUID id){
        Optional<ClienteModel> optionalClienteModel = service.getClienteRepository().findById(id);
        return optionalClienteModel.<ResponseEntity<Object>>map(
                        clienteModel -> ResponseEntity.status(HttpStatus.OK).body(optionalClienteModel.get()))
                .orElseGet( () -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Found Venda")
        );
    }

    @PostMapping
    public ResponseEntity<Object> efetuarCliente (@RequestBody @Valid ClienteDTO clienteDTO) {
        ClienteModel clienteModel = new ClienteModel();
        BeanUtils.copyProperties(clienteDTO, clienteModel);
        clienteModel.setSenha(security.PasswordEncode().encode(clienteDTO.senha()));
        clienteModel.setDataCadastro(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));
        return ResponseEntity.status(HttpStatus.CREATED).body(service.getClienteRepository().save(clienteModel));
    }

    @PutMapping ("/{id}")
    public ResponseEntity<Object> alterarCliente (@PathVariable (value = "id") UUID id, @RequestBody @Valid ClienteDTO clienteDTO) {
        Optional<ClienteModel> optionalClienteModel = service.getClienteRepository().findById(id);
        if(!optionalClienteModel.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Found Venda");
        optionalClienteModel.get().setNome(clienteDTO.nome());
        optionalClienteModel.get().setSenha(security.PasswordEncode().encode(clienteDTO.senha()));
        optionalClienteModel.get().setTell(clienteDTO.tell());
        optionalClienteModel.get().setEmail(clienteDTO.email());
        optionalClienteModel.get().setCep(clienteDTO.cep());
        optionalClienteModel.get().setRules(clienteDTO.rules());
        return ResponseEntity.status(HttpStatus.CREATED).body(service.getClienteRepository().save(optionalClienteModel.get()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletarCliente (@PathVariable (value = "id") UUID id) {
        Optional<ClienteModel> optionalClienteModel = service.getClienteRepository().findById(id);
        if(!optionalClienteModel.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Found Venda");
        service.getClienteRepository().delete(optionalClienteModel.get());
        return ResponseEntity.status(HttpStatus.OK).body("Client delete Success!!");
    }

}