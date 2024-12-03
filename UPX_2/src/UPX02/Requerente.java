package UPX02;

// Classe que representa um requerente, que é um tipo de entidade.
public class Requerente extends Entidade {

    /**
     * Construtor da classe Requerente.
     *
     * @param idRequerente Identificador único do requerente.
     * @param nome Nome do requerente.
     */
    public Requerente(int idRequerente, String nome) {
        // Chama o construtor da classe pai (Entidade) para inicializar os atributos herdados.
        super(idRequerente, nome);
    }
}
