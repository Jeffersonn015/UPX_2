package UPX02;

// Classe que representa uma prefeitura, que é um tipo de entidade.
public class Prefeitura extends Entidade {
    // Construtor que inicializa uma nova instância de Prefeitura.
    // Recebe o ID da prefeitura e o nome como parâmetros.
    public Prefeitura(int idPrefeitura, String nome) {
        // Chama o construtor da superclasse (Entidade) para inicializar os atributos.
        super(idPrefeitura, nome);
    }
}
