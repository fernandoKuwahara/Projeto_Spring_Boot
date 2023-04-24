package br.com.projeto.api_meio_ambiente.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.projeto.api_meio_ambiente.models.Person;
import br.com.projeto.api_meio_ambiente.repositorys.Repositorys;

@RestController
public class Controller {

    @Autowired
    private Repositorys exec;

    //REQUISIÇÕES HTTP PELO MÉTODO GET
    @GetMapping("/api/pessoas")
    public List<Person> encontrarPessoas() {
        return exec.findAll();
    }

    @GetMapping("/api/encontrarPessoa/{cd}")
    public Person encontrarPessoa(@PathVariable int cd) {
        return exec.findById(cd);
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

    @GetMapping("api/apresentacoes")
    public ArrayList<String> nos() {
        ArrayList<String> eu = new ArrayList<>();
        List<Person> p = encontrarPessoas();

        for (Person a : p) {
            eu.add("Meu nome é " + a.getName() + " e eu tenho " + a.getYearOld());
        }

        return eu;
    }

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

    //REQUISIÇÕES HTTP PELO MÉTODO POST
    @PostMapping("/api/cadastrar")
    public Person cadastrarPessoa(@RequestBody Person p) {
        return exec.save(p);
    }

    //REQUISIÇÕES HTTP PELO MÉTODO PUT
    @PutMapping("/api/editar")
    public Person editarPessoa(@RequestBody Person p) {
        return exec.save(p);
    }

    //REQUISIÇÕES HTTP PELO MÉTODO DELETE
    @DeleteMapping("/api/deletarPessoa/{cd}")
    public void excluirPessoa(@PathVariable int cd) {
        Person p = encontrarPessoa(cd);

        exec.delete(p);
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

}
