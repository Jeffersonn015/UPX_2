package UPX02;

// Classe que representa uma entidade com um identificador e um nome.
public class Entidade {
    // Atributo que armazena o ID da entidade.
    private int id;

    // Atributo que armazena o nome da entidade.
    private String nome;

    // Construtor que inicializa os atributos id e nome da entidade.
    public Entidade(int id, String nome) {
        this.id = id; // Inicializa o atributo id com o valor fornecido.
        this.nome = nome; // Inicializa o atributo nome com o valor fornecido.
    }

    // Método para obter o ID da entidade.
    public int getId() {
        return id; // Retorna o valor do atributo id.
    }

    // Método para definir o ID da entidade.
    public void setId(int id) {
        this.id = id; // Atualiza o atributo id com o novo valor fornecido.
    }

    // Método para obter o nome da entidade.
    public String getNome() {
        return nome; // Retorna o valor do atributo nome.
    }

    // Método para definir o nome da entidade.
    public void setNome(String nome) {
        this.nome = nome; // Atualiza o atributo nome com o novo valor fornecido.
    }
}
