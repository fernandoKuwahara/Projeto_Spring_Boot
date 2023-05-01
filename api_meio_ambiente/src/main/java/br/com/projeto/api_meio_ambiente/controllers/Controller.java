package br.com.projeto.api_meio_ambiente.controllers;

// import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.projeto.api_meio_ambiente.models.Client;
import br.com.projeto.api_meio_ambiente.models.Person;
import br.com.projeto.api_meio_ambiente.repositorys.Repositorys;
import br.com.projeto.api_meio_ambiente.services.Services;
import jakarta.validation.Valid;

@RestController
public class Controller {

    @Autowired
    private Repositorys exec;

    @Autowired
    private Services service;

    //REQUISIÇÕES HTTP PELO MÉTODO GET
    @GetMapping("/api/pessoas")
    public ResponseEntity<?> encontrarPessoas() {
        return service.selecionar();
    }

    @GetMapping("/api/encontrarPessoa/{cd}")
    public ResponseEntity<?> encontrarPessoa(@PathVariable int cd) {
        return service.selecionarPeloCodigo(cd);
    }

    @GetMapping("/api/contador")
    public long contador() {
        return exec.count();
    }

    @GetMapping("/api/ordenarNomes")
    public List<Person> ordenarNome() {
        return exec.findByOrderByName();
    }

    @GetMapping("/api/ordenarIdades")
    public List<Person> ordenarIdade() {
        return exec.findByOrderByYearOldDesc();
    }

    // @GetMapping("api/apresentacoes")
    // public ArrayList<String> nos() {
    //     ArrayList<String> eu = new ArrayList<>();
    //     List<Person> p = encontrarPessoas();

    //     for (Person a : p) {
    //         eu.add("Meu nome é " + a.getName() + " e eu tenho " + a.getYearOld());
    //     }

    //     return eu;
    // }

    @GetMapping("/api/filtrarPor/{term}")
    public List<Person> nomeContem(@PathVariable String term) {
        return exec.findByNameContaining(term);
    }

    @GetMapping("/api/iniciaCom/{termo}")
    public List<Person> iniciaCom(@PathVariable String term) {
        return exec.findByNameStartsWith(term);
    }

    @GetMapping("/api/finalizaCom/{term}")
    public List<Person> finalizaCom(@PathVariable String term) {
        return exec.findByNameEndsWith(term);
    }

    @GetMapping("/api/somarIdades")
    public int somarIdades() {
        return exec.sumYearsOld();
    }


    @GetMapping("/api/idadeMaiorQue/{yearOld}")
    public List<Person> idadeMaiorIgual(@PathVariable int yearOld) {
        return exec.yearOldBggerThan(yearOld);
    }

    //REQUISIÇÕES HTTP PELO MÉTODO POST
    @PostMapping("/api/cadastrar")
    public ResponseEntity<?> cadastrarPessoa(@RequestBody Person p) {
        return service.cadastrar(p);
    }

    //REQUISIÇÕES HTTP PELO MÉTODO PUT
    @PutMapping("/api/editar")
    public ResponseEntity<?> editarPessoa(@RequestBody Person p) {
        return service.editar(p);
    }

    //REQUISIÇÕES HTTP PELO MÉTODO DELETE
    @DeleteMapping("/api/deletarPessoa/{cd}")
    public ResponseEntity<?> excluirPessoa(@PathVariable int cd) {
        return service.deletarPessoa(cd);
    }



    @PostMapping("/client")
    public void client(@Valid @RequestBody Client c) {
        
    }



    //WHATEVER
    @GetMapping("/")
    public String messenge() {
        return "Hello World";
    }

    @GetMapping("/welcome")
    public String welcome(){
        return "Welcome to Raccon City";
    }

    @GetMapping("/welcome/{nome}")
    public String welcome(@PathVariable String nome) {
        return "Welcome to Raccon City " + nome;
    }

    @PostMapping("/person")
    public Person pessoa(@RequestBody Person p) {
        return p;
    }

    @GetMapping("/status")
    public ResponseEntity<?> status() {
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}
