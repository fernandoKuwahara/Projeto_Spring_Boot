package br.com.projeto.api_meio_ambiente.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.projeto.api_meio_ambiente.models.Messenger;
import br.com.projeto.api_meio_ambiente.models.Person;
import br.com.projeto.api_meio_ambiente.repositorys.Repositorys;

@Service
public class Services {
    
    @Autowired
    private Messenger messenger;

    @Autowired
    private Repositorys exec;

    //Método para cadastrar pessoas
    public ResponseEntity<?> cadastrar(Person p) {

        if (p.getName().equals("")) {
            messenger.setMessenger("Informe um nome válido");
            return new ResponseEntity<>(messenger, HttpStatus.BAD_REQUEST);
        } else if (p.getYearOld() < 0) {
            messenger.setMessenger("Informe uma idade válida");
            return new ResponseEntity<>(messenger, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(exec.save(p), HttpStatus.CREATED);
        }

    }

    //Método para selecioanr pessoas
    public ResponseEntity<?> selecionar() {
        return new ResponseEntity<>(exec.findAll(), HttpStatus.OK);
    }

    //Método para selecionar pessoas por id
    public ResponseEntity<?> selecionarPeloCodigo(int id) {
        
        if (exec.countById(id) == 0) {
            messenger.setMessenger("Pessoa não encontrada!");
            return new ResponseEntity<>(messenger, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(exec.findById(id), HttpStatus.OK);
        }

    }

    //Método para editar dados
    public ResponseEntity<?> editar(Person p) {

        if (exec.countById(p.getId()) == 0) {

            messenger.setMessenger("Pessoa não existe para ser editada");
            return new ResponseEntity<>(messenger, HttpStatus.NOT_FOUND);

        } else if (p.getName().equals("")) {

            messenger.setMessenger("Informe um nome válido!");
            return new ResponseEntity<>(messenger, HttpStatus.BAD_REQUEST);

        } else if (p.getYearOld() < 0) {

            messenger.setMessenger("Informe uma idade válida!");
            return new ResponseEntity<>(messenger, HttpStatus.BAD_REQUEST);

        } else {
        
            return new ResponseEntity<>(exec.save(p), HttpStatus.OK);

        }

    }

    //Método para deletar pessoa
    public ResponseEntity<?> deletarPessoa(int id) {

        if (exec.countById(id) == 0) {

            messenger.setMessenger("Pessoa não encontrada!");
            return new ResponseEntity<>(messenger, HttpStatus.NOT_FOUND);

        } else {

            Person p = exec.findById(id);

            exec.delete(p);

            messenger.setMessenger("Pessoa excluída com sucesso!");
            return new ResponseEntity<>(messenger, HttpStatus.OK);

        }

    }

}
