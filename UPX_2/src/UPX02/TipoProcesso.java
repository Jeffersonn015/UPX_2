package UPX02;

// Classe TipoProcesso que herda da classe Entidade
public class TipoProcesso extends Entidade {
    
    // Recebe um ID do tipo de processo e uma descrição
    public TipoProcesso(int idTipoProcesso, String descricao) {
        // Chama o construtor da superclasse Entidade
        // Passando o ID e a descrição para inicializar os atributos herdados
        super(idTipoProcesso, descricao);
    }
}
